package com.example.sikemas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.sikemas.dao.MahasiswaMapper;
import com.example.sikemas.model.MahasiswaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MahasiswaServiceDB implements MahasiswaService {
	
	@Autowired
	private MahasiswaMapper mahasiswaMapper;

	@Override
	public MahasiswaModel selectMhs(String npm) {
		log.info ("select mahasiswa with npm {}", npm);
        return mahasiswaMapper.selectMhs(npm);
	}

	@Override
	public List<MahasiswaModel> selectAllMhsByProdiId(int id_prodi) {
		log.info ("select all Mahasiswa by Program studi id {}", id_prodi);
        return mahasiswaMapper.selectAllMhsByProdiId(id_prodi);
	}

	@Override
	public void addMahasiswa(MahasiswaModel mahasiswa) {
		mahasiswaMapper.addMahasiswa(mahasiswa);
		
	}

	@Override
	public MahasiswaModel selectLastNpm(String npm) {
		log.info ("select Last npm {}", npm);
        return mahasiswaMapper.selectLastNpm(npm);
	}

	@Override
	public void updateMahasiswa(MahasiswaModel mahasiswa) {
		mahasiswaMapper.updateMahasiswa(mahasiswa);
		
	}

	@Override
	public MahasiswaModel selectMhsWithId(String npm) {
		log.info ("select mahasiswa with npm {}", npm);
        return mahasiswaMapper.selectMhsWithId(npm);
	}

	@Override
	public int countMhsLulusPerTahunPerId(String tahun_masuk, int id_prodi) {
		log.info ("count mahasiswa LULUS tahun masuk {} dengan id prodi {}", tahun_masuk, id_prodi);
        return mahasiswaMapper.countMhsLulusPerTahunPerId(tahun_masuk, id_prodi, "lulus");
	}

	@Override
	public int countTotalMhsPerTahunPerId(String tahun_masuk, int id_prodi) {
		log.info ("count total mahasiswa tahun masuk {} dengan id prodi {}", tahun_masuk, id_prodi);
        return mahasiswaMapper.countTotalMhsPerTahunPerId(tahun_masuk, id_prodi);
	}

}
