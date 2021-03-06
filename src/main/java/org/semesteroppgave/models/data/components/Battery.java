package org.semesteroppgave.models.data.components;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The attributes are not serialized because they are always the same when creating a new object
 */

public class Battery extends Component {

    private transient ArrayList<String> model;
    private transient String component;

    public Battery(String version, double price, String description) {
        super(version, price, description);
        this.model = new ArrayList<>(Arrays.asList("Electric", "Hybrid"));
        this.component = "Battery";
    }

    @Override
    public ArrayList<String> getModel() {
        return this.model;
    }

    @Override
    public String getComponent() {
        return component;
    }

    @Override
    public void setComponent(String component) {
        this.component = component;
    }

    @Override
    public int getIndex() {
        return 5;
    }

    @Override
    public String toString() {
        return super.getVersion();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.model = new ArrayList<>(Arrays.asList("Electric", "Hybrid"));
        this.component = "Battery";
    }

}
