package com.sonja.dmeno.sonja;

import android.os.Build;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DeviceInfo {

    public DeviceInfo(){

    }

    public String getSysDeviceInfo()
    {
        return "SERIAL: " + Build.SERIAL + "\n" +
                        "MODEL: " + Build.MODEL + "\n" +
                        "ID: " + Build.ID + "\n" +
                        "Manufacture: " + Build.MANUFACTURER + "\n" +
                        "Brand: " + Build.BRAND + "\n" +
                        "Type: " + Build.TYPE + "\n" +
                        "User: " + Build.USER + "\n" +
                        "BASE: " + Build.VERSION_CODES.BASE + "\n" +
                        "INCREMENTAL: " + Build.VERSION.INCREMENTAL + "\n" +
                        "SDK:  " + Build.VERSION.SDK + "\n" +
                        "BOARD: " + Build.BOARD + "\n" +
                        "BRAND: " + Build.BRAND + "\n" +
                        "HOST: " + Build.HOST + "\n" +
                        "FINGERPRINT: "+Build.FINGERPRINT + "\n" +
                        "Version Code: " + Build.VERSION.RELEASE;
    }

    public static boolean RootCheck ()
    {
        boolean retval;
        Process suProcess;
        try
        {
            suProcess = Runtime.getRuntime().exec("su");
            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(suProcess.getOutputStream()));
            BufferedReader osRes = new BufferedReader(new InputStreamReader(suProcess.getInputStream()));
            os.write("id\n");
            os.flush();
            String currUid = osRes.readLine();
            boolean exitSu;
            if (null == currUid)
            {
                retval = false;
                exitSu = false;
            }
            else if (currUid.contains("uid=0"))
            {
                retval = true;
                exitSu = true;
            }
            else
            {
                retval = false;
                exitSu = true;
            }

            if (exitSu)
            {
                os.write("exit\n");
                os.flush();
            }
        }
        catch (Exception e)
        {
            retval = false;
        }
        return retval;
    }

}
