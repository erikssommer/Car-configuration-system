package org.semesteroppgave.controller;

/**
 * Interface for kontrollere som skal laste inn filer i egen tråd
 * Kontrollerne må implementere 'intitialize' i tillegg
 */

public interface ApplicationThread extends ApplicationController{

    void disableGUI();
    void enableGUI();

}