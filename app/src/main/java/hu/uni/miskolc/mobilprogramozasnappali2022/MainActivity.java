package hu.uni.miskolc.mobilprogramozasnappali2022;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import hu.uni.miskolc.mobilprogramozasnappali2022.model.Cim;

public class MainActivity extends AppCompatActivity {

    CheckBox terms;
    Button kepGomb;
    Button kuldesGomb;

    private EditText iranyitoszamMezo;
    private EditText varosMezo;
    private EditText utcaMezo;
    private EditText hazszamMezo;

    ActivityResultLauncher<Intent> kamerakep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        terms = findViewById(R.id.feltetelek);
        kuldesGomb = findViewById(R.id.kuldesGomb);
        kepGomb = findViewById(R.id.kepGomb);

        iranyitoszamMezo = findViewById(R.id.iranyitoszamBevitel);
        varosMezo = findViewById(R.id.varosBevitel);
        utcaMezo = findViewById(R.id.utcaBevitel);
        hazszamMezo = findViewById(R.id.hazSzamBevitel);

        kamerakep = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Bitmap img = (Bitmap) result.getData().
                                getExtras().get("data");
                        ViewGroup layout = findViewById(R.id.hatter);
                        layout.setBackground(
                                new BitmapDrawable(getResources(), img));

                    }
                });
    }


    @Override
    protected void onStart() {
        super.onStart();
        terms.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                kuldesGomb.setEnabled(true);
            } else {
                kuldesGomb.setEnabled(false);
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
            kamerakep.launch(intent);
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