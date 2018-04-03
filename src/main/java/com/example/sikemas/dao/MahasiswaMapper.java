package com.example.sikemas.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.sikemas.model.MahasiswaModel;



@Mapper
public interface MahasiswaMapper {
	@Select("select M.npm, M.nama, M.tempat_lahir, M.tanggal_lahir, M.jenis_kelamin, M.agama, M.golongan_darah, M.tahun_masuk, M.jalur_masuk, M.status from mahasiswa M, program_studi PS, fakultas F, universitas U WHERE M.id_prodi = PS.id AND PS.id_fakultas = F.id AND F.id_univ = U.id AND M.npm = #{npm}")
    MahasiswaModel selectMhs (@Param("npm") String npm);
	
	@Select("select * from mahasiswa where id_prodi = #{id_prodi}")
    List<MahasiswaModel> selectAllMhsByProdiId(@Param("id_prodi") int id_prodi);
	
	@Select("select * from mahasiswa where npm like #{npm} order by npm desc limit 1")
	MahasiswaModel selectLastNpm (@Param("npm") String npm);
	
	@Insert("INSERT INTO mahasiswa (npm, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, agama, golongan_darah, status, tahun_masuk, jalur_masuk, id_prodi) VALUES (#{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{id_prodi})")
	void addMahasiswa (MahasiswaModel mahasiswa);
	
	@Update("UPDATE mahasiswa SET npm=#{npm}, nama=#{nama}, tempat_lahir=#{tempat_lahir}, tanggal_lahir=#{tanggal_lahir}, jenis_kelamin=#{jenis_kelamin}, agama=#{agama}, golongan_darah=#{golongan_darah}, tahun_masuk=#{tahun_masuk}, jalur_masuk=#{jalur_masuk}, id_prodi=#{id_prodi}, status=#{status} WHERE id = #{id}")
    void updateMahasiswa (MahasiswaModel mahasiswa);
	
	@Select("select * from mahasiswa where npm = #{npm}")
	MahasiswaModel selectMhsWithId(@Param("npm") String npm);
	
	@Select("select count(1) from mahasiswa where tahun_masuk = #{tahun_masuk} and id_prodi = #{id_prodi}  and status like #{status}")
	int countMhsLulusPerTahunPerId(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") int id_prodi, @Param("status") String status);
	
	@Select("select count(1) from mahasiswa where tahun_masuk = #{tahun_masuk} and id_prodi = #{id_prodi} ")
	int countTotalMhsPerTahunPerId(@Param("tahun_masuk") String tahun_masuk, @Param("id_prodi") int id_prodi);
}
