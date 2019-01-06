package com.example.mohamdkazem.advancetodolist;

import android.util.Log;

import java.io.FileWriter;
import java.io.IOException;

public class MyLogger {
    static boolean isDebugMode = false;
    static boolean isSaveToFile = false;
    public static void d(String tag,String txt) throws IOException {
        if (isDebugMode) {

            if (isSaveToFile){
                saveTofile(txt);
            }
            Log.d(tag,txt);
        }
    }


    private static void saveTofile(String text) throws IOException {
        FileWriter writer = new FileWriter("rg");
        writer.append(text);

        writer.close();
    }


}
