package com.demotask.decathlon.controller;

import com.demotask.decathlon.dto.ResultDTO;
import com.demotask.decathlon.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    public ResultController(){
    }

    @PostMapping
    public ResultDTO create(@RequestBody ResultDTO resultDTO) {
        return save(resultDTO);
    }

    @PutMapping()
    public ResultDTO update(@RequestBody ResultDTO resultDTO) {
        return save(resultDTO);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") int id){
        resultService.delete(id);
    }

    @GetMapping("{id}")
    public ResultDTO getById(@PathVariable int id) {
        return resultService.getDTOById(id);
    }

    @GetMapping("/user/{userId}/list")
    public List<ResultDTO> getAllByUserId(@PathVariable("userId") int userId) {
        return resultService.getAllDTOsByUserId(userId);
    }

    @GetMapping("/user/{userId}/sum")
    public Double getPointSumByUserId(@PathVariable("userId") int userId) {
        return resultService.getPointSumByUserId(userId);
    }

    @GetMapping()
    public List<ResultDTO> getAll() {
        return resultService.getAllDTOs();
    }

    private ResultDTO save(ResultDTO resultDTO) {
        return resultService.save(resultDTO);
    }
}