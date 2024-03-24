package org.example.consumer.repository;

import org.example.consumer.entity.TransactionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionOrderRepository extends JpaRepository<TransactionOrder, UUID> {
}