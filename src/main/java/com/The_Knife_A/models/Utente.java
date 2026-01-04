package com.The_Knife_A.models;

import org.mindrot.jbcrypt.BCrypt;
import com.The_Knife_A.utility.GestioneFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utente {

    private int id;
    private String nome, cognome, username, ClearPassword, dataNascita, ruolo;
    private String domicilio;

    private String HashPsw;
    boolean boolRuolo;
    private GestioneFile file = new GestioneFile("data/Utenti.csv");


    private int getNextUserId() {

        int lastId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("data/Utenti.csv"))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] campi = line.split(",");

                try {
                    lastId = Integer.parseInt(campi[0]);
                } catch (Exception ignored) {}
            }

        } catch (IOException e) {}

        return lastId + 1;
    }


    public Utente(String nome, String cognome, String username, String psw, String dataNascita,String domicilio, String ruolo) {

        this.id = getNextUserId();
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.ClearPassword = psw;
        this.dataNascita = dataNascita;
        this.domicilio = domicilio;
        this.ruolo = ruolo;
        

        this.boolRuolo = ruolo.equals("ristoratore");

        HashPsw = BCrypt.hashpw(psw, BCrypt.gensalt());

        file.scriviSuFile(
                id + "," +
                nome + "," +
                cognome + "," +
                username + "," +
                HashPsw + "," +
                dataNascita + "," +
                domicilio + "," +
                ruolo,
                true
        );
    }


    public Utente(String username, String psw) {

        this.username = username;
        this.ClearPassword = psw;

        if (!login(username, psw)) {
            System.out.println("Login fallito");
            return;
        }

        System.out.println("Login effettuato con successo");

        String[] dati = file.getRiga(username, 3);

        if (dati == null || dati.length < 8) {
            System.out.println("Errore: dati utente non validi nel file.");
            return;
        }

        this.id = Integer.parseInt(dati[0]);
        this.nome = dati[1];
        this.cognome = dati[2];
        this.dataNascita = dati[5];
        this.domicilio = dati[6];
        this.ruolo = dati[7];

        this.boolRuolo = ruolo.equals("ristoratore");
    }


    public int getId() { return id; }

    public String getUsername() { return username; }

    public String getRuolo() { return ruolo; }

    public String getDomicilio() { return domicilio; }


    public boolean login(String username, String password) {

        if (file.cercaMatch(username, 3)) {

            String hash = file.getMatch(username, 3, 4);

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
