package co.com.Platzi.PrimerosPasos.repository;

import co.com.Platzi.PrimerosPasos.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
