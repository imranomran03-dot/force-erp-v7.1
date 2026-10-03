package ly.moi.forceerp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonnelDao {

    // إضافة منتسب
    @Insert
    long insert(Personnel personnel);

    // تعديل بيانات منتسب
    @Update
    int update(Personnel personnel);

    // حذف منتسب
    @Delete
    int delete(Personnel personnel);

    // جلب جميع المنتسبين
    @Query("SELECT * FROM personnel ORDER BY fullName ASC")
    List<Personnel> getAll();

    // جلب منتسب بواسطة المعرّف
    @Query("SELECT * FROM personnel WHERE id = :id LIMIT 1")
    Personnel getById(long id);

    // البحث بالاسم
    @Query("SELECT * FROM personnel WHERE fullName LIKE '%' || :name || '%' ORDER BY fullName ASC")
    List<Personnel> searchByName(String name);

    // البحث بالرقم الوطني
    @Query("SELECT * FROM personnel WHERE nationalNumber = :nationalNumber LIMIT 1")
    Personnel getByNationalNumber(String nationalNumber);

    // جلب منتسبي فرع محدد
    @Query("SELECT * FROM personnel WHERE branch = :branch ORDER BY fullName ASC")
    List<Personnel> getByBranch(String branch);

    // إجمالي القوة
    @Query("SELECT COUNT(*) FROM personnel")
    int getTotalCount();

    // إجمالي فرع محدد
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch")
    int getBranchCount();

    // عدد حالة مرتب محددة
    @Query("SELECT COUNT(*) FROM personnel WHERE salaryStatus = :salaryStatus")
    int getSalaryStatusCount(String salaryStatus);

    // عدد حالة مرتب محددة داخل فرع
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND salaryStatus = :salaryStatus")
    int getBranchSalaryStatusCount(String branch, String salaryStatus);

    // عدد فئة وظيفية محددة
    @Query("SELECT COUNT(*) FROM personnel WHERE jobStatus = :jobStatus")
    int getJobStatusCount(String jobStatus);

    // عدد فئة وظيفية محددة داخل فرع
    @Query("SELECT COUNT(*) FROM personnel WHERE branch = :branch AND jobStatus = :jobStatus")
    int getBranchJobStatusCount(String branch, String jobStatus);

    // عدد المتقاعدين/المنتهية خدمتهم
    @Query("SELECT COUNT(*) FROM personnel WHERE terminationStatus IS NOT NULL AND terminationStatus != ''")
    int getTerminatedCount();

    // حذف جميع البيانات
    @Query("DELETE FROM personnel")
    void deleteAll();
}