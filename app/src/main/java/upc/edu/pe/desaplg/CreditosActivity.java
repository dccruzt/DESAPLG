package upc.edu.pe.desaplg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Daniela on 23/10/2015.
 */
public class CreditosActivity extends Activity {

    int tiempo = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_creditos);

        empezarTurno();
    }

    public void empezarTurno(){

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

                        tiempo--;
                        circ2.clearAnimation();
                        tiempoTurno.setText(String.valueOf(tiempo));
                        if(tiempo > 0) {
                            circ4.setVisibility(View.VISIBLE);
                            empezarTurno();
                        }
                        else
                        {
                            circ2.setVisibility(View.INVISIBLE);
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
}
