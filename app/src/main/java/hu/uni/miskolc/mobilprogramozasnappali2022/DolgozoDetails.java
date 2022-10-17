package hu.uni.miskolc.mobilprogramozasnappali2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import hu.uni.miskolc.mobilprogramozasnappali2022.databinding.ActivityDolgozoDetailsBinding;
import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoDTO;

public class DolgozoDetails extends AppCompatActivity {

    private ActivityDolgozoDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDolgozoDetailsBinding.inflate(
                getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onStart() {
        super.onStart();
        DolgozoDTO dolgozo = (DolgozoDTO) getIntent().
                getSerializableExtra("dolgozo");
        if (dolgozo !=null){
            binding.azonositoMezo.setText(
                    String.valueOf(dolgozo.getId()));
            binding.vezeteknevMezo.setText(
                    dolgozo.getVezetekNev());
            binding.keresztnevMezo.setText(
                    dolgozo.getKeresztNev());
            binding.fizetesMezo.setText(
                    String.valueOf(dolgozo.getFizetes()));
        }
        Intent serviceIntent = new Intent(DolgozoDetails.this, ZenelejatszasService.class);
        startService(serviceIntent);
    }
}