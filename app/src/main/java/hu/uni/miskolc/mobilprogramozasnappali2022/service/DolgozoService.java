package hu.uni.miskolc.mobilprogramozasnappali2022.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DolgozoService {
    @GET("dolgozok")
    Call<List<DolgozoDTO>> dolgozokListazasa();

    @GET("dolgozok/{id}")
    Call<DolgozoDTO> dolgozoKeresese(@Path("id") int id);

    @POST("dolgozok")
    Call<DolgozoDTO> dolgozoLetrehozasa(@Body DolgozoDTO dolgozo);

    @GET("dolgozok")
    Call<List<DolgozoDTO>> dolgozokAdottFizetessel(@Query("fizetes") int fizetes);

}
