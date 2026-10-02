package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private int green = Color.rgb(27, 94, 32);
    private int dark = Color.rgb(35, 35, 35);
    private int gray = Color.rgb(100, 100, 100);
    private int light = Color.rgb(245, 247, 246);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    private void showLogin() {

        LinearLayout layout = baseLayout();

        TextView title = title("منظومة إدارة القوة العمومية");
        TextView version = centerText("V7.1", 18, green);

        EditText username = new EditText(this);
        username.setHint("اسم المستخدم");
        username.setSingleLine(true);

        EditText password = new EditText(this);
        password.setHint("كلمة المرور");
        password.setSingleLine(true);
        password.setInputType(
                android.text.InputType.TYPE_CLASS_TEXT |
                android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        Button login = button("دخول");

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
                Toast.makeText(
                        this,
                        "اسم المستخدم أو كلمة المرور غير صحيحة",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void showDashboard() {

        LinearLayout layout = baseLayout();

        TextView head = title("لوحة القيادة الاستراتيجية");

        TextView subtitle = centerText(
                "رئاسة جهاز مكافحة الهجرة غير الشرعية\nمنظومة إدارة القوة العمومية V7.1",
                17,
                green
        );

        TextView total = cardNumber("إجمالي القوة", "1,250");

        Button personnel = button("👥 المنتسبون");
        Button search = button("🔎 البحث عن منتسب");
        Button reports = button("📊 التقارير والإحصائيات");
        Button financial = button("💰 البطاقة المالية");
        Button courses = button("🎓 الدورات والمؤهلات");
        Button movement = button("🔄 الحركة والتنقلات");
        Button settings = button("⚙ الإعدادات");

        layout.addView(head);
        layout.addView(subtitle);
        layout.addView(total);

        layout.addView(personnel);
        layout.addView(search);
        layout.addView(reports);
        layout.addView(financial);
        layout.addView(courses);
        layout.addView(movement);
        layout.addView(settings);

        setContentView(layout);

        personnel.setOnClickListener(v -> showPersonnelCard());
        search.setOnClickListener(v -> showSearch());
        reports.setOnClickListener(v -> message("التقارير والإحصائيات"));
        financial.setOnClickListener(v -> message("البطاقة المالية"));
        courses.setOnClickListener(v -> message("الدورات والمؤهلات"));
        movement.setOnClickListener(v -> message("الحركة والتنقلات"));
        settings.setOnClickListener(v -> message("الإعدادات"));
    }

    private void showPersonnelCard() {

        ScrollView scroll = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(20, 20, 20, 30);
        layout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        layout.setBackgroundColor(light);

        TextView header = title("👤 بطاقة المنتسب");

        TextView profile = cardText(
                "الاسم: أحمد محمد علي\n" +
                "الرتبة: ملازم أول\n" +
                "الرقم العسكري: 100245\n" +
                "الفرع: طرابلس\n" +
                "الوحدة / القطاع: الإدارة العامة\n" +
                "الحالة الحالية: مستمر في العمل"
        );

        TextView financial = section(
                "💳 البطاقة المالية المصغرة",
                "الرقم المالي: 458721\n" +
                "الدرجة الوظيفية: 8\n" +
                "الحالة المالية: راتب جاري\n" +
                "حالة العمل: مستمر"
        );

        TextView movement = section(
                "🔄 التكليف والندب والحركة",
                "اتجاه الحركة: إلينا ← من جهة أخرى\n" +
                "نوع الإجراء: ندب إلينا\n" +
                "جهة التكليف: وزارة الداخلية\n" +
                "تاريخ البداية: 01/01/2026\n" +
                "تاريخ النهاية: 31/12/2026\n" +
                "المدة: 12 شهرًا\n" +
                "الحالة: ساري"
        );

        TextView alerts = section(
                "🔔 التنبيهات",
                "لا توجد تنبيهات عاجلة\n\n" +
                "يمكن لاحقًا حساب التنبيهات تلقائيًا من تواريخ الوثائق والقرارات والتكليفات."
        );

        TextView data = section(
                "📋 بيانات المنتسب",
                "عدد الخانات المرتبطة بالمنتسب: 116\n\n" +
                "البيانات الشخصية\n" +
                "البيانات الوظيفية والعسكرية\n" +
                "التعيين والترقيات\n" +
                "التكليف والندب والنقل\n" +
                "الدورات والمؤهلات\n" +
                "اللغات\n" +
                "الوثائق والتنبيهات\n" +
                "الأوسمة والجزاءات\n" +
                "الإصابات والقضايا\n" +
                "التقييم السنوي\n" +
                "السلاح واللاسلكي والمركبة والمعدات"
        );

        Button edit = button("✏ تعديل بيانات المنتسب");
        Button archive = button("📁 أرشيف المنتسب");
        Button back = button("↩ العودة إلى لوحة القيادة");

        layout.addView(header);
        layout.addView(profile);
        layout.addView(financial);
        layout.addView(movement);
        layout.addView(alerts);