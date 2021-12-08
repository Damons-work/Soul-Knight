import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        TestJFrame testJFrame=new TestJFrame();
        testJFrame.requestFocus();

//        Map m=new Map();
//        m.randomRoom();

    }


    public static void main(String[] args) {
        launch(args);
    }
}