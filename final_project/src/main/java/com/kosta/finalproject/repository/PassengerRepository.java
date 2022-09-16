package com.kosta.finalproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.finalproject.model.Passenger;


public interface PassengerRepository extends JpaRepository<Passenger, Integer>{

	//Passenger findByboardNo(Long boardNo);
	List<Passenger> findByboardNo(Long boardNo);


	
}