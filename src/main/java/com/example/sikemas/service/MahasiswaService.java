package com.example.sikemas.service;

import java.util.List;

import com.example.sikemas.model.MahasiswaModel;


public interface MahasiswaService {
	MahasiswaModel selectMhs(String npm);
	
	MahasiswaModel selectLastNpm(String npm);
	
	List<MahasiswaModel> selectAllMhsByProdiId(int id_prodi);
	
	void addMahasiswa(MahasiswaModel mahasiswa);
	
	void updateMahasiswa(MahasiswaModel mahasiswa);
	
	MahasiswaModel selectMhsWithId(String npm);
	
	int countMhsLulusPerTahunPerId(String tahun_masuk, int id_prodi);
	
	int countTotalMhsPerTahunPerId(String tahun_masuk, int id_prodi);
	
}
