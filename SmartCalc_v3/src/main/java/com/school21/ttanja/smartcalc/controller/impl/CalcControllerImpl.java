package com.school21.ttanja.smartcalc.controller.impl;


import com.school21.ttanja.smartcalc.controller.CalcController;
import com.school21.ttanja.smartcalc.model.impl.CalcModelImpl;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CalcControllerImpl implements CalcController {
    private static final Logger log = LoggerFactory.getLogger(CalcControllerImpl.class);
    String setSizeFxmlFile = "/view/fxml/setsize.fxml";
    @FXML
    TextArea textArea;
    CalcModelImpl calcService = CalcModelImpl.getCalcModel();

    @Override
    public void show() {
        textArea.setText(calcService.show());
    }

    public void clean() {
        calcService.clean();
        show();
        log.debug("key clean");
    }

    public void set0() {
        calcService.set0();
        show();
        log.debug("key \"0\"");
    }

    public void set1() {
        calcService.set1();
        show();
        log.debug("key \"1\"");
    }

    public void set2() {
        calcService.set2();
        show();
        log.debug("key \"2\"");
    }

    public void set3() {
        calcService.set3();
        show();
        log.debug("key \"3\"");
    }

    public void set4() {
        calcService.set4();
        show();
        log.debug("key \"4\"");
    }

    public void set5() {
        calcService.set5();
        show();
        log.debug("key \"5\"");
    }

    public void set6() {
        calcService.set6();
        show();
        log.debug("key \"6\"");
    }

    public void set7() {
        calcService.set7();
        show();
        log.debug("key \"7\"");
    }

    public void set8() {
        calcService.set8();
        show();
        log.debug("key \"8\"");
    }

    public void set9() {
        calcService.set9();
        show();
        log.debug("key \"9\"");
    }

    public void dot() {
        calcService.dot();
        show();
        log.debug("key \".\"");
    }

    public void sum() {
        calcService.summ();
        show();
        log.debug("key \"+\"");
    }

    public void subtle() {
        calcService.subtle();
        show();
        log.debug("key \"-\"");
    }

    public void divide() {
        calcService.divide();
        show();
        log.debug("key \"/\"");
    }

    public void multiply() {
        calcService.multiply();
        show();
        log.debug("key \"*\"");
    }

    public void result() {
        calcService.result();
        show();
        log.debug("key \"=\"");
    }

    public void sqrt() {
        calcService.sqrt();
        show();
        log.debug("key \"sqrt\"");
    }

    public void setC() {
        calcService.setC();
        show();
        log.debug("key \"C\"");
    }

    public void setOpenBracket() {
        calcService.setOpenBracket();
        show();
        log.debug("key \"(\"");
    }

    public void setClosingBracket() {
        calcService.setClosingBracket();
        show();
        log.debug("key \")\"");
    }

    public void setNegative() {
        calcService.setNegative();
        show();
        log.debug("key \"set negative\"");
    }

    public void setX() {
        calcService.setX();
        show();
        log.debug("key \"x\"");
    }

    public void setLog() {
        calcService.setLog();
        show();
        log.debug("key \"log\"");
    }


    public void setAtan() {
        calcService.setAtan();
        show();
        log.debug("key \"aTan\"");
    }

    public void setSin() {
        calcService.setSin();
        show();
        log.debug("key \"sin\"");
    }

    public void setLn() {
        calcService.setLn();
        show();
        log.debug("key \"sin\"");
    }

    public void setCos() {
        calcService.setCos();
        show();
        log.debug("key \"cos\"");
    }

    public void setTan() {
        calcService.setTan();
        show();
        log.debug("key \"tan\"");
    }

    public void setAsin() {
        calcService.setAsin();
        show();
        log.debug("key \"aSin\"");
    }

    public void setPi() {
        calcService.setPi();
        show();
        log.debug("key \"Pi\"");
    }

    public void setAcos() {
        calcService.setAcos();
        show();
        log.debug("key \"aCos\"");
    }

    public void setDegree() {
        calcService.setDegree();
        show();
        log.debug("key \"degree\"");
    }

    public void setMod() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(setSizeFxmlFile));
            Scene scene = new Scene(loader.load(), 300, 160);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public void history() {
        new HistoryControllerImpl().start();
    }

    @Override
    public void cleanHistory(){
        calcService.removeHistory();
    }

    @Override
    public void about() {
        Stage stage = new Stage();
        Label label = new Label("Нажми на кнопку - получишь результат");
        int HEIGHT = 400;
        int WIDTH = 400;
        Scene scene  = new Scene(label, WIDTH, HEIGHT);

        stage.setScene(scene);
        stage.show();
        calcService.saveFunction();
    }

    public void setFromMemory(){
        show();
    }

}
