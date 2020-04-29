package org.semesteroppgave.models.signin.user;

import org.semesteroppgave.Main;
import org.semesteroppgave.models.exceptions.InvalidUsernameException;
import org.semesteroppgave.models.filehandlers.FileHandler;
import org.semesteroppgave.models.utilities.alerts.Dialogs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Modell for bruker
 */

public class UserSignIn {
    private final ArrayList<User> userList = new ArrayList<>();
    private static String activeUsername;

    public ArrayList<User> getUserList() {
        return this.userList;
    }

    public void setUserList(User user) {
        userList.add(user);
    }

    public static String getActiveUsername() {
        return activeUsername;
    }

    public static void setActiveUsername(String activeUsername) {
        UserSignIn.activeUsername = activeUsername;
    }

    public void register(String username, String password, String name, String phonenumber, String eMail) throws IOException, IllegalArgumentException {
        checkIfNotExisting(username); //Kaster et avvik hvis brukernavn er opptatt
        User newUser = new User(username, password, name, phonenumber, eMail);
        setUserList(newUser);
        Dialogs.showSuccessDialog("Ny bruker", "Ny bruker ble registrert", "Logg inn med brukernavn og passord");
        saveToFileUsernamePassword();
        saveToFileInfo();
        Main.setRoot("usersignin");
    }

    // Leser inn eksisterende brukere fra bruker-filene og legger de i en ArrayList.
    public void parseExistingUser() {
        userList.clear();
        // Tar inn info fra bruker-filer
        var filepathUnamePword = Paths.get(String.valueOf(FileHandler.getFile("signin/userUsernameAndPassword")));
        var filepathUInfo = Paths.get(String.valueOf(FileHandler.getFile("signin/userInfo")));

        try {
            Scanner s = new Scanner(new File(String.valueOf(filepathUInfo)));
            Scanner g = new Scanner(new File(String.valueOf(filepathUnamePword)));

            s.useDelimiter("[,\n]");
            g.useDelimiter("[,\n]");

            while (s.hasNext() && g.hasNext()) {
                String name = s.next();
                String phonenumber = s.next();
                String email = s.next();
                String username = g.next();
                String password = g.next();
                // Oppretter bruker fra fil og legger i userList
                User newUser = new User(username, password, name, phonenumber, email);
                userList.add(newUser);
            }
        } catch (IllegalArgumentException | FileNotFoundException e) {
            Dialogs.showErrorDialog("Oups", "En feil har skjedd ved parsing av brukerfil", e.getMessage());
        }
    }

    public boolean verifyLogin(String username, String password) {

        var filepath = Paths.get(String.valueOf(FileHandler.getFile("signin/userUsernameAndPassword")));

        try {
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();
                String checkPassword = s.next();

                if (checkUsername.trim().equals(username.trim()) && checkPassword.trim().equals(password.trim())) {
                    System.out.println("\nSuccessful match with username " + username.trim() + " in file userUsernameAndPassword");
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
        return false;
    }

    private void checkIfNotExisting(String username) throws InvalidUsernameException {

        var filepath = Paths.get(String.valueOf(FileHandler.getFile("signin/userUsernameAndPassword")));

        try {
            Scanner s = new Scanner(new File(String.valueOf(filepath)));

            s.useDelimiter("[,\n]");
            while (s.hasNext()) {
                String checkUsername = s.next();

                if (checkUsername.trim().equals(username.trim())) {
                    throw new InvalidUsernameException("\nBrukernavnet " + username.trim() + " er ikke ledig\nVelg et nytt");
                }
            }
        } catch (FileNotFoundException e) {
            System.err.print("Error :" + e.getMessage());
        }
    }

    // Lagrer admins brukernavn og passord til fil
    private void saveToFileUsernamePassword() {
        var filepath = Paths.get(String.valueOf(FileHandler.getFile("signin/userUsernameAndPassword")));
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileUsernamePassword().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Lagrer admins info til fil
    private void saveToFileInfo() {
        var filepath = Paths.get(String.valueOf(FileHandler.getFile("signin/userInfo")));
        try {
            Files.write(Paths.get(String.valueOf(filepath)), txtToFileUserInfo().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Skriver brukers brukernavn og passord til fil
    private String txtToFileUsernamePassword() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUsernamePassword()).append("\n");
        }
        return ut.toString();
    }

    // Skriver brukers info til fil
    private String txtToFileUserInfo() {
        StringBuilder ut = new StringBuilder();
        for (User newUser : userList) {
            ut.append(newUser.printTxtUserInfo()).append("\n");
        }
        return ut.toString();
    }
}
