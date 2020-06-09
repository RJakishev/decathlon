package com.demotask.decathlon.util.factory;

import com.demotask.decathlon.dto.ResultDTO;
import com.demotask.decathlon.model.Event;
import com.demotask.decathlon.model.Result;
import com.demotask.decathlon.model.User;
import com.demotask.decathlon.service.EventService;
import com.demotask.decathlon.service.ResultService;
import com.demotask.decathlon.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultFactory {

    private static final Logger log = LoggerFactory.getLogger(ResultFactory.class);


    @Autowired
    private ResultService resultService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    public ResultFactory(){

    }

    public ResultDTO toDto(Result result) {

        if (result == null) {
            return null;
        }

        log.debug("Converting Result entity to DTO.");

        ResultDTO resultDTO = new ResultDTO();

        resultDTO.setId(result.getId());
        if(result.getEvent() != null) resultDTO.setEventId(result.getId());
        if(result.getPoints() != null) resultDTO.setPoints(result.getPoints());
        if(result.getEventResult() != null) resultDTO.setEventResult(result.getEventResult());
        if(result.getUser() != null) resultDTO.setUserId(result.getUser().getId());

        return resultDTO;
    }

    public Result toEntity(ResultDTO resultDTO) {

        if (resultDTO == null) {
            return null;
        }

        log.debug("Converting ResultDTO to result.");

        Result result = resultService.getById(resultDTO.getId());

        if(result == null) result = new Result();

        if(resultDTO.getPoints() != null){
            result.setPoints(resultDTO.getPoints());
        }

        if(resultDTO.getEventResult() != null){
            result.setEventResult(resultDTO.getEventResult());
        }

        if(resultDTO.getEventId() != null) {
            Event event = eventService.getById(resultDTO.getEventId());

            result.setEvent(event);
        }

        if(resultDTO.getUserId() != null) {
            User user = userService.getById(resultDTO.getUserId());

            result.setUser(user);
        }

        return result;
    }

    public List<ResultDTO> toDto(List<Result> results){

        List<ResultDTO> resultDTOs = new ArrayList<>();

        for (Result result : results) {
            resultDTOs.add(toDto(result));
        }

        return resultDTOs;
    }
}