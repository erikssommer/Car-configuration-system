package org.semesteroppgave;

import javafx.scene.control.*;
import org.semesteroppgave.carcomponents.*;
import org.semesteroppgave.carcustomization.Autopilot;
import org.semesteroppgave.carcustomization.Gps;
import org.semesteroppgave.carcustomization.Sunroof;
import org.semesteroppgave.carcustomization.Towbar;
import org.semesteroppgave.carmodel.Car;
import org.semesteroppgave.carmodel.Diesel;
import org.semesteroppgave.carmodel.Electric;
import org.semesteroppgave.carmodel.Hybrid;
import org.semesteroppgave.gui.Dialogs;

public class UserCreateCar {

    private TableView<Component> tableViewComponent;
    private TableView<Component> tableViewVersion;
    private ComboBox<String> cbModel;
    private Label lblMessage;
    private TextField txtTotalPrice;

    private Battery battery;
    private FuelContainer fuelContainer;
    private Gearbox gearbox;
    private Motor motor;
    private Rim rim;
    private SeatCover seatCover;
    private Spoiler spoiler;
    private Tires tires;

    private Autopilot autopilot;
    private Gps gps;
    private Sunroof sunroof;
    private Towbar towbar;

    private double livePrice;
    private double previousPrice;

    public UserCreateCar(TableView<Component> tableViewComponent, TableView<Component> tableViewVersion, ComboBox<String> cbModel, Label lblMessage, TextField txtTotalPrice){
        this.tableViewComponent = tableViewComponent;
        this.tableViewVersion = tableViewVersion;
        this.cbModel = cbModel;
        this.lblMessage = lblMessage;
        this.txtTotalPrice = txtTotalPrice;
    }

