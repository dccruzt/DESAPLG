package upc.edu.pe.desaplg;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Random;
import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;
import upc.edu.pe.desaplg.view.DesaplgTextView;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JuegoActivity extends Activity implements View.OnLongClickListener {

    private ViewGroup layoutFichas;
    private ViewGroup layoutRuleta;
    private ViewGroup marco;
    private ViewGroup marcoTurno;
    private ViewGroup marcoAnimacion;
    private TextView txtTurno;
    private Button pin_ruleta;
    private Button imgRuleta;

    Animation anim_pin_ruleta;
    private boolean pin;
    private int ancho;
    private int alto;
    private int xDelta;
    private int yDelta;
    private int tiempo = 3;
    ImageView imgGlow;
    boolean movimientoActivo = false;
    VelocityTracker vTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_juego);

        ConnectionHelper.desaplgListener.setJuegoActivity(this);
        repartirFichas(StatusHelper.fichas.length());

        marco = (ViewGroup) findViewById(R.id.marco);
        marcoTurno = (ViewGroup) findViewById(R.id.marcoTurno);
        marcoAnimacion = (ViewGroup) findViewById(R.id.marcoAnimacion);
        layoutFichas = (ViewGroup) findViewById(R.id.layoutFichas);
        layoutRuleta = (ViewGroup) findViewById(R.id.layoutRuleta);
        txtTurno = (TextView) findViewById(R.id.txtTurno);

        DesaplgTextView nombreJugador = (DesaplgTextView) findViewById(R.id.nombreJugador);
        nombreJugador.setText(StatusHelper.nombreJugador);

        imgGlow = (ImageView)findViewById(R.id.imgGlow);
        imgRuleta = (Button) findViewById(R.id.imgRuleta);
        ancho =  getResources().getDimensionPixelSize(R.dimen.ancho_ruleta)/2; alto =  getResources().getDimensionPixelSize(R.dimen.alto_ruleta)/2;
        pin = false;
        setearElementos(layoutFichas, false);
        setearElementos(layoutRuleta, false);
    }

    public void screeon(){

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    public void empezarTurno(){

        txtTurno.setText(StatusHelper.nombreJugador + getResources().getString(R.string.turno));
        if(StatusHelper.turno) {

            pin = true;
            setearElementos(layoutRuleta, true);
            animarPinRuleta();
        }
    }

    public void iniciarJuego(){

        marcoAnimacion.setVisibility(View.VISIBLE);
        marcoTurno.setVisibility(View.VISIBLE);

        final TextView tiempoTurno = (TextView) findViewById(R.id.tiempoTurno);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.turno1);
        final Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.turno2);

        final ImageView circ4 = (ImageView) findViewById(R.id.circ4);
        final ImageView circ2 = (ImageView) findViewById(R.id.circ2);

        anim1.setAnimationListener(new Animation.AnimationListener() {

            int duracion = 3;

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                circ4.setVisibility(View.INVISIBLE);
                circ4.clearAnimation();

                anim2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        tiempoTurno.setText(String.valueOf(tiempo));
                        tiempo--;
                        circ2.clearAnimation();
                        if(tiempo > 0) {
                            circ4.setVisibility(View.VISIBLE);
                            iniciarJuego();
                        }
                        else
                        {
                            //circ2.setVisibility(View.INVISIBLE);
                            marcoTurno.setVisibility(View.INVISIBLE);
                            marcoAnimacion.setVisibility(View.INVISIBLE);
                            setearElementos(layoutFichas, true);
                            tiempo = 3;
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {


                    }
                });

                circ2.startAnimation(anim2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        circ4.startAnimation(anim1);
    }

    public void animarPinRuleta(){

        anim_pin_ruleta = AnimationUtils.loadAnimation(this, R.anim.pin_ruleta);
        pin_ruleta = (Button)findViewById(R.id.pin_ruleta);

        anim_pin_ruleta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if(pin)
                    animarPinRuleta();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        pin_ruleta.startAnimation(anim_pin_ruleta);
    }

    private static void setearElementos(ViewGroup layout, boolean estado) {

        layout.setEnabled(estado);
        for (int i = 0; i < layout.getChildCount(); i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                setearElementos((ViewGroup) child, estado);
            } else {
                if(!estado){
                    if(child instanceof Button)
                        ((Button)child).getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
                    if(child instanceof TextView)
                        ((TextView)child).setTextColor(Color.GRAY);
                }else{
                    if(child instanceof Button)
                        ((Button)child).getBackground().setColorFilter(null);
                    if(child instanceof TextView)
                        ((TextView)child).setTextColor(Color.WHITE);
                }

                child.setEnabled(estado);
            }
        }
    }

    public void repartirFichas(int numFichas) {

        try{
            int idFicha;
            String letra, puntos;

            for(int i = 0; i < numFichas; i++){

                //Se obtiene el id del view de la ficha y se obtiene
                idFicha = getResources().getIdentifier("ficha" + (i + 1), "id", getPackageName());
                RelativeLayout rlFicha = (RelativeLayout) findViewById(idFicha);

                //Se asigna la letra y el puntaje en dos variables
                letra = StatusHelper.fichas.getString(i);
                puntos = StatusHelper.puntajes_fichas().getString(letra);

                //Se pinta y setea la letra y su respectivo puntaje en cada ficha
                rlFicha.getChildAt(0).setTag(letra);
                ((TextView)rlFicha.getChildAt(1)).setText(letra);
                ((TextView)rlFicha.getChildAt(2)).setText(puntos);

                //Se hace visible la ficha y se le asigna el método onLongClick
                rlFicha.setVisibility(View.VISIBLE);
                rlFicha.getChildAt(0).setOnLongClickListener(this);

                StatusHelper.fichas_todas.add(rlFicha);
            }
        }catch(JSONException e) {

        }
    }

    public void girarRuleta(View v){

        Random random = new Random();
        int nVueltasRandom = random.nextInt(10)+2;
        final int ndeplazamiento = (random.nextInt(10)+1);
        int nfin = 360*nVueltasRandom;
        int ninicial = 2;

        RotateAnimation animation = new RotateAnimation(0, nfin + ndeplazamiento*18, ancho,alto);
        animation.setFillAfter(true);
        animation.setFillEnabled(true);
        animation.setDuration(500 * nVueltasRandom);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

                pin = false;
                pin_ruleta.setEnabled(false);
                pin_ruleta.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                String categoria = null;
                try {
                    categoria = StatusHelper.categoria().getString(String.valueOf(ndeplazamiento));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ConnectionHelper.webAppSession.sendMessage(JsonHelper.mostrarCategoria(categoria), new ResponseListener<Object>() {

                    @Override
                    public void onError(ServiceCommandError error) {
                    }

                    @Override
                    public void onSuccess(Object object) {

                        setearElementos(layoutRuleta, false);
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        imgRuleta.startAnimation(animation);
    }

    @Override
    public boolean onLongClick(final View v) {

        if(StatusHelper.turno) {

            StatusHelper.fichas_movidas.push((View) v.getParent());
            String letraSeleccionada = String.valueOf(v.getTag());

            //Le dices a la TV que acabas de seleccionar una ficha
            ConnectionHelper.webAppSession.sendMessage(JsonHelper.empezarMovimiento(letraSeleccionada), new ResponseListener<Object>() {

                @Override
                public void onError(ServiceCommandError error) {
                }

                @Override
                public void onSuccess(Object object) {

                    marco.setVisibility(View.VISIBLE);
                    //Activas el movimiento
                    movimientoActivo = true;

                    //Esto hace que el TouchEvent no se bloquee con el LongClick, y pueda entrar onTouchEvent del Activity que vas a implementar abajo
                    MotionEvent event = MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis() + 1000, MotionEvent.ACTION_DOWN, v.getX(), v.getY(), 0);
                    JuegoActivity.this.dispatchTouchEvent(event);
                }
            });
            movimientoActivo = true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (movimientoActivo) {

            switch(event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:

                    imgGlow.setX(event.getX() - imgGlow.getWidth() / 2);
                    imgGlow.setY(event.getY() - imgGlow.getHeight() / 2);
                    //Esto es para inicializar un objeto (vTracker) que va a medir la velocidad de tu dedo desplazándose
                    if(vTracker == null)
                        vTracker = VelocityTracker.obtain();
                    else
                        vTracker.clear();
                    vTracker.addMovement(event);
                    break;
                case MotionEvent.ACTION_MOVE:

                    //Esto es lo que hace que el GLOW se mueva
                    //ImageView imgGlow = (ImageView)findViewById(R.id.imgGlow);
                    imgGlow.setX(event.getX() - imgGlow.getWidth() / 2);
                    imgGlow.setY(event.getY() - imgGlow.getHeight() / 2);

                    //Esto hace que el vTracker saque la velocidad de tu dedo en cada movimiento
                    vTracker.addMovement(event);
                    vTracker.computeCurrentVelocity(1000); //Este 1000 es por así decirlo la "precisión" de la medida

                    //Aquí mandas a la TV la velocidad de tu dedo que básicamente es el desplazamiento en X y en Y que debe tener la ficha en la TV
                    ConnectionHelper.webAppSession.sendMessage(JsonHelper.moverFicha(Math.round(vTracker.getXVelocity()/30) , Math.round(vTracker.getYVelocity()/30)), new ResponseListener<Object>() {

                        @Override
                        public void onError(ServiceCommandError error) {
                        }

                        @Override
                        public void onSuccess(Object object) {
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    //Con esto le dices a la TV que coloque la ficha cuando sueltes tu dedo.
                    marco.setVisibility(View.GONE);
                    ConnectionHelper.webAppSession.sendMessage(JsonHelper.soltarFicha(), new ResponseListener<Object>() {

                        @Override
                        public void onError(ServiceCommandError error) {
                        }

                        @Override
                        public void onSuccess(Object object) {
                        }
                    });
                    movimientoActivo = false;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    //Esto mata al vTracker
                    vTracker.recycle();
                    break;
            }
        }
        return super.onTouchEvent(event);
    }

    public void jugar(View v){

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.jugar(), new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {

                StatusHelper.boton = "J";
            }
        });
    }

    public void validarPalabra(JSONObject palabra) throws JSONException{

        Boolean valido = palabra.getBoolean("valido");

        if(valido)
            setearElementos(layoutFichas, false);
        Toast toast = Toast.makeText(getApplicationContext(), palabra.getString("mensaje"), 2);
        toast.show();
    }

    public void posicionFicha(){

        View v = StatusHelper.fichas_movidas.peek();
        if(StatusHelper.posicion_valida)
            v.setVisibility(View.INVISIBLE);
        else {
            StatusHelper.fichas_movidas.pop();
        }
    }

    public void retrocederFicha(View v){

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.retrocederFicha(), new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {

                if (!StatusHelper.fichas_movidas.empty())
                    StatusHelper.fichas_movidas.pop().setVisibility(View.VISIBLE);
            }
        });
    }

    public void terminarTurno(JSONArray nuevasFichas) throws JSONException{

        setearElementos(layoutFichas, false);
        int cant = StatusHelper.fichas_movidas.size();

        //Si la palabra fue válida, el número de letras a reponer será mayor a cero.
        if(nuevasFichas != null && nuevasFichas.length() > 0) {

            if(StatusHelper.boton.equals("J")){

                for (int i = 0; i < cant; i++) {

                    //Se setean las nuevas fichas
                    RelativeLayout rl = (RelativeLayout) StatusHelper.fichas_movidas.pop();

                    rl.getChildAt(0).setTag(nuevasFichas.getString(i));
                    ((TextView) (rl).getChildAt(1)).setText(nuevasFichas.getString(i));
                    ((TextView) (rl).getChildAt(2)).setText(StatusHelper.puntajes_fichas().getString(nuevasFichas.getString(i)));
                    rl.setVisibility(View.VISIBLE);
                }
            }
            if(StatusHelper.boton.equals("C")){

                for (int i = 0; i < StatusHelper.fichas_todas.size(); i++){

                    RelativeLayout rl = (RelativeLayout)StatusHelper.fichas_todas.get(i);
                    rl.getChildAt(0).setTag(nuevasFichas.getString(i));
                    ((TextView) (rl).getChildAt(1)).setText(nuevasFichas.getString(i));
                    ((TextView) (rl).getChildAt(2)).setText(StatusHelper.puntajes_fichas().getString(nuevasFichas.getString(i)));
                }
            }
        }else{//Si la palabra no fue válida, el número de fichas a reponer es cero. Las fichas movidas vuelven a su lugar

            for(int i = 0; i < cant; i++){

                StatusHelper.fichas_movidas.pop().setVisibility(View.VISIBLE);
            }
        }

        for(int i = 0; i < StatusHelper.fichas_todas.size(); i++)
            Log.e(String.valueOf(i),((RelativeLayout) StatusHelper.fichas_todas.get(i)).getChildAt(0).getTag().toString());
    }

    public void cambiarFichas(View v){

        JSONArray fichas = new JSONArray();
        for(int i = 0; i < StatusHelper.fichas_todas.size(); i++){

            if(StatusHelper.fichas_todas.get(i).getVisibility() == View.VISIBLE)
                fichas.put(((RelativeLayout)StatusHelper.fichas_todas.get(i)).getChildAt(0).getTag().toString());
        }

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.cambiarFichas(StatusHelper.fichas), new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {

                StatusHelper.boton = "C";
                StatusHelper.fichas_movidas.removeAllElements();
                setearElementos(layoutFichas, false);
            }
        });
    }

    public void pasarTurno(View v){

        ConnectionHelper.webAppSession.sendMessage(JsonHelper.pasarTurno(), new ResponseListener<Object>() {

            @Override
            public void onError(ServiceCommandError error) {
            }

            @Override
            public void onSuccess(Object object) {

                setearElementos(layoutFichas, false);
            }
        });
    }

    @Override
    protected void onDestroy() {

        ConnectionHelper.desaplgListener.setJuegoActivity(null);
        System.gc();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {}
}