package myPIcture;

//헤더파일
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;


// JFrame을 상속받아 그림판 기반을 구성 하는 클래스
class Picture extends JFrame {
	public MyPanel panel = new MyPanel();
	public DrawDialog ddialog;
	private JMenuBar mb = new JMenuBar();
	private JFileChooser chooser;
	private String currentfilename = null;	//전역변수들 생성

	
	public Picture() {	//생성자 메소드
		setTitle("20152229유태형");	//윈도우창 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//윈도우창의 'X'를 누르면 종료
		
		Container c = getContentPane();	//현재 컨탠트팬(panel)을 가리키는 레퍼런스 c 생성
		panel.setBackground(Color.WHITE);	//배경색을 흰색으로 지정

		c.add(panel);

		panel.setLayout(null);
		createMenu();

		c.addMouseListener(panel);	//c가 가리키는레퍼런스(panel)에 마우스리스너 추가
		c.addMouseMotionListener(panel);//마우스가 움직이는 동안 그려지는 모양을 위한 이벤트
		
		ddialog = new DrawDialog(this,"도형선택"); //도형 다이얼로그 객체생성
			
		setSize(1200,600);	//그림판 크기설정
		setVisible(true);	//그림판을 실행시 보이게 설정
	}

	//메뉴바와 메뉴들을 생성하는 메소드
	void createMenu() {
		//File메뉴와 하위 아이템들을 생성
		JMenu file = new JMenu("File");
		
		JMenuItem New = new JMenuItem("New");
		New.addActionListener(new NewFileListener());
		file.add(New);
		
		JMenuItem open = new JMenuItem("Open");
		open.addActionListener(new OpenFileListener());
		file.add(open);
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new SaveFileListener());
		file.add(save);
		
		JMenuItem saveas = new JMenuItem("SaveAs");
		saveas.addActionListener(new SaveAsFileListener());
		file.add(saveas);
		
