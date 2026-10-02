package ly.moi.forceerp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends Activity {

    private static final int GREEN = Color.rgb(20, 105, 65);
    private static final int DARK_GREEN = Color.rgb(8, 55, 38);
    private static final int GOLD = Color.rgb(190, 145, 55);
    private static final int BLUE = Color.rgb(35, 95, 155);
    private static final int RED = Color.rgb(170, 55, 55);
    private static final int ORANGE = Color.rgb(205, 125, 35);
    private static final int PURPLE = Color.rgb(105, 75, 150);
    private static final int LIGHT = Color.rgb(246, 248, 247);
    private static final int WHITE = Color.WHITE;
    private static final int DARK = Color.rgb(35, 42, 39);
    private static final int GRAY = Color.rgb(105, 115, 110);

    private LinearLayout root;
    private SharedPreferences prefs;

    private final ArrayList<JSONObject> personnel = new ArrayList<>();
    private JSONObject currentPerson;

    private final String[] FIELDS = {
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
            "المدة المتبقية للترقية (شهر)",
            "حالة تنبيه الترقية",
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
            "الإجازات المرضية (عدد الأيام)",
            "الجرحى والمصابين (نعم/لا)",
            "تاريخ الإصابة",
            "الشهداء (نعم/لا)",
            "تاريخ الاستشهاد",
            "رسائل الشكر (عدد)",
            "نقاط الإيجابيات (التقييم)",
            "نقاط السلبيات (التقييم)",
            "النسبة المئوية للتقييم السنوي",
            "التقدير السنوي (تلقائي)",
            "مسار/رابط الصورة الشخصية",
            "مشاركة في خطط أمنية",
            "قبض على قضايا",
            "عدد القضايا",
            "حسن سيرة وسلوك",
            "السلاح (النوع + الرقم)",
            "الجهاز اللاسلكي (النوع + الرقم)",
            "المركبة (النوع + اللوحة)",
            "معدات أخرى"
    };

    private final String[] BRANCHES = {
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
    };

    private final String[] ASSIGNMENT_TYPES = {
            "تكليف",
            "تعيين",
            "نقل",
            "ندب",
            "ندب وزاري",
            "ندب وكيل وزارة الداخلية",
            "عقد"
    };

    private final String[] POSITION_TYPES = {
            "لا يوجد",
            "مدير فرع",
            "مدير إدارة",
            "مدير مكتب",
            "مدير وحدة"
    };

    private final String[] SALARY_STATUS = {
            "جاري",
            "منحة",
            "موقوف"
    };

    private final String[] SERVICE_STATUS = {
            "مستمر",
            "استقالة",
            "فصل",
            "انتهاء ندب",
            "انتهاء تكليف",
            "انتهاء عقد"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("force_erp_v71", MODE_PRIVATE);
        loadPersonnel();

        showLogin();
    }

    /* =========================================================
       LOGIN
       ========================================================= */

    private void showLogin() {

        root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(dp(22), dp(22), dp(22), dp(22));
        root.setBackgroundColor(LIGHT);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(24), dp(30), dp(24), dp(30));
        card.setGravity(Gravity.CENTER_HORIZONTAL);
        card.setBackground(round(WHITE, 28, GOLD));

        TextView logo = text("◆", 52, GOLD, true);
        logo.setGravity(Gravity.CENTER);
        card.addView(logo, params(-1, -2));

        TextView title = text("منظومة إدارة القوة العمومية", 25, DARK_GREEN, true);
        title.setGravity(Gravity.CENTER);
        card.addView(title, params(-1, -2));

        TextView version = text("V7.1 • نظام الإدارة الذكية", 14, GOLD, true);
        version.setGravity(Gravity.CENTER);
        card.addView(version, marginParams(-1, -2, 0, 8, 0, 20));

        EditText username = input("اسم المستخدم", false);
        card.addView(username, marginParams(-1, -2, 0, 8, 0, 8));

        EditText password = input("الرقم السري", true);
        card.addView(password, marginParams(-1, -2, 0, 8, 0, 18));

        Button login = button("دخول إلى المنظومة", GREEN);
        card.addView(login, params(-1, dp(58)));

        login.setOnClickListener(v -> {
            if (username.getText().toString().trim().isEmpty()
                    || password.getText().toString().trim().isEmpty()) {

                showDialog(
                        "بيانات الدخول",
                        "يرجى إدخال اسم المستخدم والرقم السري."
                );
                return;
            }

            showDashboard();
        });

        root.addView(card, new LinearLayout.LayoutParams(
                -1,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));

        setContentView(root);
    }

    /* =========================================================
       DASHBOARD
       ========================================================= */

    private void showDashboard() {

        LinearLayout page = page();

        addHeader(page,
                "لوحة القيادة",
                "مركز التحكم الرئيسي • Force ERP V7.1");

        LinearLayout hero = panel();
        TextView heroTitle = text("القوة العمومية", 18, DARK_GREEN, true);
        hero.addView(heroTitle, params(-1, -2));

        TextView total = text(
                String.valueOf(personnel.size()),
                42,
                GOLD,
                true
        );
        total.setGravity(Gravity.CENTER);
        hero.addView(total, marginParams(-1, -2, 0, 8, 0, 4));

        TextView totalLabel = text(
                "إجمالي السجلات المحلية",
                13,
                GRAY,
                false
        );
        totalLabel.setGravity(Gravity.CENTER);
        hero.addView(totalLabel, params(-1, -2));

        page.addView(hero, marginParams(-1, -2, 0, 0, 0, 12));

        LinearLayout stats = new LinearLayout(this);
        stats.setOrientation(LinearLayout.VERTICAL);

        stats.addView(
                dashboardButton(
                        "👮  الضباط",
                        "إدارة وعرض سجلات الضباط",
                        BLUE,
                        () -> showPersonnelByCategory("ضابط")
                ),
                marginParams(-1, dp(70), 0, 5, 0, 5)
        );

        stats.addView(
                dashboardButton(
                        "🛡  ضباط الصف",
                        "إدارة وعرض سجلات ضباط الصف",
                        GREEN,
                        () -> showPersonnelByCategory("ضابط صف")
                ),
                marginParams(-1, dp(70), 0, 5, 0, 5)
        );

        stats.addView(
                dashboardButton(
                        "◉  الأفراد",
                        "إدارة وعرض سجلات الأفراد",
                        ORANGE,
                        () -> showPersonnelByCategory("فرد")
                ),
                marginParams(-1, dp(70), 0, 5, 0, 5)
        );

        stats.addView(
                dashboardButton(
                        "▣  الموظفون",
                        "إدارة وعرض سجلات الموظفين",
                        PURPLE,
                        () -> showPersonnelByCategory("موظف")
                ),
                marginParams(-1, dp(70), 0, 5, 0, 10)
        );

        page.addView(stats);

        sectionTitle(page, "الوصول السريع");

        addQuick(page, "👥 المنتسبون", "إضافة • عرض • تعديل • بحث",
                GREEN, this::showPersonnel);

        addQuick(page, "🔎 البحث عن منتسب", "بحث بالاسم أو الرقم الوطني أو الحسابي",
                BLUE, this::showSearch);

        addQuick(page, "💳 البطاقات المالية", "البيانات المالية وحالة المرتب",
                GOLD, this::showFinancial);

        addQuick(page, "🎓 الدورات والمؤهلات", "الدورات والشهادات والمؤهلات",
                PURPLE, this::showCourses);

        addQuick(page, "🚗 الحركة والتكليف", "التكليف • النقل • الندب • التعيين",
                ORANGE, this::showMovement);

        addQuick(page, "📊 التقارير", "تقارير القوة والحالات والبيانات",
                BLUE, this::showReports);

        addQuick(page, "⚙ الإعدادات", "إعدادات المنظومة",
                DARK_GREEN, this::showSettings);

        sectionTitle(page, "التنبيهات الذكية");

        int active = countStatus("مستمر");
        int stopped = countSalary("موقوف");
        int terminated = countServiceNotActive();

        page.addView(
                infoCard(
                        "الحالة التشغيلية",
                        "السجلات المستمرة: " + active,
                        GREEN
                ),
                marginParams(-1, -2, 0, 4, 0, 4)
        );

        page.addView(
                infoCard(
                        "المرتبات",
                        "السجلات ذات المرتب الموقوف: " + stopped,
                        RED
                ),
                marginParams(-1, -2, 0, 4, 0, 4)
        );

        page.addView(
                infoCard(
                        "الأرشيف",
                        "سجلات منتهية الخدمة: " + terminated,
                        GRAY
                ),
                marginParams(-1, -2, 0, 4, 0, 12)
        );

        Button logout = button("تسجيل الخروج", RED);
        page.addView(logout, marginParams(-1, dp(55), 0, 15, 0, 20));

        logout.setOnClickListener(v -> showLogin());

        setContentView(page);
    }

    /* =========================================================
       PERSONNEL
       ========================================================= */

    private void showPersonnel() {

        LinearLayout page = page();

        addHeader(page,
                "إدارة المنتسبين",
                "قاعدة بيانات القوة العمومية");

        Button add = button("＋ إضافة منتسب جديد", GREEN);
        page.addView(add, marginParams(-1, dp(58), 0, 0, 0, 8));
        add.setOnClickListener(v -> showAddPersonnel());

        Button search = button("⌕ بحث متقدم", BLUE);
        page.addView(search, marginParams(-1, dp(58), 0, 0, 0, 15));
        search.setOnClickListener(v -> showSearch());

        sectionTitle(page, "السجلات الحالية");

        if (personnel.isEmpty()) {
            page.addView(
                    infoCard(
                            "لا توجد سجلات بعد",
                            "ابدأ بإضافة أول منتسب إلى المنظومة.",
                            GOLD
                    )
            );
        } else {

            for (int i = personnel.size() - 1; i >= 0; i--) {

                JSONObject p = personnel.get(i);

                String name = p.optString("الاسم الثلاثي", "بدون اسم");
                String rank = p.optString("الرتبة", "بدون رتبة");
                String branch = p.optString("الفرع", "بدون فرع");

                Button item = button(
                        name + "\n" + rank + " • " + branch,
                        DARK_GREEN
                );

                item.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
                page.addView(
                        item,
                        marginParams(-1, dp(72), 0, 4, 0, 4)
                );

                final JSONObject selected = p;
                item.setOnClickListener(v -> showPersonnelCard(selected));
            }
        }

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    private void showPersonnelByCategory(String category) {

        LinearLayout page = page();

        addHeader(
                page,
                category,
                "السجلات المطابقة للتصنيف"
        );

        boolean found = false;

        for (int i = personnel.size() - 1; i >= 0; i--) {

            JSONObject p = personnel.get(i);
            String rank = p.optString("الرتبة", "");

            if (rank.contains(category)) {

                found = true;

                Button item = button(
                        p.optString("الاسم الثلاثي", "بدون اسم")
                                + "\n"
                                + rank
                                + " • "
                                + p.optString("الفرع", ""),
                        GREEN
                );

                page.addView(
                        item,
                        marginParams(-1, dp(70), 0, 4, 0, 4)
                );

                item.setOnClickListener(
                        v -> showPersonnelCard(p)
                );
            }
        }

        if (!found) {
            page.addView(
                    infoCard(
                            "لا توجد نتائج",
                            "لا توجد سجلات مطابقة لهذا التصنيف حاليًا.",
                            GRAY
                    )
            );
        }

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    /* =========================================================
       ADD PERSONNEL
       ========================================================= */

    private void showAddPersonnel() {

        final LinearLayout page = page();

        addHeader(
                page,
                "إضافة منتسب",
                "إدخال البيانات الرسمية للمنتسب"
        );

        final JSONObject data = new JSONObject();

        for (String field : FIELDS) {

            if (field.equals("معرّف السجل")) {

                addReadonly(page, field,
                        String.valueOf(System.currentTimeMillis()));

            } else if (field.equals("العمر (تلقائي)")
                    || field.equals("يوم متبقي")
                    || field.equals("المدة المتبقية للترقية (شهر)")
                    || field.equals("التقدير السنوي (تلقائي)")) {

                addReadonly(page, field, "يُحسب تلقائيًا");

            } else if (field.equals("الفرع")) {

                addSpinner(page, data, field, BRANCHES);

            } else if (field.equals("نوع التكليف")) {

                addSpinner(page, data, field, ASSIGNMENT_TYPES);

            } else if (field.equals("تكليف بالمنصب")) {

                addSpinner(page, data, field, POSITION_TYPES);

            } else if (field.equals("حالة المرتب")) {

                addSpinner(page, data, field, SALARY_STATUS);

            } else if (field.equals("حالة انتهاء الخدمة/العضوية")) {

                addSpinner(page, data, field, SERVICE_STATUS);

            } else if (isYesNo(field)) {

                addSpinner(
                        page,
                        data,
                        field,
                        new String[]{"نعم", "لا"}
                );

            } else if (isDateField(field)) {

                addDateField(page, data, field);

            } else if (isNumericField(field)) {

                addNumberField(page, data, field);

            } else {

                addTextField(page, data, field);
            }
        }

        /*
         * الحقول الإضافية المعتمدة في التصميم
         */
        sectionTitle(page, "بيانات إدارية إضافية");

        addSpinner(
                page,
                data,
                "تكليف بالمنصب",
                POSITION_TYPES
        );

        addSpinner(
                page,
                data,
                "حالة المرتب",
                SALARY_STATUS
        );

        addSpinner(
                page,
                data,
                "حالة انتهاء الخدمة/العضوية",
                SERVICE_STATUS
        );

        addSpinner(
                page,
                data,
                "يتبع دوريات صحراوية؟",
                new String[]{"لا", "نعم"}
        );

        addTextField(page, data, "اسم المصرف");
        addTextField(page, data, "اسم فرع المصرف");
        addTextField(page, data, "رقم الحساب");
        addTextField(page, data, "IBAN");

        sectionTitle(page, "حفظ السجل");

        Button save = button(
                "✓ حفظ المنتسب",
                GREEN
        );

        page.addView(
                save,
                marginParams(-1, dp(60), 0, 10, 0, 5)
        );

        save.setOnClickListener(v -> {

            String name = value(data, "الاسم الثلاثي");

            if (name.isEmpty()) {

                showDialog(
                        "بيانات ناقصة",
                        "الاسم الثلاثي مطلوب قبل حفظ السجل."
                );

                return;
            }

            try {

                if (!data.has("معرّف السجل")) {
                    data.put(
                            "معرّف السجل",
                            String.valueOf(System.currentTimeMillis())
                    );
                }

                calculateAutomaticFields(data);

                personnel.add(data);

                savePersonnel();

                currentPerson = data;

                showDialog(
                        "تم الحفظ",
                        "تم إنشاء سجل المنتسب وحفظه بنجاح."
                );

                showPersonnelCard(data);

            } catch (Exception e) {

                showDialog(
                        "خطأ",
                        "تعذر حفظ السجل: " + e.getMessage()
                );
            }
        });

        addBack(page, this::showPersonnel);

        setContentView(page);
    }

    /* =========================================================
       PERSONNEL CARD
       ========================================================= */

    private void showPersonnelCard(JSONObject p) {

        currentPerson = p;

        LinearLayout page = page();

        addHeader(
                page,
                "بطاقة المنتسب",
                p.optString("الاسم الثلاثي", "بدون اسم")
        );

        LinearLayout profile = panel();

        TextView name = text(
                p.optString("الاسم الثلاثي", "بدون اسم"),
                24,
                DARK_GREEN,
                true
        );

        profile.addView(name, params(-1, -2));

        profile.addView(
                text(
                        p.optString("الرتبة", "بدون رتبة")
                                + " • "
                                + p.optString("الفرع", "بدون فرع"),
                        15,
                        GOLD,
                        true
                ),
                marginParams(-1, -2, 0, 7, 0, 0)
        );

        profile.addView(
                text(
                        "الرقم الوطني: "
                                + p.optString("الرقم الوطني", "غير مدخل"),
                        13,
                        GRAY,
                        false
                ),
                params(-1, -2)
        );

        page.addView(
                profile,
                marginParams(-1, -2, 0, 0, 0, 10)
        );

        sectionTitle(page, "البيانات الأساسية");

        for (String field : FIELDS) {

            if (field.equals("معرّف السجل")
                    || field.equals("العمر (تلقائي)")
                    || field.equals("يوم متبقي")
                    || field.equals("المدة المتبقية للترقية (شهر)")
                    || field.equals("التقدير السنوي (تلقائي)")) {

                continue;
            }

            String value = p.optString(field, "");

            if (!value.isEmpty()) {

                page.addView(
                        dataRow(field, value),
                        marginParams(-1, -2, 0, 2, 0, 2)
                );
            }
        }

        sectionTitle(page, "الإدارة المالية");

        page.addView(
                dataRow(
                        "حالة المرتب",
                        p.optString("حالة المرتب", "غير محدد")
                )
        );

        page.addView(
                dataRow(
                        "اسم المصرف",
                        p.optString("اسم المصرف", "غير مدخل")
                )
        );

        page.addView(
                dataRow(
                        "اسم فرع المصرف",
                        p.optString("اسم فرع المصرف", "غير مدخل")
                )
        );

        page.addView(
                dataRow(
                        "رقم الحساب",
                        p.optString("رقم الحساب", "غير مدخل")
                )
        );

        sectionTitle(page, "التقييم");

        String evaluation = calculateEvaluation(p);

        page.addView(
                infoCard(
                        "التقييم السنوي",
                        evaluation,
                        PURPLE
                )
        );

        Button edit = button("✎ تعديل بيانات المنتسب", BLUE);
        page.addView(
                edit,
                marginParams(-1, dp(55), 0, 10, 0, 4)
        );

        edit.setOnClickListener(v -> showEditPersonnel(p));

        Button archive = button("▣ إنهاء الخدمة / الأرشفة", ORANGE);
        page.addView(
                archive,
                marginParams(-1, dp(55), 0, 4, 0, 4)
        );

        archive.setOnClickListener(
                v -> showServiceDialog(p)
        );

        Button delete = button("حذف نهائي — صلاحية إدارية", RED);
        page.addView(
                delete,
                marginParams(-1, dp(55), 0, 4, 0, 10)
        );

        delete.setOnClickListener(
                v -> confirmDelete(p)
        );

        addBack(page, this::showPersonnel);

        setContentView(page);
    }

    /* =========================================================
       EDIT
       ========================================================= */

    private void showEditPersonnel(JSONObject data) {

        LinearLayout page = page();

        addHeader(
                page,
                "تعديل المنتسب",
                data.optString("الاسم الثلاثي", "")
        );

        for (String field : FIELDS) {

            String existing = data.optString(field, "");

            if (field.equals("معرّف السجل")) {

                addReadonly(page, field, existing);

            } else if (field.equals("العمر (تلقائي)")
                    || field.equals("يوم متبقي")
                    || field.equals("المدة المتبقية للترقية (شهر)")
                    || field.equals("التقدير السنوي (تلقائي)")) {

                addReadonly(page, field, existing);

            } else {

                addEditableField(page, data, field, existing);
            }
        }

        Button save = button("✓ حفظ التعديلات", GREEN);

        page.addView(
                save,
                marginParams(-1, dp(58), 0, 12, 0, 5)
        );

        save.setOnClickListener(v -> {

            calculateAutomaticFields(data);
            savePersonnel();

            showPersonnelCard(data);
        });

        addBack(
                page,
                () -> showPersonnelCard(data)
        );

        setContentView(page);
    }

    /* =========================================================
       SEARCH
       ========================================================= */

    private void showSearch() {

        LinearLayout page = page();

        addHeader(
                page,
                "البحث الذكي",
                "ابحث بالاسم أو الرقم الوطني أو الرقم الحسابي"
        );

        EditText query = input(
                "اكتب قيمة البحث...",
                false
        );

        page.addView(
                query,
                marginParams(-1, -2, 0, 0, 0, 8)
        );

        Button search = button(
                "⌕ تنفيذ البحث",
                BLUE
        );

        page.addView(
                search,
                marginParams(-1, dp(55), 0, 0, 0, 12)
        );

        LinearLayout results = new LinearLayout(this);
        results.setOrientation(LinearLayout.VERTICAL);

        page.addView(results);

        search.setOnClickListener(v -> {

            results.removeAllViews();

            String q = query.getText()
                    .toString()
                    .trim()
                    .toLowerCase(Locale.ROOT);

            for (JSONObject p : personnel) {

                String name = value(p, "الاسم الثلاثي");
                String national = value(p, "الرقم الوطني");
                String account = value(p, "الرقم الحسابي");

                if (q.isEmpty()
                        || name.toLowerCase(Locale.ROOT).contains(q)
                        || national.contains(q)
                        || account.contains(q)) {

                    Button item = button(
                            name + "\n"
                                    + p.optString("الرتبة", "")
                                    + " • "
                                    + p.optString("الفرع", ""),
                            GREEN
                    );

                    results.addView(
                            item,
                            marginParams(-1, dp(72), 0, 4, 0, 4)
                    );

                    item.setOnClickListener(
                            x -> showPersonnelCard(p)
                    );
                }
            }

            if (results.getChildCount() == 0) {

                results.addView(
                        infoCard(
                                "لا توجد نتائج",
                                "لم يتم العثور على سجل مطابق.",
                                GRAY
                        )
                );
            }
        });

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    /* =========================================================
       FINANCIAL
       ========================================================= */

    private void showFinancial() {

        LinearLayout page = page();

        addHeader(
                page,
                "البطاقات المالية",
                "الإدارة المالية للمنتسبين"
        );

        sectionTitle(page, "الحالات المالية");

        page.addView(
                infoCard(
                        "جاري",
                        String.valueOf(countSalary("جاري")),
                        GREEN
                )
        );

        page.addView(
                infoCard(
                        "منحة",
                        String.valueOf(countSalary("منحة")),
                        GOLD
                )
        );

        page.addView(
                infoCard(
                        "موقوف",
                        String.valueOf(countSalary("موقوف")),
                        RED
                )
        );

        sectionTitle(page, "السجلات");

        for (JSONObject p : personnel) {

            Button item = button(
                    p.optString("الاسم الثلاثي", "بدون اسم")
                            + "\n"
                            + "حالة المرتب: "
                            + p.optString("حالة المرتب", "غير محدد"),
                    DARK_GREEN
            );

            page.addView(
                    item,
                    marginParams(-1, dp(70), 0, 4, 0, 4)
            );

            item.setOnClickListener(
                    v -> showPersonnelCard(p)
            );
        }

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    /* =========================================================
       COURSES
       ========================================================= */

    private void showCourses() {

        LinearLayout page = page();

        addHeader(
                page,
                "الدورات والمؤهلات",
                "التدريب والتأهيل والشهادات"
        );

        page.addView(
                infoCard(
                        "إجمالي السجلات",
                        String.valueOf(personnel.size()),
                        PURPLE
                )
        );

        for (JSONObject p : personnel) {

            String course = p.optString("اسم الدورة", "");

            if (!course.isEmpty()) {

                page.addView(
                        dataRow(
                                p.optString("الاسم الثلاثي", ""),
                                course
                        )
                );
            }
        }

        Button add = button(
                "＋ إدارة بيانات الدورات",
                PURPLE
        );

        page.addView(
                add,
                marginParams(-1, dp(58), 0, 15, 0, 5)
        );

        add.setOnClickListener(
                v -> showAddCourse()
        );

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    private void showAddCourse() {

        LinearLayout page = page();

        addHeader(
                page,
                "إضافة دورة",
                "إدخال سجل تدريبي"
        );

        EditText name = input("اسم الدورة", false);
        EditText type = input("نوع الدورة", false);
        EditText institution = input("الجهة التدريبية", false);

        page.addView(name, marginParams(-1, -2, 0, 5, 0, 5));
        page.addView(type, marginParams(-1, -2, 0, 5, 0, 5));
        page.addView(institution, marginParams(-1, -2, 0, 5, 0, 15));

        Button save = button("✓ حفظ الدورة", GREEN);

        page.addView(
                save,
                marginParams(-1, dp(58), 0, 0, 0, 8)
        );

        save.setOnClickListener(v -> {

            if (currentPerson == null) {

                showDialog(
                        "لا يوجد منتسب",
                        "افتح بطاقة منتسب أولًا لإضافة الدورة إليه."
                );

                return;
            }

            try {

                currentPerson.put(
                        "اسم الدورة",
                        name.getText().toString()
                );

                currentPerson.put(
                        "نوع الدورة",
                        type.getText().toString()
                );

                currentPerson.put(
                        "الجهة التدريبية",
                        institution.getText().toString()
                );

                savePersonnel();

                showPersonnelCard(currentPerson);

            } catch (Exception e) {

                showDialog(
                        "خطأ",
                        e.getMessage()
                );
            }
        });

        addBack(page, this::showCourses);

        setContentView(page);
    }

    /* =========================================================
       MOVEMENT
       ========================================================= */

    private void showMovement() {

        LinearLayout page = page();

        addHeader(
                page,
                "الحركة والتكليف",
                "التعيين • النقل • الندب • التكليف"
        );

        for (JSONObject p : personnel) {

            String type = p.optString("نوع التكليف", "");

            if (!type.isEmpty()) {

                page.addView(
                        dataRow(
                                p.optString("الاسم الثلاثي", ""),
                                type
                                        + " — "
                                        + p.optString("جهة التكليف", "")
                        ),
                        marginParams(-1, -2, 0, 2, 0, 2)
                );
            }
        }

        Button add = button(
                "＋ تسجيل حركة / تكليف",
                ORANGE
        );

        page.addView(
                add,
                marginParams(-1, dp(58), 0, 15, 0, 5)
        );

        add.setOnClickListener(
                v -> showMovementEditor()
        );

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    private void showMovementEditor() {

        LinearLayout page = page();

        addHeader(
                page,
                "تسجيل حركة",
                "إجراء إداري جديد"
        );

        if (currentPerson == null) {

            page.addView(
                    infoCard(
                            "اختر منتسبًا",
                            "افتح بطاقة المنتسب ثم عد إلى الحركة والتكليف.",
                            ORANGE
                    )
            );

        } else {

            page.addView(
                    dataRow(
                            "المنتسب",
                            currentPerson.optString(
                                    "الاسم الثلاثي",
                                    ""
                            )
                    )
            );

            Spinner spinner = spinner(
                    ASSIGNMENT_TYPES
            );

            page.addView(
                    spinner,
                    marginParams(-1, dp(55), 0, 10, 0, 8)
            );

            EditText authority = input(
                    "جهة التكليف",
                    false
            );

            EditText place = input(
                    "مكان التكليف",
                    false
            );

            page.addView(authority);
            page.addView(place);

            Button save = button(
                    "✓ حفظ الحركة",
                    GREEN
            );

            page.addView(
                    save,
                    marginParams(-1, dp(58), 0, 15, 0, 5)
            );

            save.setOnClickListener(v -> {

                try {

                    currentPerson.put(
                            "نوع التكليف",
                            spinner.getSelectedItem().toString()
                    );

                    currentPerson.put(
                            "جهة التكليف",
                            authority.getText().toString()
                    );

                    currentPerson.put(
                            "مكان التكليف",
                            place.getText().toString()
                    );

                    savePersonnel();

                    showPersonnelCard(currentPerson);

                } catch (Exception e) {

                    showDialog(
                            "خطأ",
                            e.getMessage()
                    );
                }
            });
        }

        addBack(page, this::showMovement);

        setContentView(page);
    }

    /* =========================================================
       REPORTS
       ========================================================= */

    private void showReports() {

        LinearLayout page = page();

        addHeader(
                page,
                "التقارير",
                "مؤشرات القوة والبيانات"
        );

        page.addView(
                reportCard(
                        "إجمالي السجلات",
                        String.valueOf(personnel.size()),
                        GREEN
                )
        );

        page.addView(
                reportCard(
                        "مستمر",
                        String.valueOf(countStatus("مستمر")),
                        BLUE
                )
        );

        page.addView(
                reportCard(
                        "موقوف المرتب",
                        String.valueOf(countSalary("موقوف")),
                        RED
                )
        );

        page.addView(
                reportCard(
                        "إنهاء الخدمة",
                        String.valueOf(countServiceNotActive()),
                        ORANGE
                )
        );

        page.addView(
                reportCard(
                        "مصابون",
                        String.valueOf(countYes("الجرحى والمصابين (نعم/لا)")),
                        PURPLE
                )
        );

        page.addView(
                reportCard(
                        "شهداء",
                        String.valueOf(countYes("الشهداء (نعم/لا)")),
                        GOLD
                )
        );

        Button personnelReport = button(
                "تقرير تفصيلي للمنتسبين",
                DARK_GREEN
        );

        page.addView(
                personnelReport,
                marginParams(-1, dp(58), 0, 15, 0, 5)
        );

        personnelReport.setOnClickListener(
                v -> showPersonnel()
        );

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    /* =========================================================
       SETTINGS
       ========================================================= */

    private void showSettings() {

        LinearLayout page = page();

        addHeader(
                page,
                "الإعدادات",
                "إدارة المنظومة"
        );

        page.addView(
                infoCard(
                        "الإصدار",
                        "Force ERP V7.1",
                        GOLD
                )
        );

        Button backup = button(
                "⬇ تصدير نسخة بيانات",
                BLUE
        );

        page.addView(
                backup,
                marginParams(-1, dp(58), 0, 6, 0, 6)
        );

        backup.setOnClickListener(
                v -> exportPreview()
        );

        Button clear = button(
                "⚠ حذف جميع البيانات المحلية",
                RED
        );

        page.addView(
                clear,
                marginParams(-1, dp(58), 0, 6, 0, 15)
        );

        clear.setOnClickListener(
                v -> confirmClear()
        );

        addBack(page, this::showDashboard);

        setContentView(page);
    }

    /* =========================================================
       SERVICE / DELETE
       ========================================================= */

    private void showServiceDialog(JSONObject p) {

        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(dp(20), dp(5), dp(20), dp(5));

        Spinner status = spinner(SERVICE_STATUS);

        box.addView(status);

        EditText date = input(
                "تاريخ انتهاء الخدمة",
                false
        );

        box.addView(date);

        EditText decision = input(
                "رقم القرار",
                false
        );

        box.addView(decision);

        EditText reason = input(
                "سبب الانتهاء",
                false
        );

        box.addView(reason);

        new AlertDialog.Builder(this)
                .setTitle("إنهاء الخدمة / الأرشفة")
                .setView(box)
                .setNegativeButton("إلغاء", null)
                .setPositiveButton("حفظ", (d, w) -> {

                    try {

                        p.put(
                                "حالة انتهاء الخدمة/العضوية",
                                status.getSelectedItem().toString()
                        );

                        p.put(
                                "تاريخ انتهاء الخدمة",
                                date.getText().toString()
                        );

                        p.put(
                                "رقم قرار انتهاء الخدمة",
                                decision.getText().toString()
                        );

                        p.put(
                                "سبب انتهاء الخدمة",
                                reason.getText().toString()
                        );

                        savePersonnel();

                        showPersonnelCard(p);

                    } catch (Exception e) {

                        showDialog(
                                "خطأ",
                                e.getMessage()
                        );
                    }
                })
                .show();
    }

    private void confirmDelete(JSONObject p) {

        new AlertDialog.Builder(this)
                .setTitle("حذف نهائي")
                .setMessage(
                        "هذا الإجراء يحذف السجل من التخزين المحلي. هل أنت متأكد؟"
                )
                .setNegativeButton("إلغاء", null)
                .setPositiveButton("حذف", (d, w) -> {

                    personnel.remove(p);
                    savePersonnel();
                    showPersonnel();
                })
                .show();
    }

    private void confirmClear() {

        new AlertDialog.Builder(this)
                .setTitle("حذف جميع البيانات")
                .setMessage(
                        "سيتم حذف جميع السجلات المحلية نهائيًا."
                )
                .setNegativeButton("إلغاء", null)
                .setPositiveButton("متابعة", (d, w) -> {

                    personnel.clear();
                    savePersonnel();
                    showDashboard();
                })
                .show();
    }

    private void exportPreview() {

        StringBuilder report = new StringBuilder();

        report.append("Force ERP V7.1\n");
        report.append("عدد السجلات: ")
                .append(personnel.size())
                .append("\n\n");

        for (JSONObject p : personnel) {

            report.append(
                    p.optString("الاسم الثلاثي", "بدون اسم")
            );

            report.append(" | ");

            report.append(
                    p.optString("الرقم الوطني", "")
            );

            report.append("\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("معاينة النسخة")
                .setMessage(report.toString())
                .setPositiveButton("إغلاق", null)
                .show();
    }

    /* =========================================================
       DATABASE-LIKE LOCAL STORAGE
       ========================================================= */

    private void savePersonnel() {

        JSONArray array = new JSONArray();

        try {

            for (JSONObject p : personnel) {
                array.put(p);
            }

            prefs.edit()
                    .putString(
                            "personnel",
                            array.toString()
                    )
                    .apply();

        } catch (Exception ignored) {
        }
    }

    private void loadPersonnel() {

        personnel.clear();

        String raw = prefs.getString(
                "personnel",
                "[]"
        );

        try {

            JSONArray array = new JSONArray(raw);

            for (int i = 0; i < array.length(); i++) {

                personnel.add(
                        array.getJSONObject(i)
                );
            }

        } catch (Exception ignored) {
        }
    }

    /* =========================================================
       AUTOMATIC CALCULATIONS
       ========================================================= */

    private void calculateAutomaticFields(JSONObject p) {

        try {

            String dob = p.optString(
                    "تاريخ الميلاد",
                    ""
            );

            if (!dob.isEmpty()) {

                int age = calculateAge(dob);

                p.put(
                        "العمر (تلقائي)",
                        String.valueOf(age)
                );
            }

            String injured = p.optString(
                    "الجرحى والمصابين (نعم/لا)",
                    "لا"
            );

            String martyr = p.optString(
                    "الشهداء (نعم/لا)",
                    "لا"
            );

            if (martyr.equals("نعم")) {

                p.put(
                        "التقدير السنوي (تلقائي)",
                        "غير خاضع للتقييم — شهيد"
                );

            } else if (injured.equals("نعم")) {

                p.put(
                        "التقدير السنوي (تلقائي)",
                        "غير خاضع للتقييم — مصاب"
                );

            } else {

                double positive = parseDouble(
                        p.optString(
                                "نقاط الإيجابيات (التقييم)",
                                "0"
                        )
                );

                double negative = parseDouble(
                        p.optString(
                                "نقاط السلبيات (التقييم)",
                                "0"
                        )
                );

                double score = positive - negative;

                if (score < 0) {
                    score = 0;
                }

                p.put(
                        "النسبة المئوية للتقييم السنوي",
                        String.format(
                                Locale.US,
                                "%.1f",
                                score
                        )
                );

                String grade;

                if (score >= 90) {
                    grade = "ممتاز";
                } else if (score >= 80) {
                    grade = "جيد جدًا";
                } else if (score >= 70) {
                    grade = "جيد";
                } else if (score >= 60) {
                    grade = "مقبول";
                } else {
                    grade = "يحتاج متابعة";
                }

                p.put(
                        "التقدير السنوي (تلقائي)",
                        grade
                );
            }

        } catch (Exception ignored) {
        }
    }

    private String calculateEvaluation(JSONObject p) {

        String martyr = p.optString(
                "الشهداء (نعم/لا)",
                "لا"
        );

        String injured = p.optString(
                "الجرحى والمصابين (نعم/لا)",
                "لا"
        );

        if (martyr.equals("نعم")) {
            return "غير خاضع للتقييم — شهيد";
        }

        if (injured.equals("نعم")) {
            return "غير خاضع للتقييم — مصاب";
        }

        return p.optString(
                "التقدير السنوي (تلقائي)",
                "لم يتم احتساب التقييم بعد"
        );
    }

    private int calculateAge(String date) {

        try {

            String[] parts = date.split("/");

            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1;
            int year = Integer.parseInt(parts[2]);

            Calendar dob = Calendar.getInstance();
            dob.set(year, month, day);

            Calendar now = Calendar.getInstance();

            int age =
                    now.get(Calendar.YEAR)
                            - dob.get(Calendar.YEAR);

            if (
                    now.get(Calendar.DAY_OF_YEAR)
                            < dob.get(Calendar.DAY_OF_YEAR)
            ) {
                age--;
            }

            return Math.max(age, 0);

        } catch (Exception e) {

            return 0;
        }
    }

    /* =========================================================
       FORM HELPERS
       ========================================================= */

    private void addTextField(
            LinearLayout page,
            JSONObject data,
            String label
    ) {

        EditText e = input(label, false);

        page.addView(
                e,
                marginParams(-1, -2, 0, 3, 0, 3)
        );

        e.setOnFocusChangeListener(
                (v, hasFocus) -> {
                    if (!hasFocus) {
                        try {
                            data.put(
                                    label,
                                    e.getText().toString()
                            );
                        } catch (Exception ignored) {
                        }
                    }
                }
        );
    }

    private void addNumberField(
            LinearLayout page,
            JSONObject data,
            String label
    ) {

        EditText e = input(label, false);

        e.setInputType(
                InputType.TYPE_CLASS_NUMBER
        );

        page.addView(
                e,
                marginParams(-1, -2, 0, 3, 0, 3)
        );

        e.setOnFocusChangeListener(
                (v, hasFocus) -> {
                    if (!hasFocus) {
                        try {
                            data.put(
                                    label,
                                    e.getText().toString()
                            );
                        } catch (Exception ignored) {
                        }
                    }
                }
        );
    }

    private void addDateField(
            LinearLayout page,
            JSONObject data,
            String label
    ) {

        Button date = button(
                label + "\nاضغط لاختيار التاريخ",
                WHITE
        );

        date.setTextColor(DARK);
        date.setGravity(
                Gravity.CENTER_VERTICAL
                        | Gravity.RIGHT
        );

        page.addView(
                date,
                marginParams(-1, dp(62), 0, 3, 0, 3)
        );

        date.setOnClickListener(v -> {

            Calendar c = Calendar.getInstance();

            DatePickerDialog dialog =
                    new DatePickerDialog(
                            this,
                            (view, year, month, day) -> {

                                String value =
                                        String.format(
                                                Locale.US,
                                                "%02d/%02d/%04d",
                                                day,
                                                month + 1,
                                                year
                                        );

                                date.setText(
                                        label + "\n" + value
                                );

                                try {
                                    data.put(label, value);
                                } catch (Exception ignored) {
                                }
                            },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                    );

            dialog.show();
        });
    }

    private void addSpinner(
            LinearLayout page,
            JSONObject data,
            String label,
            String[] values
    ) {

        LinearLayout box = fieldBox();

        TextView title = text(
                label,
                13,
                DARK_GREEN,
                true
        );

        box.addView(
                title,
                marginParams(-1, -2, 0, 3, 0, 2)
        );

        Spinner spinner = spinner(values);

        box.addView(
                spinner,
                params(-1, dp(52))
        );

        page.addView(
                box,
                marginParams(-1, -2, 0, 3, 0, 3)
        );

        spinner.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            View view,
                            int position,
                            long id
                    ) {

                        try {

                            data.put(
                                    label,
                                    values[position]
                            );

                        } catch (Exception ignored) {
                        }
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent
                    ) {
                    }
                }
        );
    }

    private void addEditableField(
            LinearLayout page,
            JSONObject data,
            String field,
            String existing
    ) {

        if (field.equals("الفرع")) {

            addSpinner(
                    page,
                    data,
                    field,
                    BRANCHES
            );

        } else if (field.equals("نوع التكليف")) {

            addSpinner(
                    page,
                    data,
                    field,
                    ASSIGNMENT_TYPES
            );

        } else if (isYesNo(field)) {

            addSpinner(
                    page,
                    data,
                    field,
                    new String[]{"نعم", "لا"}
            );

        } else if (isDateField(field)) {

            addDateField(
                    page,
                    data,
                    field
            );

        } else if (isNumericField(field)) {

            addNumberField(
                    page,
                    data,
                    field
            );

        } else {

            EditText e = input(
                    field,
                    false
            );

            e.setText(existing);

            page.addView(
                    e,
                    marginParams(-1, -2, 0, 3, 0, 3)
            );

            e.setOnFocusChangeListener(
                    (v, hasFocus) -> {

                        if (!hasFocus) {

                            try {

                                data.put(
                                        field,
                                        e.getText().toString()
                                );

                            } catch (Exception ignored) {
                            }
                        }
                    }
            );
        }
    }

    private void addReadonly(
            LinearLayout page,
            String label,
            String value
    ) {

        EditText e = input(
                label,
                false
        );

        e.setText(value);
        e.setEnabled(false);

        page.addView(
                e,
                marginParams(-1, -2, 0, 3, 0, 3)
        );
    }

    /* =========================================================
       UI
       ========================================================= */

    private LinearLayout page() {

        LinearLayout p = new LinearLayout(this);

        p.setOrientation(
                LinearLayout.VERTICAL
        );

        p.setPadding(
                dp(15),
                dp(12),
                dp(15),
                dp(25)
        );

        p.setBackgroundColor(LIGHT);

        ScrollView scroll = new ScrollView(this);

        scroll.setFillViewport(true);

        scroll.addView(p);

        root = p;

        /*
         * يتم وضع الـScrollView كجذر للشاشة
         */
        LinearLayout wrapper = new LinearLayout(this);
        wrapper.setOrientation(LinearLayout.VERTICAL);
        wrapper.addView(
                scroll,
                params(-1, -1)
        );

        return wrapper;
    }

    private void addHeader(
            LinearLayout page,
            String title,
            String subtitle
    ) {

        LinearLayout header = new LinearLayout(this);

        header.setOrientation(
                LinearLayout.VERTICAL
        );

        header.setPadding(
                dp(18),
                dp(18),
                dp(18),
                dp(18)
        );

        header.setGravity(
                Gravity.RIGHT
        );

        header.setBackground(
                round(DARK_GREEN, 24, GOLD)
        );

        TextView t = text(
                title,
                23,
                WHITE,
                true
        );

        t.setGravity(
                Gravity.RIGHT
        );

        header.addView(
                t,
                params(-1, -2)
        );

        TextView s = text(
                subtitle,
                13,
                Color.rgb(225, 225, 225),
                false
        );

        s.setGravity(
                Gravity.RIGHT
        );

        header.addView(
                s,
                marginParams(-1, -2, 0, 4, 0, 0)
        );

        page.addView(
                header,
                marginParams(-1, -2, 0, 0, 0, 12)
        );
    }

    private void sectionTitle(
            LinearLayout page,
            String title
    ) {

        TextView t = text(
                title,
                18,
                DARK_GREEN,
                true
        );

        t.setGravity(
                Gravity.RIGHT
        );

        page.addView(
                t,
                marginParams(-1, -2, 0, 12, 0, 6)
        );
    }

    private void addQuick(
            LinearLayout page,
            String title,
            String subtitle,
            int color,
            final Runnable action
    ) {

        Button b = dashboardButton(
                title,
                subtitle,
                color,
                action
        );

        page.addView(
                b,
                marginParams(-1, dp(70), 0, 4, 0, 4)
        );
    }

    private Button dashboardButton(
            String title,
            String subtitle,
            int color,
            final Runnable action
    ) {

        Button b = button(
                title + "\n" + subtitle,
                color
        );

        b.setGravity(
                Gravity.CENTER_VERTICAL
                        | Gravity.RIGHT
        );

        b.setOnClickListener(
                v -> action.run()
        );

        return b;
    }

    private Button button(
            String title,
            int color
    ) {

        Button b = new Button(this);

        b.setText(title);
        b.setTextColor(WHITE);
        b.setTextSize(15);
        b.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        b.setGravity(
                Gravity.CENTER
        );

        b.setAllCaps(false);
        b.setPadding(
                dp(12),
                dp(5),
                dp(12),
                dp(5)
        );

        b.setBackground(
                round(color, 18, Color.TRANSPARENT)
        );

        return b;
    }

    private EditText input(
            String hint,
            boolean password
    ) {

        EditText e = new EditText(this);

        e.setHint(hint);
        e.setTextSize(15);
        e.setTextColor(DARK);
        e.setHintTextColor(GRAY);
        e.setGravity(
                Gravity.RIGHT
        );

        e.setPadding(
                dp(15),
                dp(5),
                dp(15),
                dp(5)
        );

        if (password) {

            e.setInputType(
                    InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD
            );
        }

        e.setBackground(
                round(WHITE, 16, Color.LTGRAY)
        );

        return e;
    }

    private Spinner spinner(
            String[] values
    ) {

        Spinner s = new Spinner(
                this,
                Spinner.MODE_DROPDOWN
        );

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        values
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        s.setAdapter(adapter);

        return s;
    }

    private LinearLayout panel() {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setPadding(
                dp(16),
                dp(16),
                dp(16),
                dp(16)
        );

        box.setBackground(
                round(WHITE, 22, Color.LTGRAY)
        );

        return box;
    }

    private LinearLayout fieldBox() {

        LinearLayout box = panel();

        box.setPadding(
                dp(12),
                dp(9),
                dp(12),
                dp(9)
        );

        return box;
    }

    private View dataRow(
            String label,
            String value
    ) {

        LinearLayout row = new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        row.setPadding(
                dp(12),
                dp(10),
                dp(12),
                dp(10)
        );

        row.setBackground(
                round(WHITE, 15, Color.LTGRAY)
        );

        TextView valueView =
                text(value, 14, DARK, false);

        valueView.setGravity(
                Gravity.RIGHT
        );

        TextView labelView =
                text(label, 13, DARK_GREEN, true);

        labelView.setGravity(
                Gravity.RIGHT
        );

        row.addView(
                valueView,
                new LinearLayout.LayoutParams(
                        0,
                        -2,
                        1
                )
        );

        row.addView(
                labelView,
                new LinearLayout.LayoutParams(
                        dp(145),
                        -2
                )
        );

        return row;
    }

    private View infoCard(
            String title,
            String value,
            int color
    ) {

        LinearLayout box = panel();

        TextView t =
                text(title, 15, color, true);

        TextView v =
                text(value, 17, DARK, true);

        box.addView(t);
        box.addView(
                v,
                marginParams(-1, -2, 0, 5, 0, 0)
        );

        return box;
    }

    private View reportCard(
            String title,
            String value,
            int color
    ) {

        LinearLayout box = panel();

        TextView valueText =
                text(value, 30, color, true);

        valueText.setGravity(
                Gravity.CENTER
        );

        TextView titleText =
                text(title, 14, DARK_GREEN, true);

        titleText.setGravity(
                Gravity.CENTER
        );

        box.addView(
                valueText,
                params(-1, dp(55))
        );

        box.addView(
                titleText,
                params(-1, -2)
        );

        return box;
    }

    private TextView text(
            String value,
            float size,
            int color,
            boolean bold
    ) {

        TextView t =
                new TextView(this);

        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);

        if (bold) {
            t.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );
        }

        return t;
    }

    private void addBack(
            LinearLayout page,
            final Runnable action
    ) {

        Button back =
                button("‹ العودة", GRAY);

        page.addView(
                back,
                marginParams(-1, dp(52), 0, 18, 0, 0)
        );

        back.setOnClickListener(
                v -> action.run()
        );
    }

    /* =========================================================
       COUNTERS
       ========================================================= */

    private int countStatus(
            String status
    ) {

        int count = 0;

        for (JSONObject p : personnel) {

            if (
                    p.optString(
                            "الحالة العسكرية الحالية",
                            ""
                    ).contains(status)
                            ||
                    p.optString(
                            "حالة انتهاء الخدمة/العضوية",
                            ""
                    ).equals(status)
            ) {

                count++;
            }
        }

        return count;
    }

    private int countSalary(
            String status
    ) {

        int count = 0;

        for (JSONObject p : personnel) {

            if (
                    p.optString(
                            "حالة المرتب",
                            ""
                    ).equals(status)
            ) {
                count++;
            }
        }

        return count;
    }

    private int countYes(
            String field
    ) {

        int count = 0;

        for (JSONObject p : personnel) {

            if (
                    p.optString(
                            field,
                            ""
                    ).equals("نعم")
            ) {
                count++;
            }
        }

        return count;
    }

    private int countServiceNotActive() {

        int count = 0;

        for (JSONObject p : personnel) {

            String s =
                    p.optString(
                            "حالة انتهاء الخدمة/العضوية",
                            "مستمر"
                    );

            if (!s.equals("مستمر")) {
                count++;
            }
        }

        return count;
    }

    /* =========================================================
       FIELD RULES
       ========================================================= */

    private boolean isYesNo(
            String field
    ) {

        return field.contains("نعم/لا")
                || field.equals("مشاركة في خطط أمنية")
                || field.equals("قبض على قضايا")
                || field.equals("حسن سيرة وسلوك");
    }

    private boolean isDateField(
            String field
    ) {

        return field.contains("تاريخ")
                || field.equals("تاريخ الميلاد");
    }

    private boolean isNumericField(
            String field
    ) {

        return field.contains("عدد")
                || field.equals("عدد الأبناء")
                || field.equals("الدرجة الوظيفية")
                || field.equals("سنة التخرج")
                || field.contains("نقاط")
                || field.contains("النسبة المئوية");
    }

    private String value(
            JSONObject object,
            String key
    ) {

        return object.optString(
                key,
                ""
        );
    }

    private double parseDouble(
            String value
    ) {

        try {

            return Double.parseDouble(
                    value.replace(",", ".")
            );

        } catch (Exception e) {

            return 0;
        }
    }

    /* =========================================================
       UI UTILITIES
       ========================================================= */

    private GradientDrawable round(
            int color,
            int radius,
            int strokeColor
    ) {

        GradientDrawable d =
                new GradientDrawable();

        d.setColor(color);
        d.setCornerRadius(dp(radius));

        if (strokeColor != Color.TRANSPARENT) {
            d.setStroke(
                    dp(1),
                    strokeColor
            );
        }

        return d;
    }

    private int dp(int value) {

        return (int) (
                value
                        * getResources()
                        .getDisplayMetrics()
                        .density
        );
    }

    private LinearLayout.LayoutParams params(
            int width,
            int height
    ) {

        return new LinearLayout.LayoutParams(
                width == -1 ? -1 : dp(width),
                height == -1 ? -1 : dp(height)
        );
    }

    private LinearLayout.LayoutParams marginParams(
            int width,
            int height,
            int left,
            int top,
            int right,
            int bottom
    ) {

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(
                        width == -1 ? -1 : dp(width),
                        height == -1 ? -1 : dp(height)
                );

        p.setMargins(
                dp(left),
                dp(top),
                dp(right),
                dp(bottom)
        );

        return p;
    }

    private void showDialog(
            String title,
            String message
    ) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(
                        "حسنًا",
                        null
                )
                .show();
    }
}