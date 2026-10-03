package ly.moi.forceerp;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {

    private static volatile ForceDatabase INSTANCE;

    public static ForceDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (DatabaseProvider.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            ForceDatabase.class,
                            "force_erp_database"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}