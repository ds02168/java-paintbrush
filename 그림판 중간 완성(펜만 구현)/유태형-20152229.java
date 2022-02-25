package myPIcture;

//������� ����
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;
import java.io.*;
import java.util.*; //���� ��� ��Ű��


//������ �׷ȴ� ������ �׸� ��ü�� ���� ������ ����ִ� Ŭ����
class Draw implements Serializable {

	private int x, y, x1, y1;	//(x,y):������ ������ ��ǥ,(x1,y1):���� ���콺 ��ǥ
	private int dist;	//������ ������ ���� ����
	private String str = "Pen";	//������ ������ ���� ����
	private Color selectedcolor = Color.BLACK;	//������ ���� ���� ����
	public int getDist() {return dist;}	//������ ��ȯ
	public void setDist(int dist) {this.dist = dist;}	//������ ����
	public int getX() {return x;}	//x�� ����
	public void setX(int x) {this.x = x;}	//x�� ��ȯ
	public int getY() {return y;}	//y�� ��ȯ
	public void setY(int y) {this.y = y;}	//y�� ����
	public int getX1() {return x1;}	//x1�� ��ȯ
	public void setX1(int x1) {this.x1 = x1;}	//x1�� ����
	public int getY1() {return y1;}	//y1�� ��ȯ
	public void setY1(int y1) {this.y1 = y1;}	//y1�� ����
	public String getstr() {return str;}	//���� ��ȯ
	public void setstr(String str) {this.str = str;}	//���� ����
	public Color getcc() {return selectedcolor;}	//�� ��ȯ
	public void setcc(Color selectedcolor) {this.selectedcolor = selectedcolor;}	//�� ����

}

//���� �׸��� ���� �׸���ü�� ���� ������ ��� �ִ� Ŭ����
class option {
	private String bs = "Pen"; //������ ����(�ʱⰪ�� Pen)
	private Color bc = Color.BLACK;	//�� (�ʱⰪ�� ������)
	public String getbs() {return bs;}	//������ ���� ��ȯ
	public void setbs(String bs) {this.bs = bs;}	//������ ���� ����
	public Color getbc() {return bc;}	//�� ��ȯ
	public void setbc(Color bc) {this.bc = bc;}	//�� ����

}

// JFrame�� ��ӹ޾� �׸��� ����� ���� �ϴ� Ŭ����
class Picture extends JFrame {
	private MyPanel panel = new MyPanel();	//MyPanelŬ������ panel��ü ����	
	private JMenuBar mb = new JMenuBar();	//�޴��� ����(���� DRAW�� COLOR�� ������ ��ܹ�)	
	private JMenuItem [] drawitem = new JMenuItem [4];	//DRAW�޴���  ���μ��� 4���� �޴�������
	private String [] drawtitle = {"Pen", "Line", "Oval", "Rect"};	//���μ��� 4������ �̸����� ����
	private JMenu draw = new JMenu("DRAW");	//DRAW�޴� ����	
	private JMenu color = new JMenu("COLOR");	//COLOR�޴� ����
	private JMenuItem colorchooser = new JMenuItem("Color");	//COLOR�޴��� ������ ���� Color�޴� ����	
	int x, x1, y, y1;	//���� �׸����ִ� �׸���ü�� ��ǥ��((x,y):������ ������ ���콺 ��ǥ, (x1,y1):���� ���콺 ��ǥ)
	public option o =new option();	//���� �׸��� �ִ� �׸���ü�� ������ ���� ��ü ����
	public Vector vc = new Vector();	//������ �׷ȴ� �׸����� ������ vector ����

