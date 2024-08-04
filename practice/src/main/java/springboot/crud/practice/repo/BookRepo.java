package springboot.crud.practice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springboot.crud.practice.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {

}
