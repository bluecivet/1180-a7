/**

 * File:        Window.java

 * Author:      Zhilong Gan

 * ID:          100331942

 * Date:        2019.03.05

 * class:       CPSC 1181-03

 * instructor:  Hengameh Hamavand

 * title        the window for program

 * Compiler:    java JDK 10.2

 */


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ComponentAdapter;

/**
 * this class will create a windon which will contain:
 * a label, a text field, a button, a text area
 * the user can only input from the text field either press enter or the button the get further process
 * the class is a calculator which alow user input an expression of math then output the result in the
 * text area
 * if the user enter wrong message there will be a warning message which is highlinghted 
 */

/*
  the layout will seperate the JFrame into three part
  the top(North) the center the bottom(south)
  all of them are show in the horizontal center of the window
 */
public class Window extends JFrame
{
	private JPanel top;    
	private JPanel center1;   //contain the label in the center of the borderLayout
	private JPanel center2;   //contain the textArea in the center in the south part of borderLayout
    private JScrollPane js;   // let the text area scroll
    private JTextField input;
	private JButton result;
	private JTextArea output;
    private JLabel label;
    private JLabel warning;    // the container that show the warnin gmessage
    private final Font DEFAULT_FONT = new Font("Times New Roman",0, 25);  // all the component use ths font

//----------------------------------------------------------------------------------
//
    /**
     * the class extend the ComponentAdapter class and implement the WindowStatedListener interface
     * when the window resize or get to maxium size of the windwo
     * the text area will change to fix the window
     */
    
    class ResizeListener extends ComponentAdapter implements WindowStateListener
    {
    	/**
    	 * the method is use when resizing the window
    	 * it will fix the text area
    	 * @param e the ComponentEvent 
    	 */
        @Override
        public void componentResized(ComponentEvent e)
        {
            Component con = e.getComponent();
            js.setPreferredSize(new Dimension((con.getWidth() >> 2) * 3, con.getHeight() >> 1));
        }

        /**
         * the method is used when the window set to max of min size 
         * it will fix the text area
         * @param e the WindowEvent
         */

        @Override
        public void windowStateChanged(WindowEvent e)
        {
            Component con = e.getComponent();
            js.setPreferredSize(new Dimension((con.getWidth() >> 2) * 3, con.getHeight() >> 1));
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * the class extdends KeyAdapter class and implement ActionListener interface
     * when the user pressed enter or press the button 
     * the text are will show the result for the expression in the text field
     */

    class PressListener extends KeyAdapter implements ActionListener
    {

    	/**
    	 * the method will be used if the user press the button or press enter
    	 * it will show the result for the expression in the text field
    	 * @param e the ActionEvent
    	 */
    	
        @Override
        public void actionPerformed(ActionEvent e)
        {
            getResult();
        }

    
    }


    ////////////////////////////////////////////////////////////////
    
    /**
     * the method will load the user input from the text field to the memory 
     * calculate the result for the expression 
     * then show intp the text area
     * if the user enter incorrect then a warnning message will show
     */
    public void getResult()
    {
		String operator;
		String message = "";     // the message show in text area
		String warningMessage = "";
		String input = this.input.getText();  // load input

		int answer = 0;
		int firstNum, secondNum;
        Scanner scan = new Scanner(input);

        warning.setText("");  // clear warning message
        
        try
        {
            firstNum = scan.nextInt();
            operator = scan.next();
            secondNum = scan.nextInt();

            try
            {
                scan.next();
                throw new InvalidExpressionException();
            }
            catch(NoSuchElementException e)
            {
                // do nothing;
            }

            switch(operator)
            {
                case "+": answer = firstNum + secondNum; break;

                case "-": answer = firstNum - secondNum; break;

                case "*": answer = firstNum * secondNum; break;

                case "/": answer = firstNum / secondNum; break;

                case "%": answer = firstNum % secondNum; break;

                default: throw new IllegalArgumentException();
            }

            message = firstNum + " " + operator + " " + secondNum + " = " + answer + "\n";
        } // end try
        catch(InputMismatchException e)
        {
            message = "";
            warningMessage = "please input integer!! other characters will not accept except operator";
        }
        catch(NoSuchElementException e)
        {
            message = "";
            warningMessage = "you are missing something!!!";
        }
        catch(IllegalArgumentException e)
        {
            message = "";
            warningMessage = "the operator is not correct";
        }
        catch(InvalidExpressionException e)
        {
            message = "";
            warningMessage = "you input too much!!";
        }
        catch(ArithmeticException e)
        {
            message = "";
            warningMessage = e.getMessage();
        }
        catch(Exception e)    // in case of other error format that user enter
        {
            message = "";
            warningMessage = e.toString();
        }

        //display result
        output.append(message);
        warning.setText(warningMessage);
    } // end the method


//-----------------------------------------------------------------------------------
  
  /**
   * the constructor of the class 
   * it will set all the variable and listener and style for all the component
   * @return [description]
   */

  public Window()
  {
    setVariable();
    setStyle();
    setListener();
  }


//-----------------------------------------------------------------------------------
  
  /**
   * the method will set the style that need to display for the user
   * all the component will set to one font 
   * the warning message will show the marked style
   */
  
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
    
    /**
     * the method will create all the variable
     * and locate them in the window
     */

    private void setVariable()
    {
        //the north part of the JFrame
        label = new JLabel("enter the expression of the calculation");
        top = new JPanel();
        input = new JTextField(10);
        result = new JButton("result");
        top.add(label);
        top.add(input);
        top.add(result);
        this.add(top,BorderLayout.NORTH);
        
        //the center part of the JFrame
        center1 = new JPanel();
		warning = new JLabel("");
		center1.add(warning);
		this.add(center1);
        
        // teh south part of the JFrame
        center2 = new JPanel();
        output = new JTextArea();
        js = new JScrollPane(output);
        output.setEditable(false);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        center2.add(js);
        this.add(center2,BorderLayout.SOUTH);
        
    }


    ////////////////////////////////////////////////////////////////////////////////
    
    /**
     * the methos will set all the listener that is needed for the program
     */

    private void setListener()
    {
    	PressListener pressed = new PressListener();  // press the button or enter
    	ResizeListener listener = new ResizeListener();  // when resizing the window
    	this.addComponentListener(listener);
    	this.addWindowStateListener(listener);
        //input.addKeyListener(pressed);
        input.addActionListener(pressed);
    	result.addActionListener(pressed);
    }


//-----------------------------------------------------------------------------------
  
  /**
   * the method will let the user to enable to visit the window
   */
  
  public void showWindow()
  {
  	setSize(1000,500);
  	setLocation(200,200);
  	setTitle("calcultor");
  	setDefaultCloseOperation(EXIT_ON_CLOSE);
  	setVisible(true);
  }


} // end class