package myPIcture;
//헤더파일
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DrawDialog extends JDialog {	
	static boolean filli=false; //채우기 활성화를 구분하기 위한 변수
	static boolean thicki=false; //굵기 활성화를 구분하기 위한 변수
	
	public DrawDialog(JFrame frame, String title) {
		super(frame, title, true);
		
		//이미지 불러오기
		ImageIcon penimg = new ImageIcon("C:\\source\\pen.jpg");
		ImageIcon lineimg = new ImageIcon("C:\\source\\line.jpg");
		ImageIcon ovalimg = new ImageIcon("C:\\source\\oval.jpg");
		ImageIcon rectimg = new ImageIcon("C:\\source\\rect.jpg");
		ImageIcon triangleimg = new ImageIcon("C:\\source\\triangle.jpg");
		ImageIcon fifthangleimg = new ImageIcon("C:\\source\\fifthangle.jpg");
		ImageIcon eraserimg = new ImageIcon("C:\\source\\eraser.jpg");
		ImageIcon fillimg = new ImageIcon("C:\\source\\fill.jpg");
		ImageIcon fontimg = new ImageIcon("C:\\source\\font.jpg");
		ImageIcon thickimg = new ImageIcon("C:\\source\\thick.jpg");
				
		//버튼 생성
		JButton pen = new JButton(penimg);
		JButton line = new JButton(lineimg);
		JButton oval = new JButton(ovalimg);
		JButton rect = new JButton(rectimg);
		JButton triangle = new JButton(triangleimg);
		JButton fifthangle = new JButton(fifthangleimg);
		JButton eraser = new JButton(eraserimg);
		JButton fill = new JButton(fillimg);
		JButton font = new JButton(fontimg);
		JButton thick = new JButton(thickimg);
		JButton fillon = new JButton("Fill");
		JButton thickon = new JButton("Tck");
		
		//리스너
		pen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Pen");
				setVisible(false);
			}
		});
		line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Line");
				setVisible(false);
			}
		});
		oval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Oval");
				setVisible(false);
			}
		});
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Rect");
				setVisible(false);
			}
		});
		triangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("TriAngle");
				setVisible(false);
			}
		});
		fifthangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("FifthAngle");
				setVisible(false);
			}
		});
		eraser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Eraser");
				setVisible(false);
			}
		});
		font.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbs("Font");
				setVisible(false);
			}
		}); 
		
		//fill과 thick은 활성화 구분을 위해 boolean변수로 fillon,thickon버튼을 활성/비활성 시킴
		fill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyPanel.o.setbf(true);
				if(filli == false) {
					MyPanel.o.setbf(true);
					fillon.setEnabled(true);
					filli = true;
				}
				else {
					MyPanel.o.setbf(false);
					fillon.setEnabled(false);
					filli = false;
				}
				setVisible(false);
			}
		});

		thick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thicki == false) {
					MyPanel.o.setbt(true);
					thickon.setEnabled(true);
					thicki = true;
				}
				else {
					MyPanel.o.setbt(false);
					thickon.setEnabled(false);
					thicki = false;
				}
				setVisible(false);
			}
		});
				
		add(pen);
		add(line);
		add(oval);
		add(rect);
		add(triangle);
		add(fifthangle);
		add(eraser);
		add(fill);
		add(font);
		add(thick);
		add(fillon);
		add(thickon);
		
		fillon.setEnabled(false);
		thickon.setEnabled(false);
		
		setLayout(new GridLayout(4,3,0,0));		
		setSize(210,280);
	}
}
