package com.demotask.decathlon.repository;

import com.demotask.decathlon.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {
    List<Result> findAllByUserId(int userId);

    @Query("SELECT SUM(points) FROM Result where user=:userId")
    Double userPointSum(int userId);
}
