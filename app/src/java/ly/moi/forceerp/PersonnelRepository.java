package ly.moi.forceerp;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonnelRepository {

    public interface Callback<T> {
        void onResult(T result);
    }

    private final PersonnelDao dao;
    private final ExecutorService executor;

    public PersonnelRepository(ForceDatabase database) {
        dao = database.personnelDao();
        executor = Executors.newFixedThreadPool(2);
    }

    public void insert(Personnel personnel, Callback<Long> callback) {
        executor.execute(() -> {
            long id = dao.insert(personnel);

            if (callback != null) {
                callback.onResult(id);
            }
        });
    }

    public void update(Personnel personnel, Callback<Integer> callback) {
        executor.execute(() -> {
            int result = dao.update(personnel);

            if (callback != null) {
                callback.onResult(result);
            }
        });
    }

    public void delete(Personnel personnel, Callback<Integer> callback) {
        executor.execute(() -> {
            int result = dao.delete(personnel);

            if (callback != null) {
                callback.onResult(result);
            }
        });
    }

    public void getById(long id, Callback<Personnel> callback) {
        executor.execute(() -> {
            Personnel personnel = dao.getById(id);

            if (callback != null) {
                callback.onResult(personnel);
            }
        });
    }

    public void getAll(Callback<List<Personnel>> callback) {
        executor.execute(() -> {
            List<Personnel> list = dao.getAll();

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void searchByName(String name, Callback<List<Personnel>> callback) {
        executor.execute(() -> {
            List<Personnel> list = dao.searchByName(name);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByNationalNumber(
            String nationalNumber,
            Callback<Personnel> callback) {

        executor.execute(() -> {
            Personnel personnel =
                    dao.getByNationalNumber(nationalNumber);

            if (callback != null) {
                callback.onResult(personnel);
            }
        });
    }

    public void getByAccountNumber(
            String accountNumber,
            Callback<Personnel> callback) {

        executor.execute(() -> {
            Personnel personnel =
                    dao.getByAccountNumber(accountNumber);

            if (callback != null) {
                callback.onResult(personnel);
            }
        });
    }

    public void globalSearch(
            String text,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {
            List<Personnel> list = dao.globalSearch(text);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void deleteAll(Callback<Integer> callback) {
        executor.execute(() -> {
            int result = dao.deleteAll();

            if (callback != null) {
                callback.onResult(result);
            }
        });
    }

    public void shutdown() {
        executor.shutdown();
    }
}