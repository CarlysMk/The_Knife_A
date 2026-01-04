package com.The_Knife_A.models;

import org.mindrot.jbcrypt.BCrypt;
import com.The_Knife_A.utility.GestioneFile;

public class Utente {

    private String nome, cognome, username, ClearPassword, dataNascita, ruolo;
    private String[] domicilio = new String[3];
    private String HashPsw;
    boolean boolRuolo; // TRUE per ristoratore, FALSE per utente
    private GestioneFile file = new GestioneFile("data/Utenti.csv");

    // costruttore per registrazione utente
    public Utente(String nome, String cognome, String username, String psw, String dataNascita, String ruolo) {

        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ClearPassword = psw;
        this.dataNascita = dataNascita;
        this.ruolo = ruolo;

        if (this.ruolo.equals("ristoratore")) {
            this.boolRuolo = true;
        } else if (this.ruolo.equals("utente")) {
            this.boolRuolo = false;
        }

        HashPsw = BCrypt.hashpw(psw, BCrypt.gensalt());

        // scrittura nel file CSV
        file.scriviSuFile(nome + "," + cognome + "," + username + "," + HashPsw + "," + dataNascita + "," + ruolo,true);
    }

    // costruttore per login utente
    public Utente(String username, String psw) {

        this.username = username;
        this.ClearPassword = psw;

        // Controllo login
        if (!login(username, psw)) {
            System.out.println("Login fallito");
            return;
        }

        System.out.println("Login effettuato con successo");

        // Recupero dati da file
        String[] dati = file.getRiga(username, 2);

        if (dati == null) {
            System.out.println("Utente non trovato");
            return;
        }

        this.nome = dati[0];
        this.cognome = dati[1];
        this.dataNascita = dati[4];
        this.ruolo = dati[5];

        if (this.ruolo.equals("ristoratore")) {
            this.boolRuolo = true;
        } else if (this.ruolo.equals("utente")) {
            this.boolRuolo = false;
        }
    }

    public String getUsername() {
        return username;
    }

    public String getRuolo() {
        return ruolo;
    }

    // metodo login
    public boolean login(String username, String password) {

        if (file.cercaMatch(username, 2)) {

            String hash = file.getMatch(username, 2, 3);

            if (BCrypt.checkpw(password, hash)) {
                return true;
            } else {
                System.out.println("Password errata");
                return false;
            }

        } else {
            System.out.println("Username errato");
            return false;
        }
    }
}
