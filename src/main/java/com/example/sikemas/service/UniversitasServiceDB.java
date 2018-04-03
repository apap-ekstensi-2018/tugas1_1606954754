package com.example.sikemas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sikemas.dao.UniversitasMapper;
import com.example.sikemas.model.UniversitasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UniversitasServiceDB implements UniversitasService {
	
	@Autowired
	private UniversitasMapper universitasMapper;

	@Override
	public UniversitasModel selectUnivByNpm(String npm) {
		log.info ("select universitas with mahasiswa npm {}", npm);
		return universitasMapper.selectUnivByNpm(npm);
	}

	@Override
	public List<UniversitasModel> selectAllUniv() {
		log.info ("select all universitas");
		return universitasMapper.selectAllUniv();
	}

	@Override
	public UniversitasModel selectUnivById(int id) {
		log.info ("select universitas by id {}", id);
		return universitasMapper.selectUnivById(id);
	}

}
