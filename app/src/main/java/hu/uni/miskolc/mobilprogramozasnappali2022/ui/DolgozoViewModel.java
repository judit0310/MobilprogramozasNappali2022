package hu.uni.miskolc.mobilprogramozasnappali2022.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoDTO;
import hu.uni.miskolc.mobilprogramozasnappali2022.service.DolgozoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DolgozoViewModel extends ViewModel {

    private MutableLiveData<List<DolgozoDTO>> dolgozok;


    public LiveData<List<DolgozoDTO>> getDolgozok() {
        if (dolgozok == null){
            dolgozok = new MutableLiveData<>();
            loadDolgozok();
        }
        return dolgozok;
    }

    public void deleteDolgozo(DolgozoDTO dolgozoDTO){
        dolgozok.getValue().remove(dolgozoDTO);
        dolgozok.postValue(dolgozok.getValue());

        }

    public void loadDolgozok(){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://my-json-server.typicode.com/judit0310/dummyJsonServer/").addConverterFactory(GsonConverterFactory.create()).build();

        DolgozoService service = retrofit.create(DolgozoService.class);

        Call<List<DolgozoDTO>> szemelyekListazasa = service.dolgozokListazasa();
        szemelyekListazasa.enqueue(new Callback<List<DolgozoDTO>>() {
            @Override
            public void onResponse(Call<List<DolgozoDTO>> call, Response<List<DolgozoDTO>> response) {
                System.out.println(response.body());
                dolgozok.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DolgozoDTO>> call, Throwable t) {

            }
        });

    }

}
