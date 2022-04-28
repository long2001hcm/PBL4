import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Color;
import java.awt.Desktop;

public class main extends JFrame implements ActionListener{

	public static void main(String[] args) {
		new main();
	}
	
	public main() {
		this.setTitle("Nhân ma trận");
		this.setSize(500, 400);
		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setLayout(null);
		JLabel Label = new JLabel("Nhân ma trận dùng Multithreading");
		Label.setFont(new Font("", Font.BOLD, 23));
		Label.setBounds(50, 40, 400, 35);
		
		JLabel LabelA = new JLabel("Nhập ma trận A:");
		LabelA.setBounds(20, 115, 100, 25);
		JButton buttonA = new JButton("Browse");
		buttonA.setBounds(345, 115, 115, 25);
        JTextField textA = new JTextField();
        textA.setEditable(false);
        textA.setBounds(125, 115, 200, 25);
        buttonA.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  	JFileChooser chooser = new JFileChooser("C:\\");
        	        int returnVal = chooser.showOpenDialog(null);
        	        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	            textA.setText(chooser.getSelectedFile().getPath());
        	        }
        	  } 
        });
        
        JLabel LabelB = new JLabel("Nhập ma trận B:");
        LabelB.setBounds(20, 165, 100, 25);
		JButton buttonB = new JButton("Browse");
		buttonB.setBounds(345, 165, 115, 25);
        JTextField textB = new JTextField();
        textB.setEditable(false);
        textB.setBounds(125, 165, 200, 25);
        buttonB.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) { 
        		  	JFileChooser chooser = new JFileChooser("C:\\");
        	        int returnVal = chooser.showOpenDialog(null);
        	        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	            textB.setText(chooser.getSelectedFile().getPath());
        	        }
        	  } 
        });
        
        JLabel LabelKQ = new JLabel("Ma trận kết quả:");
        LabelKQ.setBounds(20, 215, 100, 25);
		JButton buttonKQ = new JButton("Browse");
		buttonKQ.setBounds(345, 215, 115, 25);
		JButton buttonOpen = new JButton("Open Folder");
		buttonOpen.setBounds(345, 245, 115, 25);
        JTextField textKQ = new JTextField("C:\\Output");
        textKQ.setEditable(false);
        textKQ.setBounds(125, 215, 200, 25);
        buttonKQ.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) {
        		  	JFileChooser chooser = new JFileChooser(textKQ.getText());
        		  	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
        	        int returnVal = chooser.showOpenDialog(null);
        	        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	        	textKQ.setText(chooser.getSelectedFile().getPath());
        	        }
        	  } 
        });
        
        buttonOpen.addActionListener(new ActionListener() { 
        	  public void actionPerformed(ActionEvent e) {
        		  try {
        			  Desktop.getDesktop().open(new File(textKQ.getText()));
        		  } catch (Exception v) {
        			  
        		  }
        	  }
        });
        
        
        JButton buttonNhan = new JButton("Nhân 2 ma trận");
        buttonNhan.setBounds(175, 300, 150, 25);
        buttonNhan.addActionListener(new ActionListener() { 
      	  public void actionPerformed(ActionEvent e) { 
      		  try {
    			  Files.createDirectories(Paths.get("C:\\Output"));
      			  XuLy xl = new XuLy();
      			  xl.setURL(textA.getText(), textB.getText(), textKQ.getText());
      			  long startTime = System.nanoTime();
      			  xl.run();
      			  long stopTime = System.nanoTime();
      			  double time = (double)(stopTime - startTime)/1000000000;
      			  JOptionPane.showMessageDialog(null, "Nhân thành công, thời gian thực hiện là " + String.format("%.6f", time) + " giây.");
      		  } catch (Exception a) {
      			  JOptionPane.showMessageDialog(null, "Ma trận không hợp lệ.");
      		  }
      	  }
        });
        
        this.add(buttonOpen);
        this.add(Label);
        this.add(buttonNhan);
        this.add(LabelB);
        this.add(buttonB);
        this.add(textB);
        this.add(LabelA);
        this.add(buttonA);
        this.add(textA);
        this.add(LabelKQ);
        this.add(buttonKQ);
        this.add(textKQ);
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
