package com.example.springbootdemo.repository;

import com.example.springbootdemo.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecordRepository extends JpaRepository<Record, Long> {

}
