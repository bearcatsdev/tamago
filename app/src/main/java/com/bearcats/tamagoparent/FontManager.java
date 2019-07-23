package com.bearcats.tamagoparent;

import android.content.Context;
import android.graphics.Typeface;

public final class FontManager {
    private static final String fontPathRegular = "fonts/Quicksand-Regular.ttf";
    private static final String fontPathBold = "fonts/Quicksand-Bold.ttf";
    private static final String fontPathMedium = "fonts/Quicksand-Medium.ttf";
    private static final String fontPathLight = "fonts/Quicksand-Light.ttf";

    public static Typeface getFontRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fontPathRegular);
    }

    public static Typeface getFontBold(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fontPathBold);
    }

    public static Typeface getFontMedium(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fontPathMedium);
    }

    public static Typeface getFontLight(Context context) {
        return Typeface.createFromAsset(context.getAssets(), fontPathLight);
    }
}
