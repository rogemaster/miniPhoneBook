package miniPhoneBook;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MiniPhoneBook implements KeyListener {
	
	JFrame mainFrame = new JFrame();
	JPanel mainPane = new JPanel();
	JTextArea menuDisplay = new JTextArea();
	JTextArea printDisplay = new JTextArea();
	JTextField inputDisplay = new JTextField();
	JScrollPane printScrollPane ;
	
	//MODE 0 메뉴 / 21 입력메뉴 중 이름입력 / 22 입력메뉴 중 전화번호 입력 / 23 입력메뉴 이름만 중복 시  / 3 = 31~32 삭제 / 4 = 41~43 수정 / 5 종료
	int MODE = 0;
	
	String name, phone;
	
	ArrayList<PhoneItem> phonelist = new ArrayList<PhoneItem>();
	PhoneItem delitem = new PhoneItem();
	
	public MiniPhoneBook() {
		makeDisplay();
		makephonelist();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		int keyN = arg0.getKeyCode();
		
	//	System.out.println(keyN);
		
		//menu1
		if(keyN == 49 && MODE == 0) {
			printDisplay.setText("");
			menu1();
			
		//menu2
		}else if(keyN == 50 && MODE == 0) {
			inputDisplay.setText("");
			menu2_name();
		}else if(keyN == 10 && MODE == 21) {
			menu2_phone();
		}else if(keyN == 10 && MODE == 22) {
			menu2();
		}else if(keyN == 89 && MODE == 23) {
			//이름은 같지만 전화번호가 달랐고,유저가 중복을 허용해서 저장
			menu2_force();
		}else if(keyN == 78 && MODE == 23) {
			menu_no();
			
		// 3 = 51 / 4 = 52 / 5 = 53	
		// menu3 삭제
		}else if(keyN == 51 && MODE == 0) {
			menu3();
			inputDisplay.setText("");
		}else if(keyN == 10 && MODE == 31) {
			menu3_Name();
		}else if(keyN == 10 && MODE == 32) {
			nemu3_Phone();
		}else if(keyN == 89 && MODE == 32) {
			menu3_Remove();
		}else if(keyN == 78 && MODE == 32) {
			menu_no();

		// menu4	수정
		}else if(keyN == 52 && MODE == 0) {
			menu4();
			inputDisplay.setText("");
		}else if(keyN == 10 && MODE == 41) {
			menu4_Name();
		}else if(keyN == 10 && MODE == 42) {
			menu4_Phone();
		}else if(keyN == 89 && MODE == 42) {
			menu4_check();
		}else if(keyN == 78 && MODE == 42) {
			menu_no();
		}else if(keyN == 10 && MODE == 43) {
			menu4_Modify();
			
		//menu5 프로그램 종료
		}else if(keyN == 53 && MODE == 0) {
			System.exit(0);
		}
			
		if(MODE == 0 || keyN == 10 ||(MODE == 23 && keyN == 89) ||(MODE == 23 && keyN == 78) || (MODE == 31 && keyN == 10) || 
				(MODE == 32 && keyN == 10) || (MODE == 32 && keyN == 89) || (MODE == 32 && keyN == 78) || (MODE == 41 && keyN == 10) ||
				(MODE == 42 && keyN == 10) || (MODE == 42 && keyN == 89) || (MODE == 42 && keyN == 78) || (MODE == 43 && keyN == 10) || 
				(MODE == 43 && keyN == 89) || (MODE == 43 && keyN == 78)) inputDisplay.setText("");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MiniPhoneBook pb = new MiniPhoneBook();
	}
	
//---------------------------backend logic-------------------------------------------------------------	
	
	public void makeDisplay() {
		
		mainFrame.setTitle("phoneBook");
		mainFrame.setSize(600, 400);
		mainFrame.setDefaultCloseOperation(3);
		mainFrame.setLocationRelativeTo(null);		
		
		menuDisplay.setBorder(new TitledBorder("menu"));
		menuDisplay.setSize(240, 230);
		menuDisplay.setLocation(10, 20);
		menuDisplay.setEditable(false);		
		
		printDisplay.setSize(240, 230);
		printDisplay.setLocation(255, 20);	
		printDisplay.setEditable(false);
	    printDisplay.setFocusable(false);
	    printDisplay.setRequestFocusEnabled(false);
	    printDisplay.setWrapStyleWord(true);
	    printDisplay.setLineWrap(true);
		
		printScrollPane = new JScrollPane(printDisplay);
		printScrollPane.setBorder(new TitledBorder("display"));
		printScrollPane.setSize(240, 230);
		printScrollPane.setLocation(255, 20);		
	
		inputDisplay.setBorder(new TitledBorder("input"));
		inputDisplay.setSize(400, 40);
		inputDisplay.setLocation(10, 250);
		inputDisplay.addKeyListener(this);
		
		mainPane.setBorder(new TitledBorder("main"));
		mainPane.setLayout(null);
		mainPane.setLocation(0, 0);
		
		mainPane.add(menuDisplay);
		mainPane.add(printScrollPane);
		mainPane.add(inputDisplay);
		
		mainFrame.getContentPane().add(mainPane,null);
	//	mainFrame.getContentPane().add(printScrollPane,null);
	//	mainFrame.getContentPane().add(inputPane,null);
		
		//mainFrame.pack();
		mainFrame.setVisible(true);
		
		inputDisplay.requestFocus();
		
		menuDisplay.setText(Properties.menu_EX1);
		

		System.out.println("MODE = " + MODE);
	}
	
	public void makephonelist() {
		
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(new File(Properties.file_PATH)));
			String num;
			String[] numArr;
			
			while(true) {
				num = dis.readLine();
				
				if(num == null) {
					break;
				}
				numArr = num.split(",");
				phonelist.add(new PhoneItem(numArr[0], numArr[1]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// =================================================리스트 메뉴1
	public void menu1() {
		
		for(int i = 0; i < phonelist.size(); i++) {
			printDisplay.append(phonelist.get(i).toString() + "\n");
		}
	}
	
	// ================================================= 저장 메뉴2
	public void menu2_name() {
		printDisplay.setText(Properties.menu2_name);
		MODE = 21;
	}
	
	public void menu2_phone() {
		name = inputDisplay.getText();
		printDisplay.setText(Properties.menu2_phone);
		MODE = 22;
	}
	
	// 중복여부 체크 1 = 전부 중복 / 2 = 이름만 중복 / 0 = 중복아님
	public void menu2() {
		phone = inputDisplay.getText();
		int chk = 0;
		
		for(int i = 0; i < phonelist.size(); i++) {
			delitem = phonelist.get(i);
			
			System.out.println(delitem);
			
			if(delitem.name.equals(name) && delitem.teleNum.equals(phone)) {
				System.out.println("전부 중복");
				chk = 1;
				break;
			}else if(delitem.name.equals(name) && !delitem.teleNum.equals(phone)) {
				System.out.println("이름만 중복");
				chk = 2;
			}
		}
		
		if(chk == 1) {
			printDisplay.setText(Properties.menu2_duplicate);
			MODE = 0;
		}else if(chk == 2) {
			printDisplay.setText(Properties.menu2_duplicate2);
			MODE = 23;
		}else if(chk == 0) {
			printDisplay.setText(Properties.menu2_add);
			MODE = 0;
			phonelist.add(new PhoneItem(name, phone));
			fileSave();
		}
		System.out.println("체크=" + chk);
	}
	
	// 이름만 동일 저장 y = 89 = 저장 / n = 78
	public void menu2_force() {
		phonelist.add(new PhoneItem(name, phone));
		MODE = 0;
		fileSave();
		printDisplay.setText(Properties.menu2_add);
	}
	
	// ===================================================삭제 메뉴3
	public void menu3() {
		printDisplay.setText(Properties.menu3_name);
		MODE = 31;;
	}
	
	// 전화번호 삭제 - 이름 추출
	public void menu3_Name() {
		name = inputDisplay.getText();
		printDisplay.setText(Properties.menu3_phone);
		
		int cnt = 0;
		for(int i = 0; i < phonelist.size(); i++) {
			delitem = phonelist.get(i);
			
			if(delitem.name.equals(name)) {
				printDisplay.append(delitem.toString() + "\n");
				MODE = 32;
				cnt = 1;
			}
		}
		
		// 삭제할 정보가 없을때
		if(cnt == 0) {
			printDisplay.setText(Properties.menu_nodata);
			MODE = 0;
		}
	}
	
	// 전화번호 삭제 - 전화번호 추출
	public void nemu3_Phone() {
		phone = inputDisplay.getText();
		printDisplay.setText(Properties.menu3_check);
	}
	
	// 전화번호 삭제 - 삭제
	public void menu3_Remove() {
		int cnt = 0;
		for(int i = 0; i <phonelist.size(); i++) {
			delitem = phonelist.get(i);
			if(delitem.name.equals(name) && delitem.teleNum.equals(phone)) {
				phonelist.remove(i);
				System.out.println("삭제");
				cnt = 1;
			}
		}
		fileSave();
		MODE = 0;
		printDisplay.setText(Properties.menu3_result);
		
		// 삭제할 정보가 없을때
		if(cnt == 0) {
			printDisplay.setText(Properties.menu_nodata);
			MODE = 0;
			System.out.println("삭제 안됨");
		}
	}
	
	// ==================================================수정 메뉴4
	public void menu4() {
		printDisplay.setText(Properties.menu4);
		MODE = 41;
		System.out.println("menu4");
	}
	
	// 전화번호 수정 - 이름 추출
	public void menu4_Name() {
		name = inputDisplay.getText();
		printDisplay.setText(Properties.menu4_phone);
		MODE = 42;
		System.out.println("menu4_Name");
		
		int cnt = 0;
		for(int i = 0; i < phonelist.size(); i++) {
			delitem = phonelist.get(i);
			
			if(delitem.name.equals(name)) {
				printDisplay.append(phonelist.get(i).toString() + "\n");
				cnt = 1;
			}
		}
		
		if(cnt == 0) {
			printDisplay.setText(Properties.menu_nodata);
			MODE = 0;
		}
	}
	
	// 전화번호 수정 - 전화번호 추출
	public void menu4_Phone() {
		phone = inputDisplay.getText();
		System.out.println("menu4_Phone1");
		
		int cnt = 0;
		for(int i = 0; i < phonelist.size(); i++) {
			delitem = phonelist.get(i);
			
			if(delitem.name.equals(name) && delitem.teleNum.equals(phone)) {
				printDisplay.setText("조회가 완료 되었습니다.\n" + "\n"+ phonelist.get(i).toString() + "\n" + "\n" +Properties.menu4_check);
			//	phonelist.remove(i);
				System.out.println("=====menu4_Phone2=====");
				cnt = 1;
				break;
			}
		}
		System.out.println("menu4_Phone3");
		
		if(cnt == 0) {
			printDisplay.setText(Properties.menu_nodata);
			MODE = 0;
			System.out.println("no!!!!!!!!!!");
		}
	}
	
	//전화번호 수정 - 수정할 데이터 체크
	public void menu4_check() {
		printDisplay.setText(Properties.menu4_modi1);
		System.out.println("menu4_check");
		MODE = 43;
	}
	
	// 전화번호 수정 - 원래 전화번호에서 새 전화번호로 덮어씌우기
	public void menu4_Modify() {
		String phoneNumber = inputDisplay.getText();
		System.out.println("menu4_Modify1");
		
		if(!phoneNumber.contains(",")) {
			printDisplay.setText(Properties.menu4_ERR);
			MODE = 0;
			System.out.println("ERROR!!!!!");
			System.out.println();
		}else {
			int cnt = phoneNumber.indexOf(",");
			delitem.name = phoneNumber.substring(0, cnt);
			delitem.teleNum = phoneNumber.substring(cnt + 1, phoneNumber.length());
			System.out.println("menu4_Modify2");
			fileSave();
			MODE = 0;
			printDisplay.setText(Properties.menu4_result);
		}
	}
	
	// =================================================파일 저장
	// 저장 취소
	public void menu_no() {
		printDisplay.setText(Properties.menu_no);
		MODE = 0;
	}
	
	// 파일 저장(모든 정보)
	public void fileSave() {
		
		try {
			System.out.println("저장시작");
			FileWriter fw = new FileWriter(Properties.file_PATH);
			for(int i = 0; i < phonelist.size(); i++) {
				delitem = phonelist.get(i);
				fw.write(delitem.name + "," + delitem.teleNum + "\n");
			}
			fw.close();
			System.out.println("저장끝");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
