package com.example.sikemas.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sikemas.model.FakultasModel;
import com.example.sikemas.model.MahasiswaModel;
import com.example.sikemas.model.ProDiModel;
import com.example.sikemas.model.UniversitasModel;
import com.example.sikemas.service.FakultasService;
import com.example.sikemas.service.MahasiswaService;
import com.example.sikemas.service.ProDiService;
import com.example.sikemas.service.UniversitasService;

@Controller
public class MahasiswaController {
	
	@Autowired
	MahasiswaService mhsDAO;
	@Autowired
	ProDiService prodiDAO; 
	@Autowired
	FakultasService fakDAO;
	@Autowired
	UniversitasService univDAO;
	
	@RequestMapping("/")
	public String index ()
    {
        return "index";
    }
	
	@RequestMapping(value="/mahasiswa", method = RequestMethod.GET)
    public String view (Model model,
            @RequestParam(value = "npm", required = false) String npm)
    {
        MahasiswaModel Mhs = mhsDAO.selectMhs(npm);
        ProDiModel Prodi = prodiDAO.selectProdiByNpm(npm);
        FakultasModel Fak = fakDAO.selectFakByNpm(npm);
        UniversitasModel Univ = univDAO.selectUnivByNpm(npm);

        model.addAttribute ("Mhs", Mhs);
        model.addAttribute("Prodi", Prodi);
        model.addAttribute("Fak", Fak);
        model.addAttribute("Univ", Univ);
        return "mahasiswa";
    }
	
	@RequestMapping("/mahasiswa/tambah")
	public String tambah (@ModelAttribute("mhs") MahasiswaModel mahasiswa, Model model)
	{
		if(mahasiswa.getNama()==null) {
			return "tambah-form";
		} else {
			ProDiModel cekProDi = prodiDAO.selectProdiById(mahasiswa.getId_prodi());
			if(cekProDi == null) {
				return "idProdi-not-found";
			}
			mahasiswa.setNpm(generateNPM(mahasiswa));
			mahasiswa.setStatus("Aktif");
			model.addAttribute("npm", mahasiswa.getNpm());
			mhsDAO.addMahasiswa(mahasiswa);
			return "tambah-sukses";
		}
	}
	public String generateNPM(MahasiswaModel mahasiswa) {
		String kodeTahunMasuk = mahasiswa.getTahun_masuk().substring(2, 4);
		
		ProDiModel Prodi = prodiDAO.selectProdiById(mahasiswa.getId_prodi());
		String kodeProDi = Prodi.getKode_prodi();
		
		FakultasModel Fak = fakDAO.selectFakById(Prodi.getId_fakultas());
		UniversitasModel Univ = univDAO.selectUnivById(Fak.getId_univ());
		String kodeUniv = Univ.getKode_univ();
		
		String kodeJalurMasuk = "";
		if (mahasiswa.getJalur_masuk().equals("Olimpiade")) {
			kodeJalurMasuk = "53";
		}else if (mahasiswa.getJalur_masuk().equals("Reguler / SNMPTN")) {
			kodeJalurMasuk = "54";
		}else if (mahasiswa.getJalur_masuk().equals("Paralel / PPKB")) {
			kodeJalurMasuk = "55";
		}else if (mahasiswa.getJalur_masuk().equals("Bersama / SBMPTN")) {
			kodeJalurMasuk = "57";
		}else {
			kodeJalurMasuk = "62";
		}
		
		MahasiswaModel lastNpm = mhsDAO.selectLastNpm("%"+kodeTahunMasuk + kodeUniv + kodeProDi + kodeJalurMasuk+"%");
		String kodeUrutan = "";
		if(lastNpm==null) {
			kodeUrutan = "001";
		} else {
			kodeUrutan = String.valueOf(Integer.parseInt(lastNpm.getNpm().substring(9, 12)) + 1001).substring(1, 4);
		}
		
		String npm = kodeTahunMasuk + kodeUniv + kodeProDi + kodeJalurMasuk + kodeUrutan ;
		
		return npm;
	}
	
