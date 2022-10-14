package hu.uni.miskolc.mobilprogramozasnappali2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoDTO;
import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoService;
import hu.uni.miskolc.mobilprogramozasnappali2022.ui.DolgozoAdapter;
import hu.uni.miskolc.mobilprogramozasnappali2022.ui.DolgozoViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DolgozokLista extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolgozok_lista);
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RequestQueue sor = Volley.newRequestQueue(this);
        String url = "https://dog.ceo/api/breeds/image/random";

        Request jsonObjectRequest = new JsonObjectRequest(
                url,
                response -> {
                    try {
                        String imageURL = response.getString("message");
                        System.out.println(imageURL);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                });
        sor.add(jsonObjectRequest);

        //Retrofit
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://my-json-server.typicode.com/judit0310/dummyJsonServer/").addConverterFactory(GsonConverterFactory.create()).build();

        DolgozoService service = retrofit.create(DolgozoService.class);

        Call<List<DolgozoDTO>> szemelyekListazasa = service.dolgozokListazasa();
        szemelyekListazasa.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });

        Call<DolgozoDTO> egySzemely = service.dolgozoKeresese(60);
   /*
        Throws error because it is on main thread.
        You should manually solve it to work.

        try {

            Response<DolgozoDTO> response = egySzemely.execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }   */
        egySzemely.enqueue(new Callback<DolgozoDTO>() {
            @Override
            public void onResponse(Call<DolgozoDTO> call, Response<DolgozoDTO> response) {
                if (response.body() == null) {
                    System.out.println("Az adott azonosítóval nem létezik dolgozó");

                } else {
                    System.out.println(response.body());
                }
            }

            @Override
            public void onFailure(Call<DolgozoDTO> call, Throwable t) {
                System.out.println("Something went wrong");
            }
        });

        DolgozoDTO dolgozo = new DolgozoDTO();
        dolgozo.setId(3);
        dolgozo.setKeresztNev("Béla");
        dolgozo.setVezetekNev("Nagy");
        dolgozo.setFizetes(3);
        Call<DolgozoDTO> szemelyHozzaadasa = service.dolgozoLetrehozasa(dolgozo);
        szemelyHozzaadasa.enqueue(new Callback<DolgozoDTO>() {
            @Override
            public void onResponse(Call<DolgozoDTO> call, Response<DolgozoDTO> response) {
                if (response.code() == 200 || response.code() == 201){
                System.out.println(response.code()+" "+ response.body());
            }
            else if(response.errorBody() != null){
                    try {
                        System.out.println(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<DolgozoDTO> call, Throwable t) {

            }
        });

        Call<List<DolgozoDTO>> fizetesesEmberek = service.dolgozokAdottFizetessel(182500);
        fizetesesEmberek.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });


        //RecyclerView létrehozása
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(
                        recyclerView.getContext(),
                        DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        DolgozoViewModel vm = new ViewModelProvider(this).get(DolgozoViewModel.class);

        DolgozoAdapter adapter = new DolgozoAdapter();
        adapter.setDolgozok(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        vm.getDolgozok().observe(this, dolgozok -> {
            adapter.setDolgozok(dolgozok);
            adapter.setListener((position, v) -> {
                System.out.println("Hello");
            });
            recyclerView.setAdapter(adapter);
        });


    }
}