	public Picture() {	//������ �޼ҵ�
		
		setTitle("�߰���-20152229������");	//������â ���� ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//������â�� 'X'�� ������ ����
		setContentPane(panel);	//MyPanelŬ������ panel ��ü�� ����Ʈ������ ����
		Container c = getContentPane();	//���� ����Ʈ��(panel)�� ����Ű�� ���۷��� c ����
		c.setBackground(Color.WHITE);	//������ ������� ����
		setLayout(null);//��ġ������ ����
		
		for(int i = 0; i<drawitem.length;i++) {	//DRAW�޴��� 4���� �޴��������� �����ϴ� for��
			drawitem[i] = new JMenuItem(drawtitle[i]);	//������ ������ ���μ��� 4�����̸����� 4���� �޴��������� ���� ����
			drawitem[i].addActionListener(new MyPanel());	//���� �׼Ǹ����� �߰�
			draw.add(drawitem[i]);	//DRAW�޴��� 4���� �޴������� ����
		}
		
		color.add(colorchooser);	//COLOR�޴��� Color�޴������� ����		
		mb.add(draw);	//�޴��ٿ� DRAW����
		mb.add(color);	//�޴��ٿ� COLOR����		
		setJMenuBar(mb);	//�޴��� �߰�
				
		colorchooser.addActionListener(new MyPanel());	//Color�޴������ۿ� �׼Ǹ����� �߰�
		c.addMouseListener(new MyPanel());	//c�� ����Ű�·��۷���(panel)�� ���콺������ �߰�
		c.addMouseMotionListener(new MyPanel());//���콺�� �����̴� ���� �׷����� ����� ���� �̺�Ʈ
		
		setSize(1200,600);	//�׸��� ũ�⼳��
		setVisible(true);	//�׸����� ����� ���̰� ����
	}
	
	//JPanel�� ��ӹް� ���콺������,���콺��Ǹ�����,�׼Ǹ����ʸ� �����ϴ� MyPanel Ŭ����
	class MyPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{			
		public void paint(Graphics g) {	//������Ʈ�ڽŰ� ����ڽ� �׸��� �޼ҵ�
			super.paint(g);	//JPanel�� Graphics g���ڷ� ����
			
			for(int i = 0; i < vc.size(); i++) {	//������ �׷ȴ� �׸����� �����ϴ� for��
				Draw vd = (Draw)vc.elementAt(i);	//���Ϳ� ����Ǿ��ִ� ��ü���� vd���۷����� ����Ŵ
				g.setColor(vd.getcc());	//������ ��ü���� �׷ȴ� ������ �׸���
				if(vd.getDist() == 1) {	//Line�̾��� ��
					g.drawLine(vd.getX(), vd.getY(), vd.getX1(), vd.getY1());	//Line���׸���
				}
				else if(vd.getDist() == 2) {	//Oval�̾��� ��
					g.drawOval(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());	//Oval�� �׸���
				}
				else if(vd.getDist() == 3) {	//Rect�̾��� ��
					g.drawRect(vd.getX(), vd.getY(), vd.getX1() - vd.getX(), vd.getY1() - vd.getY());	//Rect�� �׸���
				}
			}
			
			g.setColor(o.getbc());	//���� �׸��� �׸��� ���� �����ߴ� ������ ����
			
			// ���콺�� ������ �������� 			
			if(o.getbs().equals("Line")) {  //Line�� üũ�ϸ�
				g.drawLine(x, y, x1, y1);  //x,y��ǥ���� x1,y1��ǥ�� ������ �׸���
			}
			else if(o.getbs().equals("Oval")){  //Oval�� üũ�ϸ�
				g.drawOval(x, y, x1 - x, y1 - y); //x,y��ǥ���� x1,y1��ǥ�� oval�� �׸���
			}
			else if(o.getbs().equals("Rect")) {    //Rect�� üũ�ϸ�
				g.drawRect(x, y, x1 - x, y1 - y);  //x,y��ǥ���� x1,y1��ǥ�� Rect�� �׸���
			}
		
		}
		
