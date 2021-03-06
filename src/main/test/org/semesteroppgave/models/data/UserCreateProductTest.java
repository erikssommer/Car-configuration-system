package org.semesteroppgave.models.data;

import org.junit.jupiter.api.Test;
import org.semesteroppgave.models.data.components.*;
import org.semesteroppgave.models.data.customizations.*;
import org.semesteroppgave.models.data.productmodels.*;
import org.semesteroppgave.models.exceptions.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tester for opprettelse av produkt
 */

class UserCreateProductTest {

    @Test
    void testValidFinishedProduct() {
        //Tester at det er mulig å opprette produkter med og uten tilpasninger (gps, henger, soltat og autopilot)

        new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("100hk", 15000, "Motor for ok ytelse og ok fart"))
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km"))
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(null) //Null er tillat her
                .withAutopilot(Custom.AUTOPILOT)
                .build();

        new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("100hk", 15000, "Motor for ok ytelse og ok fart"))
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km"))
                .withGps(Custom.GPS)
                .build();

        new Hybrid.Builder("Hybrid", 850_000)
                .selectedMotor(new Motor("100hk", 15000, "Motor for ok ytelse og ok fart"))
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km"))
                .selectedFuelContainer(new FuelContainer("40-liter", 5000, "Drivstofftank som er stor nok for de aller fleste"))
                .build();
    }

    @Test
    void testInvalidFinishedProduct() {
        //Tester at ingen komponenter kan være null, ellers kastes EmptyComponentException

        assertThrows(EmptyComponentException.class, () -> new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("100hk", 15000, "Motor for ok ytelse og ok fart"))
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(null) //Null er ikke tillat her
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(Custom.TOWBAR)
                .withAutopilot(Custom.AUTOPILOT)
                .build());

        assertThrows(EmptyComponentException.class, () -> new Diesel.Builder("Diesel", 400_000)
                .selectedMotor(new Motor("100hk", 15000, "Motor for ok ytelse og ok fart"))
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(null) //Null ikke tillat her
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedGearbox(new Gearbox("Sporsautomat", 25000, "Automatgir som gir raske girskift og høy ytelse"))
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(Custom.TOWBAR)
                .build());

        assertThrows(EmptyComponentException.class, () -> new Hybrid.Builder("Hybrid", 400_000)
                .selectedMotor(null) //Null ikke tillat her
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(null) //Null ikke tillat her
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km"))
                .selectedFuelContainer(new FuelContainer("40-liter", 5000, "Drivstofftank som er stor nok for de aller fleste"))
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(Custom.TOWBAR)
                .build());
    }

    @Test
    void testInvalidVersionFinishedProduct() {
        //Tester versjonsbeskrivelsen

        assertThrows(InvalidVersionException.class, () -> new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("949834", 15000, "Motor for ok ytelse og ok fart")) //Kan ikke være tall
                .selectedRim(new Rim("Dette er for langt", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "Setetrekk i skinn som er komfortabelt for lange kjøreturer"))
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(null) //Null er ikke tillat her
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(Custom.TOWBAR)
                .withAutopilot(Custom.AUTOPILOT)
                .build());
    }

    @Test
    void testInvalidDescriptionFinishedProduct() {
        //Tester beskrivelsen

        assertThrows(InvalidDescriptionException.class, () -> new Electric.Builder("Elektrisk", 1_200_000)
                .selectedMotor(new Motor("100hk", 15000, "wdlfvdfv")) //Beskrivelsen er feil
                .selectedRim(new Rim("16-tommer", 4000, "Felg som er standard. God komfort, ok utseende"))
                .selectedSeatcover(new SeatCover("Komfort skinn", 10000, "8469823")) //Beskrivelsen er feil
                .selectedSpoiler(new Spoiler("Høy og bred", 4000, "Spoiler for stor sportsutseende og maks veigrep i høy fart"))
                .selectedTires(new Tires("Pirelli Sommer", 5600, "Sommerdekk som er gode på alle typer sommerføre"))
                .selectedBattery(new Battery("80 kWh", 15000, "EL-bilbatteri med rekkevidde på 500 km"))
                .withGps(Custom.GPS)
                .withSunroof(Custom.SUNROOF)
                .withTowbar(null)
                .withAutopilot(Custom.AUTOPILOT)
                .build());
    }

}