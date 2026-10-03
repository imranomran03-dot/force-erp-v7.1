package ly.moi.forceerp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonnelDao {

    // =========================
    // إضافة منتسب
    // =========================
    @Insert
    long insert(Personnel personnel);


    // =========================
    // تعديل بيانات منتسب
    // =========================
    @Update
    int update(Personnel personnel);


    // =========================
    // حذف منتسب
    // =========================
    @Delete
    int delete(Personnel personnel);


    // =========================
    // جلب جميع المنتسبين
    // =========================
    @Query("SELECT * FROM personnel ORDER BY fullName ASC")
    List<Personnel> getAll();


    // =========================
    // جلب منتسب بواسطة المعرّف
    // =========================
    @Query("SELECT * FROM personnel WHERE id = :id LIMIT 1")
    Personnel getById(long id);


    // =========================
    // البحث بالاسم
    // =========================
    @Query("SELECT * FROM personnel WHERE fullName LIKE '%' || :name || '%' ORDER BY fullName ASC")
    List<Personnel> searchByName(String name);


    // =========================
    // البحث بالرقم الوطني
    // =========================
    @Query("SELECT * FROM personnel WHERE nationalNumber = :nationalNumber LIMIT 1")
    Personnel getByNationalNumber(String nationalNumber);


    // =========================
    // البحث بالرقم العسكري/الحسابي
    // =========================
    @Query("SELECT * FROM personnel WHERE accountNumber = :accountNumber LIMIT 1")
    Personnel getByAccountNumber(String accountNumber);


    // =========================
    // جلب منتسبي فرع محدد
    // =========================
    @Query("SELECT * FROM personnel WHERE branch = :branch ORDER BY fullName ASC")
    List<Personnel> getByBranch(String branch);


    // =========================
    // إجمالي عدد المنتسبين
    // =========================
    @Query("SELECT COUNT(*) FROM personnel")
    int getTotalCount();


    // =========================
    // إجمالي منتسبي فرع محدد
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch")
    int getBranchCount(String branch);


    // =========================
    // عدد حالة مرتب محددة
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = :salaryStatus")
    int getSalaryStatusCount(String salaryStatus);


    // =========================
    // عدد حالة مرتب محددة داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = :salaryStatus")
    int getBranchSalaryStatusCount(String branch, String salaryStatus);


    // =========================
    // عدد فئة وظيفية محددة
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = :jobStatus")
    int getJobStatusCount(String jobStatus);


    // =========================
    // عدد فئة وظيفية محددة داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = :jobStatus")
    int getBranchJobStatusCount(String branch, String jobStatus);


    // =========================
    // عدد الضباط
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'ضابط'")
    int getOfficersCount();


    // =========================
    // عدد ضباط الصف
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'ضابط صف'")
    int getNonCommissionedOfficersCount();


    // =========================
    // عدد الأفراد
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'فرد'")
    int getIndividualsCount();


    // =========================
    // عدد الموظفين
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = 'موظف'")
    int getEmployeesCount();


    // =========================
    // عدد الضباط داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = 'ضابط'")
    int getBranchOfficersCount(String branch);


    // =========================
    // عدد ضباط الصف داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = 'ضابط صف'")
    int getBranchNonCommissionedOfficersCount(String branch);


    // =========================
    // عدد الأفراد داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = 'فرد'")
    int getBranchIndividualsCount(String branch);


    // =========================
    // عدد الموظفين داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = 'موظف'")
    int getBranchEmployeesCount(String branch);


    // =========================
    // عدد المرتب اللحظي
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'مرتب لحظي'")
    int getCurrentSalaryCount();


    // =========================
    // عدد راتب الحوافظ
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'راتب حوافظ'")
    int getSalaryListsCount();


    // =========================
    // عدد المنح
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'منحة'")
    int getGrantsCount();


    // =========================
    // عدد من لا يتقاضون مرتب
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'لا يتقاضى مرتب'")
    int getWithoutSalaryCount();


    // =========================
    // عدد الموقوفة مرتباتهم
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = 'موقوف'")
    int getSuspendedSalaryCount();


    // =========================
    // المرتب اللحظي داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = 'مرتب لحظي'")
    int getBranchCurrentSalaryCount(String branch);


    // =========================
    // راتب الحوافظ داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = 'راتب حوافظ'")
    int getBranchSalaryListsCount(String branch);


    // =========================
    // المنح داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = 'منحة'")
    int getBranchGrantsCount(String branch);


    // =========================
    // لا يتقاضى مرتب داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = 'لا يتقاضى مرتب'")
    int getBranchWithoutSalaryCount(String branch);


    // =========================
    // الموقوفة مرتباتهم داخل فرع
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = 'موقوف'")
    int getBranchSuspendedSalaryCount(String branch);


    // =========================
    // عدد المنتهية خدمتهم
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE terminationStatus IS NOT NULL AND terminationStatus != ''")
    int getTerminatedCount();


    // =========================
    // عدد الشهداء
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE martyr = 'نعم'")
    int getMartyrsCount();


    // =========================
    // عدد المصابين
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE injured = 'نعم'")
    int getInjuredCount();


    // =========================
    // عدد المشاركين في الخطط الأمنية
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE securityPlanParticipation = 'نعم'")
    int getSecurityPlanParticipantsCount();


    // =========================
    // عدد أصحاب قضايا
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE caseArrestStatus = 'نعم'")
    int getCaseArrestsCount();


    // =========================
    // عدد منتسبي فرع حسب حالة العمل
    // =========================
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND currentMilitaryStatus = :status")
    int getBranchMilitaryStatusCount(String branch, String status);


    // =========================
    // البحث العام
    // الاسم أو الرقم الوطني أو الرقم الحسابي
    // =========================
    @Query(
        "SELECT * FROM personnel " +
        "WHERE fullName LIKE '%' || :search || '%' " +
        "OR nationalNumber LIKE '%' || :search || '%' " +
        "OR accountNumber LIKE '%' || :search || '%' " +
        "ORDER BY fullName ASC"
    )
    List<Personnel> globalSearch(String search);


    // =========================
    // حذف جميع البيانات
    // =========================
    @Query("DELETE FROM personnel")
    void deleteAll();
}