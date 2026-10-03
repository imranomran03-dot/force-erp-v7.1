package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    private ForceDatabase database;
    private ExecutorService executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = DatabaseProvider.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();

        loadDashboard();
    }

    private void loadDashboard() {

        executor.execute(() -> {

            PersonnelDao dao = database.personnelDao();

            int total = dao.getTotalCount();
            int officers = dao.getOfficersCount();
            int ncos = dao.getNonCommissionedOfficersCount();
            int individuals = dao.getIndividualsCount();
            int employees = dao.getEmployeesCount();

            runOnUiThread(() -> {

                setCount(R.id.txtTotal, total);
                setCount(R.id.txtOfficers, officers);
                setCount(R.id.txtNcos, ncos);
                setCount(R.id.txtIndividuals, individuals);
                setCount(R.id.txtEmployees, employees);
            });
        });
    }

    private void setCount(int id, int value) {

        TextView textView = findViewById(id);

        if (textView != null) {
            textView.setText(String.valueOf(value));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (executor != null) {
            executor.shutdown();
        }
    }
}