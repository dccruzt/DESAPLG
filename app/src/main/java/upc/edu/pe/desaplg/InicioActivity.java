package upc.edu.pe.desaplg;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import upc.edu.pe.desaplg.helpers.FontHelper;

/**
 * Created by Daniela on 23/10/2015.
 */
public class InicioActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_inicio);

        TextView lblBienvenida = (TextView)findViewById(R.id.lblBienvenida);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, lblBienvenida);

        EditText txtConectar = (EditText)findViewById(R.id.txtConectar);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, txtConectar);

        Button btnConectar = (Button)findViewById(R.id.btnConectar);
        FontHelper.setFont(this.getApplicationContext(), FontHelper.DOSIS_EXTRABOLD, btnConectar);
    }
}