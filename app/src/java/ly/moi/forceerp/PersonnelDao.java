package ly.moi.forceerp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PersonnelDao {

    @Insert
    long insert(Personnel personnel);

    @Update
    int update(Personnel personnel);

    @Delete
    int delete(Personnel personnel);

    @Query("SELECT * FROM personnel ORDER BY fullName COLLATE NOCASE ASC")
    List<Personnel> getAll();

    @Query("SELECT * FROM personnel WHERE id = :id LIMIT 1")
    Personnel getById(long id);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE fullName LIKE '%' || :name || '%' " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> searchByName(String name);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE nationalNumber = :nationalNumber " +
            "LIMIT 1"
    )
    Personnel getByNationalNumber(String nationalNumber);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE accountNumber = :accountNumber " +
            "LIMIT 1"
    )
    Personnel getByAccountNumber(String accountNumber);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE fullName LIKE '%' || :search || '%' " +
            "OR nationalNumber LIKE '%' || :search || '%' " +
            "OR accountNumber LIKE '%' || :search || '%' " +
            "OR phone LIKE '%' || :search || '%' " +
            "OR financialNumber LIKE '%' || :search || '%' " +
            "OR passportNumber LIKE '%' || :search || '%' " +
            "OR personalCardNumber LIKE '%' || :search || '%' " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> globalSearch(String search);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE branch = :branch " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getByBranch(String branch);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE jobStatus = :jobStatus " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getByJobStatus(String jobStatus);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE gender = :gender " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getByGender(String gender);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE salaryStatus = :salaryStatus " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getBySalaryStatus(String salaryStatus);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE workStatus = :workStatus " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getByWorkStatus(String workStatus);

    @Query(
            "SELECT * FROM personnel " +
            "WHERE membershipStatus = :membershipStatus " +
            "ORDER BY fullName ASC"
    )
    List<Personnel> getByMembershipStatus(String membershipStatus);

    @Query("SELECT COUNT(*) FROM personnel")
    int getTotalCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE jobStatus = 'ضابط'"
    )
    int getOfficersCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE jobStatus = 'ضابط صف'"
    )
    int getNonCommissionedOfficersCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE jobStatus = 'فرد'"
    )
    int getIndividualsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE jobStatus = 'موظف'"
    )
    int getEmployeesCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE gender = 'ذكر'"
    )
    int getMaleCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE gender = 'أنثى'"
    )
    int getFemaleCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE salaryStatus = 'مرتب لحظي'"
    )
    int getCurrentSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE salaryStatus = 'راتب حوافظ'"
    )
    int getSalaryListsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE salaryStatus = 'منحة'"
    )
    int getGrantsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE salaryStatus = 'لا يتقاضى مرتب'"
    )
    int getWithoutSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE salaryStatus = 'موقوف'"
    )
    int getSuspendedSalaryCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch"
    )
    int getBranchCount(String branch);

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND jobStatus = :jobStatus"
    )
    int getBranchJobStatusCount(
            String branch,
            String jobStatus
    );

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND gender = :gender"
    )
    int getBranchGenderCount(
            String branch,
            String gender
    );

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE branch = :branch " +
            "AND salaryStatus = :salaryStatus"
    )
    int getBranchSalaryStatusCount(
            String branch,
            String salaryStatus
    );

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE martyr = 'نعم'"
    )
    int getMartyrsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE injured = 'نعم'"
    )
    int getInjuredCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE securityPlanParticipation = 'نعم'"
    )
    int getSecurityPlanParticipantsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE caseArrestStatus = 'نعم'"
    )
    int getCaseArrestsCount();

    @Query(
            "SELECT COALESCE(SUM(caseCount), 0) " +
            "FROM personnel"
    )
    int getTotalCaseCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE courseName IS NOT NULL " +
            "AND courseName != ''"
    )
    int getTrainedPersonnelCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE qualification IS NOT NULL " +
            "AND qualification != ''"
    )
    int getQualifiedPersonnelCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE alertStatus IS NOT NULL " +
            "AND alertStatus != '' " +
            "AND alertStatus != 'لا يوجد'"
    )
    int getAlertsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE documentStatus = 'منتهية'"
    )
    int getExpiredDocumentsCount();

    @Query(
            "SELECT COUNT(*) FROM personnel " +
            "WHERE terminationStatus IS NOT NULL " +
            "AND terminationStatus != ''"
    )
    int getTerminatedCount();

    @Query("DELETE FROM personnel")
    void deleteAll();
}