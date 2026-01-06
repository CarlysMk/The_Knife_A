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

/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747      Masolo      Carlos
 *              
 * Sede: Como
***************************************/
/**
 * Classe di utilità per la gestione dei file del progetto.
 * <p>
 * Fornisce operazioni di lettura e scrittura su file CSV,
 * oltre a funzioni di ricerca e recupero di dati specifici.
 */
public class GestioneFile {

    private String percorsoRelativo;

    /**
     * Inizializza il gestore associandolo a un percorso file.
     */
    public GestioneFile(String percorsoRelativo) {
        this.percorsoRelativo = percorsoRelativo;
    }

    /**
     * Scrive una riga nel file.
     * <p>
     * Se {@code append} è true, il contenuto viene aggiunto in coda.
     */
    public void scriviSuFile(String testo, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(percorsoRelativo, append))) {
            writer.write(testo);
            writer.newLine();
            System.out.println("Scrittura completata su: " + percorsoRelativo);
        } catch (IOException e) {
            System.out.println("Errore di scrittura: " + e.getMessage());
        }
    }

    /**
     * Legge e stampa il contenuto del file associato.
     * <p>
     * Se il file non esiste, viene mostrato un messaggio di errore.
     */
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

    /**
     * Restituisce tutte le righe del file come array di stringhe.
     * Metodo di supporto per le altre funzioni di ricerca.
     */
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

    /**
     * Verifica se una determinata stringa è presente
     * in una specifica colonna del file.
     */
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

    /**
     * Restituisce il valore di una colonna,
     * individuando prima una riga tramite un'altra colonna.
     */
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

    /**
     * Restituisce l'intera riga corrispondente
     * alla condizione di ricerca indicata.
     */
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

    /**
     * Restituisce la riga intera (come array di stringhe)
     * che contiene il valore ricercato nella colonna specificata.
     */
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

    // comandi per gestione CSV

    /**
     * Restituisce una riga dal file CSV in base al nome fornito.
     */
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

    /**
     * Recupera le coordinate (latitudine e longitudine)
     * associate al nome indicato nel file CSV.
     */
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
