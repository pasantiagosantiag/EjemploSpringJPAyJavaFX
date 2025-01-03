package ies.accesodatos;
import ies.accesodatos.repositories.CountryRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootApplication
public class Main extends Application {
    private static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        launch();


    }
    @Override
    public void init() {
        context = new SpringApplicationBuilder(Main.class).run();
    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/listado.fxml"));
        loader.setControllerFactory(context::getBean);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

}