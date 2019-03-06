import sun.awt.geom.AreaOp;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ComponentAdapter;

public class Window extends JFrame
{
	private JTextField input;
	private JButton result;
	private JTextArea output;
	private JPanel top;
	private JPanel center1;
	private JPanel center2;
    private JScrollPane js;
    private Font DEFAULT_FONT = new Font("Times New Roman",0, 25);
    JLabel label;
    JLabel warning;

//----------------------------------------------------------------------------------

    class ResizeListener extends ComponentAdapter implements WindowStateListener
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            Component con = e.getComponent();
            js.setPreferredSize(new Dimension((con.getWidth() >> 2) * 3, con.getHeight() >> 1));
        }

        @Override
        public void windowStateChanged(WindowEvent e)
        {
            Component con = e.getComponent();
            js.setPreferredSize(new Dimension((con.getWidth() >> 2) * 3, con.getHeight() >> 1));
        }
    }


    class PressListener extends KeyAdapter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getResult();
        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if(e.getKeyChar() == '\n')
            {
                System.out.println("key press");
                getResult();
            }
        }
    }


    ////////////////////////////////////////////////////////////////

    public void getResult()
    {
        String input = this.input.getText();
        Scanner scan = new Scanner(input);
        int firstNum, secondNum;
        String operator;
        char sign;
        String message = "";
        int answer = 0;
        boolean displayInTextArea = false;
        warning.setText("");
        String warningMessage = "";

        try
        {
            firstNum = scan.nextInt();
            operator = scan.next();
            if(operator.length() > 1)
                throw new IllegalArgumentException();

            sign = operator.charAt(0);
            secondNum = scan.nextInt();

            if(scan.hasNext())
            {
                throw new IllegalStateException();
            }

            switch(sign)
            {
                case '+': answer = firstNum + secondNum; break;

                case '-': answer = firstNum - secondNum; break;

                case '*': answer = firstNum * secondNum; break;

                case '/': answer = firstNum / secondNum; break;

                case '%': answer = firstNum % secondNum; break;

                default: throw new IllegalArgumentException();
            }
            message = firstNum + " " + sign + " " + secondNum + " = " + answer;
            displayInTextArea = true;

        }
        catch(InputMismatchException e)
        {
            warningMessage = "please input integer!! other characters will not accept except operator";
        }
        catch(NoSuchElementException e)
        {
            warningMessage = "you are missing something!!!";
        }
        catch(IllegalArgumentException e)
        {
            warningMessage = "the operator is not correct";
        }
        catch(IllegalStateException e)
        {
            warningMessage = "you input too much!!";
        }
        catch(ArithmeticException e)
        {
            warningMessage = e.getMessage();
        }
        catch(Exception e)
        {
            warningMessage = e.toString();
        }

        if(displayInTextArea)
        {
            output.append(message + "\n");
        }
        warning.setText(warningMessage);
    }


//-----------------------------------------------------------------------------------
  

  public Window()
  {
    setVariable();
    this.setStyle();
    ResizeListener listener = new ResizeListener();
    this.addComponentListener(listener);
    this.addWindowStateListener(listener);
  }


//-----------------------------------------------------------------------------------
  
  private void setStyle()
  {
  	input.setFont(DEFAULT_FONT);
  	result.setFont(DEFAULT_FONT);
  	output.setFont(DEFAULT_FONT);
  	label.setFont(DEFAULT_FONT);
  	warning.setFont(DEFAULT_FONT);
  	warning.setForeground(Color.blue);
  	warning.setBackground(Color.magenta);
  	warning.setOpaque(true);

  }


  ///////////////////////////////////////////////////////////////////////////////////

    private void setVariable()
    {
        PressListener pressed = new PressListener();
        label = new JLabel("enter the expression of the calculation");
        top = new JPanel();
        input = new JTextField();
        input.addKeyListener(pressed);
        input.setColumns(10);
        result = new JButton("result");
        result.addActionListener(pressed);
        top.add(label);
        top.add(input);
        top.add(result);
        this.add(top,BorderLayout.NORTH);

        center1 = new JPanel();
        center2 = new JPanel();
        warning = new JLabel("");
        output = new JTextArea();
        output.setEditable(false);
        js = new JScrollPane(output);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        center1.add(js);
        center2.add(warning);
        this.add(center2);
        this.add(center1, BorderLayout.SOUTH);
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