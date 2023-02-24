package com.school21.ttanja.smartcalc;

import com.school21.ttanja.smartcalc.CalcApplication.StageReadyEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/view/fxml/calc.fxml")
    private Resource calcResource;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(calcResource.getURL());
            Parent parent = fxmlLoader.load();

        stage.setScene(new Scene(parent, 355,  300));
        stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
