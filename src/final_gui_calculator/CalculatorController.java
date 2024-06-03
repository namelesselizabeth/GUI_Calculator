package final_gui_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import java.lang.Math;

public class CalculatorController {

    @FXML
    private TextField display;

    @FXML
    private Text savedNumber;
    
    @FXML
    private String firstNumber = "";
    
    @FXML
    private String currentNumber = "";
    
    @FXML
    private String calculation;
    
   
    
    //Adding the 0-9 buttons on a simple calculator
    
    @FXML
    void button0(ActionEvent event)  {
    	if(!currentNumber.equals("")) {
    		addNumber("0");
    	} else
    	addNumber("0");
    }
    
    @FXML
    void button1(ActionEvent event) {addNumber("1");}
    
    @FXML
    void button2(ActionEvent event) {addNumber("2");}
    
    @FXML
    void button3(ActionEvent event) {addNumber("3");}
    
    @FXML
    void button4(ActionEvent event) {addNumber("4");}
    
    @FXML
    void button5(ActionEvent event) {addNumber("5");}
    
    @FXML
    void button6(ActionEvent event) {addNumber("6");}
    
    @FXML
    void button7(ActionEvent event) {addNumber("7");}
    
    @FXML
    void button8(ActionEvent event) {addNumber("8");}
    
    @FXML
    void button9(ActionEvent event) {addNumber("9");}

    @FXML
    void buttonPoint (ActionEvent event) {
    	addNumber(".");
    }
    
    
    //Clear the field
    @FXML
    void clearTextField(ActionEvent event) {
        currentNumber = "";
        firstNumber = "";
        calculation = null;
        display.setText("");
        savedNumber.setText("");
    }
    
    //Add methods to handle calculation types like minus and addition
    @FXML
    void onAdditionClicked(ActionEvent event) {
       processCalculation("+");
    }

    @FXML
    void onMinusClicked(ActionEvent event) {
       processCalculation("-");
    }
    
    @FXML
    void onDivisionClicked(ActionEvent event) {
    	processCalculation("/");
    }
    
    @FXML
    void onMultiplicationClicked(ActionEvent event) {
    	processCalculation("*");
    }
    
    @FXML
    void onSquareRootClicked(ActionEvent event) {
    	
//        if (!currentNumber.isEmpty()) {
//            double number = Double.parseDouble(currentNumber);
//            if (number >= 0) {
//                double result = Math.sqrt(number);
//                display.setText(String.valueOf(result));
//                savedNumber.setText("√(" + currentNumber + ")");
//            } else {
//                display.setText("Error");
//                savedNumber.setText("Cannot take square root of a negative number");
//                resetCalculator();
//            }
//        } else if (!firstNumber.isEmpty()) {
//            double number = Double.parseDouble(firstNumber);
//            if (number >= 0) {
//                double result = Math.sqrt(number);
//                display.setText(String.valueOf(result));
//                savedNumber.setText("√(" + firstNumber + ")");
//            } else {
//                display.setText("Error");
//                savedNumber.setText("Cannot take square root of a negative number");
//                resetCalculator();
//            }
//        }
    	   if (!firstNumber.isEmpty() && !currentNumber.isEmpty() && calculation == null) {
    	        firstNumber = String.valueOf(Math.sqrt(Double.parseDouble(firstNumber)));
    	        updateDisplay();
    	        updateSavedNumberDisplay();
    	    } else if (!firstNumber.isEmpty() && calculation == null) {
    	        firstNumber = String.valueOf(Math.sqrt(Double.parseDouble(firstNumber)));
    	        updateDisplay();
    	        updateSavedNumberDisplay();
    	    } else if (!currentNumber.isEmpty() && calculation == null) {
    	        currentNumber = String.valueOf(Math.sqrt(Double.parseDouble(currentNumber)));
    	        updateDisplay();
    	        updateSavedNumberDisplay();
    	    }
    }
    //Add method to add numbers to the calculator
    void addNumber(String number) {
        currentNumber +=number;
        updateDisplay();
        updateSavedNumberDisplay();
    }
    
    //Add method to update display field
    void updateDisplay() {
        display.setText(currentNumber);
    }
    
    //Add method to calculate the total of two numbers depending on calculation type
    public void processCalculation(String calculation) {
    	if (!currentNumber.isEmpty()) {
    		if(firstNumber.isEmpty()) {
    			firstNumber = currentNumber;
    		} else {
    			calculate(null);
    		}
    		this.calculation = calculation;
    		updateSavedNumberDisplay();
            currentNumber = "";
    	} else if (!firstNumber.isEmpty()) {
    		this.calculation = calculation;
    		updateSavedNumberDisplay();
    	}
        
        
    }
    
    @FXML
    void calculate(ActionEvent event) {
    	
    	if (firstNumber.isEmpty() || currentNumber.isEmpty() || calculation == null) {
    		return;
    	}
        double firstNumberInt = Double.parseDouble(firstNumber);
        double secondNumberInt = Double.parseDouble(currentNumber);
        double calculatedNumber = 0;
        
        
        //switch case depending on calculation type
        switch(calculation) {
            case "+" : {calculatedNumber = firstNumberInt + secondNumberInt; break;
            }
            case "-" : {calculatedNumber = firstNumberInt - secondNumberInt; break;
            }
            case "*" : {calculatedNumber = firstNumberInt * secondNumberInt; break;}
            
            case "/" : {
            	if(secondNumberInt !=0) {
            		calculatedNumber = firstNumberInt / secondNumberInt;
            		
            	}
            	else { 
            		display.setText("Error");
            		savedNumber.setText("Cannot divide by zero");
            		//reset variables after error
            		firstNumber = "";
            		currentNumber = "";
            		calculation = null;
            		return;
            	}
            	
            	break;
            }
            
        }
        
        firstNumber = String.valueOf(calculatedNumber);
        display.setText(firstNumber);
        savedNumber.setText(firstNumber);
        currentNumber = "";
        calculation = null;
    }
    
    void updateSavedNumberDisplay() {
        String textToShow = firstNumber;
        if (calculation != null && !calculation.equals("√")) {
            textToShow += " " + calculation;
        } 
        if (currentNumber.isEmpty()) {
        	textToShow += " " ;
        }
        if (!currentNumber.isEmpty()) {
        	textToShow += " " + currentNumber;
        }
        
        savedNumber.setText(textToShow);
    }
    
    void resetCalculator () {
    	firstNumber ="";
    	currentNumber = "";
    	calculation = null;
    }
}