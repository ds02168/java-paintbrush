package myPIcture;
//헤더파일
import java.awt.Color;
import java.io.*;
//그렸던 컴포넌트의 정보들을 담는 클래스
 class Draw implements Serializable {
	private int x, y, x1, y1;	//(x,y):누르기 시작한 좌표,(x1,y1):현재 마우스 좌표
	/*dist=0:Pen ,dist=1:Line, dist=2:Oval dist=3:Rect, dist=4:Triangle, dist=5:Fifthangle, dist=6:Eraser, dist=7:Font*/
	private int dist;
	private String str = "Pen";	//도형의 종류에 대한 정보
	private Color selectedcolor = Color.BLACK;	//도형의 색에 대한 정보(초기값 BLACK)
	private boolean bt = false; //굵기 활성화 여부 정보
	private boolean bf = false; //채우기 활성화 여부 정보
	private String text = ""; //문자열 정보(초기값은 "")
	public int getDist() {return dist;}	//종류값 반환
	public void setDist(int dist) {this.dist = dist;}
	public int getX() {return x;}
	public void setX(int x) {this.x = x;}
	public int getY() {return y;}
	public void setY(int y) {this.y = y;}
	public int getX1() {return x1;}
	public void setX1(int x1) {this.x1 = x1;}
	public int getY1() {return y1;}
	public void setY1(int y1) {this.y1 = y1;}
	public String getstr() {return str;}
	public void setstr(String str) {this.str = str;}
	public Color getcc() {return selectedcolor;}
	public void setcc(Color selectedcolor) {this.selectedcolor = selectedcolor;}
	public boolean getbt() {return bt;}
	public void setbt(boolean bt) {this.bt = bt;}
	public boolean getbf() {return bf;}
	public void setbf(boolean bf) {this.bf = bf;}
	public String getInput() {return text;}
	public void setInput(String text) {this.text = text;}
}
