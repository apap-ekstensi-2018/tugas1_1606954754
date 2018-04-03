package com.example.sikemas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.sikemas.model.UniversitasModel;

@Mapper
public interface UniversitasMapper {
	@Select("select U.nama_univ from mahasiswa M, program_studi PS, fakultas F, universitas U WHERE M.id_prodi = PS.id AND PS.id_fakultas = F.id AND F.id_univ = U.id AND M.npm = #{npm}")
	UniversitasModel selectUnivByNpm (@Param("npm") String npm);
	
	@Select("select * from universitas where id = #{id}")
	UniversitasModel selectUnivById (@Param("id") int id);
	
	@Select("select * from universitas")
	List<UniversitasModel> selectAllUniv();

}
