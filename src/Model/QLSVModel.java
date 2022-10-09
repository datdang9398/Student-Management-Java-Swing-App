package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QLSVModel {
	private ArrayList<SinhVien> dsSinhVien;
	//private String luaChon;
	private String tenFile;

	public QLSVModel() {
		this.dsSinhVien = new ArrayList<SinhVien>();
	//	this.luaChon = "";
		this.tenFile = "";
	}	

	public String getTenFile() {
		return tenFile;
	}

	public void setTenFile(String tenFile) {
		this.tenFile = tenFile;
	}

	public QLSVModel(ArrayList<SinhVien> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}

	public ArrayList<SinhVien> getDsSinhVien() {
	    Collections.sort(dsSinhVien);  
		return dsSinhVien;
	}

	public void setDsSinhVien(ArrayList<SinhVien> dsSinhVien) {
		this.dsSinhVien = dsSinhVien;
	}
	
	public void insert (SinhVien sinhVien) {
		this.dsSinhVien.add(sinhVien);
	}
	
	public void delete (SinhVien sinhVien) {
		this.dsSinhVien.remove(sinhVien);
	}
	
	public void update (SinhVien sinhVien) {
		this.dsSinhVien.remove(sinhVien);
		this.dsSinhVien.add(sinhVien);
	}

	//public String getLuaChon() {
	//	return luaChon;
	//}

	//public void setLuaChon(String luaChon) {
	//	this.luaChon = luaChon;
	//}

	public boolean kiemTraTonTai(SinhVien ts) {
		for(SinhVien sinhVien:dsSinhVien ) {
			if(sinhVien.getMaSinhVien() == ts.getMaSinhVien())
				return true;
		}
 		return false;
	}	
}

