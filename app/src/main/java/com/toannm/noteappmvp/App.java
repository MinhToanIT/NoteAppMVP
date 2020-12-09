package com.toannm.noteappmvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by Minh Toan on 14/03/2018.
 */

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }

    public static Context getContext() {
        return context;
    }
}
