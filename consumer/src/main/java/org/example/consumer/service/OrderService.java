package org.example.consumer.service;

import lombok.AllArgsConstructor;
import org.example.consumer.entity.Book;
import org.example.consumer.entity.TransactionOrder;
import org.example.consumer.model.request.PlaceOrderRequest;
import org.example.consumer.repository.BookRepository;
import org.example.consumer.repository.TransactionOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@AllArgsConstructor
@Service
public class OrderService {

    private final BookRepository bookRepository;
    private final TransactionOrderRepository transactionOrderRepository;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void placeOrderApi(PlaceOrderRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new NoSuchElementException("No book found"));
        TransactionOrder order = TransactionOrder.builder()
                .book(book)
                .username(request.getUsername())
                .build();

        transactionOrderRepository.save(order);

        if (book.getQuantity() < 1) {
            throw new NoSuchElementException("out of books");
        }

        bookRepository.updateQuantity(book.getQuantity() - 1, request.getBookId());
    }

    @Transactional
    public void placeOrderKafka(PlaceOrderRequest request) {
        Book book = bookRepository.findById(request.getBookId()).orElseThrow(() -> new NoSuchElementException("No book found"));
        TransactionOrder order = TransactionOrder.builder()
                .book(book)
                .username(request.getUsername())
                .build();

        transactionOrderRepository.save(order);

        if (book.getQuantity() < 1) {
            throw new NoSuchElementException("out of books");
        }

        bookRepository.updateQuantity(book.getQuantity() - 1, request.getBookId());
    }
}
