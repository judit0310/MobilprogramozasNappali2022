package hu.uni.miskolc.mobilprogramozasnappali2022;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

import hu.uni.miskolc.mobilprogramozasnappali2022.db.CimDAO;
import hu.uni.miskolc.mobilprogramozasnappali2022.db.CimDatabase;
import hu.uni.miskolc.mobilprogramozasnappali2022.model.Cim;

public class MainActivity extends AppCompatActivity {

    CheckBox terms;
    Button kepGomb;
    Button kuldesGomb;

    private EditText iranyitoszamMezo;
    private EditText varosMezo;
    private EditText utcaMezo;
    private EditText hazszamMezo;
    private ImageButton mentesGomb;
    private Button dolgozokGomb;

    private String uriPath;
    private CimDAO cimDAO;


    ActivityResultLauncher<Intent> kamerakep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        terms = findViewById(R.id.feltetelek);
        kuldesGomb = findViewById(R.id.kuldesGomb);
        kepGomb = findViewById(R.id.kepGomb);
        mentesGomb = findViewById(R.id.mentesGomb);
        mentesGomb.setEnabled(false);
        dolgozokGomb = findViewById(R.id.dolgozokGomb);

        iranyitoszamMezo = findViewById(R.id.iranyitoszamBevitel);
        varosMezo = findViewById(R.id.varosBevitel);
        utcaMezo = findViewById(R.id.utcaBevitel);
        hazszamMezo = findViewById(R.id.hazSzamBevitel);

        kamerakep = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {

                    if (result.getResultCode() == RESULT_OK) {
                        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                        prefs.edit().putString("uri", uriPath).apply();
                        if(result.getData().getData() != null) {
                            Bitmap img = (Bitmap) result.getData().
                                    getExtras().get("data");
                            ViewGroup layout = findViewById(R.id.hatter);
                            layout.setBackground(
                                    new BitmapDrawable(getResources(), img));
                        }
                    }
                });

        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),
                "publiclog.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            int db = 0;
            while(br.readLine() != null){
                db++;

            }
            br.close();
            System.out.println("A fájlban "+db+" db sor van");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        terms.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                kuldesGomb.setEnabled(true);
                mentesGomb.setEnabled(true);
            } else {
                kuldesGomb.setEnabled(false);
                mentesGomb.setEnabled(false);
            }
        });

        StrictMode.VmPolicy.Builder builder =
                new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        CimDatabase cimdatabase = Room.databaseBuilder(getApplicationContext(),
                CimDatabase.class, "cimek").build();
        cimDAO = cimdatabase.getCimDAO();

        mentesGomb.setOnClickListener(view -> {
            ViewGroup layout = findViewById(R.id.adatok);
            if (mezokKitoltve(layout)) {
                Cim cim = new Cim(iranyitoszamMezo.getText().toString(),
                        varosMezo.getText().toString(),
                        utcaMezo.getText().toString(),
                        hazszamMezo.getText().toString());
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {

                        if (cimDAO.findSame(cim).size() == 0) {
                            cimDAO.insert(cim);

                            //File file = new File(getFilesDir()+"log.txt");
                            File file = new File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS),
                                    "publiclog.txt");
                            try {
                                if (!file.exists()){
                                    file.createNewFile();
                                }
                                FileWriter writer = new FileWriter(file, true);
                                BufferedWriter bw = new BufferedWriter(writer);
                                Timestamp now = new Timestamp(System.currentTimeMillis());
                                bw.write("Jelenleg ("+now.toString()+") "+cimDAO.getAll().size() +"" +
                                        " db cím van az adatbázisban \n" );
                                bw.flush();
                                bw.close();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println(cimDAO.getAll());

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Új cím felvitelre került", Toast.LENGTH_LONG).show();
                                    iranyitoszamMezo.setText(null);
                                    varosMezo.setText(null);
                                    utcaMezo.setText(null);
                                    hazszamMezo.setText(null);
                                    hazszamMezo.clearFocus();
                                    iranyitoszamMezo.clearFocus();

                                }
                            });
                        }
                    }
                });

            }
        });


        kuldesGomb.setOnClickListener(view -> {
            ViewGroup layout = findViewById(R.id.adatok);
            if (mezokKitoltve(layout)) {
                //TODO
                //Remove virtual keyboard
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                Toast.makeText(MainActivity.this,
                        "Minden oké", Toast.LENGTH_LONG).show();

                Cim cim = new Cim(iranyitoszamMezo.getText().toString(),
                        varosMezo.getText().toString(),
                        utcaMezo.getText().toString(),
                        hazszamMezo.getText().toString());
                Intent intent = new Intent(MainActivity.this,
                        CimKiirActivity.class);
                intent.putExtra("nev", "Béla");
                intent.putExtra("cim", cim);
                startActivity(intent);
            }
        });

        kepGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File storage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            Uri uri = Uri.fromFile(new File(storage, System.currentTimeMillis()+".jpg"));

            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri );
            uriPath=uri.getPath();
            kamerakep.launch(intent);
        });


        dolgozokGomb.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, DolgozokLista.class);
            startActivity(intent);
        });
    }

    private boolean mezokKitoltve(ViewGroup layout) {
        boolean result = true;
        int count = layout.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = layout.getChildAt(i);
            if (child instanceof ViewGroup) {
                if (!mezokKitoltve((ViewGroup) child)) {
                    result = false;
                }
            } else if (child instanceof EditText) {
                EditText editText = (EditText) child;
                if (editText.getText().toString().trim().isEmpty()) {
                    result = false;
                    editText.setError("Kötelező");
                }
            }
        }
        return result;
    }
}