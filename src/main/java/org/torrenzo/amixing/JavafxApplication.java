package org.torrenzo.amixing;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.torrenzo.amixing.api.WeavingController;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

public class JavafxApplication extends Application {

	private ConfigurableApplicationContext applicationContext;
	
	@Override
	public void init() {
		
		String[] args = getParameters().getRaw().toArray(new String[0]);
		
		this.applicationContext = new SpringApplicationBuilder()
				.sources(AmixingApplication.class)
				.run(args);
	}


	@Override
	public void stop() throws Exception {
		this.applicationContext.close();
		Platform.exit();
	}

	@Override
	public void start(Stage stage) throws Exception {
		FxWeaver fxweaver = applicationContext.getBean(FxWeaver.class);
		Parent root = fxweaver.loadView(WeavingController.class);
		Scene scene = new Scene(root);
		stage.setTitle("Grits ain't Groceries");
		stage.setScene(scene);
		stage.show();
	}

}
