package ly.moi.forceerp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

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

        TextView logo = createText(
                "قوة العمومية",
                30,
                GREEN
        );
        logo.setTypeface(null, Typeface.BOLD);
        logo.setGravity(Gravity.CENTER);

        page.addView(
                logo,
                matchParams(5)
        );

        TextView title = createText(
                "منظومة إدارة القوة العمومية",
                24,
                DARK_GREEN
        );
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        page.addView(
                title,
                matchParams(5)
        );

        TextView version = createText(
                "V7.1",
                18,
                GOLD
        );
        version.setTypeface(null, Typeface.BOLD);
        version.setGravity(Gravity.CENTER);

        page.addView(
                version,
                matchParams(8)
        );

        Space space = new Space(this);

        page.addView(
                space,
                new LinearLayout.LayoutParams(
                        1,
                        25
                )
        );

        EditText username =
                createInput("اسم المستخدم");

        username.setInputType(
                InputType.TYPE_CLASS_TEXT
        );

        page.addView(
                username,
                matchParams(10)
        );

        EditText password =
                createInput("الرقم السري");

        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        page.addView(
                password,
                matchParams(10)
        );

        Button login =
                createMainButton(
                        "دخول إلى المنظومة"
                );

        page.addView(
                login,
                matchParams(18)
        );

        TextView info = createText(
                "رئاسة جهاز مكافحة الهجرة غير الشرعية\n" +
                "نظام إدارة القوة والمنتسبين",
                13,
                GRAY
        );

        info.setGravity(Gravity.CENTER);

        page.addView(
                info,
                matchParams(20)
        );

        login.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        String user =
                                username.getText()
                                        .toString()
                                        .trim();

                        String pass =
                                password.getText()
                                        .toString()
                                        .trim();

                        if (user.isEmpty() ||
                                pass.isEmpty()) {

                            showMessage(
                                    "يرجى إدخال اسم المستخدم والرقم السري"
                            );

                            return;
                        }

                        showDashboard();
                    }
                }
        );

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

        TextView totalTitle =
                createText(
                        "إجمالي القوة الحالية",
                        17,
                        DARK_GREEN
                );

        totalTitle.setTypeface(
                null,
                Typeface.BOLD
        );

        totalTitle.setGravity(
                Gravity.CENTER
        );

        root.addView(
                totalTitle,
                matchParams(8)
        );

        TextView total =
                createText(
                        "1,250",
                        42,
                        GREEN
                );

        total.setTypeface(
                null,
                Typeface.BOLD
        );

        total.setGravity(
                Gravity.CENTER
        );

        root.addView(
                total,
                matchParams(2)
        );

        TextView totalSub =
                createText(
                        "منتسب وموظف",
                        14,
                        GRAY
                );

        totalSub.setGravity(
                Gravity.CENTER
        );

        root.addView(
                totalSub,
                matchParams(12)
        );

        LinearLayout row1 =
                new LinearLayout(this);

        row1.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row1.setGravity(
                Gravity.CENTER
        );

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

        root.addView(
                row1,
                matchParams(8)
        );

        LinearLayout row2 =
                new LinearLayout(this);

        row2.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row2.setGravity(
                Gravity.CENTER
        );

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

        root.addView(
                row2,
                matchParams(12)
        );

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

        root.addView(
                createSectionTitle(
                        "الوصول السريع"
                ),
                matchParams(8)
        );

        addDashboardAction(
                "👤  المنتسبون",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPersonnel();
                    }
                }
        );

        addDashboardAction(
                "🔎  البحث الذكي",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSearch();
                    }
                }
        );

        addDashboardAction(
                "📊  التقارير",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showReports();
                    }
                }
        );

        addDashboardAction(
                "💳  البطاقة المالية",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFinancial();
                    }
                }
        );

        addDashboardAction(
                "🎓  الدورات والمؤهلات",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showCourses();
                    }
                }
        );

        addDashboardAction(
                "🚘  الحركة والتكليفات",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMovement();
                    }
                }
        );

        addDashboardAction(
                "⚙  الإعدادات",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSettings();
                    }
                }
        );

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

        root.addView(
                createDashboardButton(
                        "🔐 تسجيل الخروج",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showLogin();
                            }
                        }
                ),
                matchParams(18)
        );

        addFooter();

        setContentView(scrollView);
    }

    private void addDashboardAction(
            String text,
            View.OnClickListener listener
    ) {

        root.addView(
                createDashboardButton(
                        text,
                        listener
                ),
                matchParams(6)
        );
    }

    // =========================================================
    // PERSONNEL
    // =========================================================

    private void showPersonnel() {

        createScrollablePage();

        addHeader(
                "إدارة المنتسبين",
                "إدارة السجلات والبطاقات والبيانات الأساسية"
        );

        root.addView(
                createDashboardButton(
                        "➕ إضافة منتسب جديد",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showAddPersonnel();
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
                        "سجل تجريبي",
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
    // ADD PERSONNEL
    // =========================================================

    private void showAddPersonnel() {

        createScrollablePage();

        addHeader(
                "إضافة منتسب جديد",
                "نموذج البيانات الشامل لمنظومة القوة العمومية V7.1"
        );

        root.addView(
                createSectionTitle(
                        "① البيانات الشخصية"
                ),
                matchParams(6)
        );

        addDynamicFields(new String[] {
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
                "اللغات المتقنة"
        });

        root.addView(
                createSectionTitle(
                        "② التعيين والترقية والخدمة"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
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
                "العمر (تلقائي)",
                "مكان الميلاد",
                "العنوان التفصيلي",
                "رقم الهاتف البديل",
                "جهة الاتصال في الطوارئ (اسم)",
                "رقم الطوارئ",
                "الوحدة/القطاع",
                "الدرجة الوظيفية",
                "الرتبة السابقة",
                "رقم قرار الترقية",
                "تاريخ قرار الترقية",
                "جهة قرار الترقية"
        });

        root.addView(
                createSectionTitle(
                        "③ التكليف والحركة"
                ),
                matchParams(10)
        );

        addChoiceField(
                "نوع التكليف / الإجراء",
                new String[] {
                        "تكليف",
                        "تعيين",
                        "نقل",
                        "ندب",
                        "ندب وزاري",
                        "ندب وكيل وزارة الداخلية",
                        "عقد"
                }
        );

        addChoiceField(
                "اتجاه الحركة",
                new String[] {
                        "منا",
                        "إلينا"
                }
        );

        addChoiceField(
                "الفرع",
                new String[] {
                        "الرئاسي",
                        "بنغازي الكبرى",
                        "المرج",
                        "البيضاء",
                        "درنة",
                        "شحات",
                        "القبة",
                        "البطنان",
                        "الوسطى",
                        "الجنوب الشرقي (الكفرة)",
                        "سبها",
                        "براك الشاطئ",
                        "غات"
                }
        );

        addChoiceField(
                "تابع لدوريات صحراوية؟",
                new String[] {
                        "لا",
                        "نعم"
                }
        );

        addChoiceField(
                "القطاع الفرعي للوسطى",
                new String[] {
                        "غير محدد",
                        "إجدابيا",
                        "سرت"
                }
        );

        addDynamicFields(new String[] {
                "جهة التكليف",
                "مكان التكليف",
                "تاريخ بداية التكليف",
                "تاريخ نهاية التكليف",
                "رقم قرار التكليف",
                "حالة التكليف"
        });

        root.addView(
                createSectionTitle(
                        "④ تكليف بالمنصب"
                ),
                matchParams(10)
        );

        addChoiceField(
                "تكليف بالمنصب",
                new String[] {
                        "لا يوجد",
                        "مدير فرع",
                        "مدير إدارة",
                        "مدير مكتب",
                        "مدير وحدة"
                }
        );

        root.addView(
                createInfoCard(
                        "نظام التقييم",
                        "يضاف أثر تكليف المنصب إلى التقييم عند خضوع المنتسب للتقييم، مع إبقاء الأوزان قابلة للضبط."
                ),
                matchParams(8)
        );

        root.addView(
                createSectionTitle(
                        "⑤ الدورات والمؤهلات"
                ),
                matchParams(10)
        );

        addChoiceField(
                "نوع الدورة",
                new String[] {
                        "تدريبية",
                        "تخصصية",
                        "تأهيلية",
                        "ورشة",
                        "أخرى"
                }
        );

        addDynamicFields(new String[] {
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
                "التقدير"
        });

        root.addView(
                createSectionTitle(
                        "⑥ اللغات"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
                "اللغة 1",
                "مستوى القراءة 1",
                "مستوى الكتابة 1",
                "مستوى المحادثة 1",
                "اللغة 2",
                "مستوى القراءة 2",
                "مستوى الكتابة 2",
                "مستوى المحادثة 2"
        });

        root.addView(
                createSectionTitle(
                        "⑦ الوثائق والتنبيهات"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
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
                "المدة المتبقية للترقية (شهر)",
                "حالة تنبيه الترقية"
        });

        root.addView(
                createSectionTitle(
                        "⑧ الأنوطة والأوسمة والانضباط"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
                "الانوطة والأوسمة",
                "عدد الترقيات الاستثنائية",
                "أرقام قرارات الترقية الاستثنائية",
                "لفت النظر شفاهي (عدد)",
                "لفت النظر كتابي (عدد)",
                "إنذار شفاهي (عدد)",
                "إنذار كتابي (عدد)",
                "عدد إسقاط رتبة",
                "صحائف الاتهام (عدد)",
                "محاضر التحقيق الإداري (عدد)",
                "الإجازات المرضية (عدد الأيام)"
        });

        root.addView(
                createSectionTitle(
                        "⑨ الحالات الخاصة"
                ),
                matchParams(10)
        );

        addChoiceField(
                "الجرحى والمصابين (نعم/لا)",
                new String[] {
                        "لا",
                        "نعم"
                }
        );

        addDateField(
                "تاريخ الإصابة"
        );

        addChoiceField(
                "الشهداء (نعم/لا)",
                new String[] {
                        "لا",
                        "نعم"
                }
        );

        addDateField(
                "تاريخ الاستشهاد"
        );

        root.addView(
                createInfoCard(
                        "قاعدة التقييم",
                        "الشهيد والمصاب والعاجز لا يُعاملون كتقييم صفري. تظهر حالة غير خاضع للتقييم مع الاحتفاظ بكامل البيانات والترقيات والتاريخ."
                ),
                matchParams(8)
        );

        root.addView(
                createSectionTitle(
                        "⑩ التقييم"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
                "رسائل الشكر (عدد)",
                "نقاط الإيجابيات",
                "نقاط السلبيات",
                "النسبة المئوية للتقييم السنوي",
                "التقدير السنوي (تلقائي)"
        });

        root.addView(
                createSectionTitle(
                        "⑪ الصورة والمعلومات الأمنية"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
                "مسار/رابط الصورة الشخصية",
                "مشاركة في خطط أمنية",
                "قبض على قضايا",
                "عدد القضايا",
                "حسن سيرة وسلوك",
                "السلاح (النوع + الرقم)",
                "الجهاز اللاسلكي (النوع + الرقم)",
                "المركبة (النوع + اللوحة)",
                "معدات أخرى"
        });

        root.addView(
                createSectionTitle(
                        "⑫ البيانات المالية"
                ),
                matchParams(10)
        );

        addDynamicFields(new String[] {
                "الرقم المالي",
                "اسم المصرف",
                "اسم فرع المصرف",
                "رقم الحساب",
                "IBAN"
        });

        addChoiceField(
                "حالة المرتب",
                new String[] {
                        "جاري",
                        "منحة",
                        "موقوف"
                }
        );

        root.addView(
                createSectionTitle(
                        "⑬ حالة الخدمة / العضوية"
                ),
                matchParams(10)
        );

        final Spinner serviceStatus =
                addChoiceField(
                        "حالة انتهاء الخدمة/العضوية",
                        new String[] {
                                "مستمر",
                                "استقالة",
                                "فصل",
                                "انتهاء ندب",
                                "انتهاء تكليف",
                                "انتهاء عقد"
                        }
                );

        final LinearLayout terminationContainer =
                new LinearLayout(this);

        terminationContainer.setOrientation(
                LinearLayout.VERTICAL
        );

        terminationContainer.setVisibility(
                View.GONE
        );

        root.addView(
                terminationContainer,
                matchParams(5)
        );

        serviceStatus.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id
                    ) {

                        terminationContainer
                                .removeAllViews();

                        if (position == 0) {

                            terminationContainer
                                    .setVisibility(
                                            View.GONE
                                    );

                        } else {

                            terminationContainer
                                    .setVisibility(
                                            View.VISIBLE
                                    );

                            TextView title =
                                    createSectionTitle(
                                            "بيانات انتهاء الخدمة"
                                    );

                            terminationContainer.addView(
                                    title,
                                    matchParams(5)
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "تاريخ انتهاء الخدمة"
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "رقم القرار"
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "تاريخ القرار"
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "جهة القرار"
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "سبب الانتهاء"
                            );

                            addFieldToContainer(
                                    terminationContainer,
                                    "ملاحظات"
                            );
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            AdapterView<?> parent
                    ) {
                    }
                }
        );

        root.addView(
                createInfoCard(
                        "حفظ السجل",
                        "سيتم الاحتفاظ بالسجل التاريخي وعدم حذفه عند إنهاء الخدمة."
                ),
                matchParams(10)
        );

        root.addView(
                createDashboardButton(
                        "💾 حفظ سجل المنتسب",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showMessage(
                                        "تم تجهيز سجل المنتسب للحفظ.\n" +
                                        "المرحلة التالية: ربط البيانات بقاعدة البيانات."
                                );
                            }
                        }
                ),
                matchParams(14)
        );

        root.addView(
                createDashboardButton(
                        "↩ العودة إلى إدارة المنتسبين",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPersonnel();
                            }
                        }
                ),
                matchParams(8)
        );

        setContentView(scrollView);
    }

    // =========================================================
    // FORM HELPERS
    // =========================================================

    private void addDynamicFields(
            String[] fields
    ) {

        for (String field : fields) {

            if (field.contains("تاريخ")) {

                addDateField(field);

            } else if (
                    field.equals("الرتبة") ||
                    field.equals("الدرجة الوظيفية") ||
                    field.equals("الرتبة السابقة") ||
                    field.equals("حالة الزواج") ||
                    field.equals("فصيلة الدم") ||
                    field.equals("حالة التكليف") ||
                    field.equals("المؤهل") ||
                    field.equals("الدولة") ||
                    field.equals("دولة التخرج") ||
                    field.equals("حالة الوثيقة") ||
                    field.equals("حالة التنبيه") ||
                    field.equals("حالة تنبيه الترقية") ||
                    field.equals("مستوى القراءة 1") ||
                    field.equals("مستوى الكتابة 1") ||
                    field.equals("مستوى المحادثة 1") ||
                    field.equals("مستوى القراءة 2") ||
                    field.equals("مستوى الكتابة 2") ||
                    field.equals("مستوى المحادثة 2")
            ) {

                addChoiceField(
                        field,
                        getOptionsForField(field)
                );

            } else {

                root.addView(
                        createInput(field),
                        matchParams(5)
                );
            }
        }
    }

    private Spinner addChoiceField(
            String label,
            String[] options
    ) {

        TextView labelView =
                createText(
                        label,
                        14,
                        DARK_GREEN
                );

        labelView.setTypeface(
                null,
                Typeface.BOLD
        );

        root.addView(
                labelView,
                matchParams(4)
        );

        Spinner spinner =
                new Spinner(
                        this,
                        Spinner.MODE_DROPDOWN
                );

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        options
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);

        spinner.setBackground(
                roundedBackground(
                        WHITE,
                        16
                )
        );

        root.addView(
                spinner,
                matchParams(4)
        );

        return spinner;
    }

    private void addDateField(
            String label
    ) {

        TextView labelView =
                createText(
                        label,
                        14,
                        DARK_GREEN
                );

        labelView.setTypeface(
                null,
                Typeface.BOLD
        );

        root.addView(
                labelView,
                matchParams(4)
        );

        final EditText dateInput =
                createInput(
                        "اضغط لاختيار التاريخ"
                );

        dateInput.setFocusable(false);
        dateInput.setClickable(true);

        dateInput.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Calendar calendar =
                                Calendar.getInstance();

                        DatePickerDialog dialog =
                                new DatePickerDialog(
                                        MainActivity.this,
                                        (view,
                                         year,
                                         month,
                                         day) -> {

                                            String date =
                                                    String.format(
                                                            "%02d/%02d/%04d",
                                                            day,
                                                            month + 1,
                                                            year
                                                    );

                                            dateInput.setText(
                                                    date
                                            );
                                        },
                                        calendar.get(
                                                Calendar.YEAR
                                        ),
                                        calendar.get(
                                                Calendar.MONTH
                                        ),
                                        calendar.get(
                                                Calendar.DAY_OF_MONTH
                                        )
                                );

                        dialog.show();
                    }
                }
        );

        root.addView(
                dateInput,
                matchParams(5)
        );
    }

    private void addFieldToContainer(
            LinearLayout container,
            String label
    ) {

        EditText input =
                createInput(label);

        container.addView(
                input,
                matchParams(5)
        );
    }

    private String[] getOptionsForField(
            String field
    ) {

        if (field.equals("الرتبة")) {

            return new String[] {
                    "اختر الرتبة",
                    "لواء",
                    "عميد",
                    "عقيد",
                    "مقدم",
                    "رائد",
                    "نقيب",
                    "ملازم أول",
                    "ملازم",
                    "ضابط صف",
                    "فرد",
                    "موظف"
            };
        }

        if (field.equals("الدرجة الوظيفية")) {

            return new String[] {
                    "اختر الدرجة",
                    "الدرجة 3",
                    "الدرجة 4",
                    "الدرجة 5",
                    "الدرجة 6",
                    "الدرجة 7",
                    "الدرجة 8",
                    "الدرجة 9",
                    "الدرجة 10",
                    "الدرجة 11",
                    "الدرجة 12",
                    "الدرجة 13",
                    "الدرجة 14",
                    "الدرجة 15",
                    "الدرجة 16"
            };
        }

        if (field.equals("حالة الزواج")) {

            return new String[] {
                    "أعزب",
                    "متزوج",
                    "مطلق",
                    "أرمل"
            };
        }

        if (field.equals("فصيلة الدم")) {

            return new String[] {
                    "A+",
                    "A-",
                    "B+",
                    "B-",
                    "AB+",
                    "AB-",
                    "O+",
                    "O-"
            };
        }

        if (field.startsWith("مستوى")) {

            return new String[] {
                    "غير محدد",
                    "مبتدئ",
                    "متوسط",
                    "جيد",
                    "جيد جداً",
                    "متقدم"
            };
        }

        if (field.equals("حالة التكليف")) {

            return new String[] {
                    "قائم",
                    "منتهي",
                    "موقوف",
                    "ملغى"
            };
        }

        if (field.equals("المؤهل")) {

            return new String[] {
                    "ثانوي",
                    "دبلوم",
                    "ليسانس",
                    "بكالوريوس",
                    "دبلوم عالي",
                    "ماجستير",
                    "دكتوراه",
                    "أخرى"
            };
        }

        if (field.equals("حالة الوثيقة")) {

            return new String[] {
                    "سارية",
                    "منتهية",
                    "قريبة الانتهاء",
                    "ملغاة"
            };
        }

        if (
                field.equals("حالة التنبيه") ||
                field.equals("حالة تنبيه الترقية")
        ) {

            return new String[] {
                    "لا يوجد",
                    "عادي",
                    "تنبيه",
                    "عاجل"
            };
        }

        return new String[] {
                "غير محدد",
                "نعم",
                "لا"
        };
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
                        "جميع بيانات المنتسب - 116 خانة"
                ),
                matchParams(8)
        );

        String[] fields =
                getAll116Fields();

        for (int i = 0;
             i < fields.length;
             i++) {

            root.addView(
                    createDataRow(
                            (i + 1) +
                            ". " +
                            fields[i],
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
                                showAddPersonnel();
                            }
                        }
                ),
                matchParams(8)
        );

        root.addView(
                createBackButton(),
                matchParams(12)
        );

       