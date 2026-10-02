package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(27, 94, 32);
    private final int DARK_GREEN = Color.rgb(20, 70, 24);
    private final int DARK = Color.rgb(35, 35, 35);
    private final int GRAY = Color.rgb(100, 100, 100);
    private final int LIGHT = Color.rgb(245, 247, 246);
    private final int WHITE = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    // =========================
    // شاشة تسجيل الدخول
    // =========================

    private void showLogin() {

        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(LIGHT);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(40, 80, 40, 60);

        // الشعار / العنوان
        TextView title = new TextView(this);
        title.setText("منظومة إدارة القوة العمومية");
        title.setTextSize(25);
        title.setTextColor(GREEN);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 20, 0, 10);

        layout.addView(title,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

        TextView version = new TextView(this);
        version.setText("V7.1");
        version.setTextSize(18);
        version.setTextColor(GRAY);
        version.setGravity(Gravity.CENTER);
        version.setPadding(0, 0, 0, 45);

        layout.addView(version,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

        // عنوان اسم المستخدم
        TextView userLabel = new TextView(this);
        userLabel.setText("اسم المستخدم");
        userLabel.setTextSize(16);
        userLabel.setTextColor(DARK);
        userLabel.setTypeface(null, Typeface.BOLD);
        userLabel.setGravity(Gravity.RIGHT);

        layout.addView(userLabel);

        // خانة اسم المستخدم
        EditText username = new EditText(this);
        username.setHint("أدخل اسم المستخدم");
        username.setTextSize(17);
        username.setSingleLine(true);
        username.setPadding(25, 5, 25, 5);

        layout.addView(username,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        65
                ));

        addSpace(layout, 18);

        // عنوان كلمة المرور
        TextView passwordLabel = new TextView(this);
        passwordLabel.setText("كلمة المرور");
        passwordLabel.setTextSize(16);
        passwordLabel.setTextColor(DARK);
        passwordLabel.setTypeface(null, Typeface.BOLD);
        passwordLabel.setGravity(Gravity.RIGHT);

        layout.addView(passwordLabel);

        // خانة كلمة المرور
        EditText password = new EditText(this);
        password.setHint("أدخل كلمة المرور");
        password.setTextSize(17);
        password.setSingleLine(true);
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        password.setPadding(25, 5, 25, 5);

        layout.addView(password,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        65
                ));

        addSpace(layout, 28);

        // رسالة توضيحية
        TextView instruction = new TextView(this);
        instruction.setText("بعد إدخال البيانات اضغط على زر تسجيل الدخول");
        instruction.setTextSize(14);
        instruction.setTextColor(GRAY);
        instruction.setGravity(Gravity.CENTER);
        instruction.setPadding(0, 0, 0, 12);

        layout.addView(instruction,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));

        // =========================
        // زر تسجيل الدخول
        // =========================

        Button loginButton = new Button(this);

        loginButton.setText("تسجيل الدخول");
        loginButton.setTextSize(19);
        loginButton.setTextColor(WHITE);
        loginButton.setTypeface(null, Typeface.BOLD);
        loginButton.setAllCaps(false);
        loginButton.setGravity(Gravity.CENTER);
        loginButton.setBackgroundColor(GREEN);
        loginButton.setMinHeight(70);
        loginButton.setPadding(20, 10, 20, 10);
        loginButton.setVisibility(View.VISIBLE);
        loginButton.setEnabled(true);

        LinearLayout.LayoutParams buttonParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        70
                );

        buttonParams.setMargins(0, 5, 0, 20);

        layout.addView(loginButton, buttonParams);

        // زر مسح البيانات
        Button clearButton = new Button(this);
        clearButton.setText("مسح البيانات");
        clearButton.setTextSize(15);
        clearButton.setAllCaps(false);
        clearButton.setTextColor(DARK);
        clearButton.setBackgroundColor(Color.LTGRAY);

        LinearLayout.LayoutParams clearParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                );

        layout.addView(clearButton, clearParams);

        // =========================
        // وظيفة زر تسجيل الدخول
        // =========================

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString();

                if (user.equals("admin") && pass.equals("Admin2026")) {

                    Toast.makeText(
                            MainActivity.this,
                            "تم تسجيل الدخول بنجاح",
                            Toast.LENGTH_SHORT
                    ).show();

                    showDashboard();

                } else {

                    Toast.makeText(
                            MainActivity.this,
                            "اسم المستخدم أو كلمة المرور غير صحيحة",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }
        });

        // وظيفة زر المسح
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username.setText("");
                password.setText("");
                username.requestFocus();
            }
        });

        scrollView.addView(layout);

        setContentView(scrollView);
    }

    // =========================
    // المسافة
    // =========================

    private void addSpace(LinearLayout layout, int height) {

        View space = new View(this);

        layout.addView(
                space,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        height
                )
        );
    }

    // =========================
    // لوحة التحكم
    // =========================

    private void showDashboard() {

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle("لوحة التحكم");

        TextView subtitle = createText(
                "رئاسة قوة العمومية\nمنظومة إدارة القوة العمومية V7.1",
                18,
                GREEN
        );

        subtitle.setGravity(Gravity.CENTER);

        layout.addView(title);
        layout.addView(subtitle);

        addSpace(layout, 25);

        Button personnel = createButton("المنتسبون");
        Button search = createButton("البحث عن منتسب");
        Button reports = createButton("التقارير");
        Button financial = createButton("البطاقات المالية");
        Button courses = createButton("الدورات والتدريب");
        Button movement = createButton("الحركة والتنقلات");
        Button archive = createButton("الأرشيف الإلكتروني");
        Button settings = createButton("الإعدادات");

        layout.addView(personnel);
        layout.addView(search);
        layout.addView(reports);
        layout.addView(financial);
        layout.addView(courses);
        layout.addView(movement);
        layout.addView(archive);
        layout.addView(settings);

        personnel.setOnClickListener(v -> showMessage(
                "قسم المنتسبين\n\n116 خانة بيانات معتمدة."
        ));

        search.setOnClickListener(v -> showSearch());

        reports.setOnClickListener(v -> showMessage(
                "قسم التقارير\n\nسيتم ربط التقارير ببيانات المنتسبين."
        ));

        financial.setOnClickListener(v -> showMessage(
                "البطاقات المالية\n\nرقم مالي - مصرف - فرع - حساب - بيانات الراتب."
        ));

        courses.setOnClickListener(v -> showMessage(
                "الدورات والتدريب\n\nتدريبية - تخصصية - تأهيلية - ورشة - أخرى."
        ));

        movement.setOnClickListener(v -> showMessage(
                "الحركة والتنقلات\n\nتكليف - انتداب - نقل - منا - إلينا."
        ));

        archive.setOnClickListener(v -> showMessage(
                "الأرشيف الإلكتروني\n\nقرارات\nملفات المنتسبين\nالشهادات\nالمستندات"
        ));

        settings.setOnClickListener(v -> showMessage(
                "الإعدادات"
        ));

        setContentView(layout);
    }

    // =========================
    // شاشة البحث
    // =========================

    private void showSearch() {

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle("البحث عن منتسب");

        EditText searchBox = new EditText(this);
        searchBox.setHint(
                "الاسم / الرقم العسكري / الرقم الوطني / الرقم المالي"
        );
        searchBox.setTextSize(16);
        searchBox.setSingleLine(true);

        Button searchButton = createButton("بحث");

        Button backButton = createButton("رجوع");

        layout.addView(title);
        layout.addView(searchBox);
        layout.addView(searchButton);
        layout.addView(backButton);

        searchButton.setOnClickListener(v -> {

            String value = searchBox.getText().toString().trim();

            if (value.isEmpty()) {

                showMessage("أدخل قيمة للبحث أولاً");

            } else {

                showMessage(
                        "نتيجة البحث\n\nالقيمة: " + value +
                        "\n\nسيتم ربط البحث لاحقاً بقاعدة بيانات المنتسبين."
                );
            }
        });

        backButton.setOnClickListener(v -> showDashboard());

        setContentView(layout);
    }

    // =========================
    // إنشاء الواجهة الأساسية
    // =========================

    private LinearLayout createBaseLayout() {

        LinearLayout layout = new LinearLayout(this);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setPadding(35, 45, 35, 35);
        layout.setBackgroundColor(LIGHT);

        ScrollView scrollView = null;

        return layout;
    }

    // =========================
    // عنوان
    // =========================

    private TextView createTitle(String text) {

        TextView title = new TextView(this);

        title.setText(text);
        title.setTextSize(25);
        title.setTextColor(GREEN);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 10, 0, 25);

        return title;
    }

    // =========================
    // نص
    // =========================

    private TextView createText(
            String text,
            int size,
            int color
    ) {

        TextView view = new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setPadding(10, 10, 10, 10);

        return view;
    }

    // =========================
    // زر عام
    // =========================

    private Button createButton(String text) {

        Button button = new Button(this);

        button.setText(text);
        button.setTextSize(17);
        button.setTextColor(WHITE);
        button.setTypeface(null, Typeface.BOLD);
        button.setAllCaps(false);
        button.setGravity(Gravity.CENTER);
        button.setBackgroundColor(GREEN);
        button.setMinHeight(65);
        button.setVisibility(View.VISIBLE);
        button.setEnabled(true);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        65
                );

        params.setMargins(0, 8, 0, 8);

        button.setLayoutParams(params);

        return button;
    }

    // =========================
    // رسالة
    // =========================

    private void showMessage(String message) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}