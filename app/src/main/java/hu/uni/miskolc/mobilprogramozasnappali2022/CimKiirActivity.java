package hu.uni.miskolc.mobilprogramozasnappali2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import hu.uni.miskolc.mobilprogramozasnappali2022.model.Cim;

public class CimKiirActivity extends AppCompatActivity {

    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cim_kiir);
        hello = findViewById(R.id.textView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = getIntent();
        if (!intent.hasExtra("cim")) {
            if (intent.hasExtra("nev")) {
                String nev = intent.getStringExtra("nev");
                hello.setText("Szia " + nev);
            }
        }else{

            LinearLayout layout = findViewById(R.id.adatok);
            Cim cim = (Cim) intent.getSerializableExtra("cim");
            layout.removeAllViews();
            TextView iranyitoszam = new TextView(this);
            iranyitoszam.setText(cim.getIranyitoszam());
            iranyitoszam.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            iranyitoszam.setGravity(Gravity.CENTER);
            iranyitoszam.setBackgroundColor(getResources().getColor(R.color.teal_700, getTheme()));
            layout.addView(iranyitoszam);

            TextView varos = new TextView(this);
            varos.setText(cim.getVaros());
            varos.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
            varos.setGravity(Gravity.CENTER);
            varos.setBackgroundColor(getResources().getColor(
                    R.color.teal_700, getTheme()));
            layout.addView(varos);
        }

    }
}