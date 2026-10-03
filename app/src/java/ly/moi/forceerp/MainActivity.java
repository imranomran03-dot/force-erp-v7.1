package ly.moi.forceerp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
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

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends Activity {

    private static final int GREEN = Color.rgb(27, 94, 32);
    private static final int DARK_GREEN = Color.rgb(12, 58, 20);
    private static final int GOLD = Color.rgb(190, 150, 45);
    private static final int BLUE = Color.rgb(25, 90, 155);
    private static final int RED = Color.rgb(170, 45, 45);
    private static final int ORANGE = Color.rgb(190, 105, 20);
    private static final int PURPLE = Color.rgb(105, 65, 145);
    private static final int GRAY = Color.rgb(105, 112, 118);
    private static final int LIGHT = Color.rgb(245, 247, 248);
    private static final int WHITE = Color.WHITE;
    private static final int DARK = Color.rgb(30, 35, 40);
    private static final int BORDER = Color.rgb(215, 220, 224);

    private LinearLayout root;
    private SharedPreferences prefs;

    private AppDatabase database;
    private PersonnelDao personnelDao;
    private ExecutorService databaseExecutor;

    private volatile boolean databaseReady = false;

    private final ArrayList<LinkedHashMap<String, String>> personnel =
            new ArrayList<>();

    private final LinkedHashMap<String, View> formViews =
            new LinkedHashMap<>();

    private String editingRecordId = "";

    // =========================================================
    // 116 FIELD MASTER LIST
    // =========================================================

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

    // =========================================================
    // EXTRA FIELDS
    // =========================================================

    private final String[] EXTRA_FIELDS = {

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
            "حالة العجز/التقييم",
            "رقم مالي"
    };

    // =========================================================
    // ROOM FIELD MAP
    // نفس ترتيب الـ116 خانة في Personnel.java
    // =========================================================

    private final String[] ENTITY_FIELDS = {

            "recordId",
            "fullName",
            "rank",
            "surname",
            "branch",
            "fatherName",
            "motherName",
            "jobStatus",
            "nationalNumber",
            "familyBookNumber",

            "accountNumber",
            "maritalStatus",
            "spouseName",
            "childrenCount",
            "bloodType",
            "uniformSize",
            "shoeSize",
            "residenceCity",
            "phone",
            "personalCardNumber",

            "passportNumber",
            "experience",
            "languages",
            "appointmentDecisionNumber",
            "appointmentDecisionDate",
            "lastPromotionNumber",
            "lastPromotionDate",
            "previousPromotions",
            "assignmentStartDate",
            "assignmentEndDate",

            "currentMilitaryStatus",
            "notes",
            "birthDate",
            "age",
            "birthPlace",
            "detailedAddress",
            "alternativePhone",
            "emergencyContactName",
            "emergencyContactPhone",
            "unitSector",

            "jobGrade",
            "previousRank",
            "promotionDecisionNumber",
            "promotionDecisionDate",
            "promotionDecisionAuthority",
            "assignmentType",
            "assignmentAuthority",
            "assignmentLocation",
            "assignmentStart",
            "assignmentEnd",

            "assignmentDecisionNumber",
            "assignmentStatus",
            "courseType",
            "courseName",
            "trainingAuthority",
            "trainingCountry",
            "courseStartDate",
            "courseEndDate",
            "courseLevel",
            "certificateNumber",

            "qualification",
            "specialization",
            "educationalInstitution",
            "graduationCountry",
            "graduationYear",
            "graduationGrade",
            "language1",
            "language1Reading",
            "language1Writing",
            "language1Speaking",

            "language2",
            "language2Reading",
            "language2Writing",
            "language2Speaking",
            "documentType",
            "documentNumber",
            "documentDate",
            "documentExpiryDate",
            "issuingAuthority",
            "documentStatus",

            "alertStatus",
            "alertText",
            "alertDate",
            "remainingDays",
            "nextPromotionEligibilityDate",
            "promotionRemainingMonths",
            "promotionAlertStatus",
            "medalsAndAwards",
            "exceptionalPromotionsCount",
            "exceptionalPromotionDecisionNumbers",

            "verbalReprimandCount",
            "writtenReprimandCount",
            "verbalWarningCount",
            "writtenWarningCount",
            "rankReductionCount",
            "chargesCount",
            "administrativeInvestigationCount",
            "sickLeaveDays",
            "injured",
            "injuryDate",

            "martyr",
            "martyrdomDate",
            "appreciationLettersCount",
            "positiveEvaluationPoints",
            "negativeEvaluationPoints",
            "annualEvaluationPercentage",
            "annualEvaluationGrade",
            "photoPath",
            "securityPlanParticipation",
            "caseArrestStatus",

            "caseCount",
            "goodConduct",
            "weapon",
            "radio",
            "vehicle",
            "otherEquipment"
    };

    // =========================================================
    // OPTIONS
    // =========================================================

    private final String[] BRANCHES = {
            "— اختر الفرع —",
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

    private final String[] ASSIGNMENT_TYPES = {
            "— اختر النوع —",
            "تعيين",
            "نقل",
            "ندب",
            "ندب وزاري",
            "ندب وكيل وزارة الداخلية",
            "تكليف",
            "عقد"
    };

    private final String[] POSITION_TYPES = {
            "— بدون تكليف بالمنصب —",
            "مدير فرع",
            "مدير إدارة",
            "مدير مكتب",
            "مدير وحدة"
    };

    private final String[] YES_NO = {
            "— اختر —",
            "نعم",
            "لا"
    };

    private final String[] SALARY_STATUS = {
            "— اختر حالة المرتب —",
            "مرتب لحظي",
            "راتب حوافظ",
            "منحة",
            "لا يتقاضى مرتب",
            "موقوف"
    };

    private final String[] WORK_STATUS = {
            "— اختر حالة العمل —",
            "مستمر",
            "مكلف",
            "منتدب",
            "موقوف عن العمل",
            "منتهي"
    };

    private final String[] MEMBERSHIP_STATUS = {
            "— فعال —",
            "استقالة",
            "فصل",
            "انتهاء ندب",
            "انتهاء تكليف",
            "انتهاء عقد"
    };

    private final String[] BLOOD_TYPES = {
            "— اختر فصيلة الدم —",
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"
    };

    private final String[] MARITAL = {
            "— اختر —",
            "أعزب",
            "متزوج",
            "مطلق",
            "أرمل"
    };

    private final String[] JOB_TYPES = {
            "— اختر الصفة الوظيفية —",
            "ضابط",
            "ضابط صف",
            "فرد",
            "موظف"
    };

    private final String[] MILITARY_STATUS = {
            "— اختر —",
            "مستمر",
            "مكلف",
            "منتدب",
            "موقوف",
            "إجازة",
            "دورة",
            "عاجز",
            "شهيد",
            "منتهي الخدمة"
    };

    private final String[] COURSE_TYPES = {
            "— اختر —",
            "تدريبية",
            "تخصصية",
            "تأهيلية",
            "ورشة",
            "أخرى"
    };

    private final String[] LEVELS = {
            "— اختر —",
            "مبتدئ",
            "متوسط",
            "جيد",
            "جيد جدًا",
            "متقدم",
            "ممتاز"
    };

    private final String[] DOC_STATUS = {
            "— اختر —",
            "سارية",
            "منتهية",
            "موقوفة",
            "غير متوفرة"
    };

    private final String[] ALERT_STATUS = {
            "— اختر —",
            "لا يوجد",
            "تنبيه",
            "عاجل"
    };

    private final String[] PROMOTION_ALERT = {
            "— اختر —",
            "لا يوجد",
            "قريب",
            "مستحق",
            "متأخر"
    };

    // =========================================================
    // CREATE
    // =========================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences(
                "force_erp_v71",
                MODE_PRIVATE
        );

        loadPersonnel();

        try {
            database = DatabaseProvider.getDatabase(this);
            personnelDao = database.personnelDao();
            databaseExecutor = Executors.newSingleThreadExecutor();

            loadFromRoom();

        } catch (Exception e) {

            databaseReady = true;

            message(
                    "قاعدة البيانات",
                    "تعذر تهيئة قاعدة البيانات.\n" +
                    safeException(e)
            );
        }

        showLogin();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (databaseExecutor != null) {
            databaseExecutor.shutdown();
        }
    }

    // =========================================================
    // ROOM LOAD + LEGACY MIGRATION
    // =========================================================

    private void loadFromRoom() {

        if (databaseExecutor == null ||
                personnelDao == null) {

            databaseReady = true;
            return;
        }

        final ArrayList<LinkedHashMap<String, String>> legacy =
                copyPersonnel();

        databaseExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        try {

                            List<Personnel> rows =
                                    personnelDao.getAll();

                            // إذا كانت Room فارغة وهناك بيانات قديمة:
                            // ننقلها إلى Room.
                            if (rows.isEmpty() &&
                                    !legacy.isEmpty()) {

                                for (LinkedHashMap<String, String> record :
                                        legacy) {

                                    Personnel entity =
                                            recordToPersonnel(
                                                    record,
                                                    null
                                            );

                                    personnelDao.insert(entity);
                                }

                                rows =
                                        personnelDao.getAll();
                            }

                            final ArrayList<LinkedHashMap<String, String>>
                                    loaded =
                                    new ArrayList<>();

                            for (Personnel entity :
                                    rows) {

                                LinkedHashMap<String, String> record =
                                        personnelToRecord(entity);

                                mergeLegacyExtras(
                                        record,
                                        legacy
                                );

                                loaded.add(record);
                            }

                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {

                                            personnel.clear();

                                            personnel.addAll(
                                                    loaded
                                            );

                                            databaseReady = true;
                                        }
                                    }
                            );

                        } catch (Exception e) {

                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {

                                            databaseReady = true;

                                            message(
                                                    "قاعدة البيانات",
                                                    "حدث خطأ أثناء قراءة البيانات.\n" +
                                                    safeException(e)
                                            );
                                        }
                                    }
                            );
                        }
                    }
                }
        );
    }

    private void mergeLegacyExtras(
            LinkedHashMap<String, String> target,
            ArrayList<LinkedHashMap<String, String>> legacy) {

        String id =
                safe(
                        target.get(
                                "معرّف السجل"
                        )
                );

        for (LinkedHashMap<String, String> old :
                legacy) {

            if (id.equals(
                    safe(
                            old.get(
                                    "معرّف السجل"
                            )
                    )
            )) {

                for (String field :
                        EXTRA_FIELDS) {

                    if (old.containsKey(field)) {

                        target.put(
                                field,
                                safeForStorage(
                                        old.get(field)
                                )
                        );
                    }
                }

                return;
            }
        }
    }

    private ArrayList<LinkedHashMap<String, String>>
    copyPersonnel() {

        ArrayList<LinkedHashMap<String, String>>
                result =
                new ArrayList<>();

        for (LinkedHashMap<String, String> record :
                personnel) {

            result.add(
                    new LinkedHashMap<>(record)
            );
        }

        return result;
    }

    // =========================================================
    // ROOM SAVE
    // =========================================================

    private void persistRecordToRoom(
            final LinkedHashMap<String, String> record) {

        saveLegacyBackup();

        if (databaseExecutor == null ||
                personnelDao == null) {
            return;
        }

        databaseExecutor.execute(
                new Runnable() {
                    @Override
                    public void run() {

                        try {

                            String recordId =
                                    safe(
                                            record.get(
                                                    "معرّف السجل"
                                            )
                                    );

                            Personnel existing =
                                    null;

                            List<Personnel> all =
                                    personnelDao.getAll();

                            for (Personnel item : all) {

                                if (recordId.equals(
                                        safe(item.recordId)
                                )) {

                                    existing = item;
                                    break;
                                }
                            }

                            Personnel entity =
                                    recordToPersonnel(
                                            record,
                                            existing
                                    );

                            if (existing == null) {

                                personnelDao.insert(
                                        entity
                                );

                            } else {

                                personnelDao.update(
                                        entity
                                );
                            }

                        } catch (Exception e) {

                            runOnUiThread(
                                    new Runnable() {
                                        @Override
                                        public void run() {

                                            message(
                                                    "خطأ في الحفظ",
                                                    "تم تحديث الواجهة، لكن حدث خطأ أثناء الكتابة إلى قاعدة البيانات.\n" +
                                                    safeException(e)
                                            );
                                        }
                                    }
                            );
                        }
                    }
                }
        );
    }

    // =========================================================
    // REFLECTION MAPPER
    // =========================================================

    private Personnel recordToPersonnel(
            LinkedHashMap<String, String> record,
            Personnel existing) {

        Personnel p =
                existing == null
                        ? new Personnel()
                        : existing;

        for (int i = 0;
             i < ENTITY_FIELDS.length &&
                     i < FIELDS.length;
             i++) {

            String entityField =
                    ENTITY_FIELDS[i];

            String uiField =
                    FIELDS[i];

            String value =
                    safeForStorage(
                            record.get(uiField)
                    );

            try {

                Field field =
                        Personnel.class.getField(
                                entityField
                        );

                setEntityField(
                        p,
                        field,
                        value
                );

            } catch (Exception ignored) {
                // الحقول تتم مطابقتها حسب Personnel.java
            }
        }

        p.financialNumber =
                safeForStorage(
                        record.get("رقم مالي")
                );

        p.bankName =
                safeForStorage(
                        record.get("اسم المصرف")
                );

        p.bankBranchName =
                safeForStorage(
                        record.get("اسم فرع المصرف")
                );

        p.bankAccountNumber =
                safeForStorage(
                        record.get("رقم الحساب")
                );

        p.salaryStatus =
                safeForStorage(
                        record.get("حالة المرتب")
                );

        p.salarySuspensionReason =
                safeForStorage(
                        record.get("سبب إيقاف المرتب")
                );

        p.salaryStatusDate =
                safeForStorage(
                        record.get("تاريخ حالة المرتب")
                );

        p.financialNotes =
                safeForStorage(
                        record.get("ملاحظات مالية")
                );

        p.assignedPosition =
                safeForStorage(
                        record.get("تكليف بالمنصب")
                );

        p.terminationStatus =
                safeForStorage(
                        record.get("حالة العضوية")
                );

        p.terminationReason =
                safeForStorage(
                        record.get("سبب إنهاء العضوية")
                );

        if (p.createdAt == null ||
                p.createdAt.trim().length() == 0) {

            p.createdAt =
                    String.valueOf(
                            System.currentTimeMillis()
                    );
        }

        p.updatedAt =
                String.valueOf(
                        System.currentTimeMillis()
                );

        return p;
    }

    private void setEntityField(
            Personnel p,
            Field field,
            String value)
            throws IllegalAccessException {

        Class<?> type =
                field.getType();

        if (type == String.class) {

            field.set(
                    p,
                    value
            );

        } else if (type == int.class) {

            field.setInt(
                    p,
                    parseInt(value)
            );

        } else if (type == double.class) {

            field.setDouble(
                    p,
                    parseDouble(value)
            );
        }
    }

    private LinkedHashMap<String, String>
    personnelToRecord(Personnel p) {

        LinkedHashMap<String, String> record =
                new LinkedHashMap<>();

        for (int i = 0;
             i < FIELDS.length &&
                     i < ENTITY_FIELDS.length;
             i++) {

            String value = "";

            try {

                Field field =
                        Personnel.class.getField(
                                ENTITY_FIELDS[i]
                        );

                Object object =
                        field.get(p);

                value =
                        object == null
                                ? ""
                                : String.valueOf(object);

            } catch (Exception ignored) {
            }

            record.put(
                    FIELDS[i],
                    value
            );
        }

        record.put(
                "رقم مالي",
                safeForStorage(
                        p.financialNumber
                )
        );

        record.put(
                "اسم المصرف",
                safeForStorage(
                        p.bankName
                )
        );

        record.put(
                "اسم فرع المصرف",
                safeForStorage(
                        p.bankBranchName
                )
        );

        record.put(
                "رقم الحساب",
                safeForStorage(
                        p.bankAccountNumber
                )
        );

        record.put(
                "حالة المرتب",
                safeForStorage(
                        p.salaryStatus
                )
        );

        record.put(
                "سبب إيقاف المرتب",
                safeForStorage(
                        p.salarySuspensionReason
                )
        );

        record.put(
                "تاريخ حالة المرتب",
                safeForStorage(
                        p.salaryStatusDate
                )
        );

        record.put(
                "ملاحظات مالية",
                safeForStorage(
                        p.financialNotes
                )
        );

        record.put(
                "تكليف بالمنصب",
                safeForStorage(
                        p.assignedPosition
                )
        );

        record.put(
                "حالة العضوية",
                safeForStorage(
                        p.terminationStatus
                )
        );

        record.put(
                "سبب إنهاء العضوية",
                safeForStorage(
                        p.terminationReason
                )
        );

        return record;
    }

    private int parseInt(String value) {

        try {

            if (value == null ||
                    value.trim().length() == 0) {
                return 0;
            }

            return Integer.parseInt(
                    value.trim()
            );

        } catch (Exception e) {

            return 0;
        }
    }

    private double parseDouble(String value) {

        try {

            if (value == null ||
                    value.trim().length() == 0) {
                return 0.0;
            }

            return Double.parseDouble(
                    value.trim()
            );

        } catch (Exception e) {

            return 0.0;
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================

    private void showLogin() {

        LinearLayout page =
                new LinearLayout(this);

        page.setOrientation(
                LinearLayout.VERTICAL
        );

        page.setGravity(
                Gravity.CENTER_HORIZONTAL
        );

        page.setPadding(
                dp(24),
                dp(30),
                dp(24),
                dp(30)
        );

        page.setBackgroundColor(LIGHT);

        page.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        TextView logo =
                text(
                        "قوة العمومية",
                        28,
                        GOLD,
                        true
                );

        logo.setGravity(Gravity.CENTER);

        page.addView(
                logo,
                lp(-1, 85)
        );

        TextView title =
                text(
                        "منظومة إدارة القوة العمومية",
                        24,
                        DARK_GREEN,
                        true
                );

        title.setGravity(Gravity.CENTER);

        page.addView(
                title,
                lp(-1, 55)
        );

        TextView version =
                text(
                        "V7.1",
                        15,
                        GOLD,
                        true
                );

        version.setGravity(Gravity.CENTER);

        page.addView(
                version,
                lp(-1, 35)
        );

        addSpace(page, 18);

        EditText username =
                createEditText("اسم المستخدم");

        username.setSingleLine(true);

        page.addView(
                username,
                margin(-1, 58, 0, 9, 0, 0)
        );

        EditText password =
                createEditText("كلمة السر");

        password.setSingleLine(true);

        password.setInputType(
                InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD
        );

        page.addView(
                password,
                margin(-1, 58, 0, 12, 0, 0)
        );

        Button login =
                createButton(
                        "دخول إلى المنظومة",
                        GREEN
                );

        page.addView(
                login,
                margin(-1, 56, 0, 18, 0, 0)
        );

        TextView note =
                text(
                        "نظام إداري متكامل لإدارة القوة العمومية",
                        14,
                        GRAY,
                        false
                );

        note.setGravity(Gravity.CENTER);

        page.addView(
                note,
                lp(-1, 50)
        );

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (username.getText()
                                .toString()
                                .trim()
                                .length() == 0 ||
                                password.getText()
                                        .toString()
                                        .trim()
                                        .length() == 0) {

                            message(
                                    "تسجيل الدخول",
                                    "أدخل اسم المستخدم وكلمة السر."
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

        createPage(
                "لوحة القيادة",
                "منظومة إدارة القوة العمومية V7.1"
        );

        TextView welcome =
                text(
                        "الموقف العام للجهاز",
                        21,
                        DARK_GREEN,
                        true
                );

        root.addView(
                welcome,
                margin(-1, 50, 0, 14, 0, 0)
        );

        addStatCard(
                "إجمالي المنتسبين",
                String.valueOf(
                        personnel.size()
                ),
                GREEN
        );

        addStatCard(
                "المكلفون",
                countField(
                        "الحالة العسكرية الحالية",
                        "مكلف"
                ),
                BLUE
        );

        addStatCard(
                "المنتدبون",
                countField(
                        "الحالة العسكرية الحالية",
                        "منتدب"
                ),
                PURPLE
        );

        addStatCard(
                "الموقوفون",
                countField(
                        "الحالة العسكرية الحالية",
                        "موقوف"
                ),
                RED
        );

        addStatCard(
                "مرتب لحظي",
                countExact(
                        "حالة المرتب",
                        "مرتب لحظي"
                ),
                GREEN
        );

        addStatCard(
                "راتب حوافظ",
                countExact(
                        "حالة المرتب",
                        "راتب حوافظ"
                ),
                BLUE
        );

        addStatCard(
                "منح",
                countExact(
                        "حالة المرتب",
                        "منحة"
                ),
                ORANGE
        );

        addStatCard(
                "لا يتقاضى مرتب",
                countExact(
                        "حالة المرتب",
                        "لا يتقاضى مرتب"
                ),
                GRAY
        );

        addStatCard(
                "موقوف المرتب",
                countExact(
                        "حالة المرتب",
                        "موقوف"
                ),
                RED
        );

        addSpace(root, 12);

        TextView quick =
                text(
                        "الوصول السريع",
                        19,
                        DARK,
                        true
                );

        root.addView(
                quick,
                margin(-1, 42, 0, 10, 0, 0)
        );

        menu(
                "المنتسبون",
                GREEN,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPersonnel();
                    }
                }
        );

        menu(
                "إضافة منتسب جديد",
                BLUE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPersonnelForm(null);
                    }
                }
        );

        menu(
                "البحث عن منتسب",
                DARK_GREEN,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSearch();
                    }
                }
        );

        menu(
                "البطاقة المالية",
                ORANGE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFinancial();
                    }
                }
        );

        menu(
                "الحركة والتكليف",
                PURPLE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMovement();
                    }
                }
        );

        menu(
                "التقارير",
                BLUE,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showReports();
                    }
                }
        );

        menu(
                "الإعدادات",
                GRAY,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showSettings();
                    }
                }
        );

        addSpace(root, 12);

        Button logout =
                createButton(
                        "تسجيل الخروج",
                        RED
                );

        root.addView(
                logout,
                margin(-1, 52, 0, 15, 0, 0)
        );

        logout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showLogin();
                    }
                }
        );
    }

    // =========================================================
    // PERSONNEL LIST
    // =========================================================

    private void showPersonnel() {

        createPage(
                "المنتسبون",
                "السجلات المحفوظة في المنظومة"
        );

        Button add =
                createButton(
                        "إضافة منتسب جديد",
                        GREEN
                );

        root.addView(
                add,
                margin(-1, 55, 0, 12, 0, 0)
        );

        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPersonnelForm(null);
                    }
                }
        );

        if (personnel.isEmpty()) {

            emptyMessage(
                    "لا توجد سجلات حتى الآن.\n" +
                    "ابدأ بإضافة أول منتسب."
            );

        } else {

            for (int i = 0;
                 i < personnel.size();
                 i++) {

                final int index = i;

                LinkedHashMap<String, String> record =
                        personnel.get(i);

                String name =
                        safe(
                                record.get(
                                        "الاسم الثلاثي"
                                )
                        );

                Button card =
                        createButton(
                                name +
                                "\n" +
                                safe(
                                        record.get("الرتبة")
                                ) +
                                "  •  " +
                                safe(
                                        record.get("الفرع")
                                ),
                                WHITE
                        );

                card.setTextColor(DARK);

                root.addView(
                        card,
                        margin(-1, 72, 0, 8, 0, 0)
                );

                card.setOnClickListener(
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

        backDashboard();
    }

    // =========================================================
    // FORM
    // =========================================================

    private void openPersonnelForm(
            LinkedHashMap<String, String> existing) {

        editingRecordId = "";

        formViews.clear();

        if (existing != null) {

            editingRecordId =
                    safe(
                            existing.get(
                                    "معرّف السجل"
                            )
                    );
        }

        createPage(
                existing == null
                        ? "إضافة منتسب جديد"
                        : "تعديل بيانات المنتسب",
                "البيانات الأساسية والمالية والإدارية"
        );

        addFormSection(
                "القسم 1 — الهوية والبيانات الشخصية",
                0,
                23
        );

        addFormSection(
                "القسم 2 — التعيين والترقية والتكليف",
                23,
                53
        );

        addFormSection(
                "القسم 3 — الدورات والتعليم",
                53,
                66
        );

        addFormSection(
                "القسم 4 — اللغات",
                66,
                74
        );

        addFormSection(
                "القسم 5 — الوثائق والتنبيهات",
                74,
                87
        );

        addFormSection(
                "القسم 6 — الأوسمة والجزاءات",
                87,
                98
        );

        addFormSection(
                "القسم 7 — الإصابات والشهداء والتقييم",
                98,
                107
        );

        addFormSection(
                "القسم 8 — الصورة والمهام والمعدات",
                107,
                116
        );

        addExtraSection();

        addSpace(root, 10);

        Button save =
                createButton(
                        existing == null
                                ? "حفظ المنتسب"
                                : "حفظ التعديلات",
                        GREEN
                );

        root.addView(
                save,
                margin(-1, 58, 0, 10, 0, 0)
        );

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        savePersonnel(existing);
                    }
                }
        );

        if (existing != null) {

            Button end =
                    createButton(
                            "إنهاء / إيقاف سجل العضوية",
                            RED
                    );

            root.addView(
                    end,
                    margin(-1, 54, 0, 10, 0, 0)
            );

            end.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showEndMembership(existing);
                        }
                    }
            );
        }

        backDashboard();
    }

    private void addFormSection(
            String title,
            int from,
            int to) {

        TextView header =
                text(
                        title,
                        18,
                        WHITE,
                        true
                );

        header.setPadding(
                dp(15),
                dp(8),
                dp(15),
                dp(8)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(DARK_GREEN);
        bg.setCornerRadius(dp(12));

        header.setBackground(bg);

        root.addView(
                header,
                margin(-1, 46, 0, 8, 0, 0)
        );

        for (int i = from; i < to; i++) {

            String field =
                    FIELDS[i];

            String value = null;

            if (editingRecordId.length() > 0) {

                int index =
                        findRecordIndex(
                                editingRecordId
                        );

                if (index >= 0) {

                    value =
                            personnel
                                    .get(index)
                                    .get(field);
                }
            }

            addDynamicField(
                    field,
                    value
            );
        }
    }

    private void addExtraSection() {

        TextView header =
                text(
                        "القسم 9 — البيانات الإضافية والمالية",
                        18,
                        WHITE,
                        true
                );

        header.setPadding(
                dp(15),
                dp(8),
                dp(15),
                dp(8)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(GOLD);
        bg.setCornerRadius(dp(12));

        header.setBackground(bg);

        root.addView(
                header,
                margin(-1, 46, 0, 8, 0, 0)
        );

        for (String field :
                EXTRA_FIELDS) {

            String value = null;

            if (editingRecordId.length() > 0) {

                int index =
                        findRecordIndex(
                                editingRecordId
                        );

                if (index >= 0) {

                    value =
                            personnel
                                    .get(index)
                                    .get(field);
                }
            }

            addDynamicField(
                    field,
                    value
            );
        }
    }

    private void addDynamicField(
            String field,
            String value) {

        if (isSpinnerField(field)) {

            Spinner spinner =
                    createSpinner(
                            field,
                            optionsFor(field)
                    );

            if (value != null) {
                selectSpinner(
                        spinner,
                        value
                );
            }

            root.addView(
                    spinner,
                    margin(-1, 58, 0, 8, 0, 0)
            );

            formViews.put(
                    field,
                    spinner
            );

        } else {

            EditText edit =
                    createEditText(field);

            if (value != null) {
                edit.setText(value);
            }

            if (field.equals(
                    "معرّف السجل")) {

                if (value == null ||
                        value.length() == 0) {

                    edit.setText(
                            generateRecordId()
                    );
                }

                edit.setEnabled(false);
            }

            if (field.equals(
                    "العمر (تلقائي)") ||
                    field.equals(
                            "التقدير السنوي (تلقائي)")) {

                edit.setEnabled(false);
            }

            if (isNumberField(field)) {

                edit.setInputType(
                        InputType.TYPE_CLASS_NUMBER |
                        InputType.TYPE_NUMBER_FLAG_DECIMAL
                );
            }

            if (isLongText(field)) {

                edit.setSingleLine(false);
                edit.setMinHeight(dp(80));
                edit.setGravity(
                        Gravity.RIGHT |
                        Gravity.TOP
                );
            }

            root.addView(
                    edit,
                    margin(
                            -1,
                            isLongText(field)
                                    ? 82
                                    : 58,
                            0,
                            8,
                            0,
                            0
                    )
            );

            formViews.put(
                    field,
                    edit
            );
        }
    }

    // =========================================================
    // SPINNERS
    // =========================================================

    private boolean isSpinnerField(
            String field) {

        return field.equals("الفرع") ||
                field.equals("حالة الزواج") ||
                field.equals("فصيلة الدم") ||
                field.equals("الصفة الوظيفية") ||
                field.equals("الحالة العسكرية الحالية") ||
                field.equals("الدرجة الوظيفية") ||
                field.equals("نوع التكليف") ||
                field.equals("حالة التكليف") ||
                field.equals("نوع الدورة") ||
                field.equals("المستوى/التقدير") ||
                field.equals("حالة الوثيقة") ||
                field.equals("حالة التنبيه") ||
                field.equals("حالة تنبيه الترقية") ||
                field.equals("الجرحى والمصابين (نعم/لا)") ||
                field.equals("الشهداء (نعم/لا)") ||
                field.equals("مشاركة في خطط أمنية") ||
                field.equals("قبض على قضايا") ||
                field.equals("حسن سيرة وسلوك") ||
                field.equals("مستوى القراءة 1") ||
                field.equals("مستوى الكتابة 1") ||
                field.equals("مستوى المحادثة 1") ||
                field.equals("مستوى القراءة 2") ||
                field.equals("مستوى الكتابة 2") ||
                field.equals("مستوى المحادثة 2") ||
                field.equals("نوع الوثيقة") ||
                field.equals("تكليف بالمنصب") ||
                field.equals("منطقة فرع الوسطى") ||
                field.equals("حالة المرتب") ||
                field.equals("حالة العمل") ||
                field.equals("حالة العضوية") ||
                field.equals("حالة العجز/التقييم");
    }

    private String[] optionsFor(
            String field) {

        if (field.equals("الفرع"))
            return BRANCHES;

        if (field.equals("حالة الزواج"))
            return MARITAL;

        if (field.equals("فصيلة الدم"))
            return BLOOD_TYPES;

        if (field.equals("الصفة الوظيفية"))
            return JOB_TYPES;

        if (field.equals(
                "الحالة العسكرية الحالية"))
            return MILITARY_STATUS;

        if (field.equals("الدرجة الوظيفية"))
            return grades();

        if (field.equals("نوع التكليف"))
            return ASSIGNMENT_TYPES;

        if (field.equals("حالة التكليف"))
            return new String[]{
                    "— اختر —",
                    "جاري",
                    "منتهي",
                    "موقوف",
                    "ملغى"
            };

        if (field.equals("نوع الدورة"))
            return COURSE_TYPES;

        if (field.equals("المستوى/التقدير"))
            return LEVELS;

        if (field.equals("حالة الوثيقة"))
            return DOC_STATUS;

        if (field.equals("حالة التنبيه"))
            return ALERT_STATUS;

        if (field.equals(
                "حالة تنبيه الترقية"))
            return PROMOTION_ALERT;

        if (field.equals(
                "الجرحى والمصابين (نعم/لا)") ||
                field.equals("الشهداء (نعم/لا)") ||
                field.equals("مشاركة في خطط أمنية") ||
                field.equals("قبض على قضايا") ||
                field.equals("حسن سيرة وسلوك")) {

            return YES_NO;
        }

        if (field.contains("مستوى القراءة") ||
                field.contains("مستوى الكتابة") ||
                field.contains("مستوى المحادثة")) {

            return LEVELS;
        }

        if (field.equals("نوع الوثيقة")) {

            return new String[]{
                    "— اختر —",
                    "بطاقة شخصية",
                    "جواز سفر",
                    "شهادة",
                    "قرار",
                    "أخرى"
            };
        }

        if (field.equals("تكليف بالمنصب"))
            return POSITION_TYPES;

        if (field.equals("منطقة فرع الوسطى")) {

            return new String[]{
                    "— غير محدد —",
                    "إجدابيا",
                    "سرت"
            };
        }

        if (field.equals("حالة المرتب"))
            return SALARY_STATUS;

        if (field.equals("حالة العمل"))
            return WORK_STATUS;

        if (field.equals("حالة العضوية"))
            return MEMBERSHIP_STATUS;

        if (field.equals("حالة العجز/التقييم")) {

            return new String[]{
                    "لا يوجد عجز",
                    "عجز",
                    "غير خاضع للتقييم"
            };
        }

        return YES_NO;
    }

    private String[] grades() {

        String[] result =
                new String[15];

        result[0] =
                "— اختر الدرجة —";

        for (int i = 1; i <= 14; i++) {

            result[i] =
                    String.valueOf(i + 2);
        }

        return result;
    }

    // =========================================================
    // SAVE FORM
    // =========================================================

    private void savePersonnel(
            LinkedHashMap<String, String> existing) {

        LinkedHashMap<String, String> record =
                new LinkedHashMap<>();

        for (String field :
                FIELDS) {

            View view =
                    formViews.get(field);

            if (view != null) {

                record.put(
                        field,
                        readValue(view)
                );
            }
        }

        for (String field :
                EXTRA_FIELDS) {

            View view =
                    formViews.get(field);

            if (view != null) {

                record.put(
                        field,
                        readValue(view)
                );
            }
        }

        String name =
                safe(
                        record.get(
                                "الاسم الثلاثي"
                        )
                );

        if (name.equals("—")) {

            message(
                    "بيانات ناقصة",
                    "الاسم الثلاثي مطلوب."
            );

            return;
        }

        String id =
                safe(
                        record.get(
                                "معرّف السجل"
                        )
                );

        if (id.equals("—")) {

            id =
                    generateRecordId();

            record.put(
                    "معرّف السجل",
                    id
            );
        }

        String birth =
                safe(
                        record.get(
                                "تاريخ الميلاد"
                        )
                );

        if (!birth.equals("—")) {

            record.put(
                    "العمر (تلقائي)",
                    calculateAge(birth)
            );
        }

        String percentage =
                safe(
                        record.get(
                                "النسبة المئوية للتقييم السنوي"
                        )
                );

        if (!percentage.equals("—")) {

            record.put(
                    "التقدير السنوي (تلقائي)",
                    calculateAnnualGrade(
                            percentage
                    )
            );
        }

        // قواعد التقييم
        String martyr =
                safe(
                        record.get(
                                "الشهداء (نعم/لا)"
                        )
                );

        String injured =
                safe(
                        record.get(
                                "الجرحى والمصابين (نعم/لا)"
                        )
                );

        String disability =
                safe(
                        record.get(
                                "حالة العجز/التقييم"
                        )
                );

        if (martyr.equals("نعم") ||
                injured.equals("نعم") ||
                disability.equals("عجز") ||
                disability.equals("غير خاضع للتقييم")) {

            record.put(
                    "حالة العجز/التقييم",
                    "غير خاضع للتقييم"
            );

            record.put(
                    "النسبة المئوية للتقييم السنوي",
                    ""
            );

            record.put(
                    "التقدير السنوي (تلقائي)",
                    ""
            );
        }

        // نحفظ في الذاكرة ثم في Room
        int oldIndex =
                findRecordIndex(id);

        if (oldIndex >= 0) {

            personnel.set(
                    oldIndex,
                    record
            );

        } else {

            personnel.add(record);
        }

        persistRecordToRoom(record);

        messageAndRun(
                "تم الحفظ",
                "تم حفظ بيانات المنتسب في المنظومة.",
                new Runnable() {
                    @Override
                    public void run() {
                        showPersonnel();
                    }
                }
        );
    }

    // =========================================================
    // PERSONNEL CARD
    // =========================================================

    private void showPersonnelCard(
            LinkedHashMap<String, String> record) {

        createPage(
                "بطاقة المنتسب",
                safe(
                        record.get(
                                "الاسم الثلاثي"
                        )
                )
        );

        addCardInfo(
                "الاسم الثلاثي",
                record.get("الاسم الثلاثي")
        );

        addCardInfo(
                "الرتبة",
                record.get("الرتبة")
        );

        addCardInfo(
                "اللقب",
                record.get("اللقب")
        );

        addCardInfo(
                "الفرع",
                record.get("الفرع")
        );

        addCardInfo(
                "الرقم الوطني",
                record.get("الرقم الوطني")
        );

        addCardInfo(
                "الرقم الحسابي",
                record.get("الرقم الحسابي")
        );

        addCardInfo(
                "الحالة العسكرية الحالية",
                record.get(
                        "الحالة العسكرية الحالية"
                )
        );

        addCardInfo(
                "تكليف بالمنصب",
                record.get(
                        "تكليف بالمنصب"
                )
        );

        addCardInfo(
                "نوع التكليف",
                record.get(
                        "نوع التكليف"
                )
        );

        addCardInfo(
                "حالة المرتب",
                record.get(
                        "حالة المرتب"
                )
        );

        addCardInfo(
                "اسم المصرف",
                record.get(
                        "اسم المصرف"
                )
        );

        addCardInfo(
                "اسم فرع المصرف",
                record.get(
                        "اسم فرع المصرف"
                )
        );

        addCardInfo(
                "رقم الحساب",
                record.get(
                        "رقم الحساب"
                )
        );

        addCardInfo(
                "العمر",
                record.get(
                        "العمر (تلقائي)"
                )
        );

        addCardInfo(
                "حالة العضوية",
                record.get(
                        "حالة العضوية"
                )
        );

        addSpace(root, 10);

        Button edit =
                createButton(
                        "تعديل بيانات المنتسب",
                        BLUE
                );

        root.addView(
                edit,
                margin(-1, 54, 0, 9, 0, 0)
        );

        edit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPersonnelForm(record);
                    }
                }
        );

        Button full =
                createButton(
                        "عرض جميع البيانات",
                        GREEN
                );

        root.addView(
                full,
                margin(-1, 54, 0, 9, 0, 0)
        );

        full.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showAllRecordData(record);
                    }
                }
        );

        Button financial =
                createButton(
                        "البطاقة المالية",
                        ORANGE
                );

        root.addView(
                financial,
                margin(-1, 54, 0, 9, 0, 0)
        );

        financial.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showFinancialCard(record);
                    }
                }
        );

        backDashboard();
    }

    private void showAllRecordData(
            LinkedHashMap<String, String> record) {

        createPage(
                "جميع بيانات المنتسب",
                "116 خانة + البيانات الإضافية"
        );

        for (String field :
                FIELDS) {

            addCardInfo(
                    field,
                    record.get(field)
            );
        }

        for (String field :
                EXTRA_FIELDS) {

            addCardInfo(
                    field,
                    record.get(field)
            );
        }

        backDashboard();
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void showSearch() {

        createPage(
                "البحث عن منتسب",
                "بحث شامل داخل السجلات"
        );

        EditText query =
                createEditText(
                        "الاسم / الرقم الوطني / الرقم الحسابي"
                );

        root.addView(
                query,
                margin(-1, 58, 0, 10, 0, 0)
        );

        Button search =
                createButton(
                        "بحث",
                        GREEN
                );

        root.addView(
                search,
                margin(-1, 54, 0, 15, 0, 0)
        );

        LinearLayout results =
                new LinearLayout(this);

        results.setOrientation(
                LinearLayout.VERTICAL
        );

        root.addView(
                results,
                lp(-1, -2)
        );

        search.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        results.removeAllViews();

                        String q =
                                query.getText()
                                        .toString()
                                        .trim()
                                        .toLowerCase(
                                                Locale.ROOT
                                        );

                        if (q.length() == 0) {

                            message(
                                    "البحث",
                                    "أدخل كلمة أو رقم."
                            );

                            return;
                        }

                        int found = 0;

                        for (int i = 0;
                             i < personnel.size();
                             i++) {

                            LinkedHashMap<String, String> r =
                                    personnel.get(i);

                            if (matches(r, q)) {

                                final int index = i;

                                Button result =
                                        createButton(
                                                safe(
                                                        r.get(
                                                                "الاسم الثلاثي"
                                                        )
                                                ) +
                                                "\n" +
                                                safe(
                                                        r.get(
                                                                "الرقم الوطني"
                                                        )
                                                ),
                                                WHITE
                                        );

                                result.setTextColor(DARK);

                                results.addView(
                                        result,
                                        margin(
                                                -1,
                                                70,
                                                0,
                                                8,
                                                0,
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

                            emptyInto(
                                    results,
                                    "لا توجد نتائج مطابقة."
                            );
                        }
                    }
                }
        );

        backDashboard();
    }

    private boolean matches(
            LinkedHashMap<String, String> record,
            String q) {

        for (String value :
                record.values()) {

            if (value != null &&
                    value.toLowerCase(
                            Locale.ROOT
                    ).contains(q)) {

                return true;
            }
        }

        return false;
    }

    // =========================================================
    // FINANCIAL
    // =========================================================

    private void showFinancial() {

        createPage(
                "البطاقات المالية",
                "بيانات المرتب والحسابات"
        );

        if (personnel.isEmpty()) {

            emptyMessage(
                    "لا توجد بطاقات مالية بعد."
            );

        } else {

            for (int i = 0;
                 i < personnel.size();
                 i++) {

                final int index = i;

                LinkedHashMap<String, String> r =
                        personnel.get(i);

                Button b =
                        createButton(
                                safe(
                                        r.get(
                                                "الاسم الثلاثي"
                                        )
                                ) +
                                "\nالمصرف: " +
                                safe(
                                        r.get(
                                                "اسم المصرف"
                                        )
                                ),
                                WHITE
                        );

                b.setTextColor(DARK);

                root.addView(
                        b,
                        margin(
                                -1,
                                75,
                                0,
                                8,
                                0,
                                0
                        )
                );

                b.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                showFinancialCard(
                                        personnel.get(index)
                                );
                            }
                        }
                );
            }
        }

        backDashboard();
    }

    private void showFinancialCard(
            LinkedHashMap<String, String> r) {

        createPage(
                "البطاقة المالية",
                safe(
                        r.get(
                                "الاسم الثلاثي"
                        )
                )
        );

        addCardInfo(
                "الاسم",
                r.get("الاسم الثلاثي")
        );

        addCardInfo(
                "الرقم المالي",
                r.get("رقم مالي")
        );

        addCardInfo(
                "الرقم الحسابي",
                r.get("الرقم الحسابي")
        );

        addCardInfo(
                "الرتبة",
                r.get("الرتبة")
        );

        addCardInfo(
                "الدرجة الوظيفية",
                r.get("الدرجة الوظيفية")
        );

        addCardInfo(
                "المصرف",
                r.get("اسم المصرف")
        );

        addCardInfo(
                "فرع المصرف",
                r.get("اسم فرع المصرف")
        );

        addCardInfo(
                "رقم الحساب",
                r.get("رقم الحساب")
        );

        addCardInfo(
                "حالة المرتب",
                r.get("حالة المرتب")
        );

        addCardInfo(
                "سبب إيقاف المرتب",
                r.get(
                        "سبب إيقاف المرتب"
                )
        );

        addCardInfo(
                "تاريخ حالة المرتب",
                r.get(
                        "تاريخ حالة المرتب"
                )
        );

        addCardInfo(
                "ملاحظات مالية",
                r.get(
                        "ملاحظات مالية"
                )
        );

        backDashboard();
    }

    // =========================================================
    // MOVEMENT
    // =========================================================

    private void showMovement() {

        createPage(
                "الحركة والتكليف",
                "الحركة الإدارية والتكليفات"
        );

        addCardInfo(
                "أنواع الحركة",
                "تعيين\nنقل\nندب\nندب وزاري\n" +
                "ندب وكيل وزارة الداخلية\nتكليف\nعقد"
        );

        addCardInfo(
                "تكليف بالمنصب",
                "مدير فرع\nمدير إدارة\nمدير مكتب\nمدير وحدة"
        );

        addCardInfo(
                "الفروع",
                join(BRANCHES)
        );

        backDashboard();
    }

    // =========================================================
    // REPORTS
    // =========================================================

    private void showReports() {

        createPage(
                "التقارير والإحصائيات",
                "الموقف العام من السجلات المحفوظة"
        );

        addCardInfo(
                "إجمالي المنتسبين",
                String.valueOf(
                        personnel.size()
                )
        );

        addCardInfo(
                "الضباط",
                countExact(
                        "الصفة الوظيفية",
                        "ضابط"
                )
        );

        addCardInfo(
                "ضباط الصف",
                countExact(
                        "الصفة الوظيفية",
                        "ضابط صف"
                )
        );

        addCardInfo(
                "الأفراد",
                countExact(
                        "الصفة الوظيفية",
                        "فرد"
                )
        );

        addCardInfo(
                "الموظفون",
                countExact(
                        "الصفة الوظيفية",
                        "موظف"
                )
        );

        addCardInfo(
                "المكلفون",
                countField(
                        "الحالة العسكرية الحالية",
                        "مكلف"
                )
        );

        addCardInfo(
                "المنتدبون",
                countField(
                        "الحالة العسكرية الحالية",
                        "منتدب"
                )
        );

        addCardInfo(
                "الموقوفون",
                countField(
                        "الحالة العسكرية الحالية",
                        "موقوف"
                )
        );

        addCardInfo(
                "مرتب لحظي",
                countExact(
                        "حالة المرتب",
                        "مرتب لحظي"
                )
        );

        addCardInfo(
                "راتب حوافظ",
                countExact(
                        "حالة المرتب",
                        "راتب حوافظ"
                )
        );

        addCardInfo(
                "منح",
                countExact(
                        "حالة المرتب",
                        "منحة"
                )
        );

        addCardInfo(
                "لا يتقاضون مرتب",
                countExact(
                        "حالة المرتب",
                        "لا يتقاضى مرتب"
                )
        );

        addCardInfo(
                "موقوف المرتب",
                countExact(
                        "حالة المرتب",
                        "موقوف"
                )
        );

        addCardInfo(
                "الشهداء",
                countExact(
                        "الشهداء (نعم/لا)",
                        "نعم"
                )
        );

        addCardInfo(
                "المصابون",
                countExact(
                        "الجرحى والمصابين (نعم/لا)",
                        "نعم"
                )
        );

        addCardInfo(
                "ذوو تكليف بالمنصب",
                countPositioned()
        );

        backDashboard();
    }

    // =========================================================
    // SETTINGS
    // =========================================================

    private void showSettings() {

        createPage(
                "الإعدادات",
                "إعدادات منظومة Force ERP"
        );

        addCardInfo(
                "اسم المنظومة",
                "منظومة إدارة القوة العمومية"
        );

        addCardInfo(
                "الإصدار",
                "V7.1"
        );

        addCardInfo(
                "قاعدة البيانات",
                "Room / SQLite"
        );

        addCardInfo(
                "حالة قاعدة البيانات",
                databaseReady
                        ? "جاهزة"
                        : "جاري التحميل"
        );

        addCardInfo(
                "الحقول الأساسية",
                "116"
        );

        addCardInfo(
                "الحقول الإضافية",
                String.valueOf(
                        EXTRA_FIELDS.length
                )
        );

        addCardInfo(
                "إجمالي حقول نموذج المنتسب",
                String.valueOf(
                        FIELDS.length +
                        EXTRA_FIELDS.length
                )
        );

        addCardInfo(
                "السجلات",
                String.valueOf(
                        personnel.size()
                )
        );

        backDashboard();
    }

    // =========================================================
    // MEMBERSHIP
    // =========================================================

    private void showEndMembership(
            LinkedHashMap<String, String> record) {

        createPage(
                "إنهاء / إيقاف العضوية",
                safe(
                        record.get(
                                "الاسم الثلاثي"
                        )
                )
        );

        Spinner reason =
                createSpinner(
                        "سبب إنهاء العضوية",
                        MEMBERSHIP_STATUS
                );

        root.addView(
                reason,
                margin(-1, 58, 0, 12, 0, 0)
        );

        Button save =
                createButton(
                        "تأكيد حالة العضوية",
                        RED
                );

        root.addView(
                save,
                margin(-1, 56, 0, 12, 0, 0)
        );

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String value =
                                String.valueOf(
                                        reason.getSelectedItem()
                                );

                        if (value.equals(
                                "— فعال —")) {

                            message(
                                    "الحالة",
                                    "اختر سبب الإنهاء."
                            );

                            return;
                        }

                        record.put(
                                "حالة العضوية",
                                value
                        );

                        record.put(
                                "سبب إنهاء العضوية",
                                value
                        );

                        record.put(
                                "الحالة العسكرية الحالية",
                                "منتهي الخدمة"
                        );

                        int index =
                                findRecordIndex(
                                        safe(
                                                record.get(
                                                        "معرّف السجل"
                                                )
                                        )
                                );

                        if (index >= 0) {
                            personnel.set(
                                    index,
                                    record
                            );
                        }

                        persistRecordToRoom(
                                record
                        );

                        messageAndRun(
                                "تم التحديث",
                                "تم تحديث حالة العضوية.",
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        showPersonnel();
                                    }
                                }
                        );
                    }
                }
        );

        backDashboard();
    }

    // =========================================================
    // PAGE
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
                dp(28)
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

        header.setPadding(
                dp(18),
                dp(8),
                dp(18),
                dp(8)
        );

        TextView t =
                text(
                        title,
                        23,
                        WHITE,
                        true
                );

        TextView s =
                text(
                        subtitle,
                        13,
                        Color.rgb(220, 232, 220),
                        false
                );

        header.addView(
                t,
                lp(-1, 42)
        );

        header.addView(
                s,
                lp(-1, 30)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(DARK_GREEN);
        bg.setCornerRadius(dp(18));

        header.setBackground(bg);

        root.addView(
                header,
                margin(-1, 82, 0, 18, 0, 0)
        );

        setContentView(scroll);
    }

    // =========================================================
    // UI
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

        t.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        if (bold) {

            t.setTypeface(
                    Typeface.DEFAULT,
                    Typeface.BOLD
            );
        }

        return t;
    }

    private EditText createEditText(
            String hint) {

        EditText e =
                new EditText(this);

        e.setHint(hint);
        e.setTextSize(16);
        e.setTextColor(DARK);
        e.setHintTextColor(GRAY);

        e.setGravity(
                Gravity.RIGHT |
                Gravity.CENTER_VERTICAL
        );

        e.setPadding(
                dp(15),
                0,
                dp(15),
                0
        );

        e.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(dp(14));
        bg.setStroke(
                dp(1),
                BORDER
        );

        e.setBackground(bg);

        return e;
    }

    private Spinner createSpinner(
            String label,
            String[] values) {

        Spinner spinner =
                new Spinner(this);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_spinner_item,
                        values
                );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);

        spinner.setContentDescription(
                label
        );

        spinner.setLayoutDirection(
                View.LAYOUT_DIRECTION_RTL
        );

        return spinner;
    }

    private Button createButton(
            String title,
            int color) {

        Button b =
                new Button(this);

        b.setText(title);
        b.setTextSize(16);
        b.setAllCaps(false);
        b.setMinHeight(0);
        b.setMinimumHeight(0);

        b.setGravity(
                Gravity.CENTER
        );

        b.setPadding(
                dp(10),
                0,
                dp(10),
                0
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(color);
        bg.setCornerRadius(dp(14));

        b.setBackground(bg);

        b.setTextColor(
                color == WHITE
                        ? DARK
                        : WHITE
        );

        return b;
    }

    private void menu(
            String title,
            int color,
            View.OnClickListener listener) {

        Button b =
                createButton(
                        title,
                        color
                );

        root.addView(
                b,
                margin(-1, 58, 0, 9, 0, 0)
        );

        b.setOnClickListener(
                listener
        );
    }

    private void addStatCard(
            String title,
            String value,
            int color) {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setGravity(
                Gravity.CENTER
        );

        TextView number =
                text(
                        value,
                        29,
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

        box.addView(
                number,
                lp(-1, 45)
        );

        box.addView(
                label,
                lp(-1, 34)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(dp(16));
        bg.setStroke(
                dp(1),
                BORDER
        );

        box.setBackground(bg);

        root.addView(
                box,
                margin(-1, 90, 0, 8, 0, 0)
        );
    }

    private void addCardInfo(
            String label,
            String value) {

        LinearLayout box =
                new LinearLayout(this);

        box.setOrientation(
                LinearLayout.VERTICAL
        );

        box.setPadding(
                dp(14),
                dp(8),
                dp(14),
                dp(8)
        );

        TextView l =
                text(
                        label,
                        12,
                        GRAY,
                        true
                );

        TextView v =
                text(
                        safe(value),
                        16,
                        DARK,
                        false
                );

        box.addView(
                l,
                lp(-1, 27)
        );

        box.addView(
                v,
                lp(-1, -2)
        );

        GradientDrawable bg =
                new GradientDrawable();

        bg.setColor(WHITE);
        bg.setCornerRadius(dp(13));

        box.setBackground(bg);

        root.addView(
                box,
                margin(-1, -2, 0, 7, 0, 0)
        );
    }

    private void emptyMessage(
            String message) {

        TextView t =
                text(
                        message,
                        17,
                        GRAY,
                        false
                );

        t.setGravity(
                Gravity.CENTER
        );

        t.setPadding(
                dp(10),
                dp(35),
                dp(10),
                dp(35)
        );

        root.addView(
                t,
                lp(-1, -2)
        );
    }

    private void emptyInto(
            LinearLayout parent,
            String message) {

        TextView t =
                text(
                        message,
                        16,
                        GRAY,
                        false
                );

        t.setGravity(
                Gravity.CENTER
        );

        parent.addView(
                t,
                lp(-1, 65)
        );
    }

    private void backDashboard() {

        addSpace(root, 10);

        Button b =
                createButton(
                        "العودة إلى لوحة القيادة",
                        DARK_GREEN
                );

        root.addView(
                b,
                margin(-1, 54, 0, 15, 0, 0)
        );

        b.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDashboard();
                    }
                }
        );
    }

    // =========================================================
    // SHARED PREFERENCES BACKUP
    // =========================================================

    private void saveLegacyBackup() {

        if (prefs == null) {
            return;
        }

        StringBuilder all =
                new StringBuilder();

        for (int i = 0;
             i < personnel.size();
             i++) {

            if (i > 0) {
                all.append(
                        "§§RECORD§§"
                );
            }

            LinkedHashMap<String, String> record =
                    personnel.get(i);

            boolean first = true;

            for (Map.Entry<String, String> entry :
                    record.entrySet()) {

                if (!first) {

                    all.append(
                            "§§FIELD§§"
                    );
                }

                first = false;

                all.append(
                        encode(
                                entry.getKey()
                        )
                );

                all.append(
                        "§§VALUE§§"
                );

                all.append(
                        encode(
                                entry.getValue()
                        )
                );
            }
        }

        prefs.edit()
                .putString(
                        "records",
                        all.toString()
                )
                .apply();
    }

    private void loadPersonnel() {

        personnel.clear();

        if (prefs == null) {
            return;
        }

        String data =
                prefs.getString(
                        "records",
                        ""
                );

        if (data.length() == 0) {
            return;
        }

        String[] records =
                data.split(
                        "§§RECORD§§",
                        -1
                );

        for (String recordData :
                records) {

            LinkedHashMap<String, String> record =
                    new LinkedHashMap<>();

            String[] fields =
                    recordData.split(
                            "§§FIELD§§",
                            -1
                    );

            for (String field :
                    fields) {

                String[] pair =
                        field.split(
                                "§§VALUE§§",
                                2
                        );

                if (pair.length == 2) {

                    record.put(
                            decode(pair[0]),
                            decode(pair[1])
                    );
                }
            }

            if (!record.isEmpty()) {

                personnel.add(
                        record
                );
            }
        }
    }

    private String encode(String value) {

        if (value == null) {
            value = "";
        }

        return android.util.Base64
                .encodeToString(
                        value.getBytes(
                                StandardCharsets.UTF_8
                        ),
                        android.util.Base64.NO_WRAP
                );
    }

    private String decode(String value) {

        try {

            return new String(
                    android.util.Base64.decode(
                            value,
                            android.util.Base64.DEFAULT
                    ),
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            return "";
        }
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private String readValue(
            View view) {

        if (view instanceof EditText) {

            return ((EditText) view)
                    .getText()
                    .toString()
                    .trim();
        }

        if (view instanceof Spinner) {

            Object value =
                    ((Spinner) view)
                            .getSelectedItem();

            return value == null
                    ? ""
                    : String.valueOf(value);
        }

        return "";
    }

    private void selectSpinner(
            Spinner spinner,
            String value) {

        if (value == null) {
            return;
        }

        for (int i = 0;
             i < spinner.getCount();
             i++) {

            Object item =
                    spinner.getItemAtPosition(i);

            if (item != null &&
                    value.equals(
                            String.valueOf(item)
                    )) {

                spinner.setSelection(i);

                return;
            }
        }
    }

    private boolean isNumberField(
            String field) {

        return field.contains("عدد") ||
                field.contains("النسبة") ||
                field.contains("درجة") ||
                field.contains("شهر") ||
                field.equals("يوم متبقي");
    }

    private boolean isLongText(
            String field) {

        return field.contains("السيرة") ||
                field.contains("الملاحظات") ||
                field.contains("العنوان") ||
                field.contains("نص التنبيه") ||
                field.contains("سجل الترقيات") ||
                field.contains("اللغات المتقنة") ||
                field.contains("أرقام قرارات") ||
                field.contains("السلاح") ||
                field.contains("اللاسلكي") ||
                field.contains("المركبة") ||
                field.contains("معدات") ||
                field.contains("ملاحظات مالية");
    }

    private String generateRecordId() {

        return "FR-" +
                System.currentTimeMillis();
    }

    private int findRecordIndex(
            String id) {

        for (int i = 0;
             i < personnel.size();
             i++) {

            if (id.equals(
                    safe(
                            personnel.get(i)
                                    .get(
                                            "معرّف السجل"
                                    )
                    )
            )) {

                return i;
            }
        }

        return -1;
    }

    private String countField(
            String field,
            String contains) {

        int count = 0;

        for (LinkedHashMap<String, String> r :
                personnel) {

            String value =
                    safe(
                            r.get(field)
                    );

            if (value.contains(contains)) {
                count++;
            }
        }

        return String.valueOf(count);
    }

    private String countExact(
            String field,
            String value) {

        int count = 0;

        for (LinkedHashMap<String, String> r :
                personnel) {

            if (value.equals(
                    safe(
                            r.get(field)
                    )
            )) {

                count++;
            }
        }

        return String.valueOf(count);
    }

    private String countPositioned() {

        int count = 0;

        for (LinkedHashMap<String, String> r :
                personnel) {

            String value =
                    safe(
                            r.get(
                                    "تكليف بالمنصب"
                            )
                    );

            if (!value.equals(
                    "—"
            ) &&
                    !value.contains(
                            "بدون"
                    )) {

                count++;
            }
        }

        return String.valueOf(count);
    }

    private String calculateAge(
            String birth) {

        String[] formats = {
                "yyyy-MM-dd",
                "dd/MM/yyyy",
                "dd-MM-yyyy"
        };

        for (String format :
                formats) {

            try {

                SimpleDateFormat sdf =
                        new SimpleDateFormat(
                                format,
                                Locale.US
                        );

                sdf.setLenient(false);

                java.util.Date date =
                        sdf.parse(birth);

                Calendar born =
                        Calendar.getInstance();

                born.setTime(date);

                Calendar now =
                        Calendar.getInstance();

                int age =
                        now.get(
                                Calendar.YEAR
                        ) -
                        born.get(
                                Calendar.YEAR
                        );

                if (now.get(
                        Calendar.DAY_OF_YEAR
                ) <
                        born.get(
                                Calendar.DAY_OF_YEAR
                        )) {

                    age--;
                }

                if (age < 0) {
                    return "";
                }

                return String.valueOf(age);

            } catch (ParseException ignored) {
            }
        }

        return "";
    }

    private String calculateAnnualGrade(
            String percentage) {

        try {

            double p =
                    Double.parseDouble(
                            percentage
                                    .replace("%", "")
                                    .trim()
                    );

            if (p >= 90)
                return "ممتاز";

            if (p >= 80)
                return "جيد جدًا";

            if (p >= 70)
                return "جيد";

            if (p >= 60)
                return "مقبول";

            return "ضعيف";

        } catch (Exception e) {

            return "";
        }
    }

    private String safe(String value) {

        if (value == null ||
                value.trim().length() == 0) {

            return "—";
        }

        return value;
    }

    private String safeForStorage(
            String value) {

        if (value == null) {
            return "";
        }

        return value.trim();
    }

    private String safeException(
            Exception e) {

        if (e == null) {
            return "خطأ غير معروف";
        }

        String message =
                e.getMessage();

        return message == null ||
                message.trim().length() == 0
                ? e.getClass().getSimpleName()
                : message;
    }

    private String join(
            String[] values) {

        StringBuilder b =
                new StringBuilder();

        for (int i = 1;
             i < values.length;
             i++) {

            if (b.length() > 0) {
                b.append("\n");
            }

            b.append(
                    values[i]
            );
        }

        return b.toString();
    }

    // =========================================================
    // DIALOGS
    // =========================================================

    private void message(
            String title,
            String body) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setPositiveButton(
                        "حسنًا",
                        null
                )
                .show();
    }

    private void messageAndRun(
            String title,
            String body,
            final Runnable action) {

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(body)
                .setPositiveButton(
                        "متابعة",
                        (dialog, which) ->
                                action.run()
                )
                .show();
    }

    // =========================================================
    // LAYOUT
    // =========================================================

    private LinearLayout.LayoutParams lp(
            int width,
            int height) {

        int w =
                width == -1
                        ? ViewGroup.LayoutParams.MATCH_PARENT
                        : width == -2
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : width;

        int h =
                height == -1
                        ? ViewGroup.LayoutParams.MATCH_PARENT
                        : height == -2
                        ? ViewGroup.LayoutParams.WRAP_CONTENT
                        : height;