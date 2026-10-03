package ly.moi.forceerp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {Personnel.class},
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PersonnelDao personnelDao();
}