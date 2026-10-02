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
import android.widget.Space;

public class MainActivity extends Activity {

    private final int GREEN = Color.rgb(20, 92, 55);
    private final int DARK_GREEN = Color.rgb(10, 55, 34);
    private final int GOLD = Color.rgb(196, 155, 61);
    private final int RED = Color.rgb(190, 45, 45);
    private final int BLUE = Color.rgb(35, 92, 150);
    private final int ORANGE = Color.rgb(210, 125, 35);
    private final int PURPLE = Color.rgb(105, 70, 150);
    private final int GRAY = Color.rgb(105, 105, 105);
    private final int LIGHT = Color.rgb(246, 248, 247);
    private final int WHITE = Color.WHITE;
    private final int DARK = Color.rgb(35, 35, 35);

    private ScrollView scrollView;
    private LinearLayout root;

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
        page.setGravity(Gravity.CENTER);
        page.setPadding(35, 30, 35, 30);
        page.setBackgroundColor(LIGHT);

        TextView logo = new TextView(this);
        logo.setText("قوة العمومية");
        logo.setTextSize(30);
        logo.setTextColor(GREEN);
        logo.setTypeface(null, Typeface.BOLD);
        logo.setGravity(Gravity.CENTER);

        page.addView(logo, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        TextView title = new TextView(this);
        title.setText("منظومة إدارة القوة العمومية");
        title.setTextSize(24);
        title.setTextColor(DARK_GREEN);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams titleParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
        titleParams.setMargins(0, 12, 0, 5);
        page.addView(title, titleParams);

        TextView version = new TextView(this);
        version.setText("V7.1");
        version.setTextSize(18);
        version.setTextColor(GOLD);
        version.setGravity(Gravity.CENTER);

        page.addView(version);

        Space space = new Space(this);
        page.addView(space, new LinearLayout.LayoutParams(
                1, 35
        ));

        EditText username = createInput("اسم المستخدم");
        username.setInputType(InputType.TYPE_CLASS_TEXT);

        page.addView(username, matchParams(12));

        EditText password = createInput("الرقم السري");
        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        page.addView(password, matchParams(12));

        Button login = createMainButton("دخول إلى المنظومة");

        page.addView(login, matchParams(18));

        TextView info = new TextView(this);
        info.setText(
                "رئاسة جهاز مكافحة الهجرة غير الشرعية\n" +
                "نظام إدارة القوة والمنتسبين"
        );
        info.setTextSize(13);
        info.setTextColor(GRAY);
        info.setGravity(Gravity.CENTER);
        page.addView(info, matchParams(20));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty() || pass.isEmpty()) {
                    showMessage("يرجى إدخال اسم المستخدم والرقم السري");
                    return;
                }

