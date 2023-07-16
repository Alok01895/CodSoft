import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class WordCounterTASK2 extends Application {

    private TextField resultField;
    
    private TextField inputField;
    private File selectedFile;
    private ChoiceBox<String> countingMethodChoiceBox;
    private FileChooser fileChooser;
    private Button filePickerButton;
    private Button submitButton;
    private Stage primaryStage;
    private Button submit;



    public void sentenceMethodVisibility(boolean value) {
        inputField.setVisible(value);
        inputField.setManaged(value);
        submit.setVisible(value);
        submit.setManaged(value);
        resultField.setVisible(false);
        resultField.setManaged(value);
    }

    public void processSentence()
    {
        String[] words = inputField.getText().split("[\\s\\p{Punct}]+");
        int wordCount=words.length;
        int uniwords=0;
        HashSet<String> res=new HashSet<>();
        for(String word: words)
        {
            if(!res.contains(word))
            {
                uniwords++;
                res.add(word);
            }
        }
        resultField.setText("Word count: " + wordCount + "  |  " + "\n" + "Unique Words: " + uniwords);
        resultField.setVisible(true);
    }


    public void setUpSentenceMethod(){
        inputField=new TextField();
        inputField.getStyleClass().add("input-field");
        inputField.setMaxWidth(500);
        inputField.setPromptText("Enter your sentence");
        submit=new Button("Submit");
        submit.setOnAction(e-> processSentence());
        resultField=new TextField();
        resultField.setEditable(false);
        resultField.setMaxWidth(400);
        sentenceMethodVisibility(false);

    }

    // Setting for the visibility of file method
    public void fileMethodVisibility(boolean value) {
        filePickerButton.setVisible(value);
        filePickerButton.setManaged(value);
        submitButton.setVisible(value);
        submitButton.setManaged(value);
        resultField.setVisible(false);
        resultField.setManaged(value);
    }


    // processing the file
    private void processFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            int wordCount = 0;
            StringBuilder content = new StringBuilder();
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
            scanner.close();
            
            
            String[] words = content.toString().split("[\\s\\p{Punct}]+");
            
            wordCount=words.length;
            int uniwords=0;
            HashSet<String> res=new HashSet<>();
            for(String word: words)
            {
                if(!res.contains(word))
                {
                    uniwords++;
                    res.add(word);
                }
            }
            resultField.setText("Word count: " + wordCount + "  |  " + "\n" + "Unique Words: " + uniwords);
            resultField.setVisible(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    // making the file method
    public void setUpFileMethod() {
        filePickerButton = new Button("Select File");
        submitButton = new Button("Submit");
        filePickerButton.setOnAction(e -> {
            fileChooser = new FileChooser();
            fileChooser.setTitle("Select File");
            selectedFile = fileChooser.showOpenDialog(primaryStage);
        });
        submitButton.setOnAction(e -> {
            if (selectedFile != null) {
                processFile(selectedFile);
            }
        });
        resultField = new TextField();
        resultField.setMaxWidth(200);
        resultField.setEditable(false);
        fileMethodVisibility(false);
    }

    // GUI
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Word Counter");

        
        Label label = new Label("What can I count for you today?...");
        label.getStyleClass().add("cus-label");

        countingMethodChoiceBox = new ChoiceBox<>();
        countingMethodChoiceBox.getItems().addAll("File", "Sentence");
        countingMethodChoiceBox.setValue("Select One");

        

        // File method setup
        setUpFileMethod();

        // Sentence method setup
        setUpSentenceMethod();

        countingMethodChoiceBox.getStyleClass().add("options-field");
        resultField.getStyleClass().add("result-field");
        submitButton.getStyleClass().add("submit");
        submit.getStyleClass().add("submit");
        countingMethodChoiceBox.setOnAction(e -> {
            if (countingMethodChoiceBox.getValue().equals("File")) {
                sentenceMethodVisibility(false);
                fileMethodVisibility(true);
                resultField.setText("Word count: ");
            }  
            else if(countingMethodChoiceBox.getValue().equals("Sentence")){
                fileMethodVisibility(false);
                sentenceMethodVisibility(true);
                resultField.setText("Word count: ");
            }
        });

        VBox vbox = new VBox(label,countingMethodChoiceBox, filePickerButton, submitButton,inputField,submit, resultField);
        vbox.getStyleClass().add("container");
        vbox.setSpacing(10);

        
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    // Main function
    public static void main(String[] args) {
        launch(args);
    }
}
