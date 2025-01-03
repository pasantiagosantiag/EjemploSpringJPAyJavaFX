package ies.accesodatos.controllers;

import ies.accesodatos.model.Country;
import ies.accesodatos.repositories.CountryRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.LocalDateTime;

@Controller
public class ListadoCountry {
    private final CountryRepository repository;
    @FXML
    private TableView<Country> countryTable;

    @FXML
    private TableColumn<Country, Integer> idColumn;

    @FXML
    private TableColumn<Country, String> nameColumn;

    @FXML
    private TableColumn<Country, LocalDateTime> lastUpdateColumn;
    @FXML
    private TableColumn<Country, Void> borrar;
    @FXML
    private Button add;
    @FXML
    private TextField texto;
    @FXML
    private Button buscar;
    @FXML
    private TextField ciudad;
    private final ObservableList<Country> countryList = FXCollections.observableArrayList();

    public ListadoCountry(CountryRepository repository) {
        this.repository = repository;
    }
    @FXML
    public void initialize() {
        // Configura las columnas con las propiedades de la clase Country
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        lastUpdateColumn.setCellValueFactory(new PropertyValueFactory<>("lastUpdate"));
        this.add.disableProperty().bind(texto.textProperty().isEmpty());
        this.buscar.disableProperty().bind(ciudad.textProperty().isEmpty());
        this.add.setOnMouseClicked(mouseEvent -> {
            Country c=new Country();
            c.setCountry(texto.getText());
            c.setId(this.countryList.stream().map( country -> country.getId()).max(Integer::compareTo).get()+1);
            c.setLastUpdate(Instant.now());
            repository.save(c);
            texto.setText("");
            this.countryList.add(c);
        });
        buscar.setOnMouseClicked(mouseEvent -> {
            this.countryList.clear();
            this.countryList.addAll(repository.findByCountryByCityLikeIgnoreCase("%".concat(ciudad.getText().concat("%"))));

        });
        borrar.setCellFactory(col -> new TableCell<>() {
            private final Button actionButton = new Button("Borrar");

            {
                actionButton.setOnAction(event -> {
                    Country c = getTableView().getItems().get(getIndex());
                    repository.delete(c);
                    countryList.remove(c);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(actionButton);
                }
            }
        });
        // Vincula los datos con la tabla
        countryTable.setItems(countryList);
    }

    public void loadData(){
        countryList.clear();
       countryList.addAll( this.repository.findAll());

    }
}
