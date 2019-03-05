import javax.swing.*;
import java.awt.*;
public class Window extends JFrame
{
	private JTextField input;
	private JButton result;
	private JTextArea output;
	private JPanel top;
	private JPanel center1;
	private JPanel center2;
    private Font DEFAULT_FONT = new Font("Times New Roman",0, 25);
    JLabel label;
    JLabel warning;


//-----------------------------------------------------------------------------------
  

  public Window()
  {
  	label = new JLabel("enter the expression of the calculation");
    top = new JPanel();
    input = new JTextField();
    input.setColumns(10);
    result = new JButton("result");
    top.add(label);
    top.add(input);
    top.add(result);
    this.add(top,BorderLayout.NORTH);
    
    center1 = new JPanel();
    center2 = new JPanel();
    warning = new JLabel("hi");
    output = new JTextArea();
    System.out.println(getWidth());
    output.setPreferredSize(new Dimension(300,300));
    center1.add(output);
    center2.add(warning);
    this.add(center2);
    this.add(center1, BorderLayout.SOUTH);

    this.setFont();
  }


//-----------------------------------------------------------------------------------
  
  private void setFont()
  {
  	input.setFont(DEFAULT_FONT);
  	result.setFont(DEFAULT_FONT);
  	//output.setFont(DEFAULT_FONT);
  	label.setFont(DEFAULT_FONT);
  	warning.setFont(DEFAULT_FONT);

  }
//-----------------------------------------------------------------------------------
  
  public void showWindow()
  {
  	setSize(1000,500);
  	setLocation(200,200);
  	setTitle("calcultor");
  	setDefaultCloseOperation(EXIT_ON_CLOSE);
  	setVisible(true);
  }


}