package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    Meal findMealByIdAndUserId(@Param("id") int id,
                               @Param("userid") int userid);
    List<Meal> getAllByUserIdOrderByDateTimeDesc(@Param("userid") int userid);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userid")
    int delete(@Param("id") int id,
               @Param("userid") int userid);

    List<Meal> getAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(@Param("userid") int userid,
                                                                   @Param("startDate")LocalDateTime startDate,
                                                                   @Param("endDate") LocalDateTime endDate);

}
