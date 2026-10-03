package ly.moi.forceerp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonnelDao {

    // =========================================================
    // الإضافة والتعديل والحذف
    // =========================================================

    @Insert
    long insert(Personnel personnel);

    @Update
    int update(Personnel personnel);

    @Delete
    int delete(Personnel personnel);


    // =========================================================
    // جميع المنتسبين
    // =========================================================

    @Query("SELECT * FROM personnel ORDER BY fullName ASC")
    List<Personnel> getAll();


    // =========================================================
    // البحث بالمعرف
    // =========================================================

    @Query("SELECT * FROM personnel WHERE id = :id LIMIT 1")
    Personnel getById(long id);


    // =========================================================
    // البحث بالاسم
    // =========================================================

    @Query("SELECT * FROM personnel WHERE fullName LIKE '%' || :name || '%' ORDER BY fullName ASC")
    List<Personnel> searchByName(String name);


    // =========================================================
    // البحث بالرقم الوطني
    // =========================================================

    @Query("SELECT * FROM personnel WHERE nationalNumber = :nationalNumber LIMIT 1")
    Personnel getByNationalNumber(String nationalNumber);


    // =========================================================
    // البحث بالرقم الحسابي
    // =========================================================

    @Query("SELECT * FROM personnel WHERE accountNumber = :accountNumber LIMIT 1")
    Personnel getByAccountNumber(String accountNumber);


    // =========================================================
    // البحث العام
    // الاسم أو الرقم الوطني أو الرقم الحسابي
    // =========================================================

    @Query(
            "SELECT * FROM personnel " +
            "WHERE fullName LIKE '%' || :search || '%' " +
            "OR nationalNumber LIKE '%' || :search || '%' " +
            "OR accountNumber LIKE '%' || :search || '%' " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> globalSearch(String search);


    // =========================================================
    // المنتسبون حسب الفرع
    // =========================================================

    @Query("SELECT * FROM personnel WHERE branch = :branch ORDER BY fullName ASC")
    List<Personnel> getByBranch(String branch);


    // =========================================================
    // إجمالي القوة
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel")
    int getTotalCount();


    // =========================================================
    // إجمالي قوة فرع محدد
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch")
    int getBranchCount(String branch);


    // =========================================================
    // الفئات الوظيفية
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = :jobStatus")
    int getJobStatusCount(String jobStatus);

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND jobStatus = :jobStatus"
    )
    int getBranchJobStatusCount(String branch, String jobStatus);


    // =========================================================
    // الضباط
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'ضابط'")
    int getOfficersCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND jobStatus = 'ضابط'"
    )
    int getBranchOfficersCount(String branch);


    // =========================================================
    // ضباط الصف
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'ضابط صف'")
    int getNonCommissionedOfficersCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND jobStatus = 'ضابط صف'"
    )
    int getBranchNonCommissionedOfficersCount(String branch);


    // =========================================================
    // الأفراد
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'فرد'")
    int getIndividualsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND jobStatus = 'فرد'"
    )
    int getBranchIndividualsCount(String branch);


    // =========================================================
    // الموظفون
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'موظف'")
    int getEmployeesCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND jobStatus = 'موظف'"
    )
    int getBranchEmployeesCount(String branch);


    // =========================================================
    // الحالات المالية
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = :salaryStatus")
    int getSalaryStatusCount(String salaryStatus);


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = :salaryStatus"
    )
    int getBranchSalaryStatusCount(String branch, String salaryStatus);


    // =========================================================
    // مرتب لحظي
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'مرتب لحظي'")
    int getCurrentSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = 'مرتب لحظي'"
    )
    int getBranchCurrentSalaryCount(String branch);


    // =========================================================
    // راتب حوافظ
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'راتب حوافظ'")
    int getSalaryListsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = 'راتب حوافظ'"
    )
    int getBranchSalaryListsCount(String branch);


    // =========================================================
    // المنح
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'منحة'")
    int getGrantsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = 'منحة'"
    )
    int getBranchGrantsCount(String branch);


    // =========================================================
    // لا يتقاضى مرتب
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'لا يتقاضى مرتب'")
    int getWithoutSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = 'لا يتقاضى مرتب'"
    )
    int getBranchWithoutSalaryCount(String branch);


    // =========================================================
    // مرتب موقوف
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'موقوف'")
    int getSuspendedSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND salaryStatus = 'موقوف'"
    )
    int getBranchSuspendedSalaryCount(String branch);


    // =========================================================
    // الحالة العسكرية
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE currentMilitaryStatus = :status"
    )
    int getMilitaryStatusCount(String status);


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND currentMilitaryStatus = :status"
    )
    int getBranchMilitaryStatusCount(String branch, String status);


    // =========================================================
    // التكليف
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE assignmentType = :assignmentType"
    )
    int getAssignmentTypeCount(String assignmentType);


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND assignmentType = :assignmentType"
    )
    int getBranchAssignmentTypeCount(
            String branch,
            String assignmentType
    );


    // =========================================================
    // المنصب المكلف به
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE assignedPosition = :position"
    )
    int getAssignedPositionCount(String position);


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND assignedPosition = :position"
    )
    int getBranchAssignedPositionCount(
            String branch,
            String position
    );


    // =========================================================
    // الشهداء
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE martyr = 'نعم'")
    int getMartyrsCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND martyr = 'نعم'"
    )
    int getBranchMartyrsCount(String branch);


    // =========================================================
    // المصابون
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE injured = 'نعم'")
    int getInjuredCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND injured = 'نعم'"
    )
    int getBranchInjuredCount(String branch);


    // =========================================================
    // الخطط الأمنية
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE securityPlanParticipation = 'نعم'"
    )
    int getSecurityPlanParticipantsCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND securityPlanParticipation = 'نعم'"
    )
    int getBranchSecurityPlanParticipantsCount(String branch);


    // =========================================================
    // القضايا
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE caseArrestStatus = 'نعم'"
    )
    int getCaseArrestsCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch AND caseArrestStatus = 'نعم'"
    )
    int getBranchCaseArrestsCount(String branch);


    // =========================================================
    // عدد القضايا
    // =========================================================

    @Query("SELECT COALESCE(SUM(caseCount), 0) FROM personnel")
    int getTotalCaseCount();


    @Query(
            "SELECT COALESCE(SUM(caseCount), 0) FROM personnel " +
            "WHERE branch = :branch"
    )
    int getBranchCaseCount(String branch);


    // =========================================================
    // الدورات
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE courseName IS NOT NULL AND courseName != ''")
    int getTrainedPersonnelCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND courseName IS NOT NULL " +
            "AND courseName != ''"
    )
    int getBranchTrainedPersonnelCount(String branch);


    // =========================================================
    // المؤهلات
    // =========================================================

    @Query("SELECT COUNT(*) FROM personnel WHERE qualification IS NOT NULL AND qualification != ''")
    int getQualifiedPersonnelCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND qualification IS NOT NULL " +
            "AND qualification != ''"
    )
    int getBranchQualifiedPersonnelCount(String branch);


    // =========================================================
    // التنبيهات
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE alertStatus IS NOT NULL " +
            "AND alertStatus != ''"
    )
    int getAlertsCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND alertStatus IS NOT NULL " +
            "AND alertStatus != ''"
    )
    int getBranchAlertsCount(String branch);


    // =========================================================
    // الوثائق المنتهية
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE documentStatus = 'منتهية'"
    )
    int getExpiredDocumentsCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND documentStatus = 'منتهية'"
    )
    int getBranchExpiredDocumentsCount(String branch);


    // =========================================================
    // المنتهية خدمتهم
    // =========================================================

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE terminationStatus IS NOT NULL " +
            "AND terminationStatus != ''"
    )
    int getTerminatedCount();


    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND terminationStatus IS NOT NULL " +
            "AND terminationStatus != ''"
    )
    int getBranchTerminatedCount(String branch);


    // =========================================================
    // حذف جميع البيانات
    // =========================================================

    @Query("DELETE FROM personnel")
    void deleteAll();
}