package com.sonja.dmeno.sonja;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

public class FileManager  {


    public FileManager(){
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean makeExtStorageDir(String dirName) // /nomedir
    {
        boolean result=false;
        String p= Environment.getExternalStorageDirectory().getPath().toString()+dirName ;
        File f= new File(p);
        try
        {
            if(!f.exists())
                if(f.mkdir()) { result=true; }

        }
        catch (Exception e) {
            // Unable to create file, likely because external storage is
            // not currently mounted.
            Log.w("ExternalStorage", "Error writing " + f.toString(), e);
        }

        return result;
    }

    public void writeFile(String filename,String fileContents)
    {
        try {
            File root = new File(Environment.getExternalStorageDirectory(),"Sonja/note");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, filename);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(fileContents);
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