	@RequestMapping("/mahasiswa/ubah/{npm}")
    public String ubah (@PathVariable(value = "npm") String npm, Model model, @ModelAttribute("Mhs") MahasiswaModel newMhs)
    {
    		MahasiswaModel Mhs = mhsDAO.selectMhsWithId(npm);
    		
    		if(newMhs.getNama()==null) {
    			if(Mhs==null) {
    				model.addAttribute("npm", npm);
    				return "not-found";
    			}
    			model.addAttribute("Mhs", Mhs);
    			model.addAttribute("title", "Update Mahasiswa");
    			return "ubah-form";
    		} else {
    			if(Mhs.getTahun_masuk().equals(newMhs.getTahun_masuk()) && Mhs.getId_prodi() == newMhs.getId_prodi() && Mhs.getJalur_masuk().equals(newMhs.getJalur_masuk())) {
    				newMhs.setNpm(Mhs.getNpm());
    			}else {
    				newMhs.setNpm(generateNPM(newMhs));
    			}
    			mhsDAO.updateMahasiswa(newMhs);
    			model.addAttribute("npm", Mhs.getNpm());
    			model.addAttribute("title", "Update Mahasiswa");
    			return "ubah-sukses";
    		}
    }
	
	@RequestMapping("/kelulusan")
	public String kelulusan (Model model,
    		@RequestParam(value = "thn", required = false) String tahun_masuk,
    		@RequestParam(value = "prodi", required = false) String id_prodi)
    {
    	 	 
    		 if(tahun_masuk==null || id_prodi==null || tahun_masuk=="" || id_prodi=="") {
    			 //model.addAttribute("title","Lihat Persentase Kelulusan");
         	 return "kelulusan-form";
    		 }else {
    			 model.addAttribute("tahun_masuk",tahun_masuk);
    			 
    			 int intId =Integer.parseInt(id_prodi);
    			 ProDiModel prodi = prodiDAO.selectProdiById(intId);
    			 model.addAttribute("nama_prodi", prodi.getNama_prodi());
    			 
    			 FakultasModel fak =  fakDAO.selectFakById(prodi.getId_fakultas());
    			 model.addAttribute("nama_fakultas", fak.getNama_fakultas());
    			 
    			 UniversitasModel univ = univDAO.selectUnivById(fak.getId_univ());
    			 model.addAttribute("nama_univ", univ.getNama_univ());
    			 
    			 int totalMhs = mhsDAO.countTotalMhsPerTahunPerId(tahun_masuk, intId);
    			 int mhsLulus = mhsDAO.countMhsLulusPerTahunPerId(tahun_masuk, intId);
    			 int persentaseLulus = (int) (((double) mhsLulus / (double) totalMhs)	*100);
    	         
    	         model.addAttribute("totalMhs", totalMhs);
    	         model.addAttribute("mhsLulus", mhsLulus);
    	         model.addAttribute("persentaseLulus",persentaseLulus);
    	         
    	         return "kelulusan-hasil";
    		 }
    }
	
	@RequestMapping("/mahasiswa/cari")
	public String cariMahasiswa (Model model,
    		@RequestParam(value = "univ", required = false) String Univ,
    		@RequestParam(value = "fakultas", required = false) String Fak,
    		@RequestParam(value = "prodi", required = false) String Prodi)
    {
		List<UniversitasModel> univList = univDAO.selectAllUniv();
		model.addAttribute("univ", univList);
		
		if(Univ == null || Univ == "") {
			return "cariUniv-form";
		} else {
			int univId= Integer.parseInt(Univ);
			List<FakultasModel> fakList = fakDAO.selectAllFakByIdUniv(univId);
			model.addAttribute("fakultas", fakList);
			model.addAttribute("selectedUniv", univId);
			if(Fak==null) {
				return "cariFak-form";
			}else {
				int fakId = Integer.parseInt(Fak);
				List<ProDiModel> prodiList = prodiDAO.selectAllProDiByIdFak(fakId);
				model.addAttribute("prodi", prodiList);
				model.addAttribute("selectedFak", fakId);
				
				if(Prodi!=null) {
					int prodiId = Integer.parseInt(Prodi);
					List<MahasiswaModel> mhsList = mhsDAO.selectAllMhsByProdiId(prodiId);
					model.addAttribute("mhs", mhsList);
					
					UniversitasModel UnivObj = univDAO.selectUnivById(univId);
					FakultasModel FakObj = fakDAO.selectFakById(fakId);
					ProDiModel ProdiObj = prodiDAO.selectProdiById(prodiId);
					model.addAttribute("univObj", UnivObj);
					model.addAttribute("fakObj", FakObj);
					model.addAttribute("prodiObj", ProdiObj);
					
					
					return "cari-hasil";
				}
				return "cariProdi-form";
			}
			
		}
    }
	
	
}
