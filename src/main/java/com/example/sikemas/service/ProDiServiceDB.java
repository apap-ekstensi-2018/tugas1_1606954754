package com.example.sikemas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sikemas.dao.ProDiMapper;
import com.example.sikemas.model.ProDiModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProDiServiceDB implements ProDiService {
	
	@Autowired
	private ProDiMapper prodiMapper;

	@Override
	public ProDiModel selectProdiByNpm(String npm) {
		log.info ("select prodi with mahasiswa npm {}", npm);
		return prodiMapper.selectProdiByNpm(npm);
	}
	
	

	@Override
	public List<ProDiModel> selectAllProDi() {
		log.info ("select all Program Studi");
		return prodiMapper.selectAllProDi();
	}



	@Override
	public ProDiModel selectProdiById(int id) {
		log.info ("select prodi by kode prodi {}", id);
		return prodiMapper.selectProdiById(id);
	}



	@Override
	public List<ProDiModel> selectAllProDiByIdFak(int id_fakultas) {
		log.info ("select all Program Studi with Fakultas Id {}", id_fakultas);
		return prodiMapper.selectAllProDiByIdFak(id_fakultas);
	}

}
