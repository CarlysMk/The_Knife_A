package com.The_Knife_A.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class GestioneFile {
    private String percorsoRelativo;

    public GestioneFile(String percorsoRelativo) {
        this.percorsoRelativo = percorsoRelativo;
    }

    public void scriviSuFile(String testo, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoRelativo, append))) {
            writer.write(testo);
            writer.newLine();
            System.out.println("Scrittura completata su: " + percorsoRelativo);
        } catch (IOException e) {
            System.out.println("Errore di scrittura: " + e.getMessage());
        }
    }

    public void leggiDaFile() {
        Path path = Paths.get(percorsoRelativo);

        if (!Files.exists(path)) {
            System.out.println("Errore: Il file '" + percorsoRelativo + "' non esiste!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoRelativo))) {
            String linea;
            System.out.println("\nContenuto di " + percorsoRelativo + ":");
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Errore di lettura: " + e.getMessage());
        }
    }

    private String[] getAllRows(){
        ArrayList<String> righe = new ArrayList<>();
        Path path = Paths.get(percorsoRelativo);
        if (!Files.exists(path)) {
            System.out.println("Errore: Il file '" + percorsoRelativo + "' non esiste!");
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(percorsoRelativo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                righe.add(linea);
            }
        } catch (IOException e) {
            System.out.println("Errore di lettura: " + e.getMessage());
        }
        String[] righeArray = new String[righe.size()];
        for (int i = 0; i < righe.size(); i++) {
            righeArray[i] = righe.get(i);
        }
        return righeArray;
    }

    public boolean cercaMatch(String match, int colonna) {

        String[] righe = getAllRows();
        if (righe == null) return false;

        for (String riga : righe) {
          String[] campi = riga.split(",", -1);

           if (campi.length > colonna && campi[colonna].equals(match)) {
               return true;
           }
       }

    return false;
}


    public String getMatch(String match, int colonnaMatch, int colonnaGet) {

    String[] righe = getAllRows();
    if (righe == null) return null;

    for (String riga : righe) {

        String[] campi = riga.split(",", -1);

        // controllo colonna del match
        if (campi.length > colonnaMatch && campi[colonnaMatch].equals(match)) {

            // controllo colonna del valore da restituire
            if (campi.length > colonnaGet) {
                return campi[colonnaGet];
            }
        }
    }

    return null;
}


    private int getRowIndex(String match, int colonna) {
        String[] righe = getAllRows();
        if (righe == null) {
            return -1;
        }
        for (int i = 0; i < righe.length; i++) {
            if (righe[i].split(",")[colonna].equals(match)) {
                return i;
            }
        }
        return -1;
    }

    public String[] getRiga(String match, int colonna) {

    String[] righe = getAllRows();
    if (righe == null) return null;

    for (String riga : righe) {

        // spezza la riga per virgola
        String[] campi = riga.split(",", -1);


        // controlla che la colonna esista e confronta
        if (campi.length > colonna && campi[colonna].equals(match)) {
            return campi;
        }
    }

    return null;
}

    

    //comandi per gestione CSV
    public static String[] getByName(String path, String nome) throws CsvValidationException{
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (row.length > 0 && row[0].equalsIgnoreCase(nome)) {
                    return row;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getCoords(String path, String nome){
        String [] coords = new String[2];
        try {
            String[] temp = getByName(path, nome);
            if (temp != null && temp.length >= 2) {
                coords[0] = temp[5];
                coords[1] = temp[6];
                return coords;
            } else {
                System.out.println("Coordinate non trovate");
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
