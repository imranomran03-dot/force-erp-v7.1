package ly.moi.forceerp;

import android.content.Context;

import androidx.room.Room;

public final class DatabaseProvider {

    private static volatile AppDatabase INSTANCE;

    private DatabaseProvider() {
        // منع إنشاء نسخة من هذا الكلاس
    }

    public static AppDatabase getDatabase(Context context) {

        if (INSTANCE == null) {

            synchronized (DatabaseProvider.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "force_erp_v7_1.db"
                    ).build();
                }
            }
        }

        return INSTANCE;
    }
}