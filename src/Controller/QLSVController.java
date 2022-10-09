package Controller;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JOptionPane;

import Model.SinhVien;
import Model.Tinh;
import View.QLSVView;

public class QLSVController implements Action {
	QLSVView view;
		
	public QLSVController(QLSVView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand(); 
		if(actionCommand.equals("Lưu SV")) {
			this.view.thucHienThemSinhVien();
		} else if (actionCommand.equals("Hiển thị thông tin SV")) {
			this.view.hienThiThongTinSinhVienDaChon();
		} else if (actionCommand.equals("Xoá SV")) {
			this.view.thucHienXoa();
		} else if (actionCommand.equals("Huỷ bỏ")) {
			this.view.xoaForm();
		} else if (actionCommand.equals("Tìm")) {
			this.view.thucHienTim();
		} else if (actionCommand.equals("Huỷ tìm")) {
			this.view.thucHienTaiLaiDuLieu();
		} else if (actionCommand.equals("Sắp xếp")) {
			this.view.thucHienSapXep();
		} else if (actionCommand.equals("About me")) {
			this.view.hienThiAbout();
		} else if(actionCommand.equals("Exit")) {
			this.view.thoatKhoiChuongTrinh();
		} else if(actionCommand.equals("Save")) {
			this.view.thucHienSaveFile();
		} else if(actionCommand.equals("Open")) {
		 	this.view.thucHienOpenFile();
		}	
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnabled(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}

}