		//���õ� �޴��� üũ�ǵ��� �ϴ� �׼��̺�Ʈ �޼ҵ�
		public void actionPerformed(ActionEvent e){
			String cmd = e.getActionCommand();	//������ �޴��� ���ڿ��� cmd�� ����
			switch(cmd) {	//cmd���� ����
			case "Pen" :	//Pen���ý�
				o.setbs("Pen");	//o��ü�� ���� Pen����
				System.out.println(o.getbs() + " �׸����� " + o.getbc() + " ���õǾ����ϴ�.");	//���� �׸��������� ���� �ܼ�â�� ���
				break;
			case "Line" :	//Line���ý�
				o.setbs("Line");	//o��ü�� ���� Line����
				System.out.println(o.getbs() + " �׸����� " + o.getbc() + " ���õǾ����ϴ�.");	//���� �׸��������� ���� �ܼ�â�� ���
				break;
			case "Oval" :	//Oval ���ý�
				o.setbs("Oval");	//o��ü�� ���� Oval����
				System.out.println(o.getbs() + " �׸����� " + o.getbc() + " ���õǾ����ϴ�.");	//���� �׸��������� ���� �ܼ�â�� ���
				break;
			case "Rect" :	//Rect ���ý�
				o.setbs("Rect");	//o��ü�� ���� Rect����
				System.out.println(o.getbs() + " �׸����� " + o.getbc() + " ���õǾ����ϴ�.");	//���� �׸��������� ���� �ܼ�â�� ���
				break;
			case "Color" :	//Color ���ý�
				o.setbc(JColorChooser.showDialog(null,"Color",Color.YELLOW));	//�� ����â���� �� ���� o��ü�� ����
				if(o.getbc() != null) {	//���� �����Ͽ��ٸ�(������� �ʾҴٸ�)
					System.out.println(o.getbs() + " �׸����� " + o.getbc() + " ���õǾ����ϴ�.");	//���� �׸��������� ���� �ܼ�â�� ���					
				}
				break;
			}			
		}
				
		//���콺 ������ ����
		public void mouseClicked(MouseEvent e) {}
		public void mousePressed(MouseEvent e) { //��������
			x = e.getX();       //x�� ��ǥ�� ���
			y = e.getY();       //y�� ��ǥ��  ���
		}
		
		public void mouseReleased(MouseEvent e) { //��������
			x1 = e.getX();   //x1�� ��ǥ�� ���
			y1 = e.getY();   //y1�� ��ǥ�� ���
			panel.repaint();  //�׸� �׸���
			if(!o.getbs().equals("Pen")) {  //Pen�� ���������ʾ�����(Line,Oval,Rect�� �ϳ��� ����������)
				int dist = 0;	//dist�� 0���� �ʱ�ȭ
				if(o.getbs().equals("Line")) {	//Line�� ����������
					dist = 1; // dist�� 1����
				}
				else if(o.getbs().equals("Oval")) {	//Oval�� �������� ��
					dist = 2; // dist�� 2����
				}
				else if(o.getbs().equals("Rect")) {	//Rect�� �������� ��
					dist = 3; // dist�� 3����
				}
				Draw d = new Draw();  //DrawŬ������ d ��ü����
				d.setDist(dist);  //dist(�׸� ���� index) ����
		
				//(x,y)��ǥ,(x1,y1)��ǥ,dist,�� ������ ��ü d�� ����
				d.setX(x);  
				d.setY(y);
				d.setX1(x1);
				d.setY1(y1);
				d.setstr(o.getbs());
				d.setcc(o.getbc());
				vc.add(d); //���� vc�� ��ü d�߰�
			}		
		}
		
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}	//�������̽��� ��ӹ����Ƿ� �߻�޼ҵ� ��α���
		
			
		//���콺 ��� ������ ����
		public void mouseDragged(MouseEvent e) {	//�巡���ϴ� �߿�
			x1 = e.getX();	//x1 ��ǥ�� ���
			y1 = e.getY();	//y1 ��ǥ�� ���
			panel.repaint(); //�����̴� ���� ȭ�鿡 ��������
			
			if(o.getbs().equals("Pen")) {	//Pen�� �������� ��
				Draw d = new Draw();  //DrawŬ������ d��ü ����
				d.setDist(1);	//��� ������ �������� ����, dist=1�� Line�� ����
				d.setX(x);
				d.setY(y);
				d.setX1(x1);
				d.setY1(y1);
				d.setstr(o.getbs());
				d.setcc(o.getbc());	//(x,y)��ǥ,(x1,y1)��ǥ,dist,�� ������ ��ü d�� ����
				vc.add(d);	//���� vc�� ��ü d�߰�
				x = x1;	//x1�� ���� x
				y = y1;	//y1�� ���� y�� ���������ν� ���������� Line�׸��� ����
			}
		}
		public void mouseMoved(MouseEvent e) {}	//�������̽��� ��ӹ����Ƿ� �߻�޼ҵ� ��α���
	}
}


public class ExamOne {	
	public static void main(String[] args) {	//main�޼ҵ� ����
		new Picture();	//Picture�޼ҵ� ȣ��
	}
}