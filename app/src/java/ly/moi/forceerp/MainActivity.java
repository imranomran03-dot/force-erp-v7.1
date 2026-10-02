package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLogin();
    }

    private void showLogin() {

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(40, 40, 40, 40);
        layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        TextView title = new TextView(this);
        title.setText("منظومة إدارة القوة العمومية");
        title.setTextSize(24);
        title.setTextColor(Color.DKGRAY);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 30);

        TextView version = new TextView(this);
        version.setText("V7.1");
        version.setTextSize(18);
        version.setGravity(Gravity.CENTER);
        version.setPadding(0, 0, 0, 30);

        EditText username = new EditText(this);
        username.setHint("اسم المستخدم");
        username.setSingleLine(true);

        EditText password = new EditText(this);
        password.setHint("كلمة المرور");
        password.setSingleLine(true);
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        Button loginButton = new Button(this);
        loginButton.setText("دخول");

        layout.addView(title);
        layout.addView(version);
        layout.addView(username);
        layout.addView(password);
        layout.addView(loginButton);

        setContentView(layout);

        loginButton.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("admin") && pass.equals("Admin2026")) {

                showDashboard();

            } else {

                Toast.makeText(
                        MainActivity.this,
                        "اسم المستخدم أو كلمة المرور غير صحيحة",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void showDashboard() {

        setContentView(R.layout.activity_main);

        setupDashboardButtons();
    }

    private void setupDashboardButtons() {

        Button personnel = findViewById(R.id.btnPersonnel);
        Button search = findViewById(R.id.btnSearch);
        Button reports = findViewById(R.id.btnReports);
        Button financial = findViewById(R.id.btnFinancial);
        Button courses = findViewById(R.id.btnCourses);
        Button movement = findViewById(R.id.btnMovement);
        Button settings = findViewById(R.id.btnSettings);

        personnel.setOnClickListener(v -> showPersonnelScreen());

        search.setOnClickListener(v -> showSearchScreen());

        reports.setOnClickListener(v ->
                showMessage("التقارير والإحصائيات - سيتم ربطها بالبيانات الفعلية")
        );

        financial.setOnClickListener(v ->
                showMessage("البطاقة المالية - سيتم ربطها بملف المنتسب")
        );

        courses.setOnClickListener(v ->
                showMessage("الدورات والمؤهلات - سيتم ربطها ببيانات المنتسب")
        );

        movement.setOnClickListener(v ->
                showMessage("الحركة والتنقلات - سيتم ربطها بقرارات النقل والندب والتكليف")
        );

        settings.setOnClickListener(v ->
                showMessage("الإعدادات")
        );
    }

    private void showPersonnelScreen() {

        LinearLayout layout = createScreenLayout();

        TextView title = createTitle("👥 المنتسبون");

        TextView info = new TextView(this);
        info.setText(
                "إدارة بيانات المنتسبين\n\n" +
                "سيتم هنا التعامل مع كامل بيانات المنتسب البالغ عددها 116 خانة."
        );
        info.setTextSize(17);
        info.setTextColor(Color.DKGRAY);
        info.setGravity(Gravity.CENTER);
        info.setPadding(10, 20, 10, 20);

        Button add = new Button(this);
        add.setText("➕ إضافة منتسب جديد");
        add.setTextSize(17);

        Button search = new Button(this);
        search.setText("🔎 البحث عن منتسب");
        search.setTextSize(17);

        Button back = new Button(this);
        back.setText("↩ العودة إلى لوحة القيادة");

        layout.addView(title);
        layout.addView(info);
        layout.addView(add);
        layout.addView(search);
        layout.addView(back);

        setContentView(layout);

        add.setOnClickListener(v ->
                showMessage("شاشة إضافة المنتسب - ستحتوي على جميع الـ116 خانة")
        );

        search.setOnClickListener(v -> showSearchScreen());

        back.setOnClickListener(v -> showDashboard());
    }

    private void showSearchScreen() {

        LinearLayout layout = createScreenLayout();

        TextView title = createTitle("🔎 البحث عن منتسب");

        TextView hint = new TextView(this);
        hint.setText(
                "يمكنك البحث باستخدام أي معلومة متاحة:\n" +
                "الاسم • الرقم العسكري • الرقم الوطني • الرقم المالي"
        );
        hint.setTextSize(15);
        hint.setTextColor(Color.DKGRAY);
        hint.setGravity(Gravity.CENTER);
        hint.setPadding(10, 10, 10, 20);

        EditText searchBox = new EditText(this);
        searchBox.setHint("اكتب الاسم أو الرقم");
        searchBox.setSingleLine(true);

        Button searchButton = new Button(this);
        searchButton.setText("🔎 بحث");
        searchButton.setTextSize(17);

        TextView result = new TextView(this);
        result.setText(
                "نتيجة البحث ستظهر هنا\n\n" +
                "وسيتم لاحقًا عرض:\n" +
                "• صورة المنتسب\n" +
                "• بطاقة المنتسب\n" +
                "• البيانات الأساسية\n" +
                "• البطاقة المالية المصغرة\n" +
                "• التنبيهات والقرارات القريبة"
        );
        result.setTextSize(16);
        result.setTextColor(Color.DKGRAY);
        result.setPadding(15, 25, 15, 25);
        result.setGravity(Gravity.CENTER);

        Button back = new Button(this);
        back.setText("↩ العودة إلى لوحة القيادة");

        layout.addView(title);
        layout.addView(hint);
        layout.addView(searchBox);
        layout.addView(searchButton);
        layout.addView(result);
        layout.addView(back);

        setContentView(layout);

        searchButton.setOnClickListener(v -> {

            String value = searchBox.getText().toString().trim();

            if (value.isEmpty()) {

                Toast.makeText(
                        MainActivity.this,
                        "اكتب الاسم أو أحد أرقام المنتسب للبحث",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                result.setText(
                        "🔎 تم تنفيذ البحث عن:\n\n" +
                        value +
                        "\n\n" +
                        "سيتم ربط هذه الشاشة بقاعدة بيانات المنتسبين في المرحلة التالية."
                );
            }
        });

        back.setOnClickListener(v -> showDashboard());
    }

    private LinearLayout createScreenLayout() {

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(30, 30, 30, 30);
        layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        layout.setBackgroundColor(Color.rgb(245, 247, 246));

        return layout;
    }

    private TextView createTitle(String text) {

        TextView title = new TextView(this);

        title.setText(text);
        title.setTextSize(24);

        // جعل العنوان عريضًا بالطريقة الصحيحة
        title.setTypeface(
                null,
                android.graphics.Typeface.BOLD
        );

        title.setTextColor(Color.rgb(27, 94, 32));
        title.setGravity(Gravity.CENTER);
        title.setPadding(10, 10, 10, 25);

        return title;
    }

    private void showMessage(String message) {

        Toast.makeText(
                MainActivity.this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}