                showDashboard();
            }
        });

        setContentView(page);
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private void showDashboard() {

        createScrollablePage();

        addHeader(
                "لوحة القيادة الاستراتيجية",
                "رئاسة جهاز مكافحة الهجرة غير الشرعية\n" +
                "منظومة إدارة القوة العمومية V7.1"
        );

        TextView totalTitle = createText(
                "إجمالي القوة الحالية",
                17,
                DARK_GREEN
        );
        totalTitle.setGravity(Gravity.CENTER);
        root.addView(totalTitle, matchParams(8));

        TextView total = createText(
                "1,250",
                42,
                GREEN
        );
        total.setTypeface(null, Typeface.BOLD);
        total.setGravity(Gravity.CENTER);
        root.addView(total, matchParams(2));

        TextView totalSub = createText(
                "منتسب وموظف",
                14,
                GRAY
        );
        totalSub.setGravity(Gravity.CENTER);
        root.addView(totalSub, matchParams(12));

        // -----------------------------------------------------
        // CATEGORY CARDS
        // -----------------------------------------------------

        LinearLayout row1 = new LinearLayout(this);
        row1.setOrientation(LinearLayout.HORIZONTAL);
        row1.setGravity(Gravity.CENTER);

        row1.addView(
                createStatCard(
                        "الضباط",
                        "180",
                        "14.4%",
                        GREEN
                ),
                weightParams()
        );

        row1.addView(
                createStatCard(
                        "ضباط الصف",
                        "420",
                        "33.6%",
                        BLUE
                ),
                weightParams()
        );

        root.addView(row1, matchParams(8));

        LinearLayout row2 = new LinearLayout(this);
        row2.setOrientation(LinearLayout.HORIZONTAL);
        row2.setGravity(Gravity.CENTER);

        row2.addView(
                createStatCard(
                        "الأفراد",
                        "530",
                        "42.4%",
                        ORANGE
                ),
                weightParams()
        );

        row2.addView(
                createStatCard(
                        "الموظفون",
                        "120",
                        "9.6%",
                        PURPLE
                ),
                weightParams()
        );

        root.addView(row2, matchParams(12));

        // -----------------------------------------------------
        // CURRENT STATUS
        // -----------------------------------------------------

        root.addView(
                createSectionTitle(
                        "الموقف الحالي للقوة"
                ),
                matchParams(8)
        );

        root.addView(
                createStatusCard(
                        "مستمرون",
                        "82%",
                        GREEN
                ),
                matchParams(5)
        );

        root.addView(
                createStatusCard(
                        "مكلفون",
                        "8%",
                        BLUE
                ),
                matchParams(5)
        );

        root.addView(
                createStatusCard(
                        "منتدبون",
                        "4%",
                        PURPLE
                ),
                matchParams(5)
        );

        root.addView(
                createStatusCard(
                        "موقوفون",
                        "2%",
                        RED
                ),
                matchParams(5)
        );

        root.addView(
                createStatusCard(
                        "إجازة",
                        "3%",
                        ORANGE
                ),
                matchParams(5)
        );

        root.addView(
                createStatusCard(
                        "دورات",
                        "6%",
                        GOLD
                ),
                matchParams(12)
        );

        // -----------------------------------------------------
        // ALERTS
        // -----------------------------------------------------

        root.addView(
                createSectionTitle(
                        "مركز التنبيهات والمؤشرات"
                ),
                matchParams(8)
        );

        LinearLayout alertsRow =
                new LinearLayout(this);

        alertsRow.setOrientation(
                LinearLayout.HORIZONTAL
        );

        alertsRow.addView(
                createAlertCard(
                        "التنبيهات",
                        "24",
                        RED
                ),
                weightParams()
        );

        alertsRow.addView(
                createAlertCard(
                        "بيانات ناقصة",
                        "17",
                        ORANGE
                ),
                weightParams()
        );

        root.addView(
                alertsRow,
                matchParams(7)
        );

        LinearLayout alertsRow2 =
                new LinearLayout(this);

        alertsRow2.setOrientation(
                LinearLayout.HORIZONTAL
        );

        alertsRow2.addView(
                createAlertCard(
                        "ترقيات قريبة",
                        "9",
                        BLUE
                ),
                weightParams()
        );

        alertsRow2.addView(
                createAlertCard(
                        "قرارات قريبة",
                        "6",
                        PURPLE
                ),
                weightParams()
        );

        root.addView(
                alertsRow2,
                matchParams(12)
        );

        // -----------------------------------------------------
        // QUICK ACCESS
        // -----------------------------------------------------

        root.addView(
                createSectionTitle(
                        "الوصول السريع"
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "👤  المنتسبون",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnel();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "🔎  البحث الذكي",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showSearch();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "📊  التقارير",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showReports();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "💳  البطاقة المالية",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showFinancial();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "🎓  الدورات والمؤهلات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showCourses();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "🚘  الحركة والتكليفات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMovement();
                            }
                        }
                ),
                matchParams(6)
        );

        root.addView(
                createDashboardButton(
                        "⚙  الإعدادات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showSettings();
                            }
                        }
                ),
                matchParams(6)
        );

        // -----------------------------------------------------
        // ARCHIVE
        // -----------------------------------------------------

        root.addView(
                createSectionTitle(
                        "الأرشيف الإلكتروني"
                ),
                matchParams(12)
        );

        root.addView(
                createArchiveCard(
                        "📁 قرارات",
                        "أرشيف القرارات والتكليفات والترقيات"
                ),
                matchParams(6)
        );

        root.addView(
                createArchiveCard(
                        "👤 ملفات المنتسبين",
                        "الملفات والمستندات الشخصية"
                ),
                matchParams(6)
        );

        root.addView(
                createArchiveCard(
                        "🎓 الشهادات",
                        "الدورات والمؤهلات والشهادات"
                ),
                matchParams(6)
        );

        root.addView(
                createArchiveCard(
                        "📄 المستندات",
                        "المستندات والوثائق الرسمية"
                ),
                matchParams(12)
        );

        addFooter();

        setContentView(scrollView);
    }

    // =========================================================
    // PERSONNEL
    // =========================================================

    private void showPersonnel() {

        createScrollablePage();

        addHeader(
                "إدارة المنتسبين",
                "بطاقات المنتسبين والبيانات الأساسية"
        );

        root.addView(
                createDashboardButton(
                        "➕ إضافة منتسب جديد",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "شاشة إضافة منتسب\n" +
                                        "تتضمن جميع الحقول الـ116"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "🔎 البحث عن منتسب",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showSearch();
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createPersonnelPreview(
                        "عمران محمد مثال",
                        "رائد",
                        "القوة العمومية",
                        "مستمر"
                ),
                matchParams(12)
        );

        root.addView(
                createDashboardButton(
                        "👁 فتح بطاقة المنتسب",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnelCard();
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // PERSONNEL CARD
    // =========================================================

    private void showPersonnelCard() {

        createScrollablePage();

        addHeader(
                "بطاقة المنتسب",
                "البيانات الشخصية والوظيفية والمالية"
        );

        root.addView(
                createProfileCard(),
                matchParams(12)
        );

        root.addView(
                createSectionTitle(
                        "البطاقة المالية المختصرة"
                ),
                matchParams(8)
        );

        root.addView(
                createMiniFinancialCard(),
                matchParams(12)
        );

        root.addView(
                createSectionTitle(
                        "الحركة والتكليف"
                ),
                matchParams(8)
        );

        root.addView(
                createMovementSummary(),
                matchParams(12)
        );

        root.addView(
                createSectionTitle(
                        "التنبيهات"
                ),
                matchParams(8)
        );

        root.addView(
                createAlertCard(
                        "تنبيه البيانات",
                        "2",
                        ORANGE
                ),
                matchParams(10)
        );

        root.addView(
                createSectionTitle(
                        "جميع بيانات المنتسب - 116 خانة"
                ),
                matchParams(8)
        );

        String[] fields = getAll116Fields();

        for (int i = 0; i < fields.length; i++) {

            root.addView(
                    createDataRow(
                            (i + 1) + ". " + fields[i],
                            "غير مدخل"
                    ),
                    matchParams(3)
            );
        }

        root.addView(
                createDashboardButton(
                        "✏ تعديل بيانات المنتسب",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "سيتم فتح نموذج تعديل البيانات"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void showSearch() {

        createScrollablePage();

        addHeader(
                "البحث الذكي",
                "البحث باستخدام حقل واحد أو عدة حقول معًا"
        );

        EditText name =
                createInput("الاسم الثلاثي");

        EditText military =
                createInput("الرقم العسكري / الحسابي");

        EditText national =
                createInput("الرقم الوطني");

        EditText financial =
                createInput("الرقم المالي");

        EditText rank =
                createInput("الرتبة");

        EditText branch =
                createInput("الفرع / الإدارة");

        EditText city =
                createInput("مدينة الإقامة");

        root.addView(name, matchParams(5));
        root.addView(military, matchParams(5));
        root.addView(national, matchParams(5));
        root.addView(financial, matchParams(5));
        root.addView(rank, matchParams(5));
        root.addView(branch, matchParams(5));
        root.addView(city, matchParams(8));

        root.addView(
                createDashboardButton(
                        "🔎 تنفيذ البحث",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String query =
                                        "بحث متعدد الحقول\n\n" +
                                        "الاسم: " +
                                        name.getText().toString() +
                                        "\nالرقم العسكري: " +
                                        military.getText().toString() +
                                        "\nالرقم الوطني: " +
                                        national.getText().toString();

                                showMessage(query);
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createPersonnelPreview(
                        "نتيجة البحث",
                        "رائد",
                        "القوة العمومية",
                        "مستمر"
                ),
                matchParams(10)
        );

        root.addView(
                createDashboardButton(
                        "👁 فتح بطاقة النتيجة",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnelCard();
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // FINANCIAL
    // =========================================================

    private void showFinancial() {

        createScrollablePage();

        addHeader(
                "البطاقة المالية",
                "البيانات المالية والوظيفية للمنتسب"
        );

        root.addView(
                createMiniFinancialCard(),
                matchParams(12)
        );

        root.addView(
                createDataRow(
                        "الرقم المالي",
                        "FIN-001250"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "الرقم الحسابي",
                        "AC-001250"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "المصرف",
                        "مصرف الجمهورية"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "الفرع",
                        "بنغازي"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "الدرجة الوظيفية",
                        "الدرجة 10"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "حالة المرتب",
                        "جاري"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "حالة العمل",
                        "مستمر"
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "👤 فتح بطاقة المنتسب",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnelCard();
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // COURSES
    // =========================================================

    private void showCourses() {

        createScrollablePage();

        addHeader(
                "الدورات والمؤهلات",
                "السجل التدريبي والتعليمي"
        );

        root.addView(
                createDataRow(
                        "الدورات التدريبية",
                        "12"
                ),
                matchParams(6)
        );

        root.addView(
                createDataRow(
                        "الدورات التخصصية",
                        "5"
                ),
                matchParams(6)
        );

        root.addView(
                createDataRow(
                        "الدورات التأهيلية",
                        "3"
                ),
                matchParams(6)
        );

        root.addView(
                createDataRow(
                        "ورش العمل",
                        "4"
                ),
                matchParams(6)
        );

        root.addView(
                createDataRow(
                        "المؤهل العلمي",
                        "ليسانس"
                ),
                matchParams(6)
        );

        root.addView(
                createDataRow(
                        "التخصص",
                        "إدارة وأمن"
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "➕ إضافة دورة / مؤهل",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "إضافة دورة أو مؤهل جديد"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // MOVEMENT
    // =========================================================

    private void showMovement() {

        createScrollablePage();

        addHeader(
                "الحركة والتكليفات",
                "متابعة التنقلات والانتدابات والتكليفات"
        );

        root.addView(
                createStatusCard(
                        "تكليفات منا",
                        "18",
                        GREEN
                ),
                matchParams(8)
        );

        root.addView(
                createStatusCard(
                        "تكليفات إلينا",
                        "11",
                        BLUE
                ),
                matchParams(8)
        );

        root.addView(
                createDataRow(
                        "اتجاه التكليف",
                        "منا / إلينا"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "نوع التكليف",
                        "تكليف / انتداب"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "جهة التكليف",
                        "جهة أمنية"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "مكان التكليف",
                        "بنغازي"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "تاريخ البداية",
                        "01/01/2026"
                ),
                matchParams(5)
        );

        root.addView(
                createDataRow(
                        "تاريخ النهاية",
                        "31/12/2026"
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "➕ إضافة حركة / تكليف",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "إضافة حركة جديدة\n" +
                                        "الاتجاه: منا / إلينا"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // REPORTS
    // =========================================================

    private void showReports() {

        createScrollablePage();

        addHeader(
                "التقارير الذكية",
                "تقارير القوة والمنتسبين والموقف الحالي"
        );

        root.addView(
                createDashboardButton(
                        "📊 تقرير القوة حسب الفئة",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "الضباط: 180\n" +
                                        "ضباط الصف: 420\n" +
                                        "الأفراد: 530\n" +
                                        "الموظفون: 120"
                                );
                            }
                        }
                ),
                matchParams(7)
        );

        root.addView(
                createDashboardButton(
                        "📈 تقرير الموقف الحالي",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "مستمرون 82%\n" +
                                        "مكلفون 8%\n" +
                                        "منتدبون 4%\n" +
                                        "موقوفون 2%\n" +
                                        "إجازة 3%\n" +
                                        "دورات 6%"
                                );
                            }
                        }
                ),
                matchParams(7)
        );

        root.addView(
                createDashboardButton(
                        "⬆ تقرير الترقيات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "تقرير الترقيات والاستحقاقات"
                                );
                            }
                        }
                ),
                matchParams(7)
        );

        root.addView(
                createDashboardButton(
                        "🚘 تقرير التكليفات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "تقرير التكليفات والانتدابات"
                                );
                            }
                        }
                ),
                matchParams(7)
        );

        root.addView(
                createDashboardButton(
                        "⚠ تقرير التنبيهات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "24 تنبيه\n" +
                                        "17 بيانات ناقصة\n" +
                                        "9 ترقيات قريبة\n" +
                                        "6 قرارات قريبة"
                                );
                            }
                        }
                ),
                matchParams(7)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    private void showSettings() {

        createScrollablePage();

        addHeader(
                "الإعدادات",
                "إدارة النظام والمستخدمين"
        );

        root.addView(
                createDashboardButton(
                        "👤 المستخدمون والصلاحيات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "مدير النظام\n" +
                                        "مدخل بيانات\n" +
                                        "مشاهد"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "🔐 الأمان وسجل العمليات",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "سجل العمليات والأمان"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "💾 النسخ الاحتياطي",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "إدارة النسخ الاحتياطية"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createDashboardButton(
                        "ℹ معلومات المنظومة",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMessage(
                                        "منظومة إدارة القوة العمومية\n" +
                                        "V7.1"
                                );
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // UI HELPERS
    // =========================================================

    private void createScrollablePage() {

        scrollView = new ScrollView(this);
        scrollView.setFillViewport(true);
        scrollView.setBackgroundColor(LIGHT);

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(18, 18, 18, 25);
        root.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        scrollView.addView(
                root,
                new ScrollView.LayoutParams(
                        ScrollView.LayoutParams.MATCH_PARENT,
                        ScrollView.LayoutParams.WRAP_CONTENT
                )
        );
    }

    private void addHeader(
            String titleText,
            String subtitleText
    ) {

        LinearLayout header =
                new LinearLayout(this);

        header.setOrientation(
                LinearLayout.VERTICAL
        );

        header.setGravity(Gravity.CENTER);
        header.setPadding(15, 22, 15, 22);
        header.setBackground(
                roundedBackground(
                        DARK_GREEN,
                        22
                )
        );

        TextView title =
                createText(
                        titleText,
                        25,
                        WHITE
                );

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        title.setGravity(
                Gravity.CENTER
        );

        header.addView(
                title,
                matchParams(2)
        );

        TextView subtitle =
                createText(
                        subtitleText,
                        13,
                        Color.rgb(225, 235, 228)
                );

        subtitle.setGravity(
                Gravity.CENTER
        );

        header.addView(
                subtitle,
                matchParams(5)
        );

        root.addView(
                header,
                matchParams(10)
        );
    }

    private TextView createSectionTitle(
            String text
    ) {

        TextView view =
                createText(
                        text,
                        19,
                        DARK_GREEN
                );

        view.setTypeface(
                null,
                Typeface.BOLD
        );

        view.setPadding(
                8,
                12,
                8,
                8
        );

        return view;
    }

    private TextView createText(
            String text,
            float size,
            int color
    ) {

        TextView view =
                new TextView(this);

        view.setText(text);
        view.setTextSize(size);
        view.setTextColor(color);
        view.setGravity(
                Gravity.CENTER_VERTICAL
        );

        return view;
    }

    private EditText createInput(
            String hint
    ) {

        EditText input =
                new EditText(this);

        input.setHint(hint);
        input.setTextSize(16);
        input.setTextColor(DARK);
        input.setHintTextColor(GRAY);
        input.setSingleLine(true);
        input.setPadding(
                18,
                12,
                18,
                12
        );

        input.setBackground(
                roundedBackground(
                        WHITE,
                        16
                )
        );

        return input;
    }

    private Button createMainButton(
            String text
    ) {

        Button button =
                new Button(this);

        button.setText(text);
        button.setTextSize(18);
        button.setTextColor(WHITE);
        button.setTypeface(
                null,
                Typeface.BOLD
        );
        button.setGravity(
                Gravity.CENTER
        );
        button.setAllCaps(false);
        button.setPadding(
                10,
                8,
                10,
                8
        );

        button.setBackground(
                roundedBackground(
                        GREEN,
                        18
                )
        );

        return button;
    }

    private Button createDashboardButton(
            String text,
            View.OnClickListener listener
    ) {

        Button button =
                createMainButton(text);

        button.setTextSize(16);
        button.setOnClickListener(listener);

        return button;
    }

    private Button createBackButton() {

        return createDashboardButton(
                "↩ العودة إلى لوحة القيادة",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDashboard();
                    }
                }
        );
    }

    private LinearLayout createStatCard(
            String title,
            String number,
            String percentage,
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
                8,
                14,
                8,
                14
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        20
                )
        );

        TextView t =
                createText(
                        title,
                        15,
                        color
                );

        t.setTypeface(
                null,
                Typeface.BOLD
        );
        t.setGravity(Gravity.CENTER);

        card.addView(
                t,
                matchParams(2)
        );

        TextView n =
                createText(
                        number,
                        30,
                        DARK
                );

        n.setTypeface(
                null,
                Typeface.BOLD
        );
        n.setGravity(Gravity.CENTER);

        card.addView(
                n,
                matchParams(2)
        );

        TextView p =
                createText(
                        percentage,
                        14,
                        color
                );

        p.setTypeface(
                null,
                Typeface.BOLD
        );
        p.setGravity(Gravity.CENTER);

        card.addView(
                p,
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createStatusCard(
            String title,
            String value,
            int color
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.HORIZONTAL
        );

        card.setGravity(
                Gravity.CENTER_VERTICAL
        );

        card.setPadding(
                16,
                13,
                16,
                13
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        16
                )
        );

        TextView titleView =
                createText(
                        title,
                        16,
                        DARK
                );

        titleView.setTypeface(
                null,
                Typeface.BOLD
        );

        TextView valueView =
                createText(
                        value,
                        20,
                        color
                );

        valueView.setTypeface(
                null,
                Typeface.BOLD
        );

        valueView.setGravity(
                Gravity.CENTER
        );

        card.addView(
                titleView,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        card.addView(
                valueView,
                new LinearLayout.LayoutParams(
                        90,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );

        return card;
    }

    private LinearLayout createAlertCard(
            String title,
            String number,
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
                8,
                14,
                8,
                14
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        18
                )
        );

        TextView numberView =
                createText(
                        number,
                        29,
                        color
                );

        numberView.setTypeface(
                null,
                Typeface.BOLD
        );
        numberView.setGravity(
                Gravity.CENTER
        );

        card.addView(
                numberView,
                matchParams(2)
        );

        TextView titleView =
                createText(
                        title,
                        14,
                        DARK
                );

        titleView.setGravity(
                Gravity.CENTER
        );

        card.addView(
                titleView,
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createArchiveCard(
            String title,
            String description
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                16,
                14,
                16,
                14
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        18
                )
        );

        TextView titleView =
                createText(
                        title,
                        17,
                        GREEN
                );

        titleView.setTypeface(
                null,
                Typeface.BOLD
        );

        card.addView(
                titleView,
                matchParams(2)
        );

        TextView descView =
                createText(
                        description,
                        13,
                        GRAY
                );

        card.addView(
                descView,
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createPersonnelPreview(
            String name,
            String rank,
            String department,
            String status
    ) {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                18,
                18,
                18,
                18
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        20
                )
        );

        TextView nameView =
                createText(
                        "👤 " + name,
                        20,
                        DARK_GREEN
                );

        nameView.setTypeface(
                null,
                Typeface.BOLD
        );

        card.addView(
                nameView,
                matchParams(3)
        );

        card.addView(
                createDataRow(
                        "الرتبة",
                        rank
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الجهة",
                        department
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الحالة",
                        status
                ),
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createProfileCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                18,
                18,
                18,
                18
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        20
                )
        );

        TextView title =
                createText(
                        "👤 عمران محمد مثال",
                        22,
                        DARK_GREEN
                );

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        card.addView(
                title,
                matchParams(5)
        );

        card.addView(
                createDataRow(
                        "الرتبة",
                        "رائد"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الرقم العسكري",
                        "001250"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الرقم الوطني",
                        "غير مدخل"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الفرع",
                        "القوة العمومية"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الحالة",
                        "مستمر"
                ),
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createMiniFinancialCard() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                18,
                18,
                18,
                18
        );

        card.setBackground(
                roundedBackground(
                        DARK_GREEN,
                        20
                )
        );

        TextView title =
                createText(
                        "💳 البطاقة المالية",
                        20,
                        WHITE
                );

        title.setTypeface(
                null,
                Typeface.BOLD
        );

        card.addView(
                title,
                matchParams(4)
        );

        TextView data =
                createText(
                        "الرقم المالي: FIN-001250\n" +
                        "المصرف: مصرف الجمهورية\n" +
                        "الفرع: بنغازي\n" +
                        "الحالة: مرتب جاري",
                        15,
                        Color.rgb(230, 240, 232)
                );

        card.addView(
                data,
                matchParams(4)
        );

        return card;
    }

    private LinearLayout createMovementSummary() {

        LinearLayout card =
                new LinearLayout(this);

        card.setOrientation(
                LinearLayout.VERTICAL
        );

        card.setPadding(
                16,
                16,
                16,
                16
        );

        card.setBackground(
                roundedBackground(
                        WHITE,
                        18
                )
        );

        card.addView(
                createDataRow(
                        "نوع التكليف",
                        "انتداب"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الاتجاه",
                        "إلينا"
                ),
                matchParams(2)
        );

        card.addView(
                createDataRow(
                        "الجهة",
                        "جهة أمنية"
                ),
                matchParams(2)
        );

        return card;
    }

    private LinearLayout createDataRow(
            String label,
            String value
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
                12,
                8,
                12,
                8
        );

        TextView labelView =
                createText(
                        label,
                        14,
                        GRAY
                );

        TextView valueView =
                createText(
                        value,
                        15,
                        DARK
                );

        valueView.setTypeface(
                null,
                Typeface.BOLD
        );

        row.addView(
                labelView,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        row.addView(
                valueView,
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1
                )
        );

        return row;
    }

    // =========================================================
    // 116 FIELDS
    // =========================================================

    private String[] getAll116Fields() {

        return new String[] {

                "معرّف السجل",
                "الاسم الثلاثي",
                "الرتبة",
                "اللقب",
                "الفرع",
                "اسم الأب",
                "اسم الأم",
                "الصفة الوظيفية",
                "الرقم الوطني",
                "رقم ورقة العائلة",
                "الرقم الحسابي",
                "حالة الزواج",
                "اسم الزوج/ة",
                "عدد الأبناء",
                "فصيلة الدم",
                "قياس البدلة",
                "قياس الحذاء",
                "مدينة الإقامة",
                "رقم الهاتف",
                "رقم البطاقة الشخصية",
                "رقم جواز السفر",
                "الخبرات والسيرة",
                "اللغات المتقنة",
                "رقم القرار والتعيين",
                "تاريخ قرار التعيين",
                "رقم آخر ترقية",
                "تاريخ آخر ترقية",
                "سجل الترقيات السابقة",
                "تاريخ بداية الانتداب/التكليف",
                "تاريخ انتهاء الانتداب/التكليف",
                "الحالة العسكرية الحالية",
                "الملاحظات والقيود",
                "تاريخ الميلاد",
                "العمر",
                "مكان الميلاد",
                "العنوان التفصيلي",
                "رقم الهاتف البديل",
                "جهة الاتصال في الطوارئ",
                "رقم الطوارئ",
                "الوحدة/القطاع",
                "الدرجة الوظيفية",
                "الرتبة السابقة",
                "رقم قرار الترقية",
                "تاريخ قرار الترقية",
                "جهة قرار الترقية",
                "نوع التكليف",
                "جهة التكليف",
                "مكان التكليف",
                "تاريخ بداية التكليف",
                "تاريخ نهاية التكليف",
                "رقم قرار التكليف",
                "حالة التكليف",
                "نوع الدورة",
                "اسم الدورة",
                "الجهة التدريبية",
                "الدولة",
                "تاريخ بداية الدورة",
                "تاريخ نهاية الدورة",
                "المستوى/التقدير",
                "رقم الشهادة",
                "المؤهل",
                "التخصص",
                "الجهة التعليمية",
                "دولة التخرج",
                "سنة التخرج",
                "التقدير",
                "اللغة 1",
                "مستوى القراءة 1",
                "مستوى الكتابة 1",
                "مستوى المحادثة 1",
                "اللغة 2",
                "مستوى القراءة 2",
                "مستوى الكتابة 2",
                "مستوى المحادثة 2",
                "نوع الوثيقة",
                "رقم الوثيقة",
                "تاريخ الوثيقة",
                "تاريخ انتهاء الوثيقة",
                "الجهة المصدرة",
                "حالة الوثيقة",
                "حالة التنبيه",
                "نص التنبيه",
                "تاريخ التنبيه",
                "يوم متبقي",
                "تاريخ الاستحقاق القادم للترقية",
                "المدة المتبقية للترقية",
                "حالة تنبيه الترقية",
                "الأنواط والأوسمة",
                "عدد الترقيات الاستثنائية",
                "أرقام قرارات الترقية الاستثنائية",
                "لفت النظر شفاهي",
                "لفت النظر كتابي",
                "إنذار شفاهي",
                "إنذار كتابي",
                "عدد إسقاط رتبة",
                "صحائف الاتهام",
                "محاضر التحقيق الإداري",
                "الإجازات المرضية",
                "الجرحى والمصابين",
                "تاريخ الإصابة",
                "الشهداء",
                "تاريخ الاستشهاد",
                "رسائل الشكر",
                "نقاط الإيجابيات",
                "نقاط السلبيات",
                "النسبة المئوية للتقييم السنوي",
                "التقدير السنوي",
                "مسار/رابط الصورة الشخصية",
                "مشاركة في خطط أمنية",
                "قبض على قضايا",
                "عدد القضايا",
                "حسن سيرة وسلوك",
                "السلاح",
                "الجهاز اللاسلكي",
                "المركبة",
                "معدات أخرى"
        };
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void addFooter() {

        TextView footer =
                createText(
                        "منظومة إدارة القوة العمومية V7.1\n" +
                        "رئاسة جهاز مكافحة الهجرة غير الشرعية",
                        12,
                        GRAY
                );

        footer.setGravity(
                Gravity.CENTER
        );

        footer.setPadding(
                10,
                25,
                10,
                15
        );

        root.addView(
                footer,
                matchParams(10)
        );
    }

    // =========================================================
    // BACK / MESSAGE
    // =========================================================

    private void showMessage(
            String message
    ) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }

    // =========================================================
    // LAYOUT HELPERS
    // =========================================================

    private LinearLayout.LayoutParams matchParams(
            int topMargin
    ) {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );

        params.setMargins(
                0,
                topMargin,
                0,
                0
        );

        return params;
    }

    private LinearLayout.LayoutParams weightParams() {

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
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

    private GradientDrawable roundedBackground(
            int color,
            int radius
    ) {

        GradientDrawable drawable =
                new GradientDrawable();

        drawable.setColor(color);
        drawable.setCornerRadius(radius);

        return drawable;
    }
}