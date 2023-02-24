package com.school21.ttanja.smartcalc;

import com.school21.ttanja.smartcalc.controller.impl.CalcControllerImpl;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class StockUiApplication {

	private static final Logger log = LoggerFactory.getLogger(CalcControllerImpl.class);

	public static void main(String[] args) {
		log.info("Application start");
		Application.launch(CalcApplication.class, args);
	}

}
