package upc.edu.pe.desaplg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Daniela on 28/01/2016.
 */
public class DesaplgButton extends Button {

    public DesaplgButton(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Dosis-Bold.ttf"));
    }
}
