package ly.moi.forceerp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "personnel")
public class Personnel {

    @PrimaryKey(autoGenerate = true)
    public long id;

    // =========================
    // البيانات الأساسية 1-10
    // =========================
    public String recordId;
    public String fullName;
    public String rank;
    public String surname;
    public String branch;
    public String fatherName;
    public String motherName;
    public String jobStatus;
    public String nationalNumber;
    public String familyBookNumber;

    // =========================
    // 11-20
    // =========================
    public String accountNumber;
    public String maritalStatus;
    public String spouseName;
    public int childrenCount;
    public String bloodType;
    public String uniformSize;
    public String shoeSize;
    public String residenceCity;
    public String phone;
    public String personalCardNumber;

    // =========================
    // 21-30
    // =========================
    public String passportNumber;
    public String experience;
    public String languages;
    public String appointmentDecisionNumber;
    public String appointmentDecisionDate;
    public String lastPromotionNumber;
    public String lastPromotionDate;
    public String previousPromotions;
    public String assignmentStartDate;
    public String assignmentEndDate;

    // =========================
    // 31-40
    // =========================
    public String currentMilitaryStatus;
    public String notes;
    public String birthDate;
    public String age;
    public String birthPlace;
    public String detailedAddress;
    public String alternativePhone;
    public String emergencyContactName;
    public String emergencyContactPhone;
    public String unitSector;

    // =========================
    // 41-50
    // =========================
    public String jobGrade;
    public String previousRank;
    public String promotionDecisionNumber;
    public String promotionDecisionDate;
    public String promotionDecisionAuthority;
    public String assignmentType;
    public String assignmentAuthority;
    public String assignmentLocation;
    public String assignmentStart;
    public String assignmentEnd;

    // =========================
    // 51-60
    // =========================
    public String assignmentDecisionNumber;
    public String assignmentStatus;
    public String courseType;
    public String courseName;
    public String trainingAuthority;
    public String trainingCountry;
    public String courseStartDate;
    public String courseEndDate;
    public String courseLevel;
    public String certificateNumber;

    // =========================
    // 61-70
    // =========================
    public String qualification;
    public String specialization;
    public String educationalInstitution;
    public String graduationCountry;
    public String graduationYear;
    public String graduationGrade;
    public String language1;
    public String language1Reading;
    public String language1Writing;
    public String language1Speaking;

    // =========================
    // 71-80
    // =========================
    public String language2;
    public String language2Reading;
    public String language2Writing;
    public String language2Speaking;
    public String documentType;
    public String documentNumber;
    public String documentDate;
    public String documentExpiryDate;
    public String issuingAuthority;
    public String documentStatus;

    // =========================
    // 81-90
    // =========================
    public String alertStatus;
    public String alertText;
    public String alertDate;
    public int remainingDays;
    public String nextPromotionEligibilityDate;
    public int promotionRemainingMonths;
    public String promotionAlertStatus;
    public String medalsAndAwards;
    public int exceptionalPromotionsCount;
    public String exceptionalPromotionDecisionNumbers;

    // =========================
    // 91-100
    // =========================
    public int verbalReprimandCount;
    public int writtenReprimandCount;
    public int verbalWarningCount;
    public int writtenWarningCount;
    public int rankReductionCount;
    public int chargesCount;
    public int administrativeInvestigationCount;
    public int sickLeaveDays;
    public String injured;
    public String injuryDate;

    // =========================
    // 101-110
    // =========================
    public String martyr;
    public String martyrdomDate;
    public int appreciationLettersCount;
    public int positiveEvaluationPoints;
    public int negativeEvaluationPoints;
    public double annualEvaluationPercentage;
    public String annualEvaluationGrade;
    public String photoPath;
    public String securityPlanParticipation;
    public String caseArrestStatus;

    // =========================
    // 111-116
    // =========================
    public int caseCount;
    public String goodConduct;
    public String weapon;
    public String radio;
    public String vehicle;
    public String otherEquipment;

    // =========================
    // البيانات المالية
    // =========================
    public String financialNumber;
    public String bankName;
    public String bankBranchName;
    public String bankAccountNumber;
    public String salaryStatus;
    public String salarySuspensionReason;
    public String salaryStatusDate;
    public String financialNotes;

    // =========================
    // البيانات الإدارية
    // =========================
    public String assignedPosition;
    public String centralRegion;
    public String workStatus;
    public String membershipStatus;
    public String disabilityStatus;

    // =========================
    // إنهاء العضوية
    // =========================
    public String terminationStatus;
    public String terminationDate;
    public String terminationReason;

    // =========================
    // العنصر النسائي / الجنس
    // =========================
    public String gender;

    // =========================
    // التدقيق
    // =========================
    public String createdAt;
    public String updatedAt;
}
