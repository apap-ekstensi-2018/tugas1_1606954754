package com.example.sikemas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.sikemas.model.ProDiModel;


@Mapper
public interface ProDiMapper {
	@Select("select PS.nama_prodi from mahasiswa M, program_studi PS WHERE M.id_prodi = PS.id AND M.npm = #{npm}")
    ProDiModel selectProdiByNpm (@Param("npm") String npm);
	
	@Select("select * from program_studi where id = #{id}")
	ProDiModel selectProdiById (@Param("id") int id);
	
	@Select("select * from program_studi")
    List<ProDiModel> selectAllProDi();
	
	@Select("select * from program_studi where id_fakultas = #{id_fakultas}")
    List<ProDiModel> selectAllProDiByIdFak(@Param("id_fakultas") int id_fakultas);

}
