package com.school21.ttanja.smartcalc.controller.impl;

import com.school21.ttanja.smartcalc.controller.GraphController;
import com.school21.ttanja.smartcalc.exceptions.MathException;
import com.school21.ttanja.smartcalc.model.CalcModel;
import com.school21.ttanja.smartcalc.model.impl.CalcModelImpl;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import static com.school21.ttanja.smartcalc.model.impl.CalcModelImpl.expression;


@Controller
public class GraphControllerImpl implements GraphController {
    @FXML
    private TextField xFrom;
    @FXML
    private TextField xTo;
    @FXML
    private TextField yFrom;
    @FXML
    private TextField yTo;

    CalcModel calcModel = CalcModelImpl.getCalcModel();

    private static final Logger log = LoggerFactory.getLogger(CalcControllerImpl.class);

    @Override
    public void setSize(){
        Stage stage = new Stage();

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final LineChart<String,Number> lineChart =
                new LineChart<String,Number>(xAxis,yAxis);

        XYChart.Series series = new XYChart.Series();
        lineChart.setLegendVisible(false);
        series.setName("График для функции \"" + expression + "\"");

        int from = Integer.parseInt(xFrom.getText());
        int to = Integer.parseInt(xTo.getText());
        int step = (to - from)/20;
        try {
            for (int i = from; i <= to; i += step) {
                series.getData().add(new XYChart.Data(String.valueOf(i), Double.parseDouble(calcModel.calculate(setX(expression, i)))));
            }
        }
        catch (RuntimeException e){
            log.error("error call function {}", expression);
            throw new MathException();
        }
        int HEIGHT = 400;
        int WIDTH = 400;
        Scene scene  = new Scene(lineChart, WIDTH, HEIGHT);
        lineChart.getData().addAll(series);

        stage.setScene(scene);
        stage.show();
    }

    public static String setX(String toSet, int x) {
        return toSet.replaceAll("x", String.valueOf(x));
    }
}
