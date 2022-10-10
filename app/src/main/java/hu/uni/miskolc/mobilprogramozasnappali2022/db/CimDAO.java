package hu.uni.miskolc.mobilprogramozasnappali2022.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import hu.uni.miskolc.mobilprogramozasnappali2022.model.Cim;

@Dao
public interface CimDAO {
    @Query("SELECT * from Cim")
    List<Cim> getAll();

    @Query("SELECT * from Cim Where varos=:city")
    List<Cim> findByCity(String city);

    @Query("SELECT * from Cim WHERE iranyitoszam=:iranyitoszam " +
            "AND varos=:varos AND utca=:utca AND hazszam=:hazszam")
    List<Cim> findSame(String iranyitoszam,
                       String varos, String utca, String hazszam);

    default List<Cim> findSame(Cim cim){
        return findSame(cim.getIranyitoszam(),
                cim.getVaros(), cim.getUtca(), cim.getHazszam());
    }

    @Insert
    void insert(Cim cim);

    @Delete
    void delete(Cim cim);

    @Update
    void update(Cim cim);
}
