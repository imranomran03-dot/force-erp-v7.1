package ly.moi.forceerp;

import android.content.Context;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public final class DatabaseProvider {

    private static volatile AppDatabase INSTANCE;

    private static final Migration MIGRATION_1_2 =
            new Migration(1, 2) {

                @Override
                public void migrate(
                        SupportSQLiteDatabase database) {

                    database.execSQL(
                            "ALTER TABLE personnel " +
                            "ADD COLUMN gender TEXT"
                    );

                    database.execSQL(
                            "ALTER TABLE personnel " +
                            "ADD COLUMN centralRegion TEXT"
                    );

                    database.execSQL(
                            "ALTER TABLE personnel " +
                            "ADD COLUMN workStatus TEXT"
                    );

                    database.execSQL(
                            "ALTER TABLE personnel " +
                            "ADD COLUMN membershipStatus TEXT"
                    );

                    database.execSQL(
                            "ALTER TABLE personnel " +
                            "ADD COLUMN disabilityStatus TEXT"
                    );
                }
            };

    private DatabaseProvider() {
    }

    public static AppDatabase getDatabase(
            Context context) {

        if (INSTANCE == null) {

            synchronized (
                    DatabaseProvider.class) {

                if (INSTANCE == null) {

                    INSTANCE =
                            Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "force_erp_v7_1.db"
                            )
                                    .addMigrations(
                                            MIGRATION_1_2
                                    )
                                    .build();
                }
            }
        }

        return INSTANCE;
    }
}