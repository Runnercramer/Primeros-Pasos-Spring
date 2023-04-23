package co.com.Platzi.PrimerosPasos.repository;

import co.com.Platzi.PrimerosPasos.dto.UserDto;
import co.com.Platzi.PrimerosPasos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByNameAndEmail(String name, String email);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthdayBetween(LocalDate begin, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    @Query("SELECT new co.com.Platzi.PrimerosPasos.dto.UserDto(u.id, u.name, u.birthday)" +
            "FROM User u WHERE u.birthday=:parametroFecha AND u.email=:parametroEmail")
    Optional<UserDto> getAllByBirthdayAndEmail(@Param("parametroFecha") LocalDate date, @Param("parametroEmail") String email);
}