    public void createNewCar(String model1, String model2){
        Context.getInstance().getRegisterComponent().clearModelComponentsList();
        for (Component model : Context.getInstance().getRegisterComponent().getComponentsList()){
            for (String componentModel : model.getModel()){
                if (componentModel.equals(model1) || componentModel.equals(model2)){
                    if (!redundance(model.getComponent())){
                        Context.getInstance().getRegisterComponent().setModelComponentsList(model);
                        lblMessage.setText("Du kan nå velge komponenter til din \n"+model1+" bil");
                    }
                }
            }
        }
        tableViewComponent.setItems(Context.getInstance().getRegisterComponent().getModelComponentsList());

        tableViewComponent.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                showComponents(tableViewComponent.getSelectionModel().getSelectedItem().getComponent());
            }
        });
    }

    public boolean redundance(String componentModel){
        boolean redundance = false;
        for (Component component : Context.getInstance().getRegisterComponent().getModelComponentsList()){
            redundance = componentModel.equals(component.getComponent());
        }
        return redundance;
    }

    public void showComponents(String selectedComponent){
        //Teller som forsikrer at addToCar metoden bare kjøres én gang for hver event
        final int[] counter = {0};
        Context.getInstance().getRegisterComponent().clearChooseComponentList();
        for (Component component : Context.getInstance().getRegisterComponent().getComponentsList()){
            if (component.getComponent().equals(selectedComponent)){
                Context.getInstance().getRegisterComponent().setChooseComponentList(component);
            }
        }
        tableViewVersion.setItems(Context.getInstance().getRegisterComponent().getChooseComponentList());
        tableViewVersion.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null && counter[0] == 0){
                addToCar(newSelection.getComponent());
                counter[0]++;
            }
        });
    }

    public void addToCar(String selectedComponent){
        System.out.println("Teller");
        //System.out.println(selectedComponent);
        switch (selectedComponent){
            case "Motor": motor = (Motor) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(motor);
                break;
            case "Felg": rim = (Rim) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(rim);
                break;
            case "Setetrekk": seatCover = (SeatCover) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(seatCover);
                break;
            case "Spoiler": spoiler = (Spoiler) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(spoiler);
                break;
            case "Dekk": tires = (Tires) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(tires);
                break;
            case "Batteri": battery = (Battery) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(battery);
                break;
            case "Tank": fuelContainer = (FuelContainer) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(fuelContainer);
                break;
            case "Girboks": gearbox = (Gearbox) tableViewVersion.getSelectionModel().getSelectedItem();
                addToPrice(gearbox);
                break;
            default: Dialogs.showErrorDialog("Legg til komponent", "Fant ikke komponenten", "Prøv igjen");
        }
        updateLivePrice();
        lblMessage.setText("Du har opprettet ny "+selectedComponent.toLowerCase());
    }

    public void addToPrice(Component component){
        //TODO liveprisen funker ikke helt som den skal. Hvordan skal programmet huske forrige pris?
        if (!component.getAdded()){
            previousPrice = component.getPrice();
            livePrice += component.getPrice();
            component.setAdded(true);
        }else {
            livePrice -= previousPrice;
            livePrice += component.getPrice();
        }
    }


    public void customization(CheckBox cbAutopilot, CheckBox cbTowbar, CheckBox cbSunroof, CheckBox cbGps){

        if (cbAutopilot.isSelected() && autopilot == null){
            System.out.println("Autopilot");
            autopilot = new Autopilot();
            livePrice += autopilot.getPrice();
        }

        if (!cbAutopilot.isSelected() && autopilot != null){
            livePrice -= autopilot.getPrice();
            autopilot = null;
        }

        if (cbTowbar.isSelected() && towbar == null){
            System.out.println("Towbar");
            towbar = new Towbar();
            livePrice += towbar.getPrice();
        }

        if (!cbTowbar.isSelected() && towbar != null){
            livePrice -= towbar.getPrice();
            towbar = null;
        }

        if (cbSunroof.isSelected() && sunroof == null){
            System.out.println("Sunroof");
            sunroof = new Sunroof();
            livePrice += sunroof.getPrice();
        }

        if (!cbSunroof.isSelected() && sunroof != null){
            livePrice -= sunroof.getPrice();
            sunroof = null;
        }

        if (cbGps.isSelected() && gps == null){
            System.out.println("Gps");
            gps = new Gps();
            livePrice += gps.getPrice();
        }

        if (!cbGps.isSelected() && gps != null){
            livePrice -= gps.getPrice();
            gps = null;
        }

        updateLivePrice();
    }

    public void updateLivePrice(){
        txtTotalPrice.setText(String.valueOf(livePrice));
    }

    public void finishedCar (){

        if (cbModel.getValue() != null){
            try {
                Car product = null;
                switch (cbModel.getValue()){
                    case "Elektrisk": product = new Electric(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, autopilot);
                        break;
                    case "Diesel": product = new Diesel(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, fuelContainer, gearbox);
                        break;
                    case "Hybrid": product = new Hybrid(motor, rim, seatCover, spoiler, tires, gps, sunroof, towbar, battery, fuelContainer);
                        break;
                }

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bekreft");
                alert.setHeaderText("Du ønsker å opprette en "+ cbModel.getValue().toLowerCase()+ " bil");
                alert.setContentText("Er du sikker på dette?");
                Car finalProduct = product;
                alert.showAndWait().ifPresent(response -> {
                    if(response == ButtonType.OK){
                        Dialogs.showSuccessDialog("Gjennomført", "Du har nå opprettet din komfigurasjon", "Trykk på 'vis konfig' for å se en oversikt");
                        Context.getInstance().getRegisterProduct().setMyCarList(finalProduct);
                    }
                });

            }catch (NullPointerException e){
                Dialogs.showErrorDialog("Oups", "Feil i oppretting av komponenter", e.getMessage());
            }

        }else {
            Dialogs.showErrorDialog("Oups!", "Du må velge modell", "Deretter velge komponenter til bilen");
        }
    }
}
