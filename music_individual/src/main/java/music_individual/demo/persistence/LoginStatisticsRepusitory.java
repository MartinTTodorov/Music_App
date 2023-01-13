package music_individual.demo.persistence;

import music_individual.demo.persistence.entities.LoginsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginStatisticsRepusitory extends JpaRepository<LoginsEntity, Integer> {

    @Query(value = "SELECT COUNT(*) FROM musicindividual.logins " +
            "WHERE time " +
            "BETWEEN date_sub(current_date(), interval :numberOfDays DAY) " +
            "AND current_date()", nativeQuery = true)
    public Integer getNumberOfLoginsFromLastDays(@Param("numberOfDays") Integer numberOfDays);
}
