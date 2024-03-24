package org.example.consumer.repository;

import org.example.consumer.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Transactional
    @Modifying
    @Query("update Book b set b.quantity = ?1 where b.id = ?2")
    void updateQuantity(Integer quantity, Integer id);
}