package hu.uni.miskolc.mobilprogramozasnappali2022.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import hu.uni.miskolc.mobilprogramozasnappali2022.model.Cim;

@Database(entities = {Cim.class}, version = 1)
public abstract class CimDatabase extends RoomDatabase {

    public abstract CimDAO getCimDAO();
}
