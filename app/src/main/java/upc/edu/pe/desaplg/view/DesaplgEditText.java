package upc.edu.pe.desaplg.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Daniela on 28/01/2016.
 */
public class DesaplgEditText extends EditText {

    public DesaplgEditText(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Dosis-Bold.ttf"));
    }
}
