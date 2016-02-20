package upc.edu.pe.desaplg;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.wifi.WifiConfiguration;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.connectsdk.service.capability.listeners.ResponseListener;
import com.connectsdk.service.command.ServiceCommandError;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

import upc.edu.pe.desaplg.connection.ConnectionHelper;
import upc.edu.pe.desaplg.connection.JsonHelper;
import upc.edu.pe.desaplg.helpers.StatusHelper;
import upc.edu.pe.desaplg.view.DesaplgEditText;
import upc.edu.pe.desaplg.view.DesaplgTextView;

/**
 * Created by Daniela on 23/10/2015.
 */
public class JuegoActivity extends Activity implements View.OnLongClickListener {

    private ViewGroup marco;
    private ImageView imgRuleta;
    private int ancho;
    private int alto;
    private int xDelta;
    private int yDelta;
    //private ImageView ficha1;
    //private ImageView ficha2;
    //private ImageView ficha3;
    //private ImageView ficha4;
    //private ImageView ficha5;
    //private ImageView ficha6;
    //private ImageView ficha7;
    //private ImageView ficha8;

    boolean movimientoActivo = false;
    VelocityTracker vTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_juego);

        ConnectionHelper.desaplgListener.setJuegoActivity(this);
        repartirFichas(8);

        marco = (ViewGroup) findViewById(R.id.marco);

        /*ficha1 = (ImageView) findViewById(R.id.ficha1);
        ficha2 = (ImageView) findViewById(R.id.ficha2);
        ficha3 = (ImageView) findViewById(R.id.ficha3);
        ficha4 = (ImageView) findViewById(R.id.ficha4);
        ficha5 = (ImageView) findViewById(R.id.ficha5);
        ficha6 = (ImageView) findViewById(R.id.ficha6);
        ficha7 = (ImageView) findViewById(R.id.ficha7);
        ficha8 = (ImageView) findViewById(R.id.ficha8);

        ficha1.setOnLongClickListener(this);
        ficha2.setOnLongClickListener(this);
        ficha3.setOnLongClickListener(this);
        ficha4.setOnLongClickListener(this);
        ficha5.setOnLongClickListener(this);
        ficha6.setOnLongClickListener(this);
        ficha7.setOnLongClickListener(this);
        ficha8.setOnLongClickListener(this);*/

        DesaplgTextView nombreJugador = (DesaplgTextView) findViewById(R.id.nombreJugador);
        nombreJugador.setText(StatusHelper.nombreJugador);

        imgRuleta = (ImageView) findViewById(R.id.imgRuleta);
        ancho =  getResources().getDimensionPixelSize(R.dimen.ancho_ruleta)/2;
        alto =  getResources().getDimensionPixelSize(R.dimen.alto_ruleta)/2;
    }

    public void repartirFichas(int numFichas) {

        try{

            int idFicha, idLetra, idPuntos;
            String letra, puntos;

            for(int i = 1; i <= numFichas; i++){

                idFicha = getResources().getIdentifier("ficha" + i, "id", getPackageName());
                idLetra = getResources().getIdentifier("letra_ficha" + i, "id", getPackageName());
                idPuntos = getResources().getIdentifier("puntaje_ficha" + i, "id", getPackageName());

                Log.e("hola", String.valueOf(idFicha));

                ImageView ivFicha = (ImageView) findViewById(idFicha);
                TextView tvLetra = (TextView) findViewById(idLetra);
                TextView tvPuntos = (TextView) findViewById(idPuntos);

                letra = StatusHelper.fichas.getString(i - 1);
                puntos = StatusHelper.puntajes_fichas().getString(letra);

                ivFicha.setVisibility(View.VISIBLE);
                ivFicha.setOnLongClickListener(this);

                ivFicha.setTag(letra);
                tvLetra.setText(letra);
                tvPuntos.setText(puntos);
            }
        }catch(JSONException e) {

        }
    }

    public void girarRuleta(View v){

        try{

            Random random = new Random();
            int nVueltasRandom = random.nextInt(10)+2;
            int ndeplazamiento = (random.nextInt(10)+1);/*StatusHelper.categoria().getInt(categoria);*/
            int nfin = 360*nVueltasRandom;
            int ninicial = 2;

            RotateAnimation animation = new RotateAnimation(0, nfin + ndeplazamiento*18, ancho,alto);
            animation.setFillAfter(true);
            animation.setFillEnabled(true);
            animation.setDuration(1000 * nVueltasRandom);
            imgRuleta.startAnimation(animation);

            Log.e("categoria",String.valueOf(StatusHelper.categoria().getString(String.valueOf(ndeplazamiento))));

        }catch (JSONException e){
        }
    }

    @Override
    public boolean onLongClick(final View v) {

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
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (movimientoActivo) {

            switch(event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    //Esto es para inicializar un objeto (vTracker) que va a medir la velocidad de tu dedo desplazándose
                    if(vTracker == null)
                        vTracker = VelocityTracker.obtain();
                    else
                        vTracker.clear();
                    vTracker.addMovement(event);
                    break;
                case MotionEvent.ACTION_MOVE:

                    //Esto es lo que hace que el GLOW se mueva
                    ImageView imgGlow = (ImageView)findViewById(R.id.imgGlow);
                    imgGlow.setX(event.getX() - imgGlow.getWidth() / 2);
                    imgGlow.setY(event.getY() - imgGlow.getHeight() / 2);

                    //Esto hace que el vTracker saque la velocidad de tu dedo en cada movimiento
                    vTracker.addMovement(event);
                    vTracker.computeCurrentVelocity(1000); //Este 1000 es por así decirlo la "precisión" de la medida

                    //Aquí mandas a la TV la velocidad de tu dedo que básicamente es el desplazamiento en X y en Y que debe tener la ficha en la TV
                    ConnectionHelper.webAppSession.sendMessage(JsonHelper.moverFicha(Math.round(vTracker.getXVelocity()/50) , Math.round(vTracker.getYVelocity()/50)), new ResponseListener<Object>() {

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
                    /*ConnectionHelper.webAppSession.sendMessage(JsonHelper.enviarFicha(), new ResponseListener<Object>() {

                        @Override
                        public void onError(ServiceCommandError error) {
                        }

                        @Override
                        public void onSuccess(Object object) {
                        }
                    });*/
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
}