package final_gui_calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui_Calc extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("Gui_Calc.fxml"));
    	Parent root = loader.load();
        primaryStage.setTitle("GUI Calculator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
        
        CalculatorController controller = loader.getController();
        
        controller.loadCalculatorState("calculator_state.ser");
        
        controller.updateHistoryTextArea();
        
        primaryStage.setOnCloseRequest(event -> {
            // Save calculator state before closing
			controller.saveCalculatorState("calculator_state.ser");
        });


       
    }

    public static void main(String[] args) {
 
        launch(args);
    }
}
