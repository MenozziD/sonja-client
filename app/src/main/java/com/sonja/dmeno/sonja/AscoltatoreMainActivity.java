package com.sonja.dmeno.sonja;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


//import com.physicaloid.lib.usb.driver.uart.ReadLisener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;


public class AscoltatoreMainActivity implements View.OnClickListener{

    private ActivityMain app;
    private LinearLayout.LayoutParams par_close;
    private LinearLayout.LayoutParams par_open;
    private ArrayList<String> ipList;
    private DeviceInfo info = new DeviceInfo();

    private ShellExecuter exe = new ShellExecuter();
    //private String url="http://snjsys.ddns.net:65177/";
    private String url="http://192.168.1.111:65177/";
    private String url_key="&k=551f0f2fc174442cadb95df9f957d808";

    AscoltatoreMainActivity(ActivityMain app){
        this.app=app;
        Display display = app.getWindowManager().getDefaultDisplay();
        par_close = new LinearLayout.LayoutParams(display.getWidth(),0,0);
        par_open = new LinearLayout.LayoutParams(display.getWidth(),0,100);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            /* MAIN */
            case R.id.bRefresh:
                app.checkServer();
                break;
            /* HOME */
            case R.id.bHomeCmd:
                String cmd= app.getLstHome().getSelectedItem().toString();
                app.getlOutHome().setText(sendRequest(url.concat("nrf?s=".concat(cmd)).concat(url_key)));
                break;
            case R.id.bHomeDim:
                if (app.getbHomeDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwHome().setLayoutParams(par_close);
                    app.getVwHome().setVisibility(View.INVISIBLE);
                    app.getbHomeDim().setTag("");
                    app.getbHomeDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getlOutHome().setText("");
                    app.setLstHomeAdapterFromString(sendRequest(url.concat("nrf24cmdlist?").concat(url_key)));
                    app.getVwHome().setVisibility(View.VISIBLE);
                    app.getVwHome().setLayoutParams(par_open);
                    app.getbHomeDim().setTag(app.getTAG_Visible());
                    app.getbHomeDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* PIR */
            case R.id.bPIRDim:
                if (app.getbPIRDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwPIR().setLayoutParams(par_close);
                    app.getVwPIR().setVisibility(View.INVISIBLE);
                    app.getbPIRDim().setTag("");
                    app.getbPIRDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getlOutPIR().setText("");
                    //app.setLstHomeAdapterFromString(sendRequest(url.concat("nrf24cmdlist?").concat(url_key)));
                    app.getVwPIR().setVisibility(View.VISIBLE);
                    app.getVwPIR().setLayoutParams(par_open);
                    app.getbPIRDim().setTag(app.getTAG_Visible());
                    app.getbPIRDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* SERVICE */
            case R.id.bServiceDim:
                if (app.getbServiceDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwService().setLayoutParams(par_close);
                    app.getVwService().setVisibility(View.INVISIBLE);
                    app.getbServiceDim().setTag("");
                    app.getbServiceDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getlServiceOut().setText("");
                    app.setLstServiceAdapterFromString(sendRequest(url.concat("servicelist?").concat(url_key)));
                    app.getVwService().setVisibility(View.VISIBLE);
                    app.getVwService().setLayoutParams(par_open);
                    app.getbServiceDim().setTag(app.getTAG_Visible());
                    app.getbServiceDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bServiceCmd:
                String cmdservice= app.getLstServiceName().getSelectedItem().toString().concat("&c=");
                cmdservice=cmdservice.concat(app.getLstServiceCmd().getSelectedItem().toString());
                //String text= sendRequest(url.concat("servicecmd?s=").concat(cmdservice).concat(url_key)).replace("\n", "&lt;br&gt;");
                String text= sendRequest(url.concat("servicecmd?s=").concat(cmdservice).concat(url_key)).replace("\n", "X");
                app.getlServiceOut().setText(Html.fromHtml(Html.fromHtml(text).toString()));
                break;

            /* WEED */
            case R.id.bWeedCmd:
                String str=app.gettWeedInput().getText().toString();
                String response=sendRequest("https://webehigh.org/".concat(str));//<div id="cont-box"><!-- #masthead -->
                if (response.contains("<div id=\"cont-box\">")  && response.contains("Posted in")) {
                    // it contains world
                    response=response.substring(response.indexOf("<div id=\"cont-box\">"),response.indexOf("Posted in"));
                    app.getlWeedOut().setText(Html.fromHtml(response));
                }

                break;

            case R.id.bWeedDim:
                if (app.getbWeedDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwWeed().setLayoutParams(par_close);
                    app.getVwWeed().setVisibility(View.INVISIBLE);
                    app.getbWeedDim().setTag("");
                    app.getbWeedDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwWeed().setVisibility(View.VISIBLE);
                    app.getVwWeed().setLayoutParams(par_open);
                    app.getbWeedDim().setTag(app.getTAG_Visible());
                    app.getbWeedDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* QR READER */
            case R.id.bQRCopy:
                ClipboardManager clipboard = (ClipboardManager) app.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("copy port", app.getlQROut().getText().toString());
                if (clipboard != null) {
                    clipboard.setPrimaryClip(clip);
                }
                break;

            case R.id.bQROpen:
                if (!app.getlQROut().getText().toString().equals("")) {
                    openWebPage(app.getlQROut().getText().toString());
                }
                break;

            case R.id.bQRDim:
                if (app.getbQRDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwQR().setLayoutParams(par_close);
                    app.getVwQR().setVisibility(View.INVISIBLE);
                    app.getbQRDim().setTag("");
                    app.getbQRDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwQR().setVisibility(View.VISIBLE);
                    app.getVwQR().setLayoutParams(par_open);
                    app.getbQRDim().setTag(app.getTAG_Visible());
                    app.getbQRDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* ARDUINO
            case R.id.bComDim:
                if (app.getbComDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwArduino().setLayoutParams(par_close);
                    app.getVwArduino().setVisibility(View.INVISIBLE);
                    app.getbComDim().setTag("");
                    app.getbComDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwArduino().setVisibility(View.VISIBLE);
                    app.getVwArduino().setLayoutParams(par_open);
                    app.getbComDim().setTag(app.getTAG_Visible());
                    app.getbComDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bComOpen:
                app.getmPhysicaloid().setBaudrate(9600);
                try {
                    if (app.getmPhysicaloid().open()) {
                        app.getlComOut().setText(app.getResources().getString(R.string.COM_OK));

                        // read listener, When new data is received from Arduino add it to Text view
                        try {
                            app.getmPhysicaloid().addReadListener(new ReadLisener() {
                                @Override
                                public void onRead(int size) {
                                    byte[] buf = new byte[size];
                                    app.getmPhysicaloid().read(buf, size);
                                    app.tvAppend(app.getlComOut(), Html.fromHtml("<font color=blue>" + new String(buf) + "</font>"));        // add data to text view
                                }
                            });

                        }
                        catch (Exception e) {
                            app.getlComOut().setText(app.getResources().getString(R.string.COM_ERR));
                        }
                    }
                    else {
                        app.getlComOut().setText(app.getResources().getString(R.string.COM_ERR));
                    }
                }
                catch (Exception e)
                {
                    app.getlComOut().setText(app.getResources().getString(R.string.COM_ERR));
                }
                break;

            case R.id.bComClose:
                if (!app.getmPhysicaloid().close()) {
                    app.getmPhysicaloid().clearReadListener();
                }
                break;*/

            /* KEY */
            case R.id.bKeyDim:
                if (app.getbKeyDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwKey().setLayoutParams(par_close);
                    app.getVwKey().setVisibility(View.INVISIBLE);
                    app.getbKeyDim().setTag("");
                    app.getbKeyDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.setLstKeyAdapterFromString(sendRequest("http://192.168.1.111:8080/keylist"));
                    app.getVwKey().setVisibility(View.VISIBLE);
                    app.getVwKey().setLayoutParams(par_open);
                    app.getbKeyDim().setTag(app.getTAG_Visible());
                    app.getbKeyDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bKeyCmd:
                String cmdKey = app.getLstKey().getSelectedItem().toString();
                app.getlKeyOut().setText(sendRequest("http://192.168.1.111:8080/keyvalue?key=".concat(cmdKey)));
                break;

            case R.id.bKeyAdd:
                String nameKey= app.gettKeyName().getText().toString();
                String valueKey= app.gettKeyValue().getText().toString();
                if (!nameKey.equals("") && !valueKey.equals(""))
                {
                    String url="http://192.168.1.111:8080/keyadd?n=_n&v=_v";
                    url=url.replace("_n",nameKey);
                    url=url.replace("_v",valueKey);
                    app.getlKeyDebug().setText(sendRequest(url));
                }
                else
                {
                    app.getlKeyDebug().setText(R.string.t_Mex_001);
                }
                break;

            /* BULB */
            case R.id.bBulbDim:
                if (app.getbBulbDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwBulb().setLayoutParams(par_close);
                    app.getVwBulb().setVisibility(View.INVISIBLE);
                    app.getbBulbDim().setTag("");
                    app.getbBulbDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwBulb().setVisibility(View.VISIBLE);
                    app.getVwBulb().setLayoutParams(par_open);
                    app.getbBulbDim().setTag(app.getTAG_Visible());
                    app.getbBulbDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bBulbCmd:
                sendRequest("http://192.168.1.111:8080/bulbtoggle");
                break;



            /* SHELL */
            case R.id.bShellDim:
                if (app.getbShellDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwShell().setLayoutParams(par_close);
                    app.getVwShell().setVisibility(View.INVISIBLE);
                    app.getbShellDim().setTag("");
                    app.getbShellDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwShell().setVisibility(View.VISIBLE);
                    app.getVwShell().setLayoutParams(par_open);
                    app.getbShellDim().setTag(app.getTAG_Visible());
                    app.getbShellDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bShellCmd:

                String outp = exe.Executer(app.gettShellIn().getText().toString());
                app.getlShellOut().setText(outp);
                Log.d("Output", outp);
                break;

            /* NOTE */
            case R.id.bNoteDim:
                if (app.getbNoteDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwNote().setLayoutParams(par_close);
                    app.getVwNote().setVisibility(View.INVISIBLE);
                    app.getbNoteDim().setTag("");
                    app.getbNoteDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    app.getVwNote().setVisibility(View.VISIBLE);
                    app.getVwNote().setLayoutParams(par_open);
                    app.getbNoteDim().setTag(app.getTAG_Visible());
                    app.getbNoteDim().setBackgroundResource(R.drawable.down);
                }
                break;

            case R.id.bNoteSave:
                FileManager file =new FileManager();
                file.writeFile(app.gettNoteName().getText().toString(),app.gettNoteIn().getText().toString() );
                Toast.makeText(app, "Saved", Toast.LENGTH_SHORT).show();

            /* INFO */
            case R.id.bInfoDim:
                if (app.getbInfoDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwInfo().setLayoutParams(par_close);
                    app.getVwInfo().setVisibility(View.INVISIBLE);
                    app.getbInfoDim().setTag("");
                    app.getbInfoDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    DeviceInfo info = new DeviceInfo();
                    app.getlInfoOut().setText(info.getSysDeviceInfo());
                    app.getVwInfo().setVisibility(View.VISIBLE);
                    app.getVwInfo().setLayoutParams(par_open);
                    app.getbInfoDim().setTag(app.getTAG_Visible());
                    app.getbInfoDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* NET */
            case R.id.bNetDim:
                if (app.getbNetDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwNet().setLayoutParams(par_close);
                    app.getVwNet().setVisibility(View.INVISIBLE);
                    app.getbNetDim().setTag("");
                    app.getbNetDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    Toast.makeText(app, "Start Scan...", Toast.LENGTH_SHORT).show();
                    app.getlNetOut().setText(sendScan());
                    Toast.makeText(app, "Done", Toast.LENGTH_SHORT).show();
                    app.getVwNet().setVisibility(View.VISIBLE);
                    app.getVwNet().setLayoutParams(par_open);
                    app.getbNetDim().setTag(app.getTAG_Visible());
                    app.getbNetDim().setBackgroundResource(R.drawable.down);
                }
                break;

            /* Root */
            case R.id.bRootDim:
                if (app.getbRootDim().getTag().equals(app.getTAG_Visible())) {
                    app.getVwRoot().setLayoutParams(par_close);
                    app.getVwRoot().setVisibility(View.INVISIBLE);
                    app.getbRootDim().setTag("");
                    app.getbRootDim().setBackgroundResource(R.drawable.left);
                }
                else {
                    Toast.makeText(app, "Start.", Toast.LENGTH_SHORT).show();
                    String s="";
                    if(info.RootCheck()) {
                        app.getlRootOut().setTextColor(app.getResources().getColor(R.color.colorGreen));
                        app.getlRootOut().setText("Rooted");
                    }
                    else {
                        app.getlRootOut().setTextColor(app.getResources().getColor(R.color.colorRed));
                        app.getlRootOut().setText("Unrooted");
                    }
                    Toast.makeText(app, "Done", Toast.LENGTH_SHORT).show();
                    app.getVwRoot().setVisibility(View.VISIBLE);
                    app.getVwRoot().setLayoutParams(par_open);
                    app.getbRootDim().setTag(app.getTAG_Visible());
                    app.getbRootDim().setBackgroundResource(R.drawable.down);
                }
                break;
        }
    }

    private String sendRequest(String url)
    {
        String result="";
        try
        {
            result = new HttpGetRequest().execute(url).get();
        } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        return result;
    }

    private String sendScan()
    {
        String result="";
        try
        {
            result = new NetworkSniffTask(app).execute().get();
        } catch (InterruptedException | ExecutionException e) { e.printStackTrace(); }
        return result;
    }


    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(app.getPackageManager()) != null) {
            app.startActivity(intent);
        }
    }




}

