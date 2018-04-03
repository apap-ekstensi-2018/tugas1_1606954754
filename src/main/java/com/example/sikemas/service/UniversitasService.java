package com.example.sikemas.service;

import java.util.List;

import com.example.sikemas.model.UniversitasModel;

public interface UniversitasService {
	UniversitasModel selectUnivByNpm(String npm);
	
	UniversitasModel selectUnivById(int id);
	
	List<UniversitasModel> selectAllUniv();

}
