package com.example.sikemas.service;

import java.util.List;

import com.example.sikemas.model.FakultasModel;

public interface FakultasService {
	FakultasModel selectFakByNpm(String npm);
	
	FakultasModel selectFakById(int id);
	
	List<FakultasModel> selectAllFak();
	
	List<FakultasModel> selectAllFakByIdUniv(int id_univ);

}
