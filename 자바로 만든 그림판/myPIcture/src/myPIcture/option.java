package myPIcture;
//헤더파일 참조
import java.awt.*;
import java.io.Serializable;
//현재 그리는 중인 그림객체에 대한 정보를 담고 있는 클래스
class option implements Serializable {
	private String bs = "Pen"; //도형의 종류(초기값은 Pen)
	private Color bc = Color.BLACK;	//색 (초기값은 검은색)
	private boolean bt = false; //굵기 활성화 여부
	private boolean bf = false; //채우기 활성화 여부
	private String text = ""; //문자열(촤기값은 "")
	public String getbs() {return bs;}
	public void setbs(String bs) {this.bs = bs;}
	public Color getbc() {return bc;}
	public void setbc(Color bc) {this.bc = bc;}
	public boolean getbt() {return bt;}
	public void setbt(boolean bt) {this.bt = bt;}
	public boolean getbf() {return bf;}
	public void setbf(boolean bf) {this.bf = bf;}
	public String getInput() {return text;}
	public void setInput(String text) {this.text = text;}
}