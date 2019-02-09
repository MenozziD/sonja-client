package com.sonja.dmeno.sonja;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.Manifest;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityMain extends AppCompatActivity
{
    private static final String TAG_Visible = "Visible";
    private static final String TAG_Key = "Visible";

    public String getTAG_Visible()
    {
        return TAG_Visible;
    }

    /* MAIN */
    private ImageButton bRefresh;
    private TextView lMex;
    private NetUtils net = new NetUtils();

    /* HOME */
    private TextView lOutHome;
    private Button bHomeDim;
    private LinearLayout vwHome;
    private Spinner lstHome;

    /* PIR */
    private TextView lOutPIR;
    private Button bPIRDim;
    private LinearLayout vwPIR;


    /* WEED */
    private GridLayout vwWeed;
    private EditText tWeedInput;
    private Button bWeedDim;
    private TextView lWeedOut;

    private SurfaceView surfaceView;
    private CameraSource cameraSource;
    private GridLayout vwQR;
    private Button bQRDim;
    private TextView lQROut;

    /* ARDUINO
    Handler mHandler = new Handler();
    private Physicaloid mPhysicaloid ; 		// setting the context for library
    private Button bComDim;
    private ImageButton bComOpen;
    private ImageButton bComClose;
    private EditText tComInput;
    private TextView lComOut;
    private LinearLayout vwArduino; */

    /* KEY */
    private TextView lKeyOut;
    private GridLayout vwKey;
    private Spinner lstKey;
    private Button bKeyDim;
    private EditText tKeyName;
    private EditText tKeyValue;
    private TextView lKeyDebug;

    /* BULB */
    private Button bBulbDim;
    private GridLayout vwBulb;

    /* SERVICE */
    private Button bServiceDim;
    private GridLayout vwService;
    private Spinner lstServiceName;
    private Spinner lstServiceCmd;
    private TextView lServiceOut;

    /* SHELL */
    private Button bShellDim;
    private Button bShellCmd;
    private EditText tShellIn;
    private TextView lShellOut;
    private GridLayout vwShell;

    /* NOTE */
    private Button bNoteDim;
    private EditText tNoteIn;
    private GridLayout vwNote;
    private EditText tNoteName;

    /* INFO */
    private Button bInfoDim;
    private GridLayout vwInfo;
    private TextView lInfoOut;

    /* NET */
    private Button bNetDim;
    private GridLayout vwNet;
    private TextView lNetOut;

    /* ROOT */
    private Button bRootDim;
    private LinearLayout vwRoot;
    private TextView lRootOut;

    /* MAIN */
    public ImageButton getbRefresh() {
        return bRefresh;
    }
    public TextView getlMex() {
        return lMex;
    }

    
    /* HOME */
    public LinearLayout getVwHome() {
        return vwHome;
    }

    public Spinner getLstHome() {
        return lstHome;
    }

    public TextView getlOutHome() {
        return lOutHome;
    }

    public Button getbHomeDim() {
        return bHomeDim;
    }

    /* PIR */
    public LinearLayout getVwPIR() {
        return vwPIR;
    }

    public TextView getlOutPIR() {
        return lOutPIR;
    }

    public Button getbPIRDim() {
        return bPIRDim;
    }


    /* WEED */
    public GridLayout getVwWeed() {
        return vwWeed;
    }

    public Button getbWeedDim() {
        return bWeedDim;
    }

    public EditText gettWeedInput(){
        return tWeedInput;
    }

    public TextView getlWeedOut() {
        return lWeedOut;
    }

    /* QR READER */

    public GridLayout getVwQR() {
        return vwQR;
    }

    public TextView getlQROut() {
        return lQROut;
    }

    public Button getbQRDim() {
        return bQRDim;
    }

    /* ARDUINO

    public LinearLayout getVwArduino() {
        return vwArduino;
    }

    public Button getbComDim() {
        return bComDim;
    }

    public TextView getlComOut()
    {
        return lComOut;
    }

    public Physicaloid getmPhysicaloid() {
        return mPhysicaloid;
    }*/

    /* KEY */
    public Button getbKeyDim() {
        return bKeyDim;
    }

    public GridLayout getVwKey() {
        return vwKey;
    }

    public Spinner getLstKey() {
        return lstKey;
    }

    public TextView getlKeyOut() {
        return lKeyOut;
    }

    public EditText gettKeyName() {
        return tKeyName;
    }

    public EditText gettKeyValue() {
        return tKeyValue;
    }

    public TextView getlKeyDebug() {
        return lKeyDebug;
    }

    /* BULB */
    public GridLayout getVwBulb() {
        return vwBulb;
    }
    public Button getbBulbDim() {
        return bBulbDim;
    }

    /* SERVICE */
    public GridLayout getVwService() {
        return vwService;
    }
    public Button getbServiceDim() {
        return bServiceDim;
    }
    public Spinner getLstServiceCmd() {
        return lstServiceCmd;
    }
    public Spinner getLstServiceName() {
        return lstServiceName;
    }
    public TextView getlServiceOut() {
        return lServiceOut;
    }

    /* SHELL */
    public GridLayout getVwShell() {
        return vwShell;
    }
    public Button getbShellDim(){return bShellDim; }
    public EditText gettShellIn(){return tShellIn;}
    public TextView getlShellOut(){return lShellOut;}

    /* NOTE */
    public Button getbNoteDim() {return bNoteDim; }
    public EditText gettNoteIn() {return tNoteIn; }
    public GridLayout getVwNote() {return vwNote; }
    public EditText gettNoteName(){return tNoteName; }

    /* INFO */
    public Button getbInfoDim() {return bInfoDim; }
    public GridLayout getVwInfo() {return vwInfo; }
    public TextView getlInfoOut() {return lInfoOut; }

    /* NET */
    public Button getbNetDim() {return bNetDim; }
    public GridLayout getVwNet() {return vwNet; }
    public TextView getlNetOut() {return lNetOut; }


    /* ROOT */
    public Button getbRootDim() {return bRootDim; }
    public LinearLayout getVwRoot() {return vwRoot; }
    public TextView getlRootOut() {return lRootOut; }

    public void setLstKeyAdapterFromString(String s)
    {
        ArrayList<String> lst= new ArrayList<>(Arrays.asList(s.split(";")));
        ArrayAdapter<String> adapterKey = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lst);
        lstKey.setAdapter(adapterKey);
    }

    public void setLstHomeAdapterFromString(String s)
    {
        ArrayList<String> lst= new ArrayList<>(Arrays.asList(s.split(";")));
        ArrayAdapter<String> adapterHome = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lst);
        lstHome.setAdapter(adapterHome);
    }

    public void setLstServiceAdapterFromString(String s)
    {
        ArrayList<String> lst= new ArrayList<>(Arrays.asList(s.split(";")));
        ArrayAdapter<String> adapterService = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lst);
        lstServiceName.setAdapter(adapterService);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AscoltatoreMainActivity Ascoltatore = new AscoltatoreMainActivity(this);
        ArrayAdapter<CharSequence> adapter;

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        lMex=findViewById(R.id.lMex);
        bRefresh=findViewById(R.id.bRefresh);
        bRefresh.setOnClickListener(Ascoltatore);



        /* HOME */
        vwHome=findViewById(R.id.vwHome);
        lstHome = findViewById(R.id.lstHome);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.lstHome, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lstHome.setAdapter(adapter);

        ImageButton bHomeCmd = findViewById(R.id.bHomeCmd);
        bHomeDim = findViewById(R.id.bHomeDim);
        lOutHome = findViewById(R.id.lHomeOut);
        bHomeCmd.setOnClickListener(Ascoltatore);
        bHomeDim.setOnClickListener(Ascoltatore);
        bHomeDim.setTag(TAG_Visible);
        bHomeDim.callOnClick();

        /* PIR */
        vwPIR=findViewById(R.id.vwPIR);
        bPIRDim = findViewById(R.id.bPIRDim);
        lOutPIR = findViewById(R.id.lOutPIR);;
        bPIRDim.setOnClickListener(Ascoltatore);
        bPIRDim.setTag(TAG_Visible);
        bPIRDim.callOnClick();

        /* WEBEHIGH */
        vwWeed=findViewById(R.id.vwWeed);
        tWeedInput =findViewById(R.id.tWeedInput);
        tWeedInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        bWeedDim = findViewById(R.id.bWeedDim);
        lWeedOut = findViewById(R.id.lWeedOut);
        ImageButton bWeedCmd = findViewById(R.id.bWeedCmd);
        bWeedCmd.setOnClickListener(Ascoltatore);
        bWeedDim.setOnClickListener(Ascoltatore);
        bWeedDim.setTag(TAG_Visible);
        bWeedDim.callOnClick();
        LinearLayout title_webehigh = findViewById(R.id.vwWeedTitolo);
        title_webehigh.setVisibility(View.INVISIBLE);


        /* QR READER */
        ImageButton bQRCopy = findViewById(R.id.bQRCopy);
        ImageButton bQROpen = findViewById(R.id.bQROpen);
        bQRCopy.setOnClickListener(Ascoltatore);
        bQROpen.setOnClickListener(Ascoltatore);
        vwQR=findViewById(R.id.vwQR);
        bQRDim = findViewById(R.id.bQRDim);
        bQRDim.setOnClickListener(Ascoltatore);
        bQRDim.setTag(TAG_Visible);
        bQRDim.callOnClick();
        LinearLayout title_qr = findViewById(R.id.vwQRTitolo);
        //title_qr.setVisibility(View.INVISIBLE);

        surfaceView = findViewById(R.id.surfQR);
        lQROut = findViewById(R.id.lQROut);

        // chiediamo di individuare QR code e EAN 13
        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE | Barcode.EAN_13)
                .build();

        // verifichiamo che BarcodeDetector sia operativo
        if (!detector.isOperational()) {
            //exit("Detector di codici a barre non attivabile");
            return;
        }

        // istanziamo un oggetto CameraSource collegata al detector
        cameraSource = new CameraSource
                .Builder(this, detector)
                .setAutoFocusEnabled(true)
                .build();

        // gestione delle fasi di vita della SurfaceView
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback()
        {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                activateCamera();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> items = detections.getDetectedItems();

                if (items.size() != 0)
                    runOnUiThread(new Runnable() {
                        public void run() {
                            String barcode=items.valueAt(0).displayValue;
                            lQROut.setText(barcode);
                        }
                    });
            }
        });

        /* ARDUINO
        lComOut=findViewById(R.id.lCOMOut);
        vwArduino=findViewById(R.id.vwCOM);
        bComDim = findViewById(R.id.bComDim);
        bComDim.setOnClickListener(Ascoltatore);
        bComDim.setTag(TAG_Visible);
        bComDim.callOnClick();
        bComOpen=findViewById(R.id.bComOpen);
        bComOpen.setOnClickListener(Ascoltatore);
        bComClose=findViewById(R.id.bComClose);
        bComClose.setOnClickListener(Ascoltatore);*/
        LinearLayout title_arduino =findViewById(R.id.vwComTitolo);
        title_arduino.setVisibility(View.INVISIBLE);

        /* KEY */
        lKeyOut=findViewById(R.id.lKeyOut);
        vwKey=findViewById(R.id.vwKey);
        bKeyDim = findViewById(R.id.bKeyDim);
        bKeyDim.setOnClickListener(Ascoltatore);
        bKeyDim.setTag(TAG_Visible);
        bKeyDim.callOnClick();
        ImageButton bKeyCmd = findViewById(R.id.bKeyCmd);
        bKeyCmd.setTag(TAG_Key);
        bKeyCmd.setOnClickListener(Ascoltatore);
        lstKey = findViewById(R.id.lstKey);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.lstKey, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        lstKey.setAdapter(adapter);
        tKeyName =findViewById(R.id.tKeyName);
        tKeyValue=findViewById(R.id.tKeyValue);
        ImageButton bKeyAdd = findViewById(R.id.bKeyAdd);
        lKeyDebug=findViewById(R.id.lKeyDebug);
        bKeyAdd.setOnClickListener(Ascoltatore);
        bKeyAdd.setTag("");
        LinearLayout title_key =findViewById(R.id.vwKeyTitolo);
        title_key.setVisibility(View.INVISIBLE);

        /* BULB */
        vwBulb=findViewById(R.id.vwBulb);
        bBulbDim = findViewById(R.id.bBulbDim);
        bBulbDim.setOnClickListener(Ascoltatore);
        bBulbDim.setTag(TAG_Visible);
        bBulbDim.callOnClick();
        ImageButton bBulbCmd = findViewById(R.id.bBulbCmd);
        bBulbCmd.setOnClickListener(Ascoltatore);

        /* SERVICE */
        vwService=findViewById(R.id.vwService);
        bServiceDim = findViewById(R.id.bServiceDim);
        bServiceDim .setOnClickListener(Ascoltatore);
        bServiceDim .setTag(TAG_Visible);
        bServiceDim .callOnClick();
        lstServiceName=findViewById(R.id.lstServiceName);
        lstServiceCmd=findViewById(R.id.lstServiceCmd);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.lstServiceCmd, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lstServiceCmd.setAdapter(adapter);
        ImageButton bServiceCmd = findViewById(R.id.bServiceCmd);
        bServiceCmd.setOnClickListener(Ascoltatore);
        lServiceOut=findViewById(R.id.lServiceOut);
        LinearLayout title_service = findViewById(R.id.vwServiceTitolo);
        //title_service.setVisibility(View.INVISIBLE);


        /* SHELL */
        vwShell=findViewById(R.id.vwShell);
        ImageButton bShellCmd=findViewById(R.id.bShellCmd);
        bShellCmd.setOnClickListener(Ascoltatore);
        tShellIn=findViewById(R.id.tShellIn);
        lShellOut=findViewById(R.id.lShellOut);
        bShellDim=findViewById(R.id.bShellDim);
        bShellDim.setOnClickListener(Ascoltatore);
        bShellDim.setTag(TAG_Visible);
        bShellDim.callOnClick();
        LinearLayout title_shell = findViewById(R.id.vwShellTitolo);
        title_shell.setVisibility(View.INVISIBLE);


        /* NOTE */
        vwNote=findViewById(R.id.vwNote);
        bNoteDim=findViewById(R.id.bNoteDim);
        bNoteDim.setOnClickListener(Ascoltatore);
        bNoteDim.setTag(TAG_Visible);
        bNoteDim.callOnClick();
        final TextView lNoteRow=findViewById(R.id.lNoteRow);
        tNoteIn=findViewById(R.id.tNoteIn);
        ImageButton bNoteSave=findViewById(R.id.bNoteSave);
        bNoteSave.setOnClickListener(Ascoltatore);
        tNoteName=findViewById(R.id.tNoteName);

        tNoteIn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int lines = tNoteIn.getLineCount();
                String tlines = "";
                for( int x= 1; x<= lines; x++ ) { tlines=tlines + x + "\n"; }
                lNoteRow.setText(tlines);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        LinearLayout title_note = findViewById(R.id.vwNoteTitolo);
        title_note.setVisibility(View.INVISIBLE);


        /* INFO */
        vwInfo=findViewById(R.id.vwInfo);
        bInfoDim=findViewById(R.id.bInfoDim);
        bInfoDim.setOnClickListener(Ascoltatore);
        bInfoDim.setTag(TAG_Visible);
        lInfoOut=findViewById(R.id.lInfoOut);
        bInfoDim.callOnClick();
        LinearLayout title_info = findViewById(R.id.vwInfoTitolo);
        title_info.setVisibility(View.INVISIBLE);


        /* NET */
        vwNet=findViewById(R.id.vwNet);
        bNetDim=findViewById(R.id.bNetDim);
        bNetDim.setOnClickListener(Ascoltatore);
        bNetDim.setTag(TAG_Visible);
        lNetOut=findViewById(R.id.lNetOut);
        bNetDim.callOnClick();
        LinearLayout title_net = findViewById(R.id.vwNetTitolo);
        title_net.setVisibility(View.INVISIBLE);

        /* Root */
        vwRoot=findViewById(R.id.vwRoot);
        bRootDim=findViewById(R.id.bRootDim);
        bRootDim.setOnClickListener(Ascoltatore);
        bRootDim.setTag(TAG_Visible);
        lRootOut=findViewById(R.id.lRootOut);
        bRootDim.callOnClick();
        LinearLayout title_root = findViewById(R.id.vwRootTitolo);
        title_root.setVisibility(View.INVISIBLE);

        /* start */
        checkServer();
    }

    public void checkServer() {
        int status=-1;
        String str=net.ping("192.168.1.111");
        // Splitto testo risultato Ping in array
        String[] splited = str.split(" ");
        int index = -1;
        // cerco l'elemento dell'array contenente la parola "received" perchè l'elemento prima
        // è il numero dei pacchetti ritornati al comando Ping
        for (int i=0;i<splited.length;i++) {
            if (splited[i].equals("received,")) {
                index = i;
                break;
            }
        }
        // Se ho trovato la parola "received"
        if (index > 0 )
        {
            index--;
            // Leggo numero pacchetti rx e se > o connessione ok setto stato:
            // 1: Connessione OK - Comandi Abilitati
            // 0: Nessuna connessione o Errore - Comandi Disabilitati
            if (Integer.valueOf(splited[index]) > 0)
                status=1;
            else
                status=0;
        }
        else
            status=-1;

        if ( status < 1) {
            lMex.setTextColor(getResources().getColor(R.color.colorRed));
            str = "SERVER CONNECTION -FAILED";
            bHomeDim.setBackgroundResource(R.drawable.cancel);
            bHomeDim.setClickable(false);
            bBulbDim.setBackgroundResource(R.drawable.cancel);
            bBulbDim.setClickable(false);
            bPIRDim.setBackgroundResource(R.drawable.cancel);
            bPIRDim.setClickable(false);
            bServiceDim.setBackgroundResource(R.drawable.cancel);
            bServiceDim.setClickable(false);

        }
        else
        {
            lMex.setTextColor(getResources().getColor(R.color.colorGreen));
            str = "SERVER CONNECTION -OK";
            bHomeDim.setBackgroundResource(R.drawable.left);
            bHomeDim.setClickable(true);
            bBulbDim.setBackgroundResource(R.drawable.left);
            bBulbDim.setClickable(true);
            bPIRDim.setBackgroundResource(R.drawable.left);
            bPIRDim.setClickable(true);
            bServiceDim.setBackgroundResource(R.drawable.left);
            bServiceDim.setClickable(true);
        }
        lMex.setText(str);
    }



    private void activateCamera() {


        // verifichiamo che sia stata concessa la permission CAMERA

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            try {
                cameraSource.start(surfaceView.getHolder());
            } catch (IOException e) {
                lQROut.setText(this.getResources().getString(R.string.FOTO_ERR_2));
            }
//            // se arriviamo qui è perchè la permission non è stata ancora concessa
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                // mostriamo ulteriori informazioni all'utente riguardante l'uso della permission nell'app ed eventualmente richiediamo la permission
//            } else {
//
//                // se siamo qui è perchè non si è mostrata alcuna spiegazione all'utente, richiesta di permission
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ID_RICHIESTA_PERMISSION);
//            }
        }
        else
            lQROut.setText(this.getResources().getString(R.string.FOTO_ERR_1));
    }

}


