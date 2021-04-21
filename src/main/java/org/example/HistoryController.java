package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class HistoryController {

    @FXML
    private Text fioText;

    @FXML
    private TextField fioField;

    @FXML
    private Text ageText;

    @FXML
    private TextField ageField;

    @FXML
    private Text birthdayText;

    @FXML
    private TextField birthdayField;

    @FXML
    private Text adressText;

    @FXML
    private TextField adressField;

    @FXML
    private Text inspectionDateText;

    @FXML
    private TextField inspectionDateField;

    @FXML
    private Text institutionDiagnosisText;

    @FXML
    private TextArea institutionDiagnosisArea;

    @FXML
    private Text complaintsOnReceiptText;

    @FXML
    private TextArea complaintsOnReceiptArea;

    @FXML
    private Text curationСomplaintsText;

    @FXML
    private TextArea curationСomplaintsArea;

    @FXML
    private Text anamnesisOfLifeText;

    @FXML
    private TextArea anamnesisOfLifeArea;

    @FXML
    private Text objectiveExaminationText;

    @FXML
    private TextArea objectiveExaminationArea;

    @FXML
    private Text laboratoryDataText;

    @FXML
    private TextArea laboratoryDataArea;

    @FXML
    private Text instrumentalResearchDataText;

    @FXML
    private TextArea instrumentalResearchDataArea;

    @FXML
    private Text differentialDiagnosisText;

    @FXML
    private TextArea differentialDiagnosisArea;

    @FXML
    private Text diagnosisAndJustificationText;

    @FXML
    private TextArea diagnosisAndJustificationArea;

    @FXML
    private Text treatmentAndJustificationText;

    @FXML
    private TextArea treatmentAndJustificationArea;

    @FXML
    private Text treatmentForPatientText;

    @FXML
    private TextArea treatmentForPatientArea;

    @FXML
    private Text literatureText;

    @FXML
    private TextArea literatureArea;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;


    @FXML
    void initialize() {
        backButton.setOnAction(event-> {
            backButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("main.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle("Учебная история болезни");
            stage.setScene(new Scene(root));
            stage.show();
        });
        saveButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word","*.docx"));
            String filePath = String.valueOf(fileChooser.showSaveDialog(saveButton.getScene().getWindow()));
            XWPFDocument document = new XWPFDocument();
            Queue<Text> paragraphTitles = new LinkedList<>(Arrays.asList(fioText,ageText,birthdayText,adressText,inspectionDateText,institutionDiagnosisText,complaintsOnReceiptText,curationСomplaintsText,anamnesisOfLifeText,objectiveExaminationText,laboratoryDataText,instrumentalResearchDataText,differentialDiagnosisText,diagnosisAndJustificationText,treatmentAndJustificationText,treatmentForPatientText,literatureText));
            Queue<? extends TextInputControl> paragraphText = new LinkedList<>(Arrays.asList(fioField,ageField,birthdayField,adressField,inspectionDateField,institutionDiagnosisArea,complaintsOnReceiptArea,curationСomplaintsArea,anamnesisOfLifeArea,objectiveExaminationArea,laboratoryDataArea,instrumentalResearchDataArea,differentialDiagnosisArea,diagnosisAndJustificationArea,treatmentAndJustificationArea,treatmentForPatientArea,literatureArea));
            while (!(paragraphTitles.isEmpty())){
               XWPFParagraph currentTitle =  document.createParagraph();
               currentTitle.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun currentTitleInfo = currentTitle.createRun();
                currentTitleInfo.setText(paragraphTitles.poll().getText());
                currentTitleInfo.setBold(true);
                currentTitleInfo.setFontSize(18);
                XWPFParagraph currentText = document.createParagraph();
                currentText.setAlignment(ParagraphAlignment.LEFT);
                XWPFRun currentTextInfo = currentText.createRun();
                String currentString = paragraphText.poll().getText();
                if(currentString.contains("\n")){
                    String[] lines = currentString.split("\n");
                    currentTextInfo.setText(lines[0],0);
                    for(int i=1;i<lines.length;i++){
                        currentTextInfo.addBreak();
                        currentTextInfo.setText(lines[i]);
                    }
                } else {
                    currentTextInfo.setText(currentString, 0);
                }

                currentTextInfo.setFontSize(12);
            }
            try {
                FileOutputStream out = new FileOutputStream(filePath);
                document.write(out);
                out.close();
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}