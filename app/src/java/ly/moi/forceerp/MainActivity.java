package ly.moi.forceerp;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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

    // =========================================================
    // الألوان
    // =========================================================
    private static final int GREEN = Color.rgb(20, 112, 70);
    private static final int DARK_GREEN = Color.rgb(12, 73, 48);
    private static final int GOLD = Color.rgb(196, 155, 60);
    private static final int DARK = Color.rgb(30, 35, 38);
    private static final int GRAY = Color.rgb(105, 110, 115);
    private static final int LIGHT = Color.rgb(246, 248, 247);
    private static final int WHITE = Color.WHITE;
    private static final int RED = Color.rgb(190, 55, 55);
    private static final int BLUE = Color.rgb(48, 101, 170);
    private static final int ORANGE = Color.rgb(215, 130, 35);

    // الحاوية الداخلية للواجهة
    private LinearLayout root;

    // =========================================================
    // بداية التطبيق
    // =========================================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    // =========================================================
    // شاشة تسجيل الدخول
    // =========================================================
    private void showLogin() {

        root = createBaseLayout();

        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setGravity(Gravity.CENTER_HORIZONTAL);
        box.setPadding(35, 30, 35, 30);

        GradientDrawable boxBackground = new GradientDrawable();
        boxBackground.setColor(WHITE);
        boxBackground.setCornerRadius(28);
        boxBackground.setStroke(2, GOLD);
        box.setBackground(boxBackground);

        TextView title = createTitle(
                "منظومة إدارة القوة العمومية",
                25,
                DARK_GREEN
        );

        TextView version = createText(
                "V7.1",
                18,
                GOLD
        );
        version.setGravity(Gravity.CENTER);

        TextView ministry = createText(
                "رئاسة جهاز مكافحة الهجرة غير الشرعية",
                15,
                GRAY
        );
        ministry.setGravity(Gravity.CENTER);

        EditText username = new EditText(this);
        username.setHint("اسم المستخدم");
        username.setTextSize(17);
        username.setSingleLine(true);
        username.setPadding(22, 5, 22, 5);

        GradientDrawable userBg = new GradientDrawable();
        userBg.setColor(Color.rgb(248, 249, 249));
        userBg.setCornerRadius(18);
        userBg.setStroke(1, Color.LTGRAY);
        username.setBackground(userBg);

        LinearLayout.LayoutParams userParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                );

        userParams.setMargins(0, 30, 0, 15);

        EditText password = new EditText(this);
        password.setHint("الرقم السري");
        password.setTextSize(17);
        password.setSingleLine(true);
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );
        password.setPadding(22, 5, 22, 5);

        GradientDrawable passBg = new GradientDrawable();
        passBg.setColor(Color.rgb(248, 249, 249));
        passBg.setCornerRadius(18);
        passBg.setStroke(1, Color.LTGRAY);
        password.setBackground(passBg);

        LinearLayout.LayoutParams passParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                );

        passParams.setMargins(0, 0, 0, 25);

        Button login = createButton(
                "دخول إلى المنظومة",
                GREEN
        );

        login.setTextSize(18);

        login.setOnClickListener(v -> {

            String user = username.getText().toString().trim();
            String pass = password.getText().toString().trim();

            if (user.length() == 0 || pass.length() == 0) {

                Toast.makeText(
                        MainActivity.this,
                        "أدخل اسم المستخدم والرقم السري",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            showDashboard();
        });

        box.addView(title);
        box.addView(version);
        box.addView(ministry);
        box.addView(username, userParams);
        box.addView(password, passParams);
        box.addView(login);

        root.addView(
                box,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // لوحة القيادة التفاعلية
    // =========================================================
    private void showDashboard() {

        root = createBaseLayout();

        root.addView(
                createTitle(
                        "لوحة القيادة الاستراتيجية",
                        25,
                        DARK_GREEN
                )
        );

        root.addView(
                createText(
                        "رئاسة جهاز مكافحة الهجرة غير الشرعية\n" +
                        "منظومة إدارة القوة العمومية V7.1",
                        15,
                        GRAY
                )
        );

        // =====================================================
        // إجمالي القوة
        // =====================================================

        LinearLayout totalCard = createCard(GREEN);

        totalCard.addView(
                createText(
                        "إجمالي القوة",
                        17,
                        WHITE
                )
        );

        TextView totalNumber = createText(
                "1,250",
                38,
                WHITE
        );

        totalNumber.setTypeface(null, Typeface.BOLD);
        totalNumber.setGravity(Gravity.CENTER);

        totalCard.addView(totalNumber);

        TextView totalDesc = createText(
                "منتسب وموظف",
                14,
                WHITE
        );

        totalDesc.setGravity(Gravity.CENTER);

        totalCard.addView(totalDesc);

        LinearLayout.LayoutParams totalParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        145
                );

        totalParams.setMargins(0, 20, 0, 15);

        root.addView(totalCard, totalParams);

        // =====================================================
        // توزيع القوة
        // =====================================================

        root.addView(
                createSection("توزيع القوة حسب الفئة")
        );

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);

        row1.addView(
                createStatCard(
                        "الضباط",
                        "180",
                        "14.4%",
                        DARK_GREEN
                ),
                createWeightParams()
        );

        row1.addView(
                createStatCard(
                        "ضباط الصف",
                        "420",
                        "33.6%",
                        BLUE
                ),
                createWeightParams()
        );

        root.addView(row1);

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);

        row2.addView(
                createStatCard(
                        "الأفراد",
                        "530",
                        "42.4%",
                        GREEN
                ),
                createWeightParams()
        );

        row2.addView(
                createStatCard(
                        "الموظفون",
                        "120",
                        "9.6%",
                        ORANGE
                ),
                createWeightParams()
        );

        root.addView(row2);

        // =====================================================
        // الموقف الحالي
        // =====================================================

        root.addView(
                createSection("الموقف الحالي")
        );

        LinearLayout statusCard = createWhiteCard();

        statusCard.addView(
                createStatusRow(
                        "مستمرون بالعمل",
                        "82%",
                        GREEN
                )
        );

        statusCard.addView(
                createStatusRow(
                        "منتدبون",
                        "8%",
                        BLUE
                )
        );

        statusCard.addView(
                createStatusRow(
                        "مكلفون",
                        "4%",
                        ORANGE
                )
        );

        statusCard.addView(
                createStatusRow(
                        "موقوفون",
                        "2%",
                        RED
                )
        );

        statusCard.addView(
                createStatusRow(
                        "منقطعون",
                        "3%",
                        GRAY
                )
        );

        statusCard.addView(
                createStatusRow(
                        "إجازات",
                        "6%",
                        GOLD
                )
        );

        root.addView(statusCard);

        // =====================================================
        // المؤشرات والتنبيهات
        // =====================================================

        root.addView(
                createSection("المؤشرات والتنبيهات")
        );

        LinearLayout alertsRow = new LinearLayout(this);
        alertsRow.setOrientation(LinearLayout.HORIZONTAL);

        alertsRow.addView(
                createMiniCard(
                        "تنبيهات",
                        "24",
                        RED
                ),
                createMiniWeightParams()
        );

        alertsRow.addView(
                createMiniCard(
                        "بيانات ناقصة",
                        "17",
                        ORANGE
                ),
                createMiniWeightParams()
        );

        alertsRow.addView(
                createMiniCard(
                        "ترقيات قريبة",
                        "9",
                        BLUE
                ),
                createMiniWeightParams()
        );

        alertsRow.addView(
                createMiniCard(
                        "قرارات قريبة",
                        "6",
                        GOLD
                ),
                createMiniWeightParams()
        );

        root.addView(alertsRow);

        // =====================================================
        // الوصول السريع
        // =====================================================

        root.addView(
                createSection("الوصول السريع")
        );

        root.addView(
                createDashboardButton(
                        "👤  المنتسبون",
                        () -> showPersonnel()
                )
        );

        root.addView(
                createDashboardButton(
                        "🔎  البحث عن منتسب",
                        () -> showSearch()
                )
        );

        root.addView(
                createDashboardButton(
                        "📊  التقارير",
                        () -> showReports()
                )
        );

        root.addView(
                createDashboardButton(
                        "💳  البطاقات المالية",
                        () -> showFinancial()
                )
        );

        root.addView(
                createDashboardButton(
                        "🎓  الدورات والمؤهلات",
                        () -> showCourses()
                )
        );

        root.addView(
                createDashboardButton(
                        "🚔  الحركة والتكليف",
                        () -> showMovement()
                )
        );

        root.addView(
                createDashboardButton(
                        "⚙  الإعدادات",
                        () -> showSettings()
                )
        );

        // =====================================================
        // الأرشيف
        // =====================================================

        root.addView(
                createSection("الأرشيف الإلكتروني")
        );

        LinearLayout archive = createWhiteCard();

        archive.addView(
                createArchiveItem(
                        "📁",
                        "القرارات"
                )
        );

        archive.addView(
                createArchiveItem(
                        "👤",
                        "ملفات المنتسبين"
                )
        );

        archive.addView(
                createArchiveItem(
                        "🎓",
                        "الشهادات"
                )
        );

        archive.addView(
                createArchiveItem(
                        "📄",
                        "المستندات"
                )
        );

        root.addView(archive);

        // =====================================================
        // تسجيل الخروج
        // =====================================================

        Button logout = createButton(
                "تسجيل الخروج",
                DARK
        );

        logout.setOnClickListener(
                v -> showLogin()
        );

        LinearLayout.LayoutParams logoutParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        58
                );

        logoutParams.setMargins(0, 25, 0, 30);

        root.addView(logout, logoutParams);

        setContentViewFromRoot();
    }

    // =========================================================
    // المنتسبون
    // =========================================================

    private void showPersonnel() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "المنتسبون",
                        25,
                        DARK_GREEN
                )
        );

        root.addView(
                createText(
                        "إدارة بيانات القوة العمومية",
                        15,
                        GRAY
                )
        );

        root.addView(
                createInfoCard(
                        "إجمالي السجلات",
                        "1,250"
                )
        );

        root.addView(
                createDashboardButton(
                        "إضافة منتسب جديد",
                        () -> showMessage(
                                "شاشة إضافة المنتسب - سيتم ربطها بالـ116 خانة"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "بطاقة المنتسب",
                        () -> showPersonnelCard()
                )
        );

        root.addView(
                createDashboardButton(
                        "البحث عن منتسب",
                        () -> showSearch()
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // بطاقة المنتسب
    // =========================================================

    private void showPersonnelCard() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "بطاقة المنتسب",
                        24,
                        DARK_GREEN
                )
        );

        LinearLayout profile = createWhiteCard();

        TextView photo = createText(
                "صورة\nالمنتسب",
                18,
                GRAY
        );

        photo.setGravity(Gravity.CENTER);

        GradientDrawable photoBg =
                new GradientDrawable();

        photoBg.setColor(
                Color.rgb(235, 238, 237)
        );

        photoBg.setCornerRadius(20);

        photo.setBackground(photoBg);

        profile.addView(
                photo,
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        150
                )
        );

        profile.addView(
                createText(
                        "الاسم: اسم المنتسب الثلاثي",
                        17,
                        DARK
                )
        );

        profile.addView(
                createText(
                        "الرتبة: —",
                        16,
                        DARK
                )
        );

        profile.addView(
                createText(
                        "الرقم العسكري: —",
                        16,
                        DARK
                )
        );

        profile.addView(
                createText(
                        "الرقم الوطني: —",
                        16,
                        DARK
                )
        );

        profile.addView(
                createText(
                        "الحالة الحالية: مستمر",
                        16,
                        GREEN
                )
        );

        root.addView(profile);

        root.addView(
                createSection("البطاقة المالية")
        );

        root.addView(
                createInfoCard(
                        "الحالة المالية",
                        "جاري"
                )
        );

        root.addView(
                createSection(
                        "بيانات الحركة والتكليف"
                )
        );

        root.addView(
                createInfoCard(
                        "نوع التكليف",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "الاتجاه",
                        "منا / إلينا"
                )
        );

        root.addView(
                createInfoCard(
                        "جهة التكليف",
                        "—"
                )
        );

        root.addView(
                createSection(
                        "بيانات السجل"
                )
        );

        root.addView(
                createInfoCard(
                        "عدد الخانات",
                        "116 خانة"
                )
        );

        root.addView(
                createText(
                        "تشمل بيانات الهوية والرتبة والتعيين " +
                        "والترقيات والتكليف والدورات والمؤهلات " +
                        "واللغات والوثائق والتنبيهات والأوسمة " +
                        "والجزاءات والقضايا والإصابات والمركبات " +
                        "والمعدات وغيرها.",
                        14,
                        GRAY
                )
        );

        root.addView(
                createDashboardButton(
                        "تعديل بيانات المنتسب",
                        () -> showMessage(
                                "سيتم ربط التعديل بقاعدة البيانات"
                        )
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // البحث
    // =========================================================

    private void showSearch() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "البحث عن منتسب",
                        24,
                        DARK_GREEN
                )
        );

        EditText search = new EditText(this);

        search.setHint(
                "الاسم / الرقم العسكري / الرقم الوطني / الرقم الحسابي"
        );

        search.setTextSize(16);
        search.setSingleLine(true);
        search.setPadding(20, 5, 20, 5);

        GradientDrawable searchBg =
                new GradientDrawable();

        searchBg.setColor(WHITE);
        searchBg.setCornerRadius(18);
        searchBg.setStroke(1, Color.LTGRAY);

        search.setBackground(searchBg);

        LinearLayout.LayoutParams searchParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                );

        searchParams.setMargins(0, 10, 0, 10);

        root.addView(
                search,
                searchParams
        );

        Button searchButton =
                createButton(
                        "بحث",
                        GREEN
                );

        searchButton.setOnClickListener(
                v -> {

                    String value =
                            search.getText()
                                    .toString()
                                    .trim();

                    if (value.length() == 0) {

                        showMessage(
                                "أدخل قيمة للبحث"
                        );

                        return;
                    }

                    showMessage(
                            "تم تنفيذ البحث عن:\n" + value
                    );
                }
        );

        root.addView(searchButton);

        root.addView(
                createSection(
                        "البحث يمكن أن يعتمد على أي من بيانات المنتسب"
                )
        );

        root.addView(
                createText(
                        "الاسم الثلاثي\n" +
                        "الرقم العسكري\n" +
                        "الرقم الوطني\n" +
                        "الرقم الحسابي\n" +
                        "أو أكثر من حقل معاً",
                        16,
                        DARK
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // التقارير
    // =========================================================

    private void showReports() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "التقارير",
                        24,
                        DARK_GREEN
                )
        );

        root.addView(
                createDashboardButton(
                        "تقرير القوة حسب الفئة",
                        () -> showMessage(
                                "الضباط / ضباط الصف / الأفراد / الموظفون"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "تقرير الموقف الحالي",
                        () -> showMessage(
                                "مستمر / منتدب / مكلف / موقوف / منقطع / إجازة"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "تقرير الترقيات",
                        () -> showMessage(
                                "تقرير الترقيات والاستحقاقات"
                        )
                );

        root.addView(
                createDashboardButton(
                        "تقرير التكليفات",
                        () -> showMessage(
                                "التكليفات والانتدابات - منا / إلينا"
                        )
                );

        setContentViewFromRoot();
    }

    // =========================================================
    // البطاقة المالية
    // =========================================================

    private void showFinancial() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "البطاقات المالية",
                        24,
                        DARK_GREEN
                )
        );

        root.addView(
                createInfoCard(
                        "الحالة",
                        "جاري"
                )
        );

        root.addView(
                createInfoCard(
                        "الرقم المالي",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "الرقم العسكري",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "المصرف",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "الفرع",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "رقم الحساب",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "الدرجة الوظيفية",
                        "—"
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // الدورات
    // =========================================================

    private void showCourses() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "الدورات والمؤهلات",
                        24,
                        DARK_GREEN
                )
        );

        root.addView(
                createDashboardButton(
                        "الدورات التدريبية",
                        () -> showMessage("تدريبية")
                )
        );

        root.addView(
                createDashboardButton(
                        "الدورات التخصصية",
                        () -> showMessage("تخصصية")
                )
        );

        root.addView(
                createDashboardButton(
                        "الدورات التأهيلية",
                        () -> showMessage("تأهيلية")
                )
        );

        root.addView(
                createDashboardButton(
                        "ورش العمل",
                        () -> showMessage("ورش")
                )
        );

        root.addView(
                createDashboardButton(
                        "المؤهلات والشهادات",
                        () -> showMessage(
                                "المؤهلات والشهادات"
                        )
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // الحركة والتكليف
    // =========================================================

    private void showMovement() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "الحركة والتكليف",
                        24,
                        DARK_GREEN
                )
        );

        root.addView(
                createInfoCard(
                        "تكليف منا",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "تكليف إلينا",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "انتداب",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "تكليف",
                        "—"
                )
        );

        root.addView(
                createInfoCard(
                        "حركة وتنقلات",
                        "—"
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // الإعدادات
    // =========================================================

    private void showSettings() {

        root = createBaseLayout();

        addBackButton();

        root.addView(
                createTitle(
                        "الإعدادات",
                        24,
                        DARK_GREEN
                )
        );

        root.addView(
                createDashboardButton(
                        "إدارة المستخدمين",
                        () -> showMessage(
                                "إدارة المستخدمين"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "الصلاحيات",
                        () -> showMessage(
                                "الصلاحيات"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "النسخ الاحتياطي",
                        () -> showMessage(
                                "النسخ الاحتياطي"
                        )
                )
        );

        root.addView(
                createDashboardButton(
                        "إعدادات المنظومة",
                        () -> showMessage(
                                "إعدادات المنظومة"
                        )
                )
        );

        setContentViewFromRoot();
    }

    // =========================================================
    // إنشاء الواجهة الأساسية
    // =========================================================

    private LinearLayout createBaseLayout() {

        ScrollView scrollView =
                new ScrollView(this);

        scrollView.setFillViewport(true);

        LinearLayout container =
                new LinearLayout(this);

        container.setOrientation(
                LinearLayout.VERTICAL
        );

        container.setPadding(
                22,
                25,
                22,
                30
        );

        container.setBackgroundColor(LIGHT);

        scrollView.addView(
                container,
                new ScrollView.LayoutParams(
                        ScrollView.LayoutParams.MATCH_PARENT,
                        ScrollView.LayoutParams.WRAP_CONTENT
                )
        );

        /*
         * نحتفظ بالـLinearLayout الداخلي في root
         * حتى نضيف إليه جميع العناصر.
         */
        root = container;

        /*
         * نحفظ الـScrollView كـTag حتى نستعمله
         * عند setContentView.
         */
        root.setTag(scrollView);

        return root;
    }

    // =========================================================
    // وضع الـScrollView فعلياً كواجهة التطبيق
    // =========================================================

    private void setContentViewFromRoot() {

        Object tag = root.getTag();

        if (tag instanceof ScrollView) {

            ScrollView scrollView =
                    (ScrollView) tag;

            setContentView(scrollView);

        } else {

            setContentView(root);
        }
    }

    // =========================================================
    // زر الرجوع داخل الصفحات
    // =========================================================

    private void addBackButton() {

        Button back =
                createButton(
                        "← رجوع إلى لوحة القيادة",
                        DARK_GREEN
                );

        back.setOnClickListener(
                v -> showDashboard()
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        55
                );

        params.setMargins(
                0,
                0,
                0,
                15
        );

        root.addView(
                back,
                params
        );
    }

    // =========================================================
    // عنوان
    // =========================================================

    private TextView createTitle(
            String text,
            int size,
            int color
    ) {

        TextView t =
                new TextView(this);

        t.setText(text);
        t.setTextSize(size);
        t.setTextColor(color);
        t.setTypeface(
                null,
                Typeface.BOLD
        );

        t.setGravity(
                Gravity.CENTER
        );

        t.setPadding(
                5,
                8,
                5,
                8
        );

        return t;
    }

    // =========================================================
    // نص
    // =========================================================

    private TextView createText(
            String text,
            int size,
            int color
    ) {

        TextView t =
                new TextView(this);

        t.setText(text);
        t.setTextSize(size);
        t.setTextColor(color);

        t.setPadding(
                5,
                8,
                5,
                8
        );

        return t;
    }

    // =========================================================
    // كرت ملون
    // =========================================================

    private LinearLayout createCard(
            int color
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setGravity(
                Gravity.CENTER
        );

        card.setPadding(
                18,
                15,
                18,
                15
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(28);

        card.setBackground(bg);

        return card;
    }

    // =========================================================
    // كرت أبيض
    // =========================================================

    private LinearLayout createWhiteCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                18,
                15,
                18,
                15
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(22);
        bg.setStroke(
                1,
                Color.rgb(225, 228, 226)
        );

        card.setBackground(bg);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                8,
                0,
                12
        );

        card.setLayoutParams(params);

        /*
         * مهم:
         * هذه الدالة لا تضيف البطاقة إلى root.
         * المستدعي هو الذي يضيفها.
         */
        return card;
    }

    // =========================================================
    // عنوان قسم
    // =========================================================

    private TextView createSection(
            String text
    ) {

        TextView t =
                createText(
                        text,
                        18,
                        DARK_GREEN
                );

        t.setTypeface(
                null,
                Typeface.BOLD
        );

        t.setPadding(
                5,
                22,
                5,
                10
        );

        return t;
    }

    // =========================================================
    // بطاقة إحصائية
    // =========================================================

    private LinearLayout createStatCard(
            String title,
            String number,
            String percent,
            int color
    ) {

        LinearLayout card =
                createCard(color);

        TextView titleView =
                createText(
                        title,
                        15,
                        WHITE
                );

        titleView.setGravity(
                Gravity.CENTER
        );

        TextView numberView =
                createText(
                        number,
                        27,
                        WHITE
                );

        numberView.setTypeface(
                null,
                Typeface.BOLD
        );

        numberView.setGravity(
                Gravity.CENTER
        );

        TextView percentView =
                createText(
                        percent,
                        14,
                        WHITE
                );

        percentView.setGravity(
                Gravity.CENTER
        );

        card.addView(titleView);
        card.addView(numberView);
        card.addView(percentView);

        return card;
    }

    // =========================================================
    // بطاقة صغيرة
    // =========================================================

    private LinearLayout createMiniCard(
            String title,
            String number,
            int color
    ) {

        LinearLayout card =
                createCard(color);

        TextView titleView =
                createText(
                        title,
                        12,
                        WHITE
                );

        titleView.setGravity(
                Gravity.CENTER
        );

        TextView numberView =
                createText(
                        number,
                        25,
                        WHITE
                );

        numberView.setTypeface(
                null,
                Typeface.BOLD
        );

        numberView.setGravity(
                Gravity.CENTER
        );

        card.addView(titleView);
        card.addView(numberView);

        return card;
    }

    // =========================================================
    // صف حالة
    // =========================================================

    private LinearLayout createStatusRow(
            String title,
            String value,
            int color
    ) {

        LinearLayout row =
                new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        row.setPadding(
                8,
                12,
                8,
                12
        );

        TextView name =
                createText(
                        title,
                        15,
                        DARK
                );

        TextView number =
                createText(
                        value,
                        16,
                        color
                );

        number.setTypeface(
                null,
                Typeface.BOLD
        );

        number.setGravity(
                Gravity.CENTER
        );

        row.addView(
                name,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        row.addView(
                number,
                new LinearLayout.LayoutParams(
                        80,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );

        return row;
    }

    // =========================================================
    // زر لوحة القيادة
    // =========================================================

    private Button createDashboardButton(
            String text,
            final Runnable action
    ) {

        Button button =
                createButton(
                        text,
                        WHITE
                );

        button.setTextColor(
                DARK_GREEN
        );

        button.setGravity(
                Gravity.CENTER_VERTICAL |
                Gravity.RIGHT
        );

        button.setOnClickListener(
                v -> action.run()
        );

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        60
                );

        params.setMargins(
                0,
                5,
                0,
                5
        );

        button.setLayoutParams(params);

        return button;
    }

    // =========================================================
    // زر عام
    // =========================================================

    private Button createButton(
            String text,
            int color
    ) {

        Button button =
                new Button(this);

        button.setText(text);
        button.setTextSize(16);

        button.setTypeface(
                null,
                Typeface.BOLD
        );

        button.setAllCaps(false);

        if (color == WHITE) {

            button.setTextColor(DARK);

        } else {

            button.setTextColor(WHITE);
        }

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(18);

        if (color == WHITE) {

            bg.setStroke(
                    1,
                    Color.rgb(220, 225, 222)
            );
        }

        button.setBackground(bg);

        return button;
    }

    // =========================================================
    // بطاقة معلومات
    // =========================================================

    private LinearLayout createInfoCard(
            String title,
            String value
    ) {

        LinearLayout card =
                createWhiteCard();

        TextView titleView =
                createText(
                        title,
                        14,
                        GRAY
                );

        TextView valueView =
                createText(
                        value,
                        18,
                        DARK_GREEN
                );

        valueView.setTypeface(
                null,
                Typeface.BOLD
        );

        card.addView(titleView);
        card.addView(valueView);

        return card;
    }

    // =========================================================
    // عنصر الأرشيف
    // =========================================================

    private TextView createArchiveItem(
            String icon,
            String text
    ) {

        TextView item =
                createText(
                        icon + "    " + text,
                        16,
                        DARK
                );

        item.setPadding(
                12,
                16,
                12,
                16
        );

        item.setOnClickListener(
                v -> showMessage(
                        "الأرشيف: " + text
                )
        );

        return item;
    }

    // =========================================================
    // معاملات صف الإحصائيات
    // =========================================================

    private LinearLayout.LayoutParams
    createWeightParams() {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        125,
                        1
                );

        params.setMargins(
                5,
                5,
                5,
                5
        );

        return params;
    }

    // =========================================================
    // معاملات البطاقات الصغيرة
    // =========================================================

    private LinearLayout.LayoutParams
    createMiniWeightParams() {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        105,
                        1
                );

        params.setMargins(
                3,
                3,
                3,
                3
        );

        return params;
    }

    // =========================================================
    // رسالة
    // =========================================================

    private void showMessage(
            String message
    ) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }
}