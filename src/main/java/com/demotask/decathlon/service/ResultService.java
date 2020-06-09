package com.demotask.decathlon.service;

import com.demotask.decathlon.dto.ResultDTO;
import com.demotask.decathlon.model.Event;
import com.demotask.decathlon.model.Result;
import com.demotask.decathlon.repository.ResultRepository;
import com.demotask.decathlon.util.factory.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private static final Logger log = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    private ResultFactory resultFactory;

    @Autowired
    private ResultRepository resultRepository;

    public ResultService() {
    }

    public void delete(int id) {
        resultRepository.deleteById(id);
    }

    public ResultDTO save(ResultDTO resultDTO) {
        return resultFactory.toDto(save(resultFactory.toEntity(resultDTO)));
    }

    public Result save(Result result) {
        if (result == null) {
            String msg = "Cannot save Result. Result is NULL";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        if (result.getUser() == null) {
            String msg = "Cannot save Result. User in result is NULL";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        if (result.getEvent() == null) {
            String msg = "Cannot save Result. Event in result is NULL";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        if (result.getPoints() == null) {
            String msg = "Cannot save Result. Points in result is NULL";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        if (result.getEventResult() == null) {
            String msg = "Cannot save Result. EventResult in result is NULL";
            log.error(msg);
            throw new RuntimeException(msg);
        }

        result.setPoints(getPointsInEvent(result));

        Result savedResult = resultRepository.save(result);

        return savedResult;
    }

    public Result getById(int id) { return resultRepository.findById(id).orElse(null); }

    public ResultDTO getDTOById(int id) { return resultFactory.toDto(getById(id)); }

    public List<ResultDTO> getAllDTOs() { return resultFactory.toDto(getAll()); }

    public List<Result> getAll() { return resultRepository.findAll(); }

    public List<ResultDTO> getAllDTOsByUserId(int userId) { return resultFactory.toDto(getAllByUserId(userId)); }

    public List<Result> getAllByUserId(int userId) { return resultRepository.findAllByUserId(userId); }

    public Double getPointSumByUserId(int userId) { return resultRepository.userPointSum(userId); }


    Double getPointsInEvent(Result result){
        Event event = result.getEvent();
        double a =event.getA();
        double b =event.getB();
        double c =event.getC();

        double resultPoints = a * Math.pow(Math.abs(result.getEventResult() - b), c);

        return resultPoints;
    }
}
