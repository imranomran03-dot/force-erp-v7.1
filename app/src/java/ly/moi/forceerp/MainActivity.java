package ly.moi.forceerp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(27, 94, 32);
    private final int DARK_GREEN = Color.rgb(15, 61, 20);
    private final int GOLD = Color.rgb(198, 160, 65);
    private final int LIGHT = Color.rgb(245, 247, 248);
    private final int WHITE = Color.WHITE;
    private final int DARK = Color.rgb(30, 35, 40);
    private final int GRAY = Color.rgb(100, 110, 120);
    private final int BLUE = Color.rgb(25, 95, 160);
    private final int RED = Color.rgb(170, 45, 45);
    private final int ORANGE = Color.rgb(190, 110, 20);

    private LinearLayout root;

    private final ArrayList<Map<String, String>> personnel =
            new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        showLogin();
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void showLogin() {

        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setGravity(Gravity.CENTER_HORIZONTAL);
        page.setPadding(dp(24), dp(35), dp(24), dp(35));
        page.setBackgroundColor(LIGHT);
        page.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        TextView logo = new TextView(this);
        logo.setText("✦");
        logo.setTextSize(55);
        logo.setTextColor(GOLD);
        logo.setGravity(Gravity.CENTER);

        page.addView(logo, params(-1, 80));

        TextView title = text(
                "منظومة إدارة القوة العمومية",
                25,
                DARK_GREEN,
                true
        );
        title.setGravity(Gravity.CENTER);

        page.addView(title, params(-1, 60));

        TextView version = text(
                "V7.1",
                16,
                GOLD,
                true
        );
        version.setGravity(Gravity.CENTER);

        page.addView(version, params(-1, 35));

        addSpace(page, 20);

        EditText username = input("اسم المستخدم");
        username.setSingleLine(true);
        username.setInputType(InputType.TYPE_CLASS_TEXT);

        page.addView(username, marginParams(-1, 58, 0, 10, 0));

        EditText password = input("كلمة السر");
        password.setSingleLine(true);
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        page.addView(password, marginParams(-1, 58, 0, 15, 0));

        Button login = button(
                "دخول إلى المنظومة",
                GREEN
        );

        page.addView(login, marginParams(-1, 56, 0, 20, 0));

        TextView info = text(
                "نظام إداري متكامل لإدارة بيانات القوة العمومية",
                14,
                GRAY,
                false
        );
        info.setGravity(Gravity.CENTER);

        page.addView(info, params(-1, 50));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.length() == 0 || pass.length() == 0) {
                    showMessage(
                            "بيانات الدخول",
                            "يرجى إدخال اسم المستخدم وكلمة السر."
                    );
                    return;
                }

                // الدخول الفعلي إلى الشاشة التالية
                showDashboard();
            }
        });

        // الأهم: وضع واجهة الدخول داخل الـActivity
        setContentView(page);
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        createPage(
                "لوحة القيادة",
                "منظومة إدارة القوة العمومية V7.1"
        );

        TextView welcome = text(
                "مرحبًا بك في لوحة القيادة",
                22,
                DARK_GREEN,
                true
        );

        root.addView(welcome, marginParams(-1, 60, 0, 15, 0));

        LinearLayout stats = new LinearLayout(this);
        stats.setOrientation(LinearLayout.VERTICAL);

        root.addView(stats, marginParams(-1, -2, 0, 15, 0));

        addStat(
                stats,
                "إجمالي المنتسبين",
                String.valueOf(personnel.size()),
                GREEN
        );

        addStat(
                stats,
                "المكلفون",
                countStatus("مكلف"),
                BLUE
        );

        addStat(
                stats,
                "المنتدبون",
                countStatus("منتدب"),
                ORANGE
        );

        addStat(
                stats,
                "الموقوفون",
                countStatus("موقوف"),
                RED
        );

        addSpace(root, 10);

        TextView section = text(
                "الوصول السريع",
                19,
                DARK,
                true
        );

        root.addView(section, marginParams(-1, 45, 0, 10, 0));

        addMenuButton(
                "👤  المنتسبون",
                GREEN,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPersonnel();
                    }
                }
        );

        addMenuButton(
                "➕  إضافة منتسب جديد",
                BLUE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAddPersonnel();
                    }
                }
        );

        addMenuButton(
                "🔎  البحث عن منتسب",
                DARK_GREEN,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSearch();
                    }
                }
        );

        addMenuButton(
                "💳  البطاقة المالية",
                ORANGE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFinancial();
                    }
                }
        );

        addMenuButton(
                "📊  التقارير",
                BLUE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showReports();
                    }
                }
        );

        addMenuButton(
                "🔄  الحركة والتكليف",
                GREEN,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMovement();
                    }
                }
        );

        addMenuButton(
                "⚙️  الإعدادات",
                GRAY,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSettings();
                    }
                }
        );

        addSpace(root, 20);

        Button logout = button(
                "تسجيل الخروج",
                RED
        );

        root.addView(
                logout,
                marginParams(-1, 52, 0, 20, 0)
        );

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogin();
            }
        });
    }

    // =========================================================
    // PERSONNEL
    // =========================================================

    private void showPersonnel() {

        createPage(
                "المنتسبون",
                "إدارة سجلات القوة العمومية"
        );

        Button add = button(
                "＋ إضافة منتسب جديد",
                GREEN
        );

        root.addView(
                add,
                marginParams(-1, 54, 0, 15, 0)
        );

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPersonnel();
            }
        });

        if (personnel.size() == 0) {

            TextView empty = text(
                    "لا توجد سجلات منتسبين حتى الآن.\n\nاضغط «إضافة منتسب جديد» لإنشاء أول سجل.",
                    17,
                    GRAY,
                    false
            );

            empty.setGravity(Gravity.CENTER);
            empty.setPadding(
                    dp(15),
                    dp(40),
                    dp(15),
                    dp(40)
            );

            root.addView(
                    empty,
                    marginParams(-1, -2, 0, 10, 0)
            );

        } else {

            for (int i = 0; i < personnel.size(); i++) {

                final Map<String, String> record =
                        personnel.get(i);

                String name = record.get("الاسم الثلاثي");

                if (name == null || name.length() == 0) {
                    name = "منتسب بدون اسم";
                }

                Button item = button(
                        name + "\n" +
                        safe(record.get("الرتبة")) +
                        " — " +
                        safe(record.get("الفرع")),
                        WHITE
                );

                item.setTextColor(DARK);

                root.addView(
                        item,
                        marginParams(-1, 72, 0, 10, 0)
                );

                final int index = i;

                item.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnelCard(
                                        personnel.get(index)
                                );
                            }
                        }
                );
            }
        }

        addBackButton();
    }

    // =========================================================
    // ADD PERSONNEL
    // =========================================================

    private void showAddPersonnel() {

        createPage(
                "إضافة منتسب جديد",
                "بيانات المنتسب الأساسية"
        );

        addField("الاسم الثلاثي", false);
        addField("الرتبة", false);
        addField("اللقب", false);
        addField("الفرع", false);
        addField("اسم الأب", false);
        addField("اسم الأم", false);
        addField("الصفة الوظيفية", false);
        addField("الرقم الوطني", true);
        addField("الرقم الحسابي", true);
        addField("تاريخ الميلاد", false);
        addField("مدينة الإقامة", false);
        addField("رقم الهاتف", true);

        addField("تكليف بالمنصب", false);
        addField("نوع التكليف", false);
        addField("جهة التكليف", false);

        addField("اسم المصرف", false);
        addField("اسم فرع المصرف", false);
        addField("رقم الحساب", true);
        addField("حالة المرتب", false);
        addField("حالة العمل", false);

        addField("الحالة العسكرية الحالية", false);

        addSpace(root, 15);

        Button save = button(
                "حفظ بيانات المنتسب",
                GREEN
        );

        root.addView(
                save,
                marginParams(-1, 58, 0, 12, 0)
        );

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // هذه الشاشة في النسخة الأولى تستخدم
                // الحقول التي يمكن الوصول إليها مباشرة.
                // سيتم توسيعها بالـ116 خانة في المرحلة التالية.

                EditText name =
                        findInputByHint("الاسم الثلاثي");

                String personName =
                        name == null
                        ? ""
                        : name.getText().toString().trim();

                if (personName.length() == 0) {

                    showMessage(
                            "بيانات ناقصة",
                            "يجب إدخال الاسم الثلاثي أولًا."
                    );

                    return;
                }

                Map<String, String> record =
                        new HashMap<>();

                record.put(
                        "الاسم الثلاثي",
                        personName
                );

                saveCurrentInputs(record);

                personnel.add(record);

                showMessageAndDashboard(
                        "تم الحفظ",
                        "تم إنشاء سجل المنتسب بنجاح."
                );
            }
        });

        addBackButton();
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void showSearch() {

        createPage(
                "البحث عن منتسب",
                "بحث بالاسم أو الرقم الوطني أو الرقم الحسابي"
        );

        EditText search = input(
                "اكتب اسم المنتسب أو الرقم"
        );

        search.setSingleLine(true);

        root.addView(
                search,
                marginParams(-1, 58, 0, 12, 0)
        );

        Button searchButton = button(
                "بحث",
                GREEN
        );

        root.addView(
                searchButton,
                marginParams(-1, 52, 0, 20, 0)
        );

        LinearLayout results = new LinearLayout(this);
        results.setOrientation(
                LinearLayout.VERTICAL
        );

        root.addView(
                results,
                marginParams(-1, -2, 0, 10, 0)
        );

        searchButton.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        results.removeAllViews();

                        String q = search
                                .getText()
                                .toString()
                                .trim()
                                .toLowerCase();

                        if (q.length() == 0) {
                            showMessage(
                                    "البحث",
                                    "اكتب كلمة أو رقم للبحث."
                            );
                            return;
                        }

                        int found = 0;

                        for (int i = 0;
                             i < personnel.size();
                             i++) {

                            Map<String, String> r =
                                    personnel.get(i);

                            String name =
                                    safe(r.get("الاسم الثلاثي"))
                                    .toLowerCase();

                            String national =
                                    safe(r.get("الرقم الوطني"))
                                    .toLowerCase();

                            String account =
                                    safe(r.get("الرقم الحسابي"))
                                    .toLowerCase();

                            if (name.contains(q)
                                    || national.contains(q)
                                    || account.contains(q)) {

                                final int index = i;

                                Button result =
                                        button(
                                                safe(r.get(
                                                        "الاسم الثلاثي"
                                                )),
                                                WHITE
                                        );

                                result.setTextColor(DARK);

                                results.addView(
                                        result,
                                        marginParams(
                                                -1,
                                                60,
                                                0,
                                                8,
                                                0
                                        )
                                );

                                result.setOnClickListener(
                                        new View.OnClickListener() {
                                            @Override
                                            public void onClick(
                                                    View v) {
                                                showPersonnelCard(
                                                        personnel.get(
                                                                index
                                                        )
                                                );
                                            }
                                        }
                                );

                                found++;
                            }
                        }

                        if (found == 0) {

                            TextView no =
                                    text(
                                            "لا توجد نتائج مطابقة.",
                                            16,
                                            GRAY,
                                            false
                                    );

                            no.setGravity(
                                    Gravity.CENTER
                            );

                            results.addView(
                                    no,
                                    params(-1, 60)
                            );
                        }
                    }
                }
        );

        addBackButton();
    }

    // =========================================================
    // PERSONNEL CARD
    // =========================================================

    private void showPersonnelCard(
            Map<String, String> record) {

        createPage(
                "بطاقة المنتسب",
                "البيانات المسجلة"
        );

        addInfo(
                "الاسم الثلاثي",
                safe(record.get("الاسم الثلاثي"))
        );

        addInfo(
                "الرتبة",
                safe(record.get("الرتبة"))
        );

        addInfo(
                "الفرع",
                safe(record.get("الفرع"))
        );

        addInfo(
                "الرقم الوطني",
                safe(record.get("الرقم الوطني"))
        );

        addInfo(
                "الرقم الحسابي",
                safe(record.get("الرقم الحسابي"))
        );

        addInfo(
                "الحالة العسكرية",
                safe(record.get(
                        "الحالة العسكرية الحالية"
                ))
        );

        addInfo(
                "حالة المرتب",
                safe(record.get("حالة المرتب"))
        );

        addInfo(
                "المصرف",
                safe(record.get("اسم المصرف"))
        );

        addInfo(
                "فرع المصرف",
                safe(record.get("اسم فرع المصرف"))
        );

        addInfo(
                "رقم الحساب",
                safe(record.get("رقم الحساب"))
        );

        addInfo(
                "تكليف بالمنصب",
                safe(record.get("تكليف بالمنصب"))
        );

        addSpace(root, 15);

        Button close = button(
                "العودة إلى المنتسبين",
                GREEN
        );

        root.addView(
                close,
                marginParams(-1, 54, 0, 10, 0)
        );

        close.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPersonnel();
                    }
                }
        );
    }

    // =========================================================
    // FINANCIAL
    // =========================================================

    private void showFinancial() {

        createPage(
                "البطاقة المالية",
                "بيانات الحساب والمرتب"
        );

        addInfo(
                "إجمالي السجلات",
                String.valueOf(personnel.size())
        );

        addInfo(
                "حالة المرتب",
                "جاري / منحة / موقوف"
        );

        addInfo(
                "البيانات المالية",
                "يمكن ربطها بسجل المنتسب عند إدخال بياناته."
        );

        addBackButton();
    }

    // =========================================================
    // REPORTS
    // =========================================================

    private void showReports() {

        createPage(
                "التقارير",
                "إحصائيات فعلية من البيانات المسجلة"
        );

        addInfo(
                "إجمالي المنتسبين",
                String.valueOf(personnel.size())
        );

        addInfo(
                "المكلفون",
                countStatus("مكلف")
        );

        addInfo(
                "المنتدبون",
                countStatus("منتدب")
        );

        addInfo(
                "الموقوفون",
                countStatus("موقوف")
        );

        addBackButton();
    }

    // =========================================================
    // MOVEMENT
    // =========================================================

    private void showMovement() {

        createPage(
                "الحركة والتكليف",
                "إدارة أنواع الحركة والتكليف"
        );

        addInfo(
                "الأنواع المعتمدة",
                "تعيين\nنقل\nندب\nندب وزاري\n" +
                "ندب وكيل وزارة الداخلية\nتكليف\nعقد"
        );

        addBackButton();
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    private void showSettings() {

        createPage(
                "الإعدادات",
                "إعدادات المنظومة"
        );

        addInfo(
                "اسم التطبيق",
                "منظومة إدارة القوة العمومية"
        );

        addInfo(
                "الإصدار",
                "V7.1"
        );

        addInfo(
                "عدد السجلات المحلية",
                String.valueOf(personnel.size())
        );

        addBackButton();
    }

    // =========================================================
    // PAGE ENGINE
    // =========================================================

    private void createPage(
            String title,
            String subtitle) {

        ScrollView scroll =
                new ScrollView(this);

        scroll.setFillViewport(true);
        scroll.setBackgroundColor(LIGHT);

        root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(25)
        );

        root.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        scroll.addView(
                root,
                new ScrollView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
        );

        LinearLayout header =
                new LinearLayout(this);

        header.setOrientation(
                LinearLayout.VERTICAL
        );

        header.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView titleView =
                text(
                        title,
                        24,
                        WHITE,
                        true
                );

        TextView subView =
                text(
                        subtitle,
                        13,
                        Color.rgb(225, 235, 225),
                        false
                );

        header.addView(
                titleView,
                params(-1, 42)
        );

        header.addView(
                subView,
                params(-1, 30)
        );

        GradientDrawable headerBg =
                new GradientDrawable();

        headerBg.setColor(DARK_GREEN);
        headerBg.setCornerRadius(
                dp(18)
        );

        header.setBackground(
                headerBg
        );

        header.setPadding(
                dp(18),
                dp(8),
                dp(18),
                dp(8)
        );

        root.addView(
                header,
                marginParams(
                        -1,
                        82,
                        0,
                        20,
                        0
                )
        );

        // مهم جدًا:
        // هذه هي الواجهة التي تظهر للمستخدم.
        setContentView(scroll);
    }

    // =========================================================
    // UI HELPERS
    // =========================================================

    private TextView text(
            String value,
            float size,
            int color,
            boolean bold) {

        TextView t =
                new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setGravity(
                Gravity.CENTER_VERTICAL |
                Gravity.RIGHT
        );

        if (bold) {
            t.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );
        }

        t.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        return t;
    }

    private EditText input(String hint) {

        EditText e =
                new EditText(this);

        e.setHint(hint);
        e.setTextSize(16);
        e.setTextColor(DARK);
        e.setHintTextColor(GRAY);
        e.setSingleLine(true);
        e.setGravity(
                Gravity.RIGHT |
                Gravity.CENTER_VERTICAL
        );

        e.setPadding(
                dp(16),
                0,
                dp(16),
                0
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(
                dp(14)
        );
        bg.setStroke(
                dp(1),
                Color.rgb(215, 220, 223)
        );

        e.setBackground(bg);

        e.setTag(hint);

        return e;
    }

    private Button button(
            String title,
            int color) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setGravity(Gravity.CENTER);
        b.setMinHeight(0);
        b.setMinimumHeight(0);
        b.setPadding(
                dp(10),
                0,
                dp(10),
                0
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(
                dp(14)
        );

        b.setBackground(bg);

        if (color == WHITE) {
            b.setTextColor(DARK);
        } else {
            b.setTextColor(WHITE);
        }

        return b;
    }

    private void addMenuButton(
            String title,
            int color,
            View.OnClickListener listener) {

        Button b =
                button(title, color);

        root.addView(
                b,
                marginParams(
                        -1,
                        58,
                        0,
                        10,
                        0
                )
        );

        b.setOnClickListener(listener);
    }

    private void addStat(
            LinearLayout parent,
            String title,
            String value,
            int color) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                Gravity.CENTER
        );

        TextView number =
                text(
                        value,
                        28,
                        color,
                        true
                );

        number.setGravity(
                Gravity.CENTER
        );

        TextView label =
                text(
                        title,
                        14,
                        GRAY,
                        false
                );

        label.setGravity(
                Gravity.CENTER
        );

        card.addView(
                number,
                params(-1, 45)
        );

        card.addView(
                label,
                params(-1, 35)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(
                dp(16)
        );
        bg.setStroke(
                dp(1),
                Color.rgb(225, 228, 230)
        );

        card.setBackground(bg);

        parent.addView(
                card,
                marginParams(
                        -1,
                        90,
                        0,
                        8,
                        0
                )
        );
    }

    private void addInfo(
            String label,
            String value) {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        TextView l =
                text(
                        label,
                        13,
                        GRAY,
                        true
                );

        TextView v =
                text(
                        value,
                        16,
                        DARK,
                        false
                );

        box.addView(
                l,
                params(-1, 30)
        );

        box.addView(
                v,
                params(-1, -2)
        );

        box.setPadding(
                dp(14),
                dp(10),
                dp(14),
                dp(10)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(
                dp(14)
        );

        box.setBackground(bg);

        root.addView(
                box,
                marginParams(
                        -1,
                        -2,
                        0,
                        9,
                        0
                )
        );
    }

    private void addField(
            String hint,
            boolean number) {

        EditText e =
                input(hint);

        if (number) {
            e.setInputType(
                    InputType.TYPE_CLASS_NUMBER
            );
        }

        root.addView(
                e,
                marginParams(
                        -1,
                        58,
                        0,
                        9,
                        0
                )
        );
    }

    private EditText findInputByHint(
            String hint) {

        if (root == null) {
            return null;
        }

        for (int i = 0;
             i < root.getChildCount();
             i++) {

            View v =
                    root.getChildAt(i);

            if (v instanceof EditText) {

                EditText e =
                        (EditText) v;

                if (hint.equals(
                        String.valueOf(
                                e.getTag()
                        ))) {

                    return e;
                }
            }
        }

        return null;
    }

    private void saveCurrentInputs(
            Map<String, String> record) {

        if (root == null) {
            return;
        }

        for (int i = 0;
             i < root.getChildCount();
             i++) {

            View v =
                    root.getChildAt(i);

            if (v instanceof EditText) {

                EditText e =
                        (EditText) v;

                Object tag =
                        e.getTag();

                if (tag != null) {

                    String key =
                            String.valueOf(tag);

                    record.put(
                            key,
                            e.getText()
                                    .toString()
                                    .trim()
                    );
                }
            }
        }
    }

    private void addBackButton() {

        addSpace(root, 10);

        Button back =
                button(
                        "← العودة إلى لوحة القيادة",
                        DARK_GREEN
                );

        root.addView(
                back,
                marginParams(
                        -1,
                        54,
                        0,
                        15,
                        0
                )
        );

        back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDashboard();
                    }
                }
        );
    }

    // =========================================================
    // UTILITIES
    // =========================================================

    private String safe(String value) {

        if (value == null ||
                value.trim().length() == 0) {

            return "—";
        }

        return value;
    }

    private String countStatus(
            String status) {

        int count = 0;

        for (Map<String, String> r :
                personnel) {

            String current =
                    safe(
                            r.get(
                                    "الحالة العسكرية الحالية"
                            )
                    );

            if (current.contains(status)) {
                count++;
            }
        }

        return String.valueOf(count);
    }

    private void showMessage(
            String title,
            String message) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        "حسنًا",
                        null
                )
                .show();
    }

    private void showMessageAndDashboard(
            String title,
            String message) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        "متابعة",
                        (dialog, which) ->
                                showDashboard()
                )
                .show();
    }

    private void addSpace(
            LinearLayout parent,
            int height) {

        View space =
                new View(this);

        parent.addView(
                space,
                params(-1, height)
        );
    }

    private int dp(int value) {

        return (int)
                (value *
                getResources()
                        .getDisplayMetrics()
                        .density
                + 0.5f);
    }

    private LinearLayout.LayoutParams
    params(int width, int height) {

        return new LinearLayout.LayoutParams(
                width == -1
                        ? ViewGroup.LayoutParams.MATCH_PARENT
                        : width == -2
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : dp(width),

                height == -1
                        ? ViewGroup.LayoutParams.MATCH_PARENT
                        : height == -2
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : dp(height)
        );
    }

    private LinearLayout.LayoutParams
    marginParams(
            int width,
            int height,
            int left,
            int top,
            int right) {

        LinearLayout.LayoutParams p =
                params(width, height);

        p.setMargins(
                dp(left),
                dp(top),
                dp(right),
                dp(0)
        );

        return p;
    }
}