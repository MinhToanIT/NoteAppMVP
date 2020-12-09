package com.toannm.noteappmvp.util;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.toannm.noteappmvp.App;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class Util {
    public static String getStringById(int id){
        return App.getContext().getResources().getString(id);
    }
    public static String convertColorToHex(Drawable drawable){
        return String.format("#%06x",  (((ColorDrawable)drawable).getColor() & 0x00FFFFFF));
    }

}
