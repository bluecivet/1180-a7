/**

 * File:        InvalidExpressionException.java

 * Author:      Zhilong Gan

 * ID:          100331942

 * Date:        2019.03.05

 * class:       CPSC 1181-03

 * instructor:  Hengameh Hamavand

 * title        invalid expression exception

 * Compiler:    java JDK 10.2

 */


/**
 * this is the class of exception 
 * it define the expression is not valid
 */
import java.util.*;
public class InvalidExpressionException extends Exception 
{
	InvalidExpressionException()
	{
       this("the expression is invalid");
	}

	InvalidExpressionException(String message)
	{
		super(message);
	}
}