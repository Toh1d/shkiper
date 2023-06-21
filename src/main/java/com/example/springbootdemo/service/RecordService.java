package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Record;
import com.example.springbootdemo.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordService(RecordRepository userRepository) {
        this.recordRepository = userRepository;
    }

    public Record findById(Long id) {
        return recordRepository.getOne(id);
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record saveRecord(Record record) {
        return recordRepository.save(record);
    }

    public void deleteById(Long id){
        recordRepository.deleteById(id);
    }

}
