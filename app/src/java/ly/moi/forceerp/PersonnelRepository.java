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

    public void insert(
            Personnel personnel,
            Callback<Long> callback) {

        executor.execute(() -> {

            long id = dao.insert(personnel);

            if (callback != null) {
                callback.onResult(id);
            }
        });
    }

    public void update(
            Personnel personnel,
            Callback<Integer> callback) {

        executor.execute(() -> {

            int result = dao.update(personnel);

            if (callback != null) {
                callback.onResult(result);
            }
        });
    }

    public void delete(
            Personnel personnel,
            Callback<Integer> callback) {

        executor.execute(() -> {

            int result = dao.delete(personnel);

            if (callback != null) {
                callback.onResult(result);
            }
        });
    }

    public void getById(
            long id,
            Callback<Personnel> callback) {

        executor.execute(() -> {

            Personnel personnel = dao.getById(id);

            if (callback != null) {
                callback.onResult(personnel);
            }
        });
    }

    public void getAll(
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list = dao.getAll();

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void searchByName(
            String name,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.searchByName(name);

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
            String search,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.globalSearch(search);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByBranch(
            String branch,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getByBranch(branch);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByGender(
            String gender,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getByGender(gender);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByJobStatus(
            String jobStatus,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getByJobStatus(jobStatus);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getBySalaryStatus(
            String salaryStatus,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getBySalaryStatus(salaryStatus);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByWorkStatus(
            String workStatus,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getByWorkStatus(workStatus);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getByMembershipStatus(
            String membershipStatus,
            Callback<List<Personnel>> callback) {

        executor.execute(() -> {

            List<Personnel> list =
                    dao.getByMembershipStatus(membershipStatus);

            if (callback != null) {
                callback.onResult(list);
            }
        });
    }

    public void getTotalCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getTotalCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getOfficersCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getOfficersCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getNonCommissionedOfficersCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count =
                    dao.getNonCommissionedOfficersCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getIndividualsCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count =
                    dao.getIndividualsCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getEmployeesCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count =
                    dao.getEmployeesCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getMaleCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getMaleCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getFemaleCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getFemaleCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getAlertsCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getAlertsCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getMartyrsCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getMartyrsCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void getInjuredCount(
            Callback<Integer> callback) {

        executor.execute(() -> {

            int count = dao.getInjuredCount();

            if (callback != null) {
                callback.onResult(count);
            }
        });
    }

    public void deleteAll(Runnable callback) {

        executor.execute(() -> {

            dao.deleteAll();

            if (callback != null) {
                callback.run();
            }
        });
    }

    public void shutdown() {

        executor.shutdown();
    }
}