		file.addSeparator();
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitFileListener());
		file.add(exit);
		
		//Home메뉴와 하위 아이템들 생성
		JMenu draw = new JMenu("Home");
		
		JMenuItem drawitem = new JMenuItem("Draw");
		drawitem.addActionListener(new DrawDialogListener());
		draw.add(drawitem);
		
		JMenuItem colorchooser = new JMenuItem("Color");
		colorchooser.addActionListener(new ColorListener());
		draw.add(colorchooser);	
			
		//Size메뉴와 하위 아이템들 생성
		JMenu size = new JMenu("Size");

		JMenuItem full = new JMenuItem("FullScreen");
		full.addActionListener(new FullScreenListener());
		size.add(full);
		
		size.addSeparator();
		
		JMenuItem window = new JMenuItem("WindowScreen");
		window.addActionListener(new WindowScreenListener());
		size.add(window);
				
		mb.add(file);
		mb.add(draw);	
		mb.add(size);
		
		setJMenuBar(mb);	//메뉴바 추가	
	}
	
	//Color선택시 리스너
	public class ColorListener implements ActionListener {
		protected ColorDialog dialog = new ColorDialog(Picture.this, "Choose Color" , MyPanel.o.getbc()); //다이얼로그 객체 생성
		public void actionPerformed(ActionEvent e){
			Color result = dialog.showDialog();
			if(result != null){
				MyPanel.o.setbc(result); //다이얼로그에서 선택한 색 을 받아 option객체에 전달
			}
		}
	}

	//FullScreen 선택시 리스너
	public class FullScreenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setExtendedState(JFrame.MAXIMIZED_BOTH); //전체화면모드
		}
	}
	
	//windowScreen 선택시 리스너
	public class WindowScreenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setSize(1200,600); //초기 크기로 창모드
		}
	}
	
	//New 선택시 리스너
	public class NewFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			newFile();
		}
	}
	
	//Open 선택시 리스너
	public class OpenFileListener implements ActionListener {

		public OpenFileListener() {
			chooser = new JFileChooser(); //JFileChooser 객체 생성
		}
		
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, gif, png, bmp","jpg","gif","png","bmp"); //필터 설정
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int retval = chooser.showOpenDialog(null);
			if(retval == JFileChooser.APPROVE_OPTION) {
				File theFile = chooser.getSelectedFile(); //선택 파일반환
				if(theFile != null) {
					if(theFile.isFile()) {
						String filename = chooser.getSelectedFile().getAbsolutePath(); //선택파일의 절대경로
						openFile(filename);
					}
				}
			}
		}
	}
	
	//Save 선택시 리스너
	public class SaveFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			saveFile();
		}
	}
	
	//SaveAs 선택시 리스너
	public class SaveAsFileListener implements ActionListener {
		
		public SaveAsFileListener() {
			chooser = new JFileChooser(); //JFileChooser 객체 생성
		}
		
		public void actionPerformed(ActionEvent e) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg, gif, png, bmp","jpg","gif","png","bmp"); //필터 설정
			chooser.setFileFilter(filter);
			chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			
			int retval = chooser.showSaveDialog(null);
			if(retval == JFileChooser.APPROVE_OPTION);
				File theFile = chooser.getSelectedFile(); //저장파일의 반환
			if(theFile != null){
				if(!theFile.isDirectory()){
					String filename = chooser.getSelectedFile().getAbsolutePath(); //저장파일의 절대경로
					saveFileAs(filename);
				}
			}
		}
	}
	
	//Exit 선택시 리스너
	public class ExitFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "종료 전 저장하시겠습니까?", "그림판 종료", JOptionPane.YES_NO_OPTION); //종료전 저장을 물음
			if (result == JOptionPane.YES_OPTION) { //yes선택시
				saveFile(); //저장
				System.exit(0);
			}
			else {
				System.exit(0);
			}
		}
	}
	
	//New선택시 실행 메소드
	void newFile() {
		currentfilename = null;
		MyPanel.vc.clear();
		panel.x = 0;
		panel.x1 = 0;
		panel.y = 0;
		panel.y1 = 0;
		panel.repaint();
		setTitle("그림판"); //좌표값,컴포넌트 값들,파일명을 초기화
	}

	//Open 선택시 실행 메소드
	void openFile(String filename) {
		currentfilename = filename;
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)); //스트림을 열어 파일을 받음
			MyPanel.vc = (Vector) in.readObject(); //파일에 있던 객체값들을 vc백터로 옮김
			in.close(); //스트림 닫기
			panel.repaint();
		} catch (IOException e){
			System.out.println("Unable to open file: " + filename);
		} catch (ClassNotFoundException e2){
			System.out.print(e2);
		} //오류 발생시 출력
		
		setTitle("그림판 [" + currentfilename + "]");
	}
	
	//Save 선택시 실행 메소드
	void saveFile() {
		if(currentfilename == null){
			currentfilename = "C:\\Temp\\UnTitled.jpg"; //파일명이 없으면 Temp폴더에 UnTitled.jpg로 저장
		}
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(currentfilename)); //스트림 열기
			out.writeObject(MyPanel.vc); //해당 경로로 벡터값들 저장
			out.close(); //스트림 닫기
			System.out.println("save drawing to " + currentfilename);
		} catch (IOException e) {
			System.out.println("Unable to open file: " + currentfilename);
		} //오류 발생시 출력
		setTitle("그림판 [" + currentfilename + "]");
	}
	
	//SaveAs 선택시 실행 메소드
	void saveFileAs(String filename) {
		currentfilename = filename;
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)); //스트림 열기
			out.writeObject(MyPanel.vc); //해당 경로 파일로 백터들 저장
			out.close(); //스트림 닫기
			System.out.println("save drawing to " + filename);
		} catch (IOException e) {
			System.out.println("Unable to open file: " + filename);
		} //오류 발생시 출력
		setTitle("그림판 [" + currentfilename + "]");
	}
	
	//Draw 선택시 실행 리스너
	public class DrawDialogListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			ddialog.setVisible(true); //도형 다이어로그 띄움
		}
	}
}


public class ExamOne {	
	public static void main(String[] args) {	//main메소드 구현
		new Picture();	//Picture메소드 호출
	}
}