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
    private final int DARK = Color.rgb(35, 35, 35);
    private final int GRAY = Color.rgb(100, 100, 100);
    private final int LIGHT = Color.rgb(245, 247, 246);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private void showLogin() {

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle("منظومة إدارة القوة العمومية");

        TextView version = createText(
                "V7.1",
                18,
                GREEN
        );
        version.setGravity(Gravity.CENTER);

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

        Button login = createButton("دخول");

        layout.addView(title);
        layout.addView(version);
        layout.addView(username);
        layout.addView(password);
        layout.addView(login);

        setContentView(layout);

        login.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString();

            if (user.equals("admin") && pass.equals("Admin2026")) {
                showDashboard();
            } else {
                showMessage("اسم المستخدم أو كلمة المرور غير صحيحة");
            }
        });
    }

    private void showDashboard() {

        ScrollView scrollView = new ScrollView(this);

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle(
                "لوحة القيادة الاستراتيجية"
        );

        TextView subtitle = createText(
                "رئاسة جهاز مكافحة الهجرة غير الشرعية\n" +
                "منظومة إدارة القوة العمومية V7.1",
                17,
                GREEN
        );
        subtitle.setGravity(Gravity.CENTER);

        TextView total = createCard(
                "إجمالي القوة\n\n" +
                "1,250\n" +
                "منتسب وموظف",
                22
        );

        Button personnel = createButton("👥  المنتسبون");
        Button search = createButton("🔎  البحث عن منتسب");
        Button reports = createButton("📊  التقارير والإحصائيات");
        Button financial = createButton("💰  البطاقة المالية");
        Button courses = createButton("🎓  الدورات والمؤهلات");
        Button movement = createButton("🔄  الحركة والتنقلات");
        Button settings = createButton("⚙  الإعدادات");

        layout.addView(title);
        layout.addView(subtitle);
        layout.addView(total);

        layout.addView(personnel);
        layout.addView(search);
        layout.addView(reports);
        layout.addView(financial);
        layout.addView(courses);
        layout.addView(movement);
        layout.addView(settings);

        TextView archive = createSection(
                "📁 الأرشيف الإلكتروني",
                "قرارات • ملفات المنتسبين • الشهادات • المستندات"
        );

        layout.addView(archive);

        scrollView.addView(layout);

        setContentView(scrollView);

        personnel.setOnClickListener(v -> showPersonnelCard());

        search.setOnClickListener(v -> showSearch());

        reports.setOnClickListener(v ->
                showMessage("التقارير والإحصائيات")
        );

        financial.setOnClickListener(v ->
                showMessage("البطاقة المالية")
        );

        courses.setOnClickListener(v ->
                showMessage("الدورات والمؤهلات")
        );

        movement.setOnClickListener(v ->
                showMessage("الحركة والتنقلات")
        );

        settings.setOnClickListener(v ->
                showMessage("الإعدادات")
        );
    }

    private void showPersonnelCard() {

        ScrollView scrollView = new ScrollView(this);

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle(
                "👤 بطاقة المنتسب"
        );

        TextView profile = createSection(
                "البيانات الأساسية",
                "الاسم: أحمد محمد علي\n" +
                "الرتبة: ملازم أول\n" +
                "اللقب: مثال\n" +
                "الرقم العسكري: 100245\n" +
                "الرقم الوطني: 123456789\n" +
                "الفرع: طرابلس\n" +
                "الوحدة / القطاع: الإدارة العامة\n" +
                "الحالة العسكرية الحالية: مستمر في العمل"
        );

        TextView financial = createSection(
                "💳 البطاقة المالية المصغرة",
                "الرقم المالي: 458721\n" +
                "الرقم العسكري: 100245\n" +
                "الدرجة الوظيفية: 8\n" +
                "الرتبة: ملازم أول\n" +
                "الحالة المالية: راتب جاري\n" +
                "حالة العمل: مستمر"
        );

        TextView movement = createSection(
                "🔄 التكليف والندب والحركة",
                "اتجاه الحركة: إلينا ← من جهة أخرى\n" +
                "نوع الإجراء: ندب إلينا\n" +
                "جهة التكليف: وزارة الداخلية\n" +
                "مكان التكليف: طرابلس\n" +
                "تاريخ البداية: 01/01/2026\n" +
                "تاريخ النهاية: 31/12/2026\n" +
                "المدة: 12 شهرًا\n" +
                "الحالة: ساري"
        );

        TextView alerts = createSection(
                "🔔 التنبيهات",
                "لا توجد تنبيهات عاجلة حاليًا.\n\n" +
                "سيتم لاحقًا حساب التنبيهات تلقائيًا من تواريخ " +
                "الوثائق والقرارات والتكليفات والترقيات."
        );

        TextView data = createSection(
                "📋 بيانات المنتسب",
                "إجمالي الحقول المرتبطة بالمنتسب: 116 خانة\n\n" +
                "1. البيانات الشخصية\n" +
                "2. البيانات الوظيفية والعسكرية\n" +
                "3. التعيين والترقيات\n" +
                "4. التكليف والندب والنقل\n" +
                "5. الدورات والمؤهلات\n" +
                "6. اللغات\n" +
                "7. الوثائق والتنبيهات\n" +
                "8. الأوسمة والجزاءات\n" +
                "9. الإصابات والشهداء والقضايا\n" +
                "10. التقييم السنوي\n" +
                "11. الصورة الشخصية\n" +
                "12. السلاح واللاسلكي والمركبة والمعدات"
        );

        Button edit = createButton(
                "✏  تعديل بيانات المنتسب"
        );

        Button archive = createButton(
                "📁  أرشيف المنتسب"
        );

        Button back = createButton(
                "↩  العودة إلى لوحة القيادة"
        );

        layout.addView(title);
        layout.addView(profile);
        layout.addView(financial);
        layout.addView(movement);
        layout.addView(alerts);
        layout.addView(data);
        layout.addView(edit);
        layout.addView(archive);
        layout.addView(back);

        scrollView.addView(layout);

        setContentView(scrollView);

        edit.setOnClickListener(v ->
                showMessage(
                        "شاشة تعديل المنتسب ستضم جميع الـ116 خانة"
                )
        );

        archive.setOnClickListener(v ->
                showMessage(
                        "أرشيف المنتسب: القرارات والشهادات والمستندات"
                )
        );

        back.setOnClickListener(v ->
                showDashboard()
        );
    }

    private void showSearch() {

        LinearLayout layout = createBaseLayout();

        TextView title = createTitle(
                "🔎 البحث عن منتسب"
        );

        TextView hint = createText(
                "البحث باستخدام الاسم أو الرقم العسكري " +
                "أو الرقم الوطني أو الرقم المالي",
                16,
                GRAY
        );
        hint.setGravity(Gravity.CENTER);

        EditText searchBox = new EditText(this);

        searchBox.setHint(
                "اكتب الاسم أو الرقم"
        );

        searchBox.setSingleLine(true);

        Button searchButton = createButton(
                "🔎  بحث"
        );

        TextView result = createSection(
                "نتيجة البحث",
                "أدخل قيمة البحث ثم اضغط «بحث»."
        );

        Button back = createButton(
                "↩  العودة إلى لوحة القيادة"
        );

        layout.addView(title);
        layout.addView(hint);
        layout.addView(searchBox);
        layout.addView(searchButton);
        layout.addView(result);
        layout.addView(back);

        setContentView(layout);

        searchButton.setOnClickListener(v -> {

            String value =
                    searchBox.getText()
                            .toString()
                            .trim();

            if (value.isEmpty()) {

                showMessage(
                        "اكتب الاسم أو أحد أرقام المنتسب للبحث"
                );

            } else {

                result.setText(
                        "🔎 قيمة البحث:\n\n" +
                        value +
                        "\n\n" +
                        "سيتم لاحقًا عرض بطاقة المنتسب " +
                        "والبيانات والبطاقة المالية."
                );
            }
        });

        back.setOnClickListener(v ->
                showDashboard()
        );
    }

    private LinearLayout createBaseLayout() {

        LinearLayout layout =
                new LinearLayout(this);

        layout.setOrientation(
                LinearLayout.VERTICAL
        );

        layout.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        layout.setPadding(
                25,
                30,
                25,
                30
        );

        layout.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        layout.setBackgroundColor(
                LIGHT
        );

        return layout;
    }

    private TextView createTitle(String text) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(25);
        view.setTextColor(GREEN);
        view.setTypeface(
                null,
                Typeface.BOLD
        );

        view.setGravity(
                Gravity.CENTER
        );

        view.setPadding(
                10,
                10,
                10,
                25
        );

        return view;
    }

    private TextView createText(
            String text,
            int size,
            int color
    ) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setPadding(
                10,
                10,
                10,
                20
        );

        return view;
    }

    private TextView createCard(
            String text,
            int size
    ) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(GREEN);
        view.setTypeface(
                null,
                Typeface.BOLD
        );

        view.setGravity(
                Gravity.CENTER
        );

        view.setPadding(
                20,
                25,
                20,
                25
        );

        view.setBackgroundColor(
                Color.WHITE
        );

        return view;
    }

    private TextView createSection(
            String heading,
            String text
    ) {

        TextView view =
                new TextView(this);

        view.setText(
                heading +
                "\n\n" +
                text
        );

        view.setTextSize(16);
        view.setTextColor(DARK);
        view.setPadding(
                20,
                20,
                20,
                20
        );

        view.setBackgroundColor(
                Color.WHITE
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                8,
                0,
                8
        );

        view.setLayoutParams(params);

        return view;
    }

    private Button createButton(String text) {

        Button button =
                new Button(this);

        button.setText(text);
        button.setTextSize(16);
        button.setAllCaps(false);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        58
                );

        params.setMargins(
                0,
                8,
                0,
                8
        );

        button.setLayoutParams(params);

        return button;
    }

    private void showMessage(String message) {

        Toast.makeText(
                MainActivity.this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}