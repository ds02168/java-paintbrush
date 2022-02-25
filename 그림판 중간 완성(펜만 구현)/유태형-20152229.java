package myPIcture;

//헤더파일 참조
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.io.*;
import java.util.*; //벡터 헤더 패키지


//이전에 그렸던 각각의 그림 객체에 대한 정보를 담고있는 클래스
class Draw implements Serializable {

	private int x, y, x1, y1;	//(x,y):누르기 시작한 좌표,(x1,y1):현재 마우스 좌표
	private int dist;	//도형의 종류에 대한 정보
	private String str = "Pen";	//도형의 종류에 대한 정보
	private Color selectedcolor = Color.BLACK;	//도형의 색에 대한 정보
	public int getDist() {return dist;}	//종류값 반환
	public void setDist(int dist) {this.dist = dist;}	//종류값 저장
	public int getX() {return x;}	//x값 저장
	public void setX(int x) {this.x = x;}	//x값 반환
	public int getY() {return y;}	//y값 반환
	public void setY(int y) {this.y = y;}	//y값 저장
	public int getX1() {return x1;}	//x1값 반환
	public void setX1(int x1) {this.x1 = x1;}	//x1값 저장
	public int getY1() {return y1;}	//y1값 반환
	public void setY1(int y1) {this.y1 = y1;}	//y1값 저장
	public String getstr() {return str;}	//종류 반환
	public void setstr(String str) {this.str = str;}	//종류 저장
	public Color getcc() {return selectedcolor;}	//색 반환
	public void setcc(Color selectedcolor) {this.selectedcolor = selectedcolor;}	//색 저장

}

//현재 그리는 중인 그림객체에 대한 정보를 담고 있는 클래스
class option {
	private String bs = "Pen"; //도형의 종류(초기값은 Pen)
	private Color bc = Color.BLACK;	//색 (초기값은 검은색)
	public String getbs() {return bs;}	//도형의 종류 반환
	public void setbs(String bs) {this.bs = bs;}	//도형의 종류 저장
	public Color getbc() {return bc;}	//색 반환
	public void setbc(Color bc) {this.bc = bc;}	//색 저장

}

// JFrame을 상속받아 그림판 기반을 구성 하는 클래스
class Picture extends JFrame {
	private MyPanel panel = new MyPanel();	//MyPanel클래스의 panel객체 생성	
	private JMenuBar mb = new JMenuBar();	//메뉴바 생성(이후 DRAW와 COLOR을 포함할 상단바)	
	private JMenuItem [] drawitem = new JMenuItem [4];	//DRAW메뉴의  세부설정 4가지 메뉴아이템
	private String [] drawtitle = {"Pen", "Line", "Oval", "Rect"};	//세부설정 4가지의 이름들을 지정
	private JMenu draw = new JMenu("DRAW");	//DRAW메뉴 생성	
	private JMenu color = new JMenu("COLOR");	//COLOR메뉴 생성
	private JMenuItem colorchooser = new JMenuItem("Color");	//COLOR메뉴를 누르면 나올 Color메뉴 생성	
	int x, x1, y, y1;	//현재 그리고있는 그림객체의 좌표값((x,y):누르기 시작한 마우스 좌표, (x1,y1):현재 마우스 좌표)
	public option o =new option();	//현재 그리고 있는 그림객체의 정보를 담을 객체 생성
	public Vector vc = new Vector();	//이전에 그렸던 그림들을 저장할 vector 생성

	public Picture() {	//생성자 메소드
		
		setTitle("중간텀-20152229유태형");	//윈도우창 제목 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//윈도우창의 'X'를 누르면 종료
		setContentPane(panel);	//MyPanel클래스의 panel 객체를 컨텐트팬으로 지정
		Container c = getContentPane();	//현재 컨탠트팬(panel)을 가리키는 레퍼런스 c 생성
		c.setBackground(Color.WHITE);	//배경색을 흰색으로 지정
		setLayout(null);//배치관리자 삭제
		
		for(int i = 0; i<drawitem.length;i++) {	//DRAW메뉴의 4가지 메뉴아이템을 설정하는 for문
			drawitem[i] = new JMenuItem(drawtitle[i]);	//이전에 지정한 세부설정 4가지이름으로 4개의 메뉴아이템을 각각 지정
			drawitem[i].addActionListener(new MyPanel());	//각각 액션리스너 추가
			draw.add(drawitem[i]);	//DRAW메뉴에 4가지 메뉴아이템 부착
		}
		
		color.add(colorchooser);	//COLOR메뉴에 Color메뉴아이템 부착		
		mb.add(draw);	//메뉴바에 DRAW부착
		mb.add(color);	//메뉴바에 COLOR부착		
		setJMenuBar(mb);	//메뉴바 추가
				
		colorchooser.addActionListener(new MyPanel());	//Color메뉴아이템에 액션리스너 추가
		c.addMouseListener(new MyPanel());	//c가 가리키는레퍼런스(panel)에 마우스리스너 추가
		c.addMouseMotionListener(new MyPanel());//마우스가 움직이는 동안 그려지는 모양을 위한 이벤트
		
		setSize(1200,600);	//그림판 크기설정
		setVisible(true);	//그림판을 실행시 보이게 설정
	}
	
	//JPanel을 상속받고 마우스리스너,마우스모션리스너,액션리스너를 구현하는 MyPanel 클래스
	class MyPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{			
		public void paint(Graphics g) {	//컴포넌트자신과 모든자식 그리는 메소드
			super.paint(g);	//JPanel을 Graphics g인자로 생성
			
			for(int i = 0; i < vc.size(); i++) {	//이전에 그렸던 그림들을 구현하는 for문
				Draw vd = (Draw)vc.elementAt(i);	//백터에 저장되어있던 객체들을 vd래퍼런스로 가리킴
				g.setColor(vd.getcc());	//각각의 객체들을 그렸던 색으로 그린다
				if(vd.getDist() == 1) {	//Line이었을 시
					g.drawLine(vd.getX(), vd.getY(), vd.getX1(), vd.getY1());	//Line을그린다
				}
				else if(vd.getDist() == 2) {	//Oval이었을 시
					g.drawOval(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());	//Oval을 그린다
				}
				else if(vd.getDist() == 3) {	//Rect이었을 시
					g.drawRect(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());	//Rect를 그린다
				}
			}
			
			g.setColor(o.getbc());	//현재 그리는 그림의 색을 선택했던 색으로 설정
			
			// 마우스를 눌렀다 떼었을때 			
			if(o.getbs().equals("Line")) {  //Line을 체크하면
				g.drawLine(x, y, x1, y1);  //x,y좌표에서 x1,y1좌표에 라인을 그린다
			}
			else if(o.getbs().equals("Oval")){  //Oval을 체크하면
				g.drawOval(x, y, x1 - x, y1 - y); //x,y좌표에서 x1,y1좌표에 oval을 그린다
			}
			else if(o.getbs().equals("Rect")) {    //Rect를 체크하면
				g.drawRect(x, y, x1 - x, y1 - y);  //x,y좌표에서 x1,y1좌표에 Rect를 그린다
			}
		
		}
		
		//선택된 메뉴만 체크되도록 하는 액션이벤트 메소드
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();	//선택한 메뉴의 문자열을 cmd에 저장
			switch(cmd) {	//cmd값에 따라
			case "Pen" :	//Pen선택시
				o.setbs("Pen");	//o객체에 종류 Pen저장
				System.out.println(o.getbs() + " 그리기의 " + o.getbc() + " 선택되었습니다.");	//현재 그리기종류와 색을 콘솔창에 출력
				break;
			case "Line" :	//Line선택시
				o.setbs("Line");	//o객체에 종류 Line저장
				System.out.println(o.getbs() + " 그리기의 " + o.getbc() + " 선택되었습니다.");	//현재 그리기종류와 색을 콘솔창에 출력
				break;
			case "Oval" :	//Oval 선택시
				o.setbs("Oval");	//o객체에 종류 Oval저장
				System.out.println(o.getbs() + " 그리기의 " + o.getbc() + " 선택되었습니다.");	//현재 그리기종류와 색을 콘솔창에 출력
				break;
			case "Rect" :	//Rect 선택시
				o.setbs("Rect");	//o객체에 종류 Rect저장
				System.out.println(o.getbs() + " 그리기의 " + o.getbc() + " 선택되었습니다.");	//현재 그리기종류와 색을 콘솔창에 출력
				break;
			case "Color" :	//Color 선택시
				o.setbc(JColorChooser.showDialog(null,"Color",Color.YELLOW));	//색 선택창에서 고른 색을 o객체에 저장
				if(o.getbc() != null) {	//색을 선택하였다면(취소하지 않았다면)
					System.out.println(o.getbs() + " 그리기의 " + o.getbc() + " 선택되었습니다.");	//현재 그리기종류와 색을 콘솔창에 출력					
				}
				break;
			}			
		}
				
		//마우스 리스너 구현
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) { //눌렀을때
			x = e.getX();       //x의 좌표값 얻기
			y = e.getY();       //y의 좌표값  얻기
		}
		
		public void mouseReleased(MouseEvent e) { //떼었을때
			x1 = e.getX();   //x1의 좌표값 얻기
			y1 = e.getY();   //y1의 좌표값 얻기
			panel.repaint();  //그림 그리기
			if(!o.getbs().equals("Pen")) {  //Pen을 선택하지않았을때(Line,Oval,Rect중 하나를 선택했을때)
				int dist = 0;	//dist를 0으로 초기화
				if(o.getbs().equals("Line")) {	//Line을 선택했을시
					dist = 1; // dist에 1지정
				}
				else if(o.getbs().equals("Oval")) {	//Oval을 선택했을 시
					dist = 2; // dist에 2지정
				}
				else if(o.getbs().equals("Rect")) {	//Rect를 선택했을 시
					dist = 3; // dist에 3지정
				}
				Draw d = new Draw();  //Draw클래스의 d 객체생성
				d.setDist(dist);  //dist(그림 종류 index) 저장
		
				//(x,y)좌표,(x1,y1)좌표,dist,색 값들을 객체 d에 저장
				d.setX(x);  
				d.setY(y);
				d.setX1(x1);
				d.setY1(y1);
				d.setstr(o.getbs());
				d.setcc(o.getbc());
				vc.add(d); //백터 vc에 객체 d추가
			}		
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}	//인터페이스를 상속받으므로 추상메소드 모두구현
		
			
		//마우스 모션 리스너 구현
		public void mouseDragged(MouseEvent e) {	//드래그하는 중에
			x1 = e.getX();	//x1 좌표값 얻기
			y1 = e.getY();	//y1 좌표값 얻기
			panel.repaint(); //움직이는 동안 화면에 보여지기
			
			if(o.getbs().equals("Pen")) {	//Pen을 선택했을 시
				Draw d = new Draw();  //Draw클래스의 d객체 생성
				d.setDist(1);	//곡선은 수많은 직선들의 연속, dist=1로 Line의 역속
				d.setX(x);
				d.setY(y);
				d.setX1(x1);
				d.setY1(y1);
				d.setstr(o.getbs());
				d.setcc(o.getbc());	//(x,y)좌표,(x1,y1)좌표,dist,색 값들을 객체 d에 저장
				vc.add(d);	//백터 vc에 객체 d추가
				x = x1;	//x1의 값을 x
				y = y1;	//y1의 값을 y에 저장함으로써 연속적으로 Line그리기 가능
			}
		}
		public void mouseMoved(MouseEvent e) {}	//인터페이스를 상속받으므로 추상메소드 모두구현
	}
}


public class ExamOne {	
	public static void main(String[] args) {	//main메소드 구현
		new Picture();	//Picture메소드 호출
	}
}