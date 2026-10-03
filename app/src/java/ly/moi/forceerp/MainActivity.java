package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    private PersonnelRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ForceDatabase database =
                DatabaseProvider.getDatabase(this);

        repository = new PersonnelRepository(database);

        loadDashboard();
    }

    private void loadDashboard() {

        repository.getAll(personnelList -> {

            int total = personnelList.size();

            int officers = 0;
            int ncos = 0;
            int individuals = 0;
            int employees = 0;

            for (Personnel person : personnelList) {

                if ("ضابط".equals(person.jobStatus)) {
                    officers++;
                }

                if ("ضابط صف".equals(person.jobStatus)) {
                    ncos++;
                }

                if ("فرد".equals(person.jobStatus)) {
                    individuals++;
                }

                if ("موظف".equals(person.jobStatus)) {
                    employees++;
                }
            }

            final int finalOfficers = officers;
            final int finalNcos = ncos;
            final int finalIndividuals = individuals;
            final int finalEmployees = employees;

            runOnUiThread(() -> {

                setCount(R.id.txtTotal, total);
                setCount(R.id.txtOfficers, finalOfficers);
                setCount(R.id.txtNcos, finalNcos);
                setCount(R.id.txtIndividuals, finalIndividuals);
                setCount(R.id.txtEmployees, finalEmployees);
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

        if (repository != null) {
            repository.shutdown();
        }

        super.onDestroy();
    }
}