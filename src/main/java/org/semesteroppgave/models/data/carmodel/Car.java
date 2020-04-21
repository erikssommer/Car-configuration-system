package org.semesteroppgave.models.data.carmodel;

import org.semesteroppgave.models.data.carcomponents.*;
import org.semesteroppgave.models.data.carcustomization.*;
import org.semesteroppgave.models.exceptions.EmptyComponentException;

import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Car {
    private Motor motor;
    private Rim rim;
    private SeatCover seatcover;
    private Spoiler spoiler;
    private Tires tires;
    private Gps gps;
    private Sunroof sunroof;
    private Towbar towbar;

    public Car(Motor motor, Rim rim, SeatCover seatcover, Spoiler spoiler, Tires tires, Gps gps, Sunroof sunroof, Towbar towbar) {
        if (motor == null) throw new EmptyComponentException("Du har glemt å velge en motor");
        if (rim == null) throw new EmptyComponentException("Du har glemt å velge felger");
        if (seatcover == null) throw new EmptyComponentException("Du har glemt å velge setetrekk");
        if (spoiler == null) throw new EmptyComponentException("Du har glemt å velge en spoiler");
        if (tires == null) throw new EmptyComponentException("Du har glemt å velge dekk");
        this.motor = motor;
        this.rim = rim;
        this.seatcover = seatcover;
        this.spoiler = spoiler;
        this.tires = tires;
        this.gps = gps;
        this.sunroof = sunroof;
        this.towbar = towbar;
    }

    public Motor getMotor() {
        return this.motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Rim getRim() {
        return rim;
    }

    public void setRim(Rim rim) {
        this.rim = rim;
    }

    public SeatCover getSeatCover() {
        return seatcover;
    }

    public void setSeatCover(SeatCover seatcover) {
        this.seatcover = seatcover;
    }

    public Spoiler getSpoiler() {
        return spoiler;
    }

    public void setSpoiler(Spoiler spoiler) {
        this.spoiler = spoiler;
    }

    public Tires getTires() {
        return tires;
    }

    public void setTires(Tires tires) {
        this.tires = tires;
    }

    public Gps getGps() {
        return this.gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    public Sunroof getSunroof() {
        return this.sunroof;
    }

    public void setSunroof(Sunroof sunroof) {
        this.sunroof = sunroof;
    }

    public Towbar getTowbar() {
        return this.towbar;
    }

    public void setTowbar(Towbar towbar) {
        this.towbar = towbar;
    }


    public double getPrice() {

        double price = getMotor().getPrice() + getRim().getPrice() + getSeatCover().getPrice() +
                getSpoiler().getPrice() + getTires().getPrice();
        if (getGps() != null) {
            price += getGps().getPrice();
        }
        if (getSunroof() != null) {
            price += getSunroof().getPrice();
        }
        if (getTowbar() != null) {
            price += getTowbar().getPrice();
        }

        return price;
    }

    //Metode som støtter toString metodene til underklassene til Car
    //For å unngå duplikat kode
    public String testCustom(DecimalFormat df, Autopilot autopilot) {

        String message = "";
        if (getGps() != null) {
            message += getGps().getCustomProperty() + "\nPris: " + df.format(getGps().getPrice()) + "kr\n\n";
        }
        if (getSunroof() != null) {
            message += getSunroof().getCustomProperty() + "\nPris: " + df.format(getSunroof().getPrice()) + "kr\n\n";
        }
        if (getTowbar() != null) {
            message += getTowbar().getCustomProperty() + "\nPris: " + df.format(getTowbar().getPrice()) + "kr\n\n";
        }

        if (autopilot != null) {
            message += autopilot.getCustomProperty() + "\nPris: " + df.format(autopilot.getPrice()) + "kr\n\n";
        }

        if (getGps() == null && getSunroof() == null && getTowbar() == null && autopilot == null) {
            message += "Denne komfigurasjonen har ingen tilpasninger\n\n";
        }
        return message;
    }

    public String customToFile(Autopilot autopilot) {
        String message = "";
        if (getGps() != null) {
            message += getGps().toFile() + ";";
        } else {
            message += ";;";
        }
        if (getSunroof() != null) {
            message += getSunroof().toFile() + ";";
        } else {
            message += ";;";
        }
        if (getTowbar() != null) {
            message += getTowbar().toFile() + ";";
        } else {
            message += ";;";
        }

        if (autopilot != null) {
            message += autopilot.toFile() + ";";
        } else {
            message += ";;";
        }
        return message;
    }

    public String toFile() {
        return getMotor().toFile() + ";" + getRim().toFile() + ";" + getSeatCover().toFile() + ";" + getSpoiler().toFile() + ";" + getTires().toFile();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (obj instanceof Car) {
            Car product = (Car) obj;
            return product.getMotor().equals(motor) && product.getRim().equals(rim)
                    && product.getSeatCover().equals(seatcover)
                    && product.getSpoiler().equals(spoiler) && product.getTires().equals(tires)
                    && ((product.getGps() == null && gps == null) || Objects.equals(product.getGps(), gps))
                    && ((product.getSunroof() == null && sunroof == null) || Objects.equals(product.getSunroof(), sunroof))
                    && ((product.getTowbar() == null && towbar == null) || Objects.equals(product.getTowbar(), towbar));
        }
        return false;
    }


    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("###,###,###.###");
        return "Motor: " + getMotor().getVersion() + "\nPris: " + df.format(getMotor().getPrice()) + "kr\nBeskrivelse: " + getMotor().getDescription() + "\n\n" +
                "Felg: " + getRim().getVersion() + "\nPris: " + df.format(getRim().getPrice()) + "kr\nBeskrivelse: " + getRim().getDescription() + "\n\n" +
                "Setetrekk: " + getSeatCover().getVersion() + "\nPris: " + df.format(getSeatCover().getPrice()) + "kr\nBeskrivelse: " + getSeatCover().getDescription() + "\n\n" +
                "Spoiler: " + getSpoiler().getVersion() + "\nPris: " + df.format(getSpoiler().getPrice()) + "kr\nBeskrivelse: " + getSpoiler().getDescription() + "\n\n" +
                "Dekk: " + getTires().getVersion() + "\nPris: " + df.format(getTires().getPrice()) + "kr\nBeskrivelse: " + getTires().getDescription() + "\n\n";

    }
}