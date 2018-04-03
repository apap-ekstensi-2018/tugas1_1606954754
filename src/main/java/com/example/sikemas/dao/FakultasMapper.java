package com.example.sikemas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.sikemas.model.FakultasModel;


@Mapper
public interface FakultasMapper {
	@Select("select F.nama_fakultas from mahasiswa M, program_studi PS, fakultas F WHERE M.id_prodi = PS.id AND PS.id_fakultas = F.id AND M.npm = #{npm}")
	FakultasModel selectFakByNpm (@Param("npm") String npm);
	
	@Select("select * from fakultas where id = #{id}")
	FakultasModel selectFakById (@Param("id") int id);
	
	@Select("select * from fakultas")
    List<FakultasModel> selectAllFak ();
	
	@Select("select * from fakultas where id_univ = #{id_univ}")
    List<FakultasModel> selectAllFakByIdUniv (@Param("id_univ") int id_univ);

}
