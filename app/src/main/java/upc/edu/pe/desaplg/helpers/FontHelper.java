package upc.edu.pe.desaplg.helpers;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontHelper {

	public static String DOSIS_BOLD = "Dosis-Bold.ttf";
	public static String DOSIS_EXTRABOLD = "Dosis-ExtraBold.ttf";

	public static void setFont(Context context, String font, TextView textView) {
		
		Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/" + font);
		textView.setTypeface(typeface);
	}
}
