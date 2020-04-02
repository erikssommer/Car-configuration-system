package org.semesteroppgave;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.semesteroppgave.carcomponents.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RegisterComponent {

    private transient ObservableList<Component> componentsList = FXCollections.observableArrayList();
    private ObservableList<Component> modelComponentsList = FXCollections.observableArrayList();
    private ObservableList<Component> chooseComponentList = FXCollections.observableArrayList();
    private String newComponent;

    public RegisterComponent(ObservableList<Component> componentsList){
        this.componentsList = componentsList;
    }

    public RegisterComponent(){}

    public void writeObject(ObjectOutputStream s) throws IOException, ClassNotFoundException{
        s.defaultWriteObject();
        s.writeObject(new ArrayList<>(componentsList));
    }

    @SuppressWarnings("unchecked")
    public void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException{
        List<Component> newComponentList = (List<Component>) s.readObject();
        componentsList = FXCollections.observableArrayList();
        componentsList.addAll(newComponentList);
    }

    //Brukes til å finne ut hvilken type komponent som skal opprettet av admin
    public void setNewComponent(String newComponent){
        this.newComponent = newComponent;
    }

    public String getNewComponent(){
        return this.newComponent;
    }

    public void setComponentsList(ObservableList<Component> componentsList){
        this.componentsList = componentsList;
    }

    public void setComponentsList(Component component){
        componentsList.add(component);
    }

    public ObservableList<Component> getComponentsList(){
        return this.componentsList;
    }

    public void setModelComponentsList(Component component){
        modelComponentsList.add(component);
    }

    public ObservableList<Component> getModelComponentsList(){
        return this.modelComponentsList;
    }

    public void clearModelComponentsList(){
        this.modelComponentsList.clear();
    }

    public void setChooseComponentList(Component component){
        chooseComponentList.add(component);
    }

    public ObservableList<Component> getChooseComponentList(){
        return this.chooseComponentList;
    }

    public void clearChooseComponentList(){
        this.chooseComponentList.clear();
    }

    //Søkefunksjon med streams
    public ObservableList<Component> searchComponent(String value){

        return componentsList.stream().filter(component -> component.getComponent().toLowerCase().contains(value.toLowerCase())).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchVersion(String value){

        return componentsList.stream().filter(component -> component.getVersion().contains(value)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public ObservableList<Component> searchPrice(double value){

        return componentsList.stream().filter(component -> component.getPrice()==(value)).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }
}
