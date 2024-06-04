package final_gui_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
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
    
    @FXML
    void onPowerClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double base = Double.parseDouble(currentNumber);
            double exponent = 2; // You can modify this to take input for the exponent from the user
            double result = Math.pow(base, exponent);
            display.setText(String.valueOf(result));
            savedNumber.setText(currentNumber + "^" + exponent);
            currentNumber = String.valueOf(result); // Update currentNumber with the result for further calculations
        } else if (!firstNumber.isEmpty()) {
            double base = Double.parseDouble(firstNumber);
            double exponent = 2; // You can modify this to take input for the exponent from the user
            double result = Math.pow(base, exponent);
            display.setText(String.valueOf(result));
            savedNumber.setText(firstNumber + "^" + exponent);
            firstNumber = String.valueOf(result); // Update firstNumber with the result for further calculations
        }
    }
    
    @FXML
    void onPercentageClicked(ActionEvent event) {
        // If there's an ongoing calculation with a first number and current number
        if (!firstNumber.isEmpty() && !currentNumber.isEmpty() && calculation != null) {
            double firstNum = Double.parseDouble(firstNumber);
            double currentNum = Double.parseDouble(currentNumber);
            double percentage = (firstNum * currentNum) / 100;
            currentNumber = String.valueOf(percentage);
            display.setText(currentNumber);
        } 
        // If there's only a current number, convert it to a percentage
        else if (!currentNumber.isEmpty()) {
            double num = Double.parseDouble(currentNumber);
            currentNumber = String.valueOf(num / 100);
            display.setText(currentNumber);
        }
    }

    @FXML
    void onNegateClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = number * -1;
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        } else if (!firstNumber.isEmpty()) {
            double number = Double.parseDouble(firstNumber);
            double result = number * -1;
            display.setText(String.valueOf(result));
            firstNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        }
    }

    @FXML
    void onLogClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = Math.log10(number);
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        } else if (!firstNumber.isEmpty()) {
            double number = Double.parseDouble(firstNumber);
            double result = Math.log10(number);
            display.setText(String.valueOf(result));
            firstNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        }
    }

    @FXML
    void onLnClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = Math.log(number);
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        } else if (!firstNumber.isEmpty()) {
            double number = Double.parseDouble(firstNumber);
            double result = Math.log(number);
            display.setText(String.valueOf(result));
            firstNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        }
    }

    @FXML
    void onPiClicked(ActionEvent event) {
    	double pi = Math.PI;
        addNumber(String.valueOf(pi));
    }

    @FXML
    void onSineClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = Math.sin(Math.toRadians(number));
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        }
    }

    @FXML
    void onCosineClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = Math.cos(Math.toRadians(number));
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
            updateSavedNumberDisplay();
        }
    }

    @FXML
    void onTangentClicked(ActionEvent event) {
        if (!currentNumber.isEmpty()) {
            double number = Double.parseDouble(currentNumber);
            double result = Math.tan(Math.toRadians(number));
            display.setText(String.valueOf(result));
            currentNumber = String.valueOf(result);
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