package ly.moi.forceerp;

public class DatabaseTest {

    public static int getPersonnelCount(ForceDatabase database) {
        return database.personnelDao().getTotalCount();
    }
}