package myPIcture;
//헤더 파일
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.util.*; 
import java.util.Scanner;
//그림을 그리는 도화지 역할을 하는 패널 상속받는 클래스
class MyPanel extends JPanel implements MouseListener, MouseMotionListener {		
	public final static int CAP_ROUND = 1; //(펜,라인)굵기값 상수
	int x, x1, y, y1; //(x,y)마우스누른위치, (x1,y1)마우스 현재위치
	static option o = new option(); //현재 그리는중인 컴포넌트의 값들을 저장하는 객체(static)
	static Vector vc = new Vector(); //이전에 그렸던 컴포넌트의 값들을 저장하는 벡터(static)
	private String temp = ""; //문자객체 문자값
	
	//BasicStroke 클래스를 이용하여  선의 굵기를 조절
	BasicStroke thick = new BasicStroke(20,BasicStroke.CAP_ROUND,0);
	BasicStroke normal = new BasicStroke(1,0,0);
	
	//컴포넌트자신과 모든자식 그리는 메소드
	public void paint(Graphics g) {	
		super.paint(g);	//JPanel을 Graphics g인자로 생성
		
		Graphics2D g2 = (Graphics2D)g; //Graphics2D객체의 레퍼런스 (선굵기에 사용)
		
		/*이전에 그렸던 그림들을 구현하는 for문
		dist=0:Pen ,dist=1:Line, dist=2:Oval dist=3:Rect, dist=4:Triangle, dist=5:Fifthangle, dist=6:Eraser, dist=7:Font*/
		for(int i = 0; i < vc.size(); i++) {
			Draw vd = (Draw)vc.elementAt(i);
			g.setColor(vd.getcc());
			if(vd.getDist() == 1) {	
				if(vd.getbt() == false) {
					g.drawLine(vd.getX(), vd.getY(), vd.getX1(), vd.getY1());
				}
				else {
					g2.setStroke(thick);
					g.drawLine(vd.getX(), vd.getY(), vd.getX1(), vd.getY1());
					g2.setStroke(normal);
				}
			}
			else if(vd.getDist() == 2) {
				if(vd.getbf() == false) {
					g.drawOval(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());
				}
				else {
					g.fillOval(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());
				}
			}
			else if(vd.getDist() == 3) {
				if(vd.getbf() == false) {
					g.drawRect(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());
				}
				else {
					g.fillRect(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());
				}
			}
			else if(vd.getDist() == 4) {
				int [] VTx = {vd.getX(),vd.getX1()-((vd.getX1()-vd.getX())/2),vd.getX1()};
				int [] VTy = {vd.getY1(),vd.getY(),vd.getY1()};
				if(vd.getbf() == false) {
					g.drawPolygon(VTx, VTy, 3);
				}
				else {
					g.fillPolygon(VTx, VTy, 3);
				}
			}
			else if(vd.getDist() == 5) {
				int [] Fx = {vd.getX()+(vd.getX1()-vd.getX())/2,vd.getX(),vd.getX()+(vd.getX1()-vd.getX())/4,vd.getX1()-(vd.getX1()-vd.getX())/4,vd.getX1()};
				int [] Fy = {vd.getY(),vd.getY()+(vd.getY1()-vd.getY())/3,vd.getY1(),vd.getY1(),vd.getY()+(vd.getY1()-vd.getY())/3};
				if(vd.getbf() == false) {
					g.drawPolygon(Fx,Fy,5);
				}
				else {
					g.fillPolygon(Fx,Fy,5);
				}
			}
			else if(vd.getDist() == 6) {
				g.clearRect(vd.getX()-30,vd.getY()-30,60,60);
			}
			else if(vd.getDist() == 7) {
				g.drawString(vd.getInput(), vd.getX1(), vd.getY1());
			}
		}
		
		g.setColor(o.getbc());	//현재 그리는 그림의 색을 선택했던 색으로 설정
		
		// 마우스를 눌렀다 떼었을때 			
		if(o.getbs().equals("Line")) {
			if(o.getbt() ==false) {
				g.drawLine(x, y, x1, y1);
			}
			else {
				g2.setStroke(thick);
				g.drawLine(x, y, x1, y1);
				g2.setStroke(normal);
			}
		}
		else if(o.getbs().equals("Oval")){
			if(x1 >= x) {
				if(y1 >= y) {
					if(o.getbf() == false)
						g.drawOval(x, y, x1 - x, y1 - y);
					else
						g.fillOval(x, y, x1 - x, y1 - y);
				}else {
					if(o.getbf() == false)
						g.drawOval(x, y1, x1 - x, y - y1);
					else
						g.fillOval(x, y1, x1 - x, y - y1);
				}
			}else {
				if(y1 >= y) {
					if(o.getbf() == false)
						g.drawOval(x1, y, x - x1, y1 - y);
					else
						g.fillOval(x1, y, x - x1, y1 - y);
				}else {
					if(o.getbf() == false)
						g.drawOval(x1, y1, x - x1, y - y1);
					else
						g.fillOval(x1, y1, x - x1, y - y1);
				}
			}
		}
		else if(o.getbs().equals("Rect")) {
			if(x1 >= x) {
				if(y1 >= y) {
					if(o.getbf() == false)
						g.drawRect(x, y, x1 - x, y1 - y);
					else
						g.fillRect(x, y, x1 - x, y1 - y);
				}else {
					if(o.getbf() == false)
						g.drawRect(x, y1, x1 - x, y - y1);
					else
						g.fillRect(x, y1, x1 - x, y - y1);
				}
			}else {
				if(y1 >= y) {
					if(o.getbf() == false)
						g.drawRect(x1, y, x - x1, y1 - y);
					else
						g.fillRect(x1, y, x - x1, y1 - y);
				}else {
					if(o.getbf() == false)
						g.drawRect(x1, y1, x - x1, y - y1);
					else
						g.fillRect(x1, y1, x - x1, y - y1);
				}
			}
		}
		else if(o.getbs().equals("TriAngle")) {
			if(x1 >= x) {
				if(y1 >= y) {
					int [] Tx = {x,x1-((x1-x)/2),x1};
					int [] Ty = {y1,y,y1};
					if(o.getbf() == false)
						g.drawPolygon(Tx,Ty,3);
					else
						g.fillPolygon(Tx,Ty,3);
				}else {
					int [] Tx = {x,x1-((x1-x)/2),x1};
					int [] Ty = {y,y1,y};
					if(o.getbf() == false)
						g.drawPolygon(Tx,Ty,3);
					else
						g.fillPolygon(Tx,Ty,3);	
				}
			}else {
				if(y1 >= y) {
					int [] Tx = {x1,x-((x-x1)/2),x};
					int [] Ty = {y1,y,y1};
					if(o.getbf() == false)
						g.drawPolygon(Tx,Ty,3);
					else
						g.fillPolygon(Tx,Ty,3);
				}else {
					int [] Tx = {x1,x-((x-x1)/2),x};
					int [] Ty = {y,y1,y};
					if(o.getbf() == false)
						g.drawPolygon(Tx,Ty,3);
					else
						g.fillPolygon(Tx,Ty,3);	
				}
			}
		}
		else if(o.getbs().equals("FifthAngle")) {
			if(x1 >= x) {
				if(y1 >= y) {
					int [] Fx = {x+(x1-x)/2,x,x+(x1-x)/4,x1-(x1-x)/4,x1};
					int [] Fy = {y,y+(y1-y)/3,y1,y1,y+(y1-y)/3};
					if(o.getbf() == false)
						g.drawPolygon(Fx,Fy,5);
					else
						g.fillPolygon(Fx,Fy,5);	
				}else {
					int [] Fx = {x+(x1-x)/2,x,x+(x1-x)/4,x1-(x1-x)/4,x1};
					int [] Fy = {y1,y1+(y-y1)/3,y,y,y1+(y-y1)/3};
					if(o.getbf() == false)
						g.drawPolygon(Fx,Fy,5);
					else
						g.fillPolygon(Fx,Fy,5);
				}
			}else {
				if(y1 >= y) {
					int [] Fx = {x1+(x-x1)/2,x1,x1+(x-x1)/4,x-(x-x1)/4,x};
					int [] Fy = {y,y+(y1-y)/3,y1,y1,y+(y1-y)/3};
					if(o.getbf() == false)
						g.drawPolygon(Fx,Fy,5);
					else
						g.fillPolygon(Fx,Fy,5);
				}else {
					int [] Fx = {x1+(x-x1)/2,x1,x1+(x-x1)/4,x-(x-x1)/4,x};
					int [] Fy = {y1,y1+(y-y1)/3,y,y,y1+(y-y1)/3};
					if(o.getbf() == false)
						g.drawPolygon(Fx,Fy,5);
					else
						g.fillPolygon(Fx,Fy,5);
				}
			}
		}
		else if(o.getbs().equals("Eraser")) {
			g.clearRect(x-30,y-30,60,60);
		}
		else if(o.getbs().equals("Font")) {
			g.drawString(o.getInput(), x1, y1);
			x1 = 0;
			y1 = 0;
		}
	}
	
	//x>x1 or y>y1일경우 오류해결을 위해 temp필드사용
	void reverseXY() {
		int tmpx,tmpy;
		if(x>x1) {
			tmpx = x;
			x = x1;
			x1 = tmpx;
			
		}
		if(y>y1) {
			tmpy = y;
			y = y1;
			y1 = tmpy;
			
		}
	}	
	
	//마우스 리스너 구현
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) { //눌렀을때
		x = e.getX();
		y = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) { //떼었을때
		x1 = e.getX();
		y1 = e.getY();
		repaint();
		if(!o.getbs().equals("Pen")) {
			int dist = 0;
			if(o.getbs().equals("Line")) {
				dist = 1;
			}
			else if(o.getbs().equals("Oval")) {
				dist = 2;
				reverseXY();
			}
			else if(o.getbs().equals("Rect")) {
				dist = 3;
				reverseXY();
			}
			else if (o.getbs().equals("TriAngle")) {
				dist = 4;
				reverseXY();
			}
			else if (o.getbs().equals("FifthAngle")) {
				dist = 5;
				reverseXY();
			}
			else if (o.getbs().equals("Eraser")) {
				dist = 6;
			}
			else if (o.getbs().equals("Font")) {
				dist = 7;
				reverseXY();
				if(o.getInput() != "") {
					o.setInput("");
					temp = "";
				}
				System.out.println("내용을 입력해 주세요 >>");
				Scanner scanner = new Scanner(System.in);
				temp = scanner.nextLine();
				o.setInput(temp);
				
			}
			
			Draw d = new Draw();  //Draw클래스의 d 객체생성
			d.setDist(dist); //도형
			d.setX(x); //x좌표
			d.setY(y); //y좌표
			d.setX1(x1); //x1좌표
			d.setY1(y1); //y1좌표
			d.setstr(o.getbs());
			d.setcc(o.getbc()); //색
			d.setbf(o.getbf()); //채우기
			d.setbt(o.getbt());//굵기
			d.setInput(o.getInput()); //문자열
			vc.add(d); //백터 vc에 객체 d추가
		}		
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
	public void mouseDragged(MouseEvent e) {	//드래그하는 중에
		x1 = e.getX();
		y1 = e.getY();
		
		repaint(); //움직이는 동안 화면에 보여지기
			
		if(o.getbs().equals("Pen")) {
			Draw d = new Draw();
			d.setDist(1);	//곡선은 수많은 직선들의 연속, dist=1로 Line의 역속
			d.setX(x); //x좌표
			d.setY(y); //y좌표
			d.setX1(x1); //x1좌표
			d.setY1(y1); //y1좌표
			d.setstr(o.getbs());
			d.setcc(o.getbc());	//색
			d.setbt(o.getbt()); //굵기
			vc.add(d);	//백터 vc에 객체 d추가
			x = x1;	//x1의 값을 x
			y = y1;	//y1의 값을 y에 저장함으로써 연속적으로 Line그리기 가능
		}
		else if (o.getbs().equals("Eraser")) {
			Draw d = new Draw();
			d.setDist(6);
			d.setX(x);
			d.setY(y);
			d.setX1(x1);
			d.setY1(y1);
			d.setstr(o.getbs());
			vc.add(d);
			x = x1;
			y = y1;
		}	
	}		
	public void mouseMoved(MouseEvent e) {}
}
