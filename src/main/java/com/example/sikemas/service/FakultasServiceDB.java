package com.example.sikemas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sikemas.dao.FakultasMapper;
import com.example.sikemas.model.FakultasModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FakultasServiceDB implements FakultasService {
	
	@Autowired
	private FakultasMapper fakultasMapper;

	@Override
	public FakultasModel selectFakByNpm(String npm) {
		log.info ("select fakultas with mahasiswa npm {}", npm);
		return fakultasMapper.selectFakByNpm(npm);
	}

	@Override
	public List<FakultasModel> selectAllFak() {
		log.info ("select all fakultas");
		return fakultasMapper.selectAllFak();
	}

	@Override
	public FakultasModel selectFakById(int id) {
		log.info ("select fakultas by ID {}", id);
		return fakultasMapper.selectFakById(id);
	}

	@Override
	public List<FakultasModel> selectAllFakByIdUniv(int id_univ) {
		log.info ("select all fakultas dari universitas id {}", id_univ);
		return fakultasMapper.selectAllFakByIdUniv(id_univ);
	}

}
