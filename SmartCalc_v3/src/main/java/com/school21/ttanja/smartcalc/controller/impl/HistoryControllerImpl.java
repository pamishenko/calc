package com.school21.ttanja.smartcalc.controller.impl;


import com.school21.ttanja.smartcalc.controller.HistoryController;
import com.school21.ttanja.smartcalc.model.impl.CalcModelImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Controller;

@Controller
public class HistoryControllerImpl implements HistoryController {
    public static final ObservableList data =
            FXCollections.observableArrayList();
    CalcModelImpl calcService = CalcModelImpl.getCalcModel();
    CalcControllerImpl calcController = new CalcControllerImpl();
    public void start(){
        Stage primaryStage = new Stage();
        primaryStage.setTitle("History");
        final ListView langsListView = new ListView(data);
        langsListView.setPrefSize(400, 500);
        langsListView.setEditable(true);
        data.removeAll(data);
        data.addAll(calcService.getExpressionsHistory());
        langsListView.setItems(data);
        MultipleSelectionModel<String> langsSelectionModel = langsListView.getSelectionModel();
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue){
                calcService.setExpressionFromHistory(newValue);
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(langsListView);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
