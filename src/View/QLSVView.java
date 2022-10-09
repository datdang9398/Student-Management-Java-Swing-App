package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.QLSVModel;
import Model.SinhVien;
import Model.Tinh;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import Controller.QLSVController;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;

public class QLSVView extends JFrame {

	private JPanel contentPane;
	public QLSVModel model;
	public JTextField textField_MaSinhVienFilter;
	public JTable table;
	public JTextField textField_MaSinhVien;
	public JTextField textField_HoVaTen;
	public JTextField textField_NgaySinh;
	public JTextField textField_Mon1;
	public JTextField textField_Mon2;
	public JTextField textField_Mon3;
	public ButtonGroup button_GioiTinh;
	public JComboBox comboBox_QueQuan;
	public JRadioButton radioButton_nam;
	public JRadioButton radioButton_nu;
	private JComboBox comboBox_QueQuanFilter;
	private ButtonGroup button_SapXep;
	private JComboBox comboBox_SapXep;
	private JRadioButton radioButton_TangDan;
	private JRadioButton radioButton_GiamDan;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy"); 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLSVView frame = new QLSVView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public QLSVView() {
		this.model = new QLSVModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 760, 600);
		this.setTitle("Phần mềm quản lý sinh viên");
		
		Action action = new QLSVController(this);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.addActionListener(action);
		menuFile.add(menuOpen);
		
		JMenuItem menuSave = new JMenuItem("Save");
		menuSave.addActionListener(action);
		menuFile.add(menuSave);
		
		JSeparator separator = new JSeparator();
		menuFile.add(separator);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addActionListener(action);
		menuFile.add(menuExit);
		
		JMenu menuAbout = new JMenu("About");
		menuBar.add(menuAbout);
		menuAbout.addActionListener(action);
		
		JMenuItem menuAboutMe = new JMenuItem("About me");
		menuAbout.add(menuAboutMe);
		menuAboutMe.addActionListener(action);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_QueQuanFilter = new JLabel("Quê quán");
		label_QueQuanFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_QueQuanFilter.setBounds(49, 47, 76, 28);
		contentPane.add(label_QueQuanFilter);
		
		JLabel label_MaSinhVienFilter = new JLabel("Mã sinh viên");
		label_MaSinhVienFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_MaSinhVienFilter.setBounds(299, 47, 84, 28);
		contentPane.add(label_MaSinhVienFilter);
		
		comboBox_QueQuanFilter = new JComboBox();
		ArrayList<Tinh> listTinh = Tinh.getDSTinh();
		comboBox_QueQuanFilter.addItem("");
		for (Tinh tinh : listTinh) {
			comboBox_QueQuanFilter.addItem(tinh.getTenTinh());
		} 
		
		comboBox_QueQuanFilter.setBounds(135, 52, 126, 22);
		contentPane.add(comboBox_QueQuanFilter);
		
		textField_MaSinhVienFilter = new JTextField();
		textField_MaSinhVienFilter.setBounds(415, 53, 126, 22);
		contentPane.add(textField_MaSinhVienFilter);
		textField_MaSinhVienFilter.setColumns(10);
		
		JButton buttonHuyTim = new JButton("Huỷ tìm");
		buttonHuyTim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonHuyTim.setBounds(645, 51, 89, 23);
		contentPane.add(buttonHuyTim);
		buttonHuyTim.addActionListener(action);

		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 86, 724, 2);
		contentPane.add(separator_1);
		
		JLabel label_DanhSachSinhVien = new JLabel("Danh sách sinh viên");
		label_DanhSachSinhVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_DanhSachSinhVien.setBounds(10, 99, 150, 28);
		contentPane.add(label_DanhSachSinhVien);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã sinh viên", "Họ và tên", "Quê quán",  "Ngày Sinh", "Giới tính", "Môn 1", "Môn 2", "Môn 3"
			}
		));
		table.setRowHeight(25);
  		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 138, 724, 142);
		contentPane.add(scrollPane);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 289, 724, 2);
		contentPane.add(separator_1_1);
		
		JLabel label_ThongTinSinhVien = new JLabel("Thông tin sinh viên");
		label_ThongTinSinhVien.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_ThongTinSinhVien.setBounds(10, 295, 150, 28);
		contentPane.add(label_ThongTinSinhVien);
		
		JLabel label_MaSinhVien = new JLabel("Mã sinh viên");
		label_MaSinhVien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_MaSinhVien.setBounds(49, 330, 84, 28);
		contentPane.add(label_MaSinhVien);
		
		JLabel label_HoVaTen = new JLabel("Họ và tên");
		label_HoVaTen.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_HoVaTen.setBounds(49, 360, 84, 28);
		contentPane.add(label_HoVaTen);
		
		JLabel label_QueQuan = new JLabel("Quê quán");
		label_QueQuan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_QueQuan.setBounds(49, 390, 84, 28);
		contentPane.add(label_QueQuan);
		
		JLabel label_NgaySinh = new JLabel("Ngày sinh");
		label_NgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_NgaySinh.setBounds(49, 420, 84, 28);
		contentPane.add(label_NgaySinh);
		
		textField_MaSinhVien = new JTextField();
		textField_MaSinhVien.setColumns(10);
		textField_MaSinhVien.setBounds(135, 334, 126, 22);
		contentPane.add(textField_MaSinhVien);
		
		textField_HoVaTen = new JTextField();
		textField_HoVaTen.setColumns(10);
		textField_HoVaTen.setBounds(135, 364, 126, 22);
		contentPane.add(textField_HoVaTen);
		
		textField_NgaySinh = new JTextField();
		textField_NgaySinh.setColumns(10);
		textField_NgaySinh.setBounds(135, 424, 126, 22);
		contentPane.add(textField_NgaySinh);
		
		comboBox_QueQuan = new JComboBox();
		comboBox_QueQuan.addItem("");
		for (Tinh tinh : listTinh) {
			comboBox_QueQuan.addItem(tinh.getTenTinh());
		}
		comboBox_QueQuan.setBounds(135, 394, 126, 22);
		contentPane.add(comboBox_QueQuan);
		
		JLabel label_GioiTinh = new JLabel("Giới tính");
		label_GioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_GioiTinh.setBounds(415, 330, 84, 28);
		contentPane.add(label_GioiTinh);
		
		radioButton_nam = new JRadioButton("Nam");
		radioButton_nam.setBounds(487, 335, 53, 23);
		contentPane.add(radioButton_nam);
		
		radioButton_nu = new JRadioButton("Nữ");
		radioButton_nu.setBounds(542, 335, 53, 23);
		contentPane.add(radioButton_nu);
		
 		button_GioiTinh = new ButtonGroup();
 		button_GioiTinh.add(radioButton_nam);
 		button_GioiTinh.add(radioButton_nu);

		
		JLabel label_HoVaTen_1 = new JLabel("Môn 1");
		label_HoVaTen_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_HoVaTen_1.setBounds(415, 360, 84, 28);
		contentPane.add(label_HoVaTen_1);
		
		JLabel label_HoVaTen_2 = new JLabel("Môn 2");
		label_HoVaTen_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_HoVaTen_2.setBounds(415, 390, 84, 28);
		contentPane.add(label_HoVaTen_2);
		
		JLabel label_HoVaTen_3 = new JLabel("Môn 3");
		label_HoVaTen_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_HoVaTen_3.setBounds(415, 420, 84, 28);
		contentPane.add(label_HoVaTen_3);
		
		textField_Mon1 = new JTextField();
		textField_Mon1.setColumns(10);
		textField_Mon1.setBounds(487, 364, 126, 22);
		contentPane.add(textField_Mon1);
		
		textField_Mon2 = new JTextField();
		textField_Mon2.setColumns(10);
		textField_Mon2.setBounds(487, 394, 126, 22);
		contentPane.add(textField_Mon2);
		
		textField_Mon3 = new JTextField();
		textField_Mon3.setColumns(10);
		textField_Mon3.setBounds(487, 424, 126, 22);
		contentPane.add(textField_Mon3);
		
		JButton buttonXoa = new JButton("Xoá SV");
		buttonXoa.addActionListener(action);
		buttonXoa.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonXoa.setBounds(570, 494, 80, 23);
		contentPane.add(buttonXoa);
		
		JButton buttonHienThiThongTin = new JButton("Hiển thị thông tin SV");
		buttonHienThiThongTin.addActionListener(action);
		 
		buttonHienThiThongTin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonHienThiThongTin.addActionListener(action);
		buttonHienThiThongTin.setBounds(110, 494, 150, 23);
		contentPane.add(buttonHienThiThongTin);
		
		JButton buttonLuu = new JButton("Lưu SV");
		buttonLuu.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonLuu.addActionListener(action);
		buttonLuu.setBounds(440, 494, 80, 23);
		contentPane.add(buttonLuu);
		
		JButton buttonHuy = new JButton("Huỷ bỏ");
		buttonHuy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonHuy.addActionListener(action);
		buttonHuy.setBounds(310, 494, 80, 23); 
		contentPane.add(buttonHuy);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(10, 468, 724, 2);
		contentPane.add(separator_1_1_1);
		
		JButton buttonTim = new JButton("Tìm");
		buttonTim.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonTim.setBounds(546, 51, 89, 23);
		contentPane.add(buttonTim);
		buttonTim.addActionListener(action);

		
		JLabel lblSpXpTheo = new JLabel("Sắp xếp theo");
		lblSpXpTheo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSpXpTheo.setBounds(299, 99, 111, 28);
		contentPane.add(lblSpXpTheo);
		
		comboBox_SapXep = new JComboBox();
		comboBox_SapXep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox_SapXep.setBounds(415, 103, 126, 22);
		contentPane.add(comboBox_SapXep);
		comboBox_SapXep.addItem("Mã sinh viên");
		comboBox_SapXep.addItem("Họ và tên");
		comboBox_SapXep.addItem("Ngày sinh");
		
		radioButton_TangDan = new JRadioButton("↑", true);
		radioButton_TangDan.setBounds(556, 104, 31, 23);
		contentPane.add(radioButton_TangDan);
 		
		radioButton_GiamDan = new JRadioButton("↓");
		radioButton_GiamDan.setBounds(589, 104, 31, 23);
		contentPane.add(radioButton_GiamDan);
 
		button_SapXep = new ButtonGroup();
		button_SapXep.add(radioButton_GiamDan);
		button_SapXep.add(radioButton_TangDan);
		
		JButton buttonThucHienSapXep = new JButton("Sắp xếp");
		buttonThucHienSapXep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buttonThucHienSapXep.setBounds(645, 103, 89, 23);
		contentPane.add(buttonThucHienSapXep);
		
		JLabel label_DanhSachSinhVien_1 = new JLabel("Tìm kiếm sinh viên");
		label_DanhSachSinhVien_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_DanhSachSinhVien_1.setBounds(10, 13, 150, 28);
		contentPane.add(label_DanhSachSinhVien_1);
		buttonThucHienSapXep.addActionListener(action);

		this.setVisible(true);
 	}
	// xoá form
	public void xoaForm() {
		textField_HoVaTen.setText("");
		textField_MaSinhVien.setText("");
		textField_Mon1.setText("");
		textField_Mon2.setText("");
		textField_Mon3.setText("");
		textField_NgaySinh.setText("");
		comboBox_QueQuan.setSelectedIndex(-1);
		button_GioiTinh.clearSelection();

	}
		
	public SinhVien getSinhVienDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		
		int maSinhVien = Integer.valueOf(model_table.getValueAt(i_row, 0)+"");
		String tenSinhVien = model_table.getValueAt(i_row, 1)+"";
 		Tinh tinh = Tinh.getTinhByTenTinh(model_table.getValueAt(i_row, 2)+"");
 		String s_ngaySinh = model_table.getValueAt(i_row, 3)+"";
		LocalDate ngaySinh = LocalDate.parse(s_ngaySinh, this.formatter);
		String textGioiTinh = model_table.getValueAt(i_row, 4)+"";
 		boolean gioiTinh = textGioiTinh.equals("Nam");
		float diemMon1 = Float.valueOf(model_table.getValueAt(i_row, 5)+ "");
		float diemMon2 = Float.valueOf(model_table.getValueAt(i_row, 6)+ "");
		float diemMon3 = Float.valueOf(model_table.getValueAt(i_row, 7)+ "");
		
		SinhVien sv = new SinhVien(maSinhVien, tenSinhVien, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3);
		return sv;
	}
	
	public void hienThiThongTinSinhVienDaChon() {
		SinhVien sv = getSinhVienDangChon();
		
		this.textField_MaSinhVien.setText(sv.getMaSinhVien()+"");
		this.textField_HoVaTen.setText(sv.getTenSinhVien());
		this.comboBox_QueQuan.setSelectedItem(sv.getQueQuan().getTenTinh());
		this.textField_NgaySinh.setText(sv.getNgaySinh().format(formatter)); 
		if(sv.isGioiTinh()) {
			this.radioButton_nam.setSelected(true);
		} else {
			this.radioButton_nu.setSelected(true);
		}
		this.textField_Mon1.setText(sv.getDiemMon1()+"");
		this.textField_Mon2.setText(sv.getDiemMon2()+"");
		this.textField_Mon3.setText(sv.getDiemMon3()+"");
	}

	public void thucHienXoa() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá");
		if (luaChon == JOptionPane.YES_OPTION) {
			SinhVien sv = getSinhVienDangChon();
			this.model.delete(sv); 
			model_table.removeRow(i_row);
		} 
	}

	public void thucHienThemSinhVien() {
		try {
			int maSinhVien = Integer.valueOf(this.textField_MaSinhVien.getText());
			String tenSinhVien = this.textField_HoVaTen.getText();
			int queQuan = this.comboBox_QueQuan.getSelectedIndex()-1;
			Tinh tinh = Tinh.getTinhByID(queQuan);
			
			LocalDate ngaySinh = LocalDate.parse(this.textField_NgaySinh.getText(), this.formatter);
			boolean gioiTinh = true;
			if(this.radioButton_nam.isSelected()) {
				gioiTinh = true;
			} else if (this.radioButton_nu.isSelected()) {
				gioiTinh = false;
			}	
			float diemMon1 = Float.valueOf(this.textField_Mon1.getText());
			float diemMon2 = Float.valueOf(this.textField_Mon2.getText());
			float diemMon3 = Float.valueOf(this.textField_Mon3.getText());		
			
			SinhVien sv = new SinhVien(maSinhVien, tenSinhVien, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3);
			this.themHoacCapNhatSinhVien(sv);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Vui lòng nhập lại" );
		} catch ( java.time.format.DateTimeParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Vui lòng nhập lại ngày sinh với định dạng ngày/tháng/năm " );
		}
	}
	
	public void themHoacCapNhatSinhVien(SinhVien sv) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		if(!this.model.kiemTraTonTai(sv)) {
			this.model.insert(sv);	
			this.themSinhVienVaoTable(sv); 
		}
		else {
			int luaChon = JOptionPane.showConfirmDialog(this, "Sinh viên mang mã số " + sv.getMaSinhVien() + " đã tồn tại, bạn có chắc chắn muốn thay đổi thông tin");
			if (luaChon == JOptionPane.YES_OPTION) {
			this.model.update(sv);
			int soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String id = model_table.getValueAt(i, 0) + "";
				if (id.equals(sv.getMaSinhVien()+"")) {
					model_table.setValueAt(sv.getMaSinhVien() + "", i, 0);
					model_table.setValueAt(sv.getTenSinhVien() + "", i, 1);
					model_table.setValueAt(sv.getQueQuan().getTenTinh() + "", i, 2);
					model_table.setValueAt(sv.getNgaySinh().format(this.formatter), i, 3);
					model_table.setValueAt(sv.isGioiTinh()?"Nam":"Nữ", i, 4);
					model_table.setValueAt(sv.getDiemMon1() + "", i, 5);
					model_table.setValueAt(sv.getDiemMon2() + "", i, 6);
					model_table.setValueAt(sv.getDiemMon3() + "", i, 7);
				}
			}
		}
		}	
	}
 	public void themSinhVienVaoTable(SinhVien sv) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.addRow(new Object[] {
				sv.getMaSinhVien()+"", sv.getTenSinhVien(), 
				sv.getQueQuan().getTenTinh(), sv.getNgaySinh().format(this.formatter),
				(sv.isGioiTinh()?"Nam":"Nữ"),
				sv.getDiemMon1()+"", sv.getDiemMon2()+"", sv.getDiemMon3()+"",
		});
	}	
 	
	public void thucHienTim() {
		this.thucHienTaiLaiDuLieu();		
		int queQuan = this.comboBox_QueQuanFilter.getSelectedIndex()-1;
		String maSinhVienTimKiem = this.textField_MaSinhVienFilter.getText();
 		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int soLuongDong	= model_table.getRowCount();
		
		Set<Integer> idCuaSinhVienCanXoa = new TreeSet<Integer>();
		if (queQuan >= 0) {
			Tinh tinhDaChon = Tinh.getTinhByID(queQuan);
			for (int i = 0; i < soLuongDong; i++) {
				String tenTinh = model_table.getValueAt(i, 2) + "";
				String id = model_table.getValueAt(i, 0) + "";
				if (!tenTinh.equals(tinhDaChon.getTenTinh())) {
					idCuaSinhVienCanXoa.add(Integer.valueOf(id));
				}
			}
		}
		if (maSinhVienTimKiem.length()>0) {
			for (int i = 0; i < soLuongDong; i++) {
				String maSinhVienTrongTable = model_table.getValueAt(i, 0) +"";
				String id = model_table.getValueAt(i, 0) + "";
				if (!maSinhVienTrongTable.equals(maSinhVienTimKiem))  {
					idCuaSinhVienCanXoa.add(Integer.valueOf(id));
				}
			}
		}
		for (Integer idCanXoa : idCuaSinhVienCanXoa) {
			soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String idTrongTable = model_table.getValueAt(i, 0) + "";
				if (idTrongTable.equals(idCanXoa.toString())) {
					try {
					model_table.removeRow(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;	
				}
			}
		}
	}
	
	public void thucHienXoaBang() {
		while(true) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int soLuongDong	= model_table.getRowCount();
		if(soLuongDong==0)
			break;
		else 
			try {
				model_table.removeRow(0);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void thucHienTaiLaiDuLieu() {
		this.thucHienXoaBang();
		for (SinhVien sv : this.model.getDsSinhVien()) {
			this.themSinhVienVaoTable(sv);
		}
	}
	public void thucHienTaiLaiDuLieu(Comparator a) {
		this.thucHienXoaBang();
		ArrayList<SinhVien> dssv = this.model.getDsSinhVien();
		Collections.sort(dssv, a);  
		for (SinhVien sv : dssv) {
			this.themSinhVienVaoTable(sv);
		}
	}
	

	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Phần mềm quản lý sinh viên");
	} 
	
	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát khỏi chương trình",
				"Exit", JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	} 
	
	public void saveFile(String path) {
		try {
			this.model.setTenFile(path);
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (SinhVien sv : this.model.getDsSinhVien()) {
				oos.writeObject(sv);
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void thucHienSaveFile() {
		if(this.model.getTenFile().length()>0) {
			saveFile(this.model.getTenFile());
		}else {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveFile(file.getAbsolutePath());
			} 
		}
	}
	 
	public void openFile(File file) {
		ArrayList<SinhVien> ds = new ArrayList<SinhVien>();
		try {
			this.model.setTenFile(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			SinhVien sv = null;
			while((sv = (SinhVien) ois.readObject())!=null) {
				ds.add(sv);
			}
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.model.setDsSinhVien(ds);
	}
	public void thucHienOpenFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			openFile(file);
			thucHienTaiLaiDuLieu();
		} 
	}

	public void thucHienSapXep() {
		String tieuChiSapXep = (String) this.comboBox_SapXep.getSelectedItem();
		if(this.radioButton_TangDan.isSelected()) {
			switch (tieuChiSapXep) {
			case "Mã sinh viên": 
				this.thucHienTaiLaiDuLieu();
				break;	
			case "Họ và tên": 
				this.thucHienTaiLaiDuLieu(new SapXepTheoHoVaTenTangDan());
				break;	
			case "Ngày sinh": 
				this.thucHienTaiLaiDuLieu(new SapXepTheoNgaySinhTangDan());
				break;
			}			 	 
		} else {
			switch (tieuChiSapXep) {
			case "Mã sinh viên":
				this.thucHienTaiLaiDuLieu(new SapXepTheoMaSinhVienGiamDan());
				break;
			case "Họ và tên": 
				this.thucHienTaiLaiDuLieu(new SapXepTheoHoVaTenGiamDan());
				break;
			case "Ngày sinh": 
				this.thucHienTaiLaiDuLieu(new SapXepTheoNgaySinhGiamDan());
				break;
			}
		}
	}
 
	VietNameseCharactorComparator all = new VietNameseCharactorComparator();
	
	class SapXepTheoMaSinhVienGiamDan implements Comparator<SinhVien>{
		public int compare(SinhVien sv1, SinhVien sv2) {
			return sv2.getMaSinhVien()-sv1.getMaSinhVien();
		}
	}
	
	class SapXepTheoHoVaTenTangDan implements Comparator<SinhVien>{

		@Override
		public int compare(SinhVien sv1, SinhVien sv2) {
			String[] a1 = sv1.getTenSinhVien().split(" ");
			String[] a2 = sv2.getTenSinhVien().split(" ");
			ArrayList<String> list1 = new ArrayList<String>(Arrays.asList(a1));
			ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(a2));
			if(a1.length == 1 & a2.length == 1){
				return all.generator(sv1.getTenSinhVien()).compareTo(all.generator(sv2.getTenSinhVien()));	
			} else if (a1[a1.length-1].equals(a2[a2.length-1])) {
					if(a1.length == 1 & a2.length > 1) {
						return -1;
					} if(a1.length > 1 & a2.length == 1) {
						return 1;
					  } if (list1.size()>list2.size()&list2.size()>1) {
						  return sosanhmethod2(list1, list2);
						} else return sosanhmethod1(list1, list2);
					} else return all.generator(a1[a1.length-1]).compareTo(all.generator(a2[a2.length-1]));	
		}					 
	}
		 
	class SapXepTheoHoVaTenGiamDan implements Comparator<SinhVien>{
		@Override
		public int compare(SinhVien sv1, SinhVien sv2) {
			return (new SapXepTheoHoVaTenTangDan().compare(sv2, sv1));
		}					 
	}
 	public int sosanhmethod1(ArrayList<String> a1, ArrayList<String> a2 ) {
 		if(a1.get(0).equals(a2.get(0))) {
 			if(a1.size()>1) {
 				a1.remove(0); a2.remove(0);
 				ArrayList<String> b1 = a1; ArrayList<String> b2 = a2;
 				return sosanhmethod1(b1,b2);
  			} else return -1;			
 		} else return all.generator(a1.get(0)).compareTo(all.generator(a2.get(0)));				
	}
 	
 	public int sosanhmethod2(ArrayList<String> a1, ArrayList<String> a2 ) {
 		if(a1.get(0)==a2.get(0)) {
 			if(a2.size()>1) {
 				a1.remove(0); a2.remove(0);
 				ArrayList<String> b1 = a1; ArrayList<String> b2 = a2;
 				return sosanhmethod2(b1,b2);
  			} else return -1;			
 		} else return all.generator(a1.get(0)).compareTo(all.generator(a2.get(0)));				
	}
	
	class SapXepTheoNgaySinhTangDan implements Comparator<SinhVien>{

		@Override
		public int compare(SinhVien sv1, SinhVien sv2) {
			return sv1.getNgaySinh().compareTo(sv2.getNgaySinh());
		}}
	class SapXepTheoNgaySinhGiamDan implements Comparator<SinhVien>{

		@Override
		public int compare(SinhVien sv1, SinhVien sv2) {
			return sv2.getNgaySinh().compareTo(sv1.getNgaySinh());
		}
	}	
}
