package ly.moi.forceerp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    private static final int DARK_GREEN =
            Color.rgb(18, 58, 45);

    private static final int GREEN =
            Color.rgb(27, 94, 32);

    private static final int GOLD =
            Color.rgb(181, 145, 57);

    private static final int LIGHT =
            Color.rgb(246, 247, 244);

    private static final int WHITE =
            Color.WHITE;

    private static final int DARK =
            Color.rgb(35, 39, 42);

    private static final int GRAY =
            Color.rgb(105, 110, 115);

    private static final int BORDER =
            Color.rgb(215, 218, 213);

    private static final int RED =
            Color.rgb(150, 35, 35);

    private LinearLayout root;

    private AppDatabase database;
    private PersonnelDao dao;

    private final ExecutorService databaseExecutor =
            Executors.newFixedThreadPool(3);

    private final Map<String, EditText> editors =
            new LinkedHashMap<>();

    private final Map<String, Spinner> spinners =
            new LinkedHashMap<>();

    private String currentUsername = "";

    /* =========================================================
       116 FIELD NAMES
       ========================================================= */

    private static final String[] FIELDS = {

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

    private static final String[] EXTRA_FIELDS = {

            "تكليف بالمنصب",
            "منطقة فرع الوسطى",
            "اسم المصرف",
            "اسم فرع المصرف",
            "رقم الحساب",
            "حالة المرتب",
            "سبب إيقاف المرتب",
            "تاريخ حالة المرتب",
            "ملاحظات مالية",
            "حالة العمل",
            "حالة العضوية",
            "سبب إنهاء العضوية",
            "تاريخ إنهاء العضوية",
            "حالة العجز/التقييم",
            "رقم مالي"
    };

    private static final String[] GENDER_TYPES = {

            "ذكر",
            "أنثى"
    };

    private static final String[] MARITAL_STATUSES = {

            "أعزب",
            "عزباء",
            "متزوج",
            "متزوجة",
            "مطلق",
            "مطلقة",
            "أرمل",
            "أرملة"
    };

    private static final String[] YES_NO = {

            "لا",
            "نعم"
    };

    private static final String[] JOB_TYPES = {

            "ضابط",
            "ضابط صف",
            "فرد",
            "موظف"
    };

    private static final String[] BRANCHES = {

            "الرئاسي",
            "فرع بنغازي الكبرى",
            "فرع المرج",
            "فرع البيضاء",
            "فرع درنة",
            "فرع شحات",
            "فرع القبة",
            "فرع البطنان",
            "فرع الوسطى",
            "فرع الجنوب الشرقي (الكفرة)",
            "فرع سبها",
            "فرع براك الشاطئ",
            "فرع غات",
            "دوريات صحراوية"
    };

    private static final String[] RANKS = {

            "فريق",
            "لواء",
            "عميد",
            "عقيد",
            "مقدم",
            "رائد",
            "نقيب",
            "ملازم أول",
            "ملازم",
            "رئيس عرفاء",
            "رئيس عريف",
            "عريف",
            "نائب عريف",
            "جندي",
            "موظف"
    };

    private static final String[] GRADES = {

            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16"
    };

    private static final String[] BLOOD_TYPES = {

            "غير محدد",
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"
    };

    private static final String[] ASSIGNMENT_TYPES = {

            "لا يوجد",
            "تكليف",
            "انتداب",
            "إعارة",
            "نقل",
            "مهمة"
    };

    private static final String[] POSITION_TYPES = {

            "لا يوجد",
            "رئيس قسم",
            "مدير إدارة",
            "رئيس مكتب",
            "مسؤول وحدة",
            "رئيس فرع",
            "مساعد رئيس فرع",
            "عضو"
    };

    private static final String[] SALARY_STATUSES = {

            "مرتب لحظي",
            "راتب حوافظ",
            "منحة",
            "لا يتقاضى مرتب",
            "موقوف"
    };

    private static final String[] WORK_STATUSES = {

            "مستمر في العمل",
            "موقوف عن العمل",
            "منتدب",
            "مكلف",
            "منقول",
            "في إجازة",
            "متقاعد",
            "منتهية خدمته"
    };

    private static final String[] MEMBERSHIP_STATUSES = {

            "على رأس العمل",
            "موقوف",
            "منتهي العضوية"
    };

    private static final String[] COURSE_TYPES = {

            "لا يوجد",
            "تدريبية",
            "تخصصية",
            "تأهيلية",
            "ورشة",
            "أخرى"
    };

    private static final String[] DOCUMENT_STATUSES = {

            "سارية",
            "منتهية",
            "قريبة الانتهاء",
            "غير متوفرة"
    };

    private static final String[] ALERT_STATUSES = {

            "لا يوجد",
            "تنبيه",
            "عاجل",
            "مراجعة"
    };

    private static final String[] DISABILITY_STATUSES = {

            "غير محدد",
            "لا يوجد",
            "عجز جزئي",
            "عجز كلي",
            "يحتاج تقييم"
    };

    /* =========================================================
       ARABIC -> JAVA FIELD MAP
       ========================================================= */

    private static final Map<String, String> FIELD_MAP =
            new LinkedHashMap<>();

    static {

        map("معرّف السجل", "recordId");
        map("الاسم الثلاثي", "fullName");
        map("الرتبة", "rank");
        map("اللقب", "surname");
        map("الفرع", "branch");
        map("اسم الأب", "fatherName");
        map("اسم الأم", "motherName");
        map("الصفة الوظيفية", "jobStatus");
        map("الرقم الوطني", "nationalNumber");
        map("رقم ورقة العائلة", "familyBookNumber");

        map("الرقم الحسابي", "accountNumber");
        map("حالة الزواج", "maritalStatus");
        map("اسم الزوج/ة", "spouseName");
        map("عدد الأبناء", "childrenCount");
        map("فصيلة الدم", "bloodType");
        map("قياس البدلة", "uniformSize");
        map("قياس الحذاء", "shoeSize");
        map("مدينة الإقامة", "residenceCity");
        map("رقم الهاتف", "phone");
        map("رقم البطاقة الشخصية", "personalCardNumber");

        map("رقم جواز السفر", "passportNumber");
        map("الخبرات والسيرة", "experience");
        map("اللغات المتقنة", "languages");
        map("رقم القرار والتعيين", "appointmentDecisionNumber");
        map("تاريخ قرار التعيين", "appointmentDecisionDate");
        map("رقم آخر ترقية", "lastPromotionNumber");
        map("تاريخ آخر ترقية", "lastPromotionDate");
        map("سجل الترقيات السابقة", "previousPromotions");
        map("تاريخ بداية الانتداب/التكليف", "assignmentStartDate");
        map("تاريخ انتهاء الانتداب/التكليف", "assignmentEndDate");

        map("الحالة العسكرية الحالية", "currentMilitaryStatus");
        map("الملاحظات والقيود", "notes");
        map("تاريخ الميلاد", "birthDate");
        map("العمر (تلقائي)", "age");
        map("مكان الميلاد", "birthPlace");
        map("العنوان التفصيلي", "detailedAddress");
        map("رقم الهاتف البديل", "alternativePhone");
        map("جهة الاتصال في الطوارئ (اسم)", "emergencyContactName");
        map("رقم الطوارئ", "emergencyContactPhone");
        map("الوحدة/القطاع", "unitSector");

        map("الدرجة الوظيفية", "jobGrade");
        map("الرتبة السابقة", "previousRank");
        map("رقم قرار الترقية", "promotionDecisionNumber");
        map("تاريخ قرار الترقية", "promotionDecisionDate");
        map("جهة قرار الترقية", "promotionDecisionAuthority");
        map("نوع التكليف", "assignmentType");
        map("جهة التكليف", "assignmentAuthority");
        map("مكان التكليف", "assignmentLocation");
        map("تاريخ بداية التكليف", "assignmentStart");
        map("تاريخ نهاية التكليف", "assignmentEnd");

        map("رقم قرار التكليف", "assignmentDecisionNumber");
        map("حالة التكليف", "assignmentStatus");
        map("نوع الدورة", "courseType");
        map("اسم الدورة", "courseName");
        map("الجهة التدريبية", "trainingAuthority");
        map("الدولة", "trainingCountry");
        map("تاريخ بداية الدورة", "courseStartDate");
        map("تاريخ نهاية الدورة", "courseEndDate");
        map("المستوى/التقدير", "courseLevel");
        map("رقم الشهادة", "certificateNumber");

        map("المؤهل", "qualification");
        map("التخصص", "specialization");
        map("الجهة التعليمية", "educationalInstitution");
        map("دولة التخرج", "graduationCountry");
        map("سنة التخرج", "graduationYear");
        map("التقدير", "graduationGrade");
        map("اللغة 1", "language1");
        map("مستوى القراءة 1", "language1Reading");
        map("مستوى الكتابة 1", "language1Writing");
        map("مستوى المحادثة 1", "language1Speaking");

        map("اللغة 2", "language2");
        map("مستوى القراءة 2", "language2Reading");
        map("مستوى الكتابة 2", "language2Writing");
        map("مستوى المحادثة 2", "language2Speaking");
        map("نوع الوثيقة", "documentType");
        map("رقم الوثيقة", "documentNumber");
        map("تاريخ الوثيقة", "documentDate");
        map("تاريخ انتهاء الوثيقة", "documentExpiryDate");
        map("الجهة المصدرة", "issuingAuthority");
        map("حالة الوثيقة", "documentStatus");

        map("حالة التنبيه", "alertStatus");
        map("نص التنبيه", "alertText");
        map("تاريخ التنبيه", "alertDate");
        map("يوم متبقي", "remainingDays");
        map("تاريخ الاستحقاق القادم للترقية", "nextPromotionEligibilityDate");
        map("المدة المتبقية للترقية (شهر)", "promotionRemainingMonths");
        map("حالة تنبيه الترقية", "promotionAlertStatus");
        map("الانوطة والأوسمة", "medalsAndAwards");
        map("عدد الترقيات الاستثنائية", "exceptionalPromotionsCount");
        map("أرقام قرارات الترقية الاستثنائية", "exceptionalPromotionDecisionNumbers");

        map("لفت النظر شفاهي (عدد)", "verbalReprimandCount");
        map("لفت النظر كتابي (عدد)", "writtenReprimandCount");
        map("إنذار شفاهي (عدد)", "verbalWarningCount");
        map("إنذار كتابي (عدد)", "writtenWarningCount");
        map("عدد إسقاط رتبة", "rankReductionCount");
        map("صحائف الاتهام (عدد)", "chargesCount");
        map("محاضر التحقيق الإداري (عدد)", "administrativeInvestigationCount");
        map("الإجازات المرضية (عدد الأيام)", "sickLeaveDays");
        map("الجرحى والمصابين (نعم/لا)", "injured");
        map("تاريخ الإصابة", "injuryDate");

        map("الشهداء (نعم/لا)", "martyr");
        map("تاريخ الاستشهاد", "martyrdomDate");
        map("رسائل الشكر (عدد)", "appreciationLettersCount");
        map("نقاط الإيجابيات (التقييم)", "positiveEvaluationPoints");
        map("نقاط السلبيات (التقييم)", "negativeEvaluationPoints");
        map("النسبة المئوية للتقييم السنوي", "annualEvaluationPercentage");
        map("التقدير السنوي (تلقائي)", "annualEvaluationGrade");
        map("مسار/رابط الصورة الشخصية", "photoPath");
        map("مشاركة في خطط أمنية", "securityPlanParticipation");
        map("قبض على قضايا", "caseArrestStatus");

        map("عدد القضايا", "caseCount");
        map("حسن سيرة وسلوك", "goodConduct");
        map("السلاح (النوع + الرقم)", "weapon");
        map("الجهاز اللاسلكي (النوع + الرقم)", "radio");
        map("المركبة (النوع + اللوحة)", "vehicle");
        map("معدات أخرى", "otherEquipment");

        map("تكليف بالمنصب", "assignedPosition");
        map("منطقة فرع الوسطى", "centralRegion");
        map("اسم المصرف", "bankName");
        map("اسم فرع المصرف", "bankBranchName");
        map("رقم الحساب", "bankAccountNumber");
        map("حالة المرتب", "salaryStatus");
        map("سبب إيقاف المرتب", "salarySuspensionReason");
        map("تاريخ حالة المرتب", "salaryStatusDate");
        map("ملاحظات مالية", "financialNotes");
        map("حالة العمل", "workStatus");
        map("حالة العضوية", "membershipStatus");
        map("سبب إنهاء العضوية", "terminationReason");
        map("تاريخ إنهاء العضوية", "terminationDate");
        map("حالة العجز/التقييم", "disabilityStatus");
        map("رقم مالي", "financialNumber");

        map("الجنس", "gender");
    }

    private static void map(
            String arabic,
            String javaName) {

        FIELD_MAP.put(
                arabic,
                javaName
        );
    }

    /* =========================================================
       LIFECYCLE
       ========================================================= */

    @Override
    protected void onCreate(
            Bundle savedInstanceState) {

        super.onCreate(
                savedInstanceState
        );

        database =
                DatabaseProvider.getDatabase(
                        getApplicationContext()
                );

        dao =
                database.personnelDao();

        showLogin();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        databaseExecutor.shutdown();
    }

    /* =========================================================
       LOGIN
       ========================================================= */

    private void showLogin() {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        box.setPadding(
                dp(24),
                dp(35),
                dp(24),
                dp(35)
        );

        box.setBackgroundColor(
                LIGHT
        );

        TextView heading =
                title(
                        "رئاسة قوة العمومية",
                        26
                );

        heading.setGravity(
                Gravity.CENTER
        );

        box.addView(
                heading
        );

        TextView app =
                title(
                        "منظومة إدارة القوة العمومية",
                        22
                );

        app.setTextColor(
                DARK_GREEN
        );

        app.setGravity(
                Gravity.CENTER
        );

        box.addView(
                app
        );

        TextView version =
                info(
                        "الإصدار V7.1"
                );

        version.setGravity(
                Gravity.CENTER
        );

        box.addView(
                version
        );

        addSpace(
                box,
                30
        );

        EditText username =
                input(
                        "اسم المستخدم"
                );

        box.addView(
                username,
                lp(-1, 56)
        );

        addSpace(
                box,
                10
        );

        EditText password =
                input(
                        "كلمة المرور"
                );

        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        box.addView(
                password,
                lp(-1, 56)
        );

        addSpace(
                box,
                18
        );

        Button login =
                primaryButton(
                        "تسجيل الدخول"
                );

        login.setOnClickListener(
                v -> {

                    String u =
                            safe(
                                    username
                                            .getText()
                                            .toString()
                            );

                    String p =
                            safe(
                                    password
                                            .getText()
                                            .toString()
                            );

                    if (u.equals("admin")
                            && p.equals("admin")) {

                        currentUsername =
                                u;

                        showDashboard();

                    } else {

                        Toast.makeText(
                                this,
                                "اسم المستخدم أو كلمة المرور غير صحيحة",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
        );

        box.addView(
                login,
                lp(-1, 56)
        );

        addSpace(
                box,
                18
        );

        TextView note =
                info(
                        "الحساب الأولي: admin / admin"
                );

        note.setGravity(
                Gravity.CENTER
        );

        box.addView(
                note
        );

        setContentView(
                box
        );
    }

    /* =========================================================
       ROOT WITH ONE SCROLLVIEW
       ========================================================= */

    private LinearLayout createRoot() {

        root =
                new LinearLayout(this);

        root.setOrientation(
                LinearLayout.VERTICAL
        );

        root.setPadding(
                dp(14),
                dp(16),
                dp(14),
                dp(24)
        );

        root.setBackgroundColor(
                LIGHT
        );

        ScrollView scroll =
                new ScrollView(this);

        scroll.setFillViewport(
                true
        );

        scroll.addView(
                root
        );

        setContentView(
                scroll
        );

        return root;
    }

    private LinearLayout page() {

        LinearLayout page =
                new LinearLayout(this);

        page.setOrientation(
                LinearLayout.VERTICAL
        );

        page.setPadding(
                dp(2),
                dp(2),
                dp(2),
                dp(20)
        );

        return page;
    }

    /* =========================================================
       DASHBOARD
       ========================================================= */

    private void showDashboard() {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        "الموقف العام للجهاز",
                        24
                )
        );

        page.addView(
                info(
                        "لوحة معلومات مرتبطة مباشرة بقاعدة البيانات"
                )
        );

        addSpace(
                page,
                14
        );

        LinearLayout human =
                card();

        human.addView(
                sectionTitle(
                        "القوة البشرية"
                )
        );

        addCountRow(
                human,
                "الضباط",
                () -> showFilteredJob(
                        "ضابط"
                ),
                () -> dao.getOfficersCount()
        );

        addCountRow(
                human,
                "ضباط الصف",
                () -> showFilteredJob(
                        "ضابط صف"
                ),
                () -> dao.getNonCommissionedOfficersCount()
        );

        addCountRow(
                human,
                "الأفراد",
                () -> showFilteredJob(
                        "فرد"
                ),
                () -> dao.getIndividualsCount()
        );

        addCountRow(
                human,
                "الموظفين",
                () -> showFilteredJob(
                        "موظف"
                ),
                () -> dao.getEmployeesCount()
        );

        addCountRow(
                human,
                "الذكور",
                () -> showFilteredGender(
                        "ذكر"
                ),
                () -> dao.getMaleCount()
        );

        addCountRow(
                human,
                "الإناث",
                () -> showFilteredGender(
                        "أنثى"
                ),
                () -> dao.getFemaleCount()
        );

        addCountRow(
                human,
                "الإجمالي",
                this::showPersonnelList,
                () -> dao.getTotalCount()
        );

        page.addView(
                human
        );

        addSpace(
                page,
                12
        );

        LinearLayout financial =
                card();

        financial.addView(
                sectionTitle(
                        "الموقف المالي"
                )
        );

        addCountRow(
                financial,
                "مرتب لحظي",
                () -> showSalaryList(
                        "مرتب لحظي"
                ),
                () -> dao.getCurrentSalaryCount()
        );

        addCountRow(
                financial,
                "راتب حوافظ",
                () -> showSalaryList(
                        "راتب حوافظ"
                ),
                () -> dao.getSalaryListsCount()
        );

        addCountRow(
                financial,
                "منحة",
                () -> showSalaryList(
                        "منحة"
                ),
                () -> dao.getGrantsCount()
        );

        addCountRow(
                financial,
                "لا يتقاضى مرتب",
                () -> showSalaryList(
                        "لا يتقاضى مرتب"
                ),
                () -> dao.getWithoutSalaryCount()
        );

        addCountRow(
                financial,
                "موقوف",
                () -> showSalaryList(
                        "موقوف"
                ),
                () -> dao.getSuspendedSalaryCount()
        );

        page.addView(
                financial
        );

        addSpace(
                page,
                12
        );

        LinearLayout quick =
                card();

        quick.addView(
                sectionTitle(
                        "الوصول السريع"
                )
        );

        addAction(
                quick,
                "إضافة منتسب جديد",
                () -> showPersonnelForm(
                        null
                ),
                true
        );

        addAction(
                quick,
                "قائمة المنتسبين",
                this::showPersonnelList,
                false
        );

        addAction(
                quick,
                "البحث الشامل",
                this::showGlobalSearch,
                false
        );

        addAction(
                quick,
                "بيان تفصيلي للجهاز",
                this::showDetailedReport,
                false
        );

        addAction(
                quick,
                "بيان الفروع",
                this::showBranchesReport,
                false
        );

        addAction(
                quick,
                "التقارير",
                this::showReports,
                false
        );

        addAction(
                quick,
                "تسجيل الخروج",
                this::showLogin,
                false
        );

        page.addView(
                quick
        );
    }

    private void addCountRow(
            LinearLayout parent,
            String label,
            Runnable click,
            CountSupplier supplier) {

        LinearLayout row =
                new LinearLayout(this);

        row.setOrientation(
                LinearLayout.HORIZONTAL
        );

        row.setGravity(
                Gravity.CENTER_VERTICAL
        );

        TextView name =
                label(
                        label
                );

        name.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        TextView value =
                label(
                        "..."
                );

        value.setGravity(
                Gravity.CENTER
        );

        value.setTextColor(
                DARK_GREEN
        );

        value.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        Button open =
                secondaryButton(
                        "عرض"
                );

        open.setOnClickListener(
                v -> click.run()
        );

        row.addView(
                name,
                new LinearLayout.LayoutParams(
                        0,
                        dp(52),
                        1
                )
        );

        row.addView(
                value,
                new LinearLayout.LayoutParams(
                        dp(65),
                        dp(52)
                )
        );

        row.addView(
                open,
                new LinearLayout.LayoutParams(
                        dp(75),
                        dp(52)
                )
        );

        parent.addView(
                row
        );

        databaseExecutor.execute(
                () -> {

                    int count =
                            supplier.get();

                    runOnUiThread(
                            () -> value.setText(
                                    String.valueOf(
                                            count
                                    )
                            )
                    );
                }
        );
    }

    private interface CountSupplier {
        int get();
    }

    private void addAction(
            LinearLayout parent,
            String text,
            Runnable action,
            boolean primary) {

        Button b =
                primary
                        ? primaryButton(text)
                        : secondaryButton(text);

        b.setOnClickListener(
                v -> action.run()
        );

        parent.addView(
                b,
                lp(-1, 54)
        );

        addSpace(
                parent,
                7
        );
    }

    /* =========================================================
       PERSONNEL LISTS
       ========================================================= */

    private void showPersonnelList() {

        loadList(
                "كافة المنتسبين",
                () -> dao.getAll()
        );
    }

    private void showFilteredJob(
            String job) {

        loadList(
                job,
                () -> dao.getByJobStatus(
                        job
                )
        );
    }

    private void showFilteredGender(
            String gender) {

        loadList(
                gender,
                () -> dao.getByGender(
                        gender
                )
        );
    }

    private void showSalaryList(
            String salary) {

        loadList(
                salary,
                () -> dao.getBySalaryStatus(
                        salary
                )
        );
    }

    private interface PersonnelSupplier {
        List<Personnel> get();
    }

    private void loadList(
            String title,
            PersonnelSupplier supplier) {

        showLoading();

        databaseExecutor.execute(
                () -> {

                    try {

                        List<Personnel> result =
                                supplier.get();

                        runOnUiThread(
                                () -> showPersonnelListPage(
                                        title,
                                        result
                                )
                        );

                    } catch (Exception e) {

                        runOnUiThread(
                                () -> showError(
                                        "تعذر تحميل البيانات",
                                        e
                                )
                        );
                    }
                }
        );
    }

    private void showPersonnelListPage(
            String listTitle,
            List<Personnel> records) {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        listTitle,
                        23
                )
        );

        page.addView(
                info(
                        "عدد السجلات: " +
                        records.size()
                )
        );

        addSpace(
                page,
                10
        );

        addAction(
                page,
                "إضافة منتسب جديد",
                () -> showPersonnelForm(
                        null
                ),
                true
        );

        if (records.isEmpty()) {

            page.addView(
                    emptyMessage(
                            "لا توجد سجلات."
                    )
            );

        } else {

            for (Personnel p : records) {

                page.addView(
                        personnelCard(
                                p
                        )
                );

                addSpace(
                        page,
                        8
                );
            }
        }

        page.addView(
                backButton(
                        "العودة إلى لوحة القيادة",
                        this::showDashboard
                )
        );
    }

    private View personnelCard(
            Personnel p) {

        LinearLayout c =
                card();

        TextView name =
                label(
                        safe(
                                p.fullName
                        )
                );

        name.setTextSize(
                18
        );

        name.setTextColor(
                DARK_GREEN
        );

        name.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        c.addView(
                name
        );

        c.addView(
                info(
                        "الجنس: " +
                        safe(p.gender)
                )
        );

        c.addView(
                info(
                        "الرتبة: " +
                        safe(p.rank)
                )
        );

        c.addView(
                info(
                        "الصفة: " +
                        safe(p.jobStatus)
                )
        );

        c.addView(
                info(
                        "الفرع: " +
                        safe(p.branch)
                )
        );

        c.addView(
                info(
                        "الرقم الوطني: " +
                        safe(p.nationalNumber)
                )
        );

        Button open =
                secondaryButton(
                        "فتح البطاقة"
                );

        open.setOnClickListener(
                v -> showPersonnelCard(
                        p
                )
        );

        c.addView(
                open,
                lp(-1, 50)
        );

        return c;
    }

    /* =========================================================
       PERSONNEL CARD
       ========================================================= */

    private void showPersonnelCard(
            Personnel p) {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        safe(p.fullName),
                        24
                )
        );

        page.addView(
                info(
                        "معرّف السجل: " +
                        safe(p.recordId)
                )
        );

        addSectionFromFields(
                page,
                "البيانات الأساسية",
                p,
                new String[]{
                        "الجنس",
                        "الرتبة",
                        "اللقب",
                        "الفرع",
                        "اسم الأب",
                        "اسم الأم",
                        "الصفة الوظيفية",
                        "الرقم الوطني",
                        "رقم ورقة العائلة",
                        "الرقم الحسابي"
                }
        );

        addSectionFromFields(
                page,
                "البيانات الشخصية",
                p,
                new String[]{
                        "حالة الزواج",
                        "اسم الزوج/ة",
                        "عدد الأبناء",
                        "فصيلة الدم",
                        "مدينة الإقامة",
                        "رقم الهاتف",
                        "رقم البطاقة الشخصية",
                        "رقم جواز السفر",
                        "تاريخ الميلاد",
                        "العمر (تلقائي)",
                        "مكان الميلاد",
                        "العنوان التفصيلي"
                }
        );

        addSectionFromFields(
                page,
                "العمل والتكليف",
                p,
                new String[]{
                        "الحالة العسكرية الحالية",
                        "الوحدة/القطاع",
                        "الدرجة الوظيفية",
                        "نوع التكليف",
                        "جهة التكليف",
                        "مكان التكليف",
                        "تاريخ بداية التكليف",
                        "تاريخ نهاية التكليف",
                        "رقم قرار التكليف",
                        "حالة التكليف",
                        "تكليف بالمنصب"
                }
        );

        addSectionFromFields(
                page,
                "البيانات المالية",
                p,
                new String[]{
                        "رقم مالي",
                        "اسم المصرف",
                        "اسم فرع المصرف",
                        "رقم الحساب",
                        "حالة المرتب",
                        "سبب إيقاف المرتب",
                        "تاريخ حالة المرتب",
                        "ملاحظات مالية"
                }
        );

        addSectionFromFields(
                page,
                "التدريب والمؤهلات",
                p,
                new String[]{
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
                        "التقدير"
                }
        );

        addSectionFromFields(
                page,
                "الترقيات والتنبيهات",
                p,
                new String[]{
                        "رقم آخر ترقية",
                        "تاريخ آخر ترقية",
                        "رقم قرار الترقية",
                        "تاريخ قرار الترقية",
                        "جهة قرار الترقية",
                        "تاريخ الاستحقاق القادم للترقية",
                        "المدة المتبقية للترقية (شهر)",
                        "حالة تنبيه الترقية",
                        "حالة التنبيه",
                        "نص التنبيه",
                        "يوم متبقي"
                }
        );

        addSectionFromFields(
                page,
                "الانضباط والقضايا",
                p,
                new String[]{
                        "لفت النظر شفاهي (عدد)",
                        "لفت النظر كتابي (عدد)",
                        "إنذار شفاهي (عدد)",
                        "إنذار كتابي (عدد)",
                        "عدد إسقاط رتبة",
                        "صحائف الاتهام (عدد)",
                        "محاضر التحقيق الإداري (عدد)",
                        "عدد القضايا",
                        "قبض على قضايا"
                }
        );

        addSectionFromFields(
                page,
                "التقييم والإصابات والشهداء",
                p,
                new String[]{
                        "الجرحى والمصابين (نعم/لا)",
                        "تاريخ الإصابة",
                        "الشهداء (نعم/لا)",
                        "تاريخ الاستشهاد",
                        "رسائل الشكر (عدد)",
                        "نقاط الإيجابيات (التقييم)",
                        "نقاط السلبيات (التقييم)",
                        "النسبة المئوية للتقييم السنوي",
                        "التقدير السنوي (تلقائي)"
                }
        );

        addSectionFromFields(
                page,
                "التجهيزات",
                p,
                new String[]{
                        "السلاح (النوع + الرقم)",
                        "الجهاز اللاسلكي (النوع + الرقم)",
                        "المركبة (النوع + اللوحة)",
                        "معدات أخرى"
                }
        );

        addSectionFromFields(
                page,
                "الحالة الإدارية",
                p,
                new String[]{
                        "حالة العمل",
                        "حالة العضوية",
                        "حالة العجز/التقييم",
                        "سبب إنهاء العضوية",
                        "تاريخ إنهاء العضوية"
                }
        );

        addAction(
                page,
                "تعديل بيانات المنتسب",
                () -> showPersonnelForm(
                        p
                ),
                true
        );

        addAction(
                page,
                "إنهاء العضوية",
                () -> showEndMembership(
                        p
                ),
                false
        );

        addAction(
                page,
                "حذف السجل",
                () -> confirmDelete(
                        p
                ),
                false
        );

        page.addView(
                backButton(
                        "العودة",
                        this::showPersonnelList
                )
        );
    }

    private void addSectionFromFields(
            LinearLayout page,
            String section,
            Personnel p,
            String[] fields) {

        LinearLayout c =
                card();

        c.addView(
                sectionTitle(
                        section
                )
        );

        for (String label : fields) {

            String value =
                    getEntityValue(
                            p,
                            label
                    );

            c.addView(
                    info(
                            label +
                            ": " +
                            safe(value)
                    )
            );
        }

        page.addView(
                c
        );

        addSpace(
                page,
                10
        );
    }

    /* =========================================================
       FORM
       ========================================================= */

    private void showPersonnelForm(
            Personnel existing) {

        editors.clear();
        spinners.clear();

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        existing == null
                                ? "إضافة منتسب جديد"
                                : "تعديل بيانات المنتسب",
                        23
                )
        );

        page.addView(
                info(
                        "جميع البيانات تحفظ في قاعدة البيانات المحلية."
                )
        );

        addFormSection(
                page,
                "البيانات الأساسية",
                existing,
                new String[]{
                        "معرّف السجل",
                        "الاسم الثلاثي",
                        "الجنس",
                        "الرتبة",
                        "اللقب",
                        "الفرع",
                        "اسم الأب",
                        "اسم الأم",
                        "الصفة الوظيفية",
                        "الرقم الوطني",
                        "رقم ورقة العائلة"
                }
        );

        addFormSection(
                page,
                "البيانات الشخصية",
                existing,
                new String[]{
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
                }
        );

        addFormSection(
                page,
                "التعيين والترقيات",
                existing,
                new String[]{
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
                        "الوحدة/القطاع"
                }
        );

        addFormSection(
                page,
                "الوظيفة والتكليف",
                existing,
                new String[]{
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
                        "تكليف بالمنصب"
                }
        );

        addFormSection(
                page,
                "التدريب والمؤهلات",
                existing,
                new String[]{
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
                        "مستوى المحادثة 2"
                }
        );

        addFormSection(
                page,
                "الوثائق والتنبيهات",
                existing,
                new String[]{
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
                }
        );

        addFormSection(
                page,
                "الترقيات الاستثنائية والانضباط",
                existing,
                new String[]{
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
                }
        );

        addFormSection(
                page,
                "الإصابات والشهداء والتقييم",
                existing,
                new String[]{
                        "الجرحى والمصابين (نعم/لا)",
                        "تاريخ الإصابة",
                        "الشهداء (نعم/لا)",
                        "تاريخ الاستشهاد",
                        "رسائل الشكر (عدد)",
                        "نقاط الإيجابيات (التقييم)",
                        "نقاط السلبيات (التقييم)",
                        "النسبة المئوية للتقييم السنوي",
                        "التقدير السنوي (تلقائي)",
                        "مسار/رابط الصورة الشخصية"
                }
        );

        addFormSection(
                page,
                "الأمن والقضايا والتجهيزات",
                existing,
                new String[]{
                        "مشاركة في خطط أمنية",
                        "قبض على قضايا",
                        "عدد القضايا",
                        "حسن سيرة وسلوك",
                        "السلاح (النوع + الرقم)",
                        "الجهاز اللاسلكي (النوع + الرقم)",
                        "المركبة (النوع + اللوحة)",
                        "معدات أخرى"
                }
        );

        addFormSection(
                page,
                "البيانات المالية",
                existing,
                new String[]{
                        "رقم مالي",
                        "اسم المصرف",
                        "اسم فرع المصرف",
                        "رقم الحساب",
                        "حالة المرتب",
                        "سبب إيقاف المرتب",
                        "تاريخ حالة المرتب",
                        "ملاحظات مالية"
                }
        );

        addFormSection(
                page,
                "الحالة الإدارية",
                existing,
                new String[]{
                        "منطقة فرع الوسطى",
                        "حالة العمل",
                        "حالة العضوية",
                        "حالة العجز/التقييم",
                        "سبب إنهاء العضوية",
                        "تاريخ إنهاء العضوية"
                }
        );

        addSpace(
                page,
                15
        );

        Button save =
                primaryButton(
                        existing == null
                                ? "حفظ المنتسب"
                                : "حفظ التعديلات"
                );

        save.setOnClickListener(
                v -> savePersonnel(
                        existing
                )
        );

        page.addView(
                save,
                lp(-1, 58)
        );

        addSpace(
                page,
                8
        );

        page.addView(
                backButton(
                        "إلغاء",
                        existing == null
                                ? this::showDashboard
                                : () -> showPersonnelCard(
                                        existing
                                )
                )
        );
    }

    private void addFormSection(
            LinearLayout page,
            String section,
            Personnel existing,
            String[] fields) {

        LinearLayout box =
                card();

        box.addView(
                sectionTitle(
                        section
                )
        );

        for (String field : fields) {

            if (field.equals("العمر (تلقائي)")
                    || field.equals("التقدير السنوي (تلقائي)")) {

                EditText e =
                        input(
                                field
                        );

                e.setEnabled(
                        false
                );

                e.setText(
                        getEntityValue(
                                existing,
                                field
                        )
                );

                editors.put(
                        field,
                        e
                );

                box.addView(
                        e,
                        lp(-1, 55)
                );

                addSpace(
                        box,
                        6
                );

                continue;
            }

            String[] choices =
                    choicesFor(
                            field
                    );

            if (choices != null) {

                addSpinner(
                        box,
                        field,
                        choices,
                        existing
                );

            } else {

                addEditor(
                        box,
                        field,
                        existing
                );
            }
        }

        page.addView(
                box
        );

        addSpace(
                page,
                10
        );
    }

    private void addEditor(
            LinearLayout parent,
            String field,
            Personnel existing) {

        EditText e =
                input(
                        field
                );

        String value =
                getEntityValue(
                        existing,
                        field
                );

        e.setText(
                safe(value)
        );

        if (isNumberField(field)) {

            e.setInputType(
                    InputType.TYPE_CLASS_NUMBER |
                    InputType.TYPE_NUMBER_FLAG_DECIMAL
            );
        }

        if (isLongTextField(field)) {

            e.setMinLines(
                    3
            );

            e.setGravity(
                    Gravity.TOP |
                    Gravity.RIGHT
            );
        }

        editors.put(
                field,
                e
        );

        parent.addView(
                e,
                lp(-1, isLongTextField(field)
                        ? 95
                        : 55)
        );

        addSpace(
                parent,
                6
        );
    }

    private void addSpinner(
            LinearLayout parent,
            String field,
            String[] choices,
            Personnel existing) {

        TextView caption =
                label(
                        field
                );

        parent.addView(
                caption
        );

        Spinner spinner =
                new Spinner(
                        this
                );

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_item,
                        choices
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(
                adapter
        );

        String current =
                getEntityValue(
                        existing,
                        field
                );

        if (current != null
                && !current.isEmpty()) {

            int index =
                    Arrays.asList(
                            choices
                    ).indexOf(
                            current
                    );

            if (index >= 0) {

                spinner.setSelection(
                        index
                );
            }
        }

        spinners.put(
                field,
                spinner
        );

        parent.addView(
                spinner,
                lp(-1, 55)
        );

        addSpace(
                parent,
                6
        );
    }

    private String[] choicesFor(
            String field) {

        if (field.equals("الجنس")) {
            return GENDER_TYPES;
        }

        if (field.equals("حالة الزواج")) {
            return MARITAL_STATUSES;
        }

        if (field.equals("الصفة الوظيفية")) {
            return JOB_TYPES;
        }

        if (field.equals("الفرع")) {
            return BRANCHES;
        }

        if (field.equals("الرتبة")
                || field.equals("الرتبة السابقة")) {
            return RANKS;
        }

        if (field.equals("الدرجة الوظيفية")) {
            return GRADES;
        }

        if (field.equals("فصيلة الدم")) {
            return BLOOD_TYPES;
        }

        if (field.equals("نوع التكليف")
                || field.equals("حالة التكليف")) {
            return ASSIGNMENT_TYPES;
        }

        if (field.equals("تكليف بالمنصب")) {
            return POSITION_TYPES;
        }

        if (field.equals("نوع الدورة")) {
            return COURSE_TYPES;
        }

        if (field.equals("حالة الوثيقة")) {
            return DOCUMENT_STATUSES;
        }

        if (field.equals("حالة التنبيه")
                || field.equals("حالة تنبيه الترقية")) {
            return ALERT_STATUSES;
        }

        if (field.equals("حالة المرتب")) {
            return SALARY_STATUSES;
        }

        if (field.equals("حالة العمل")) {
            return WORK_STATUSES;
        }

        if (field.equals("حالة العضوية")) {
            return MEMBERSHIP_STATUSES;
        }

        if (field.equals("حالة العجز/التقييم")) {
            return DISABILITY_STATUSES;
        }

        if (field.equals("الجرحى والمصابين (نعم/لا)")
                || field.equals("الشهداء (نعم/لا)")
                || field.equals("مشاركة في خطط أمنية")
                || field.equals("قبض على قضايا")
                || field.equals("حسن سيرة وسلوك")) {
            return YES_NO;
        }

        return null;
    }

    private boolean isNumberField(
            String field) {

        return field.equals("عدد الأبناء")
                || field.contains("(عدد)")
                || field.contains("(عدد الأيام)")
                || field.equals("يوم متبقي")
                || field.contains("المدة المتبقية")
                || field.contains("النسبة المئوية")
                || field.contains("نقاط الإيجابيات")
                || field.contains("نقاط السلبيات")
                || field.equals("سنة التخرج");
    }

    private boolean isLongTextField(
            String field) {

        return field.contains("الملاحظات")
                || field.contains("الخبرات")
                || field.contains("السيرة")
                || field.contains("سجل")
                || field.equals("نص التنبيه")
                || field.equals("معدات أخرى");
    }

    /* =========================================================
       SAVE
       ========================================================= */

    private void savePersonnel(
            Personnel existing) {

        Map<String, String> values =
                collectForm();

        String fullName =
                safe(
                        values.get(
                                "الاسم الثلاثي"
                        )
                );

        if (fullName.trim().isEmpty()) {

            Toast.makeText(
                    this,
                    "الاسم الثلاثي مطلوب",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        String gender =
                safe(
                        values.get(
                                "الجنس"
                        )
                );

        if (gender.isEmpty()) {

            Toast.makeText(
                    this,
                    "يرجى تحديد الجنس",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        applyAutomaticValues(
                values
        );

        showLoading();

        databaseExecutor.execute(
                () -> {

                    try {

                        Personnel entity =
                                toPersonnel(
                                        values,
                                        existing
                                );

                        long id;

                        if (existing == null) {

                            id =
                                    dao.insert(
                                            entity
                                    );

                        } else {

                            dao.update(
                                    entity
                            );

                            id =
                                    entity.id;
                        }

                        Personnel saved =
                                dao.getById(
                                        id
                                );

                        runOnUiThread(
                                () -> {

                                    Toast.makeText(
                                            this,
                                            "تم حفظ السجل بنجاح",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                    if (saved != null) {

                                        showPersonnelCard(
                                                saved
                                        );

                                    } else {

                                        showDashboard();
                                    }
                                }
                        );

                    } catch (Exception e) {

                        runOnUiThread(
                                () -> showError(
                                        "حدث خطأ أثناء الحفظ",
                                        e
                                )
                        );
                    }
                }
        );
    }

    private Map<String, String> collectForm() {

        Map<String, String> values =
                new LinkedHashMap<>();

        for (String field : FIELD_MAP.keySet()) {

            if (editors.containsKey(field)) {

                values.put(
                        field,
                        safe(
                                editors.get(
                                        field
                                ).getText()
                                        .toString()
                        )
                );
            }

            if (spinners.containsKey(field)) {

                Object selected =
                        spinners.get(
                                field
                        ).getSelectedItem();

                values.put(
                        field,
                        selected == null
                                ? ""
                                : selected.toString()
                );
            }
        }

        return values;
    }

    /* =========================================================
       AUTOMATIC CALCULATIONS
       ========================================================= */

    private void applyAutomaticValues(
            Map<String, String> values) {

        String birthDate =
                safe(
                        values.get(
                                "تاريخ الميلاد"
                        )
                );

        values.put(
                "العمر (تلقائي)",
                String.valueOf(
                        calculateAge(
                                birthDate
                        )
                )
        );

        double percentage =
                parseDouble(
                        values.get(
                                "النسبة المئوية للتقييم السنوي"
                        )
                );

        values.put(
                "التقدير السنوي (تلقائي)",
                evaluationGrade(
                        percentage
                )
        );

        String expiry =
                safe(
                        values.get(
                                "تاريخ انتهاء الوثيقة"
                        )
                );

        if (!expiry.isEmpty()) {

            int days =
                    daysUntil(
                            expiry
                    );

            values.put(
                    "يوم متبقي",
                    String.valueOf(
                            Math.max(
                                    days,
                                    0
                            )
                    )
            );

            if (days < 0) {

                values.put(
                        "حالة التنبيه",
                        "عاجل"
                );

            } else if (days <= 30) {

                values.put(
                        "حالة التنبيه",
                        "تنبيه"
                );
            }
        }

        String promotionDate =
                safe(
                        values.get(
                                "تاريخ الاستحقاق القادم للترقية"
                        )
                );

        if (!promotionDate.isEmpty()) {

            int months =
                    monthsUntil(
                            promotionDate
                    );

            values.put(
                    "المدة المتبقية للترقية (شهر)",
                    String.valueOf(
                            Math.max(
                                    months,
                                    0
                            )
                    )
            );

            if (months <= 0) {

                values.put(
                        "حالة تنبيه الترقية",
                        "عاجل"
                );

            } else if (months <= 3) {

                values.put(
                        "حالة تنبيه الترقية",
                        "تنبيه"
                );
            }
        }
    }

    /* =========================================================
       REFLECTION ENTITY MAPPING
       ========================================================= */

    private Personnel toPersonnel(
            Map<String, String> values,
            Personnel existing)
            throws Exception {

        Personnel p =
                existing == null
                        ? new Personnel()
                        : existing;

        for (Map.Entry<String, String> entry
                : FIELD_MAP.entrySet()) {

            String arabic =
                    entry.getKey();

            String javaField =
                    entry.getValue();

            if (arabic.equals("العمر (تلقائي)")
                    || arabic.equals("التقدير السنوي (تلقائي)")) {
                continue;
            }

            Field field =
                    Personnel.class.getField(
                            javaField
                    );

            String value =
                    safe(
                            values.get(
                                    arabic
                            )
                    );

            setField(
                    p,
                    field,
                    value
            );
        }

        String birth =
                safe(
                        values.get(
                                "تاريخ الميلاد"
                        )
                );

        p.age =
                String.valueOf(
                        calculateAge(
                                birth
                        )
                );

        p.annualEvaluationGrade =
                evaluationGrade(
                        p.annualEvaluationPercentage
                );

        if (existing == null) {

            p.createdAt =
                    now();

        }

        p.updatedAt =
                now();

        return p;
    }

    private void setField(
            Personnel p,
            Field field,
            String value)
            throws Exception {

        Class<?> type =
                field.getType();

        if (type == String.class) {

            field.set(
                    p,
                    value
            );

        } else if (type == int.class
                || type == Integer.class) {

            field.setInt(
                    p,
                    parseInt(
                            value
                    )
            );

        } else if (type == double.class
                || type == Double.class) {

            field.setDouble(
                    p,
                    parseDouble(
                            value
                    )
            );
        }
    }

    private String getEntityValue(
            Personnel p,
            String arabic) {

        if (p == null) {
            return "";
        }

        String javaField =
                FIELD_MAP.get(
                        arabic
                );

        if (javaField == null) {
            return "";
        }

        try {

            Field field =
                    Personnel.class.getField(
                            javaField
                    );

            Object value =
                    field.get(
                            p
                    );

            return value == null
                    ? ""
                    : String.valueOf(
                            value
                    );

        } catch (Exception e) {

            return "";
        }
    }

    /* =========================================================
       SEARCH
       ========================================================= */

    private void showGlobalSearch() {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        "البحث الشامل",
                        23
                )
        );

        page.addView(
                info(
                        "ابحث بالاسم أو الرقم الوطني أو الحساب أو الهاتف أو الرقم المالي."
                )
        );

        EditText search =
                input(
                        "أدخل كلمة البحث"
                );

        page.addView(
                search,
                lp(-1, 56)
        );

        addSpace(
                page,
                10
        );

        Button button =
                primaryButton(
                        "بحث"
                );

        button.setOnClickListener(
                v -> {

                    String query =
                            safe(
                                    search.getText()
                                            .toString()
                            );

                    if (query.isEmpty()) {

                        Toast.makeText(
                                this,
                                "أدخل قيمة للبحث",
                                Toast.LENGTH_SHORT
                        ).show();

                        return;
                    }

                    showLoading();

                    databaseExecutor.execute(
                            () -> {

                                List<Personnel> result =
                                        dao.globalSearch(
                                                query
                                        );

                                runOnUiThread(
                                        () -> showPersonnelListPage(
                                                "نتائج البحث",
                                                result
                                        )
                                );
                            }
                    );
                }
        );

        page.addView(
                button,
                lp(-1, 55)
        );

        addSpace(
                page,
                12
        );

        page.addView(
                backButton(
                        "العودة",
                        this::showDashboard
                )
        );
    }

    /* =========================================================
       END MEMBERSHIP
       ========================================================= */

    private void showEndMembership(
            Personnel p) {

        final EditText reason =
                input(
                        "سبب إنهاء العضوية"
                );

        new AlertDialog.Builder(this)
                .setTitle(
                        "إنهاء العضوية"
                )
                .setMessage(
                        "سيتم تسجيل السجل كمنتهي العضوية."
                )
                .setView(
                        reason
                )
                .setNegativeButton(
                        "إلغاء",
                        null
                )
                .setPositiveButton(
                        "تأكيد",
                        (dialog, which) -> {

                            databaseExecutor.execute(
                                    () -> {

                                        p.membershipStatus =
                                                "منتهي العضوية";

                                        p.terminationStatus =
                                                "منتهي العضوية";

                                        p.terminationDate =
                                                now();

                                        p.terminationReason =
                                                safe(
                                                        reason.getText()
                                                                .toString()
                                                );

                                        p.updatedAt =
                                                now();

                                        dao.update(
                                                p
                                        );

                                        runOnUiThread(
                                                () -> {

                                                    Toast.makeText(
                                                            this,
                                                            "تم تسجيل إنهاء العضوية",
                                                            Toast.LENGTH_SHORT
                                                    ).show();

                                                    showPersonnelCard(
                                                            p
                                                    );
                                                }
                                        );
                                    }
                            );
                        }
                )
                .show();
    }

    /* =========================================================
       DELETE
       ========================================================= */

    private void confirmDelete(
            Personnel p) {

        new AlertDialog.Builder(this)
                .setTitle(
                        "حذف السجل"
                )
                .setMessage(
                        "هل تريد حذف سجل هذا المنتسب نهائيًا؟"
                )
                .setNegativeButton(
                        "إلغاء",
                        null
                )
                .setPositiveButton(
                        "حذف",
                        (dialog, which) -> {

                            databaseExecutor.execute(
                                    () -> {

                                        dao.delete(
                                                p
                                        );

                                        runOnUiThread(
                                                () -> {

                                                    Toast.makeText(
                                                            this,
                                                            "تم حذف السجل",
                                                            Toast.LENGTH_SHORT
                                                    ).show();

                                                    showPersonnelList();
                                                }
                                        );
                                    }
                            );
                        }
                )
                .show();
    }

    /* =========================================================
       DETAILED REPORT
       ========================================================= */

    private void showDetailedReport() {

        showLoading();

        databaseExecutor.execute(
                () -> {

                    int total =
                            dao.getTotalCount();

                    int officers =
                            dao.getOfficersCount();

                    int nco =
                            dao.getNonCommissionedOfficersCount();

                    int individuals =
                            dao.getIndividualsCount();

                    int employees =
                            dao.getEmployeesCount();

                    int males =
                            dao.getMaleCount();

                    int females =
                            dao.getFemaleCount();

                    int martyrs =
                            dao.getMartyrsCount();

                    int injured =
                            dao.getInjuredCount();

                    int cases =
                            dao.getTotalCaseCount();

                    int trained =
                            dao.getTrainedPersonnelCount();

                    int qualified =
                            dao.getQualifiedPersonnelCount();

                    int alerts =
                            dao.getAlertsCount();

                    int expired =
                            dao.getExpiredDocumentsCount();

                    int terminated =
                            dao.getTerminatedCount();

                    runOnUiThread(
                            () -> {

                                LinearLayout r =
                                        createRoot();

                                LinearLayout page =
                                        page();

                                r.addView(
                                        page
                                );

                                page.addView(
                                        title(
                                                "بيان تفصيلي للجهاز",
                                                24
                                        )
                                );

                                page.addView(
                                        info(
                                                "تقرير مباشر من قاعدة البيانات"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الإجمالي",
                                        total,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "الضباط",
                                        officers,
                                        () -> showFilteredJob(
                                                "ضابط"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "ضباط الصف",
                                        nco,
                                        () -> showFilteredJob(
                                                "ضابط صف"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الأفراد",
                                        individuals,
                                        () -> showFilteredJob(
                                                "فرد"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الموظفون",
                                        employees,
                                        () -> showFilteredJob(
                                                "موظف"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الذكور",
                                        males,
                                        () -> showFilteredGender(
                                                "ذكر"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الإناث",
                                        females,
                                        () -> showFilteredGender(
                                                "أنثى"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الشهداء",
                                        martyrs,
                                        () -> loadSpecialList(
                                                "الشهداء",
                                                () -> dao.getAll()
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الجرحى والمصابون",
                                        injured,
                                        () -> loadSpecialList(
                                                "الجرحى والمصابون",
                                                () -> dao.getAll()
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "إجمالي القضايا",
                                        cases,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "المتدربون",
                                        trained,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "الحاصلون على مؤهلات",
                                        qualified,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "التنبيهات",
                                        alerts,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "الوثائق المنتهية",
                                        expired,
                                        () -> showPersonnelList()
                                );

                                addReportNumber(
                                        page,
                                        "منتهو العضوية",
                                        terminated,
                                        () -> showPersonnelList()
                                );

                                page.addView(
                                        backButton(
                                                "العودة",
                                                this::showDashboard
                                        )
                                );
                            }
                    );
                }
        );
    }

    private void addReportNumber(
            LinearLayout page,
            String label,
            int value,
            Runnable click) {

        LinearLayout c =
                card();

        c.addView(
                sectionTitle(
                        label
                )
        );

        TextView number =
                title(
                        String.valueOf(
                                value
                        ),
                        28
                );

        number.setTextColor(
                DARK_GREEN
        );

        number.setGravity(
                Gravity.CENTER
        );

        c.addView(
                number,
                lp(-1, 55)
        );

        Button view =
                secondaryButton(
                        "عرض السجلات"
                );

        view.setOnClickListener(
                v -> click.run()
        );

        c.addView(
                view,
                lp(-1, 48)
        );

        page.addView(
                c
        );

        addSpace(
                page,
                8
        );
    }

    private void loadSpecialList(
            String title,
            PersonnelSupplier supplier) {

        loadList(
                title,
                supplier
        );
    }

    /* =========================================================
       BRANCH REPORT
       ========================================================= */

    private void showBranchesReport() {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        "بيان الفروع",
                        24
                )
        );

        page.addView(
                info(
                        "الأعداد التالية مرتبطة مباشرة بجدول المنتسبين."
                )
        );

        for (String branch : BRANCHES) {

            LinearLayout c =
                    card();

            c.addView(
                    sectionTitle(
                            branch
                    )
            );

            TextView count =
                    info(
                            "جاري التحميل..."
                    );

            c.addView(
                    count
            );

            Button open =
                    secondaryButton(
                            "فتح بيان الفرع"
                    );

            open.setOnClickListener(
                    v -> showBranchReport(
                            branch
                    )
            );

            c.addView(
                    open,
                    lp(-1, 48)
            );

            page.addView(
                    c
            );

            addSpace(
                    page,
                    8
            );

            databaseExecutor.execute(
                    () -> {

                        int value =
                                dao.getBranchCount(
                                        branch
                                );

                        runOnUiThread(
                                () -> count.setText(
                                        "عدد المنتسبين: " +
                                        value
                                )
                        );
                    }
            );
        }

        page.addView(
                backButton(
                        "العودة",
                        this::showDashboard
                )
        );
    }

    private void showBranchReport(
            String branch) {

        databaseExecutor.execute(
                () -> {

                    List<Personnel> list =
                            dao.getByBranch(
                                    branch
                            );

                    int total =
                            dao.getBranchCount(
                                    branch
                            );

                    int officers =
                            dao.getBranchJobStatusCount(
                                    branch,
                                    "ضابط"
                            );

                    int nco =
                            dao.getBranchJobStatusCount(
                                    branch,
                                    "ضابط صف"
                            );

                    int individuals =
                            dao.getBranchJobStatusCount(
                                    branch,
                                    "فرد"
                            );

                    int employees =
                            dao.getBranchJobStatusCount(
                                    branch,
                                    "موظف"
                            );

                    int males =
                            dao.getBranchGenderCount(
                                    branch,
                                    "ذكر"
                            );

                    int females =
                            dao.getBranchGenderCount(
                                    branch,
                                    "أنثى"
                            );

                    runOnUiThread(
                            () -> {

                                LinearLayout r =
                                        createRoot();

                                LinearLayout page =
                                        page();

                                r.addView(
                                        page
                                );

                                page.addView(
                                        title(
                                                branch,
                                                24
                                        )
                                );

                                page.addView(
                                        info(
                                                "بيان تفصيلي مباشر للفرع"
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الإجمالي",
                                        total,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الضباط",
                                        officers,
                                        () -> loadList(
                                                branch + " - ضباط",
                                                () -> dao.getByBranch(
                                                        branch
                                                )
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "ضباط الصف",
                                        nco,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الأفراد",
                                        individuals,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الموظفون",
                                        employees,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الذكور",
                                        males,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                addReportNumber(
                                        page,
                                        "الإناث",
                                        females,
                                        () -> showPersonnelListPage(
                                                branch,
                                                list
                                        )
                                );

                                page.addView(
                                        sectionTitle(
                                                "قائمة منتسبي الفرع"
                                        )
                                );

                                for (Personnel p : list) {

                                    page.addView(
                                            personnelCard(
                                                    p
                                            )
                                    );

                                    addSpace(
                                            page,
                                            8
                                    );
                                }

                                page.addView(
                                        backButton(
                                                "العودة إلى الفروع",
                                                this::showBranchesReport
                                        )
                                );
                            }
                    );
                }
        );
    }

    /* =========================================================
       REPORTS
       ========================================================= */

    private void showReports() {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        "التقارير",
                        24
                )
        );

        addAction(
                page,
                "البيان التفصيلي للجهاز",
                this::showDetailedReport,
                true
        );

        addAction(
                page,
                "بيان الفروع",
                this::showBranchesReport,
                false
        );

        addAction(
                page,
                "قائمة جميع المنتسبين",
                this::showPersonnelList,
                false
        );

        addAction(
                page,
                "البحث الشامل",
                this::showGlobalSearch,
                false
        );

        page.addView(
                backButton(
                        "العودة",
                        this::showDashboard
                )
        );
    }

    /* =========================================================
       SETTINGS
       ========================================================= */

    private void showSettings() {

        LinearLayout r =
                createRoot();

        LinearLayout page =
                page();

        r.addView(
                page
        );

        page.addView(
                title(
                        "الإعدادات",
                        24
                )
        );

        page.addView(
                info(
                        "المستخدم الحالي: " +
                        safe(currentUsername)
                )
        );

        page.addView(
                info(
                        "قاعدة البيانات: force_erp_v7_1.db"
                )
        );

        page.addView(
                info(
                        "إصدار قاعدة البيانات: 2"
                )
        );

        addSpace(
                page,
                15
        );

        addAction(
                page,
                "تسجيل الخروج",
                this::showLogin,
                false
        );

        page.addView(
                backButton(
                        "العودة",
                        this::showDashboard
                )
        );
    }

    /* =========================================================
       UI HELPERS
       ========================================================= */

    private LinearLayout card() {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setPadding(
                dp(14),
                dp(14),
                dp(14),
                dp(14)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                WHITE
        );

        bg.setStroke(
                dp(1),
                BORDER
        );

        bg.setCornerRadius(
                dp(12)
        );

        box.setBackground(
                bg
        );

        return box;
    }

    private TextView title(
            String text,
            int size) {

        TextView t =
                new TextView(this);

        t.setText(
                safe(text)
        );

        t.setTextSize(
                size
        );

        t.setTextColor(
                DARK
        );

        t.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        t.setGravity(
                Gravity.RIGHT
        );

        t.setPadding(
                0,
                dp(5),
                0,
                dp(5)
        );

        t.setTextDirection(
                View.TEXT_DIRECTION_RTL
        );

        return t;
    }

    private TextView sectionTitle(
            String text) {

        TextView t =
                title(
                        text,
                        18
                );

        t.setTextColor(
                DARK_GREEN
        );

        t.setPadding(
                0,
                0,
                0,
                dp(10)
        );

        return t;
    }

    private TextView label(
            String text) {

        TextView t =
                new TextView(this);

        t.setText(
                safe(text)
        );

        t.setTextSize(
                15
        );

        t.setTextColor(
                DARK
        );

        t.setGravity(
                Gravity.RIGHT |
                Gravity.CENTER_VERTICAL
        );

        t.setTextDirection(
                View.TEXT_DIRECTION_RTL
        );

        return t;
    }

    private TextView info(
            String text) {

        TextView t =
                label(
                        text
                );

        t.setTextColor(
                GRAY
        );

        t.setPadding(
                0,
                dp(3),
                0,
                dp(3)
        );

        return t;
    }

    private EditText input(
            String hint) {

        EditText e =
                new EditText(this);

        e.setHint(
                safe(hint)
        );

        e.setTextSize(
                15
        );

        e.setTextColor(
                DARK
        );

        e.setHintTextColor(
                GRAY
        );

        e.setGravity(
                Gravity.RIGHT |
                Gravity.CENTER_VERTICAL
        );

        e.setTextDirection(
                View.TEXT_DIRECTION_RTL
        );

        e.setSingleLine(
                false
        );

        return e;
    }

    private Button primaryButton(
            String text) {

        Button b =
                new Button(this);

        b.setText(
                safe(text)
        );

        b.setTextColor(
                WHITE
        );

        b.setTextSize(
                15
        );

        b.setTypeface(
                Typeface.DEFAULT,
                Typeface.BOLD
        );

        b.setAllCaps(
                false
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                DARK_GREEN
        );

        bg.setCornerRadius(
                dp(10)
        );

        b.setBackground(
                bg
        );

        return b;
    }

    private Button secondaryButton(
            String text) {

        Button b =
                new Button(this);

        b.setText(
                safe(text)
        );

        b.setTextColor(
                DARK_GREEN
        );

        b.setTextSize(
                14
        );

        b.setAllCaps(
                false
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(
                WHITE
        );

        bg.setStroke(
                dp(1),
                GOLD
        );

        bg.setCornerRadius(
                dp(10)
        );

        b.setBackground(
                bg
        );

        return b;
    }

    private Button backButton(
            String text,
            Runnable action) {

        Button b =
                secondaryButton(
                        text
                );

        b.setOnClickListener(
                v -> action.run()
        );

        return b;
    }

    private TextView emptyMessage(
            String text) {

        TextView t =
                info(
                        text
                );

        t.setGravity(
                Gravity.CENTER
        );

        t.setPadding(
                dp(15),
                dp(25),
                dp(15),
                dp(25)
        );

        return t;
    }

    private void showLoading() {

        Toast.makeText(
                this,
                "جاري تحميل البيانات...",
                Toast.LENGTH_SHORT
        ).show();
    }

    private void showError(
            String message,
            Exception e) {

        String detail =
                e.getMessage();

        if (detail == null) {
            detail = "";
        }

        new AlertDialog.Builder(this)
                .setTitle(
                        message
                )
                .setMessage(
                        detail
                )
                .setPositiveButton(
                        "حسنًا",
                        null
                )
                .show();
    }

    private void addSpace(
            LinearLayout parent,
            int height) {

        View v =
                new View(this);

        parent.addView(
                v,
                new LinearLayout.LayoutParams(
                        1,
                        dp(height)
                )
        );
    }

    private LinearLayout.LayoutParams lp(
            int width,
            int height) {

        return new LinearLayout.LayoutParams(
                width < 0
                        ? width
                        : dp(width),
                height < 0
                        ? height
                        : dp(height)
        );
    }

    private int dp(
            int value) {

        return Math.round(
                value *
                getResources()
                        .getDisplayMetrics()
                        .density
        );
    }

    private String safe(
            String value) {

        return value == null
                ? ""
                : value.trim();
    }

    private String now() {

        return new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss",
                Locale.US
        ).format(
                new Date()
        );
    }

    private int parseInt(
            String value) {

        try {

            return Integer.parseInt(
                    safe(value)
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private double parseDouble(
            String value) {

        try {

            return Double.parseDouble(
                    safe(value)
            );

        } catch (Exception e) {

            return 0.0;
        }
    }

    /* =========================================================
       AGE
       ========================================================= */

    private int calculateAge(
            String birthDate) {

        Date date =
                parseDate(
                        birthDate
                );

        if (date == null) {
            return 0;
        }

        Calendar birth =
                Calendar.getInstance();

        birth.setTime(
                date
        );

        Calendar today =
                Calendar.getInstance();

        int age =
                today.get(
                        Calendar.YEAR
                )
                -
                birth.get(
                        Calendar.YEAR
                );

        int month =
                today.get(
                        Calendar.MONTH
                )
                -
                birth.get(
                        Calendar.MONTH
                );

        if (month < 0
                || (month == 0
                && today.get(
                Calendar.DAY_OF_MONTH
        ) < birth.get(
                Calendar.DAY_OF_MONTH
        ))) {

            age--;
        }

        return Math.max(
                age,
                0
        );
    }

    private Date parseDate(
            String value) {

        String text =
                safe(value);

        if (text.isEmpty()) {
            return null;
        }

        String[] formats = {

                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "dd-MM-yyyy",
                "yyyy/MM/dd"
        };

        for (String format : formats) {

            try {

                SimpleDateFormat sdf =
                        new SimpleDateFormat(
                                format,
                                Locale.US
                        );

                sdf.setLenient(
                        false
                );

                return sdf.parse(
                        text
                );

            } catch (ParseException ignored) {
            }
        }

        return null;
    }

    private int daysUntil(
            String date) {

        Date target =
                parseDate(
                        date
                );

        if (target == null) {
            return 0;
        }

        long difference =
                target.getTime()
                -
                new Date().getTime();

        return (int)
                (difference /
                        (1000L * 60L * 60L * 24L));
    }

    private int monthsUntil(
            String date) {

        Date target =
                parseDate(
                        date
                );

        if (target == null) {
            return 0;
        }

        Calendar now =
                Calendar.getInstance();

        Calendar future =
                Calendar.getInstance();

        future.setTime(
                target
        );

        int months =
                (future.get(
                        Calendar.YEAR
                ) - now.get(
                        Calendar.YEAR
                )) * 12;

        months +=
                future.get(
                        Calendar.MONTH
                ) -
                now.get(
                        Calendar.MONTH
                );

        return months;
    }

    /* =========================================================
       EVALUATION
       ========================================================= */

    private String evaluationGrade(
            double percentage) {

        if (percentage >= 90) {
            return "ممتاز";
        }

        if (percentage >= 80) {
            return "جيد جدًا";
        }

        if (percentage >= 70) {
            return "جيد";
        }

        if (percentage >= 60) {
            return "مقبول";
        }

        if (percentage > 0) {
            return "ضعيف";
        }

        return "";
    }
}