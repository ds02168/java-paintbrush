package myPIcture;
//�������
import java.awt.Color;
import java.io.*;
//�׷ȴ� ������Ʈ�� �������� ��� Ŭ����
 class Draw implements Serializable {
	private int x, y, x1, y1;	//(x,y):������ ������ ��ǥ,(x1,y1):���� ���콺 ��ǥ
	/*dist=0:Pen ,dist=1:Line, dist=2:Oval dist=3:Rect, dist=4:Triangle, dist=5:Fifthangle, dist=6:Eraser, dist=7:Font*/
	private int dist;
	private String str = "Pen";	//������ ������ ���� ����
	private Color selectedcolor = Color.BLACK;	//������ ���� ���� ����(�ʱⰪ BLACK)
	private boolean bt = false; //���� Ȱ��ȭ ���� ����
	private boolean bf = false; //ä��� Ȱ��ȭ ���� ����
	private String text = ""; //���ڿ� ����(�ʱⰪ�� "")
	public int getDist() {return dist;}	//������ ��ȯ
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
