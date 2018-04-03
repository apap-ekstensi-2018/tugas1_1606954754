package com.example.sikemas.service;

import java.util.List;

import com.example.sikemas.model.ProDiModel;


public interface ProDiService {
	ProDiModel selectProdiByNpm(String npm);
	
	ProDiModel selectProdiById(int id);
	
	List<ProDiModel> selectAllProDi();
	
	List<ProDiModel> selectAllProDiByIdFak(int id_fakultas);

}
