/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
***************************************/

package com.The_Knife_A.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce la lista dei ristoranti preferiti degli utenti.
 * <p>
 * I preferiti sono memorizzati in un file CSV dove ogni riga
 * rappresenta un utente e contiene l'elenco dei ristoranti salvati.
 */
public class GestionePreferiti {

    private static final String FILE_PREFERITI = "data/Preferiti.csv";

    // CREA LA RIGA VUOTA PER UN NUOVO UTENTE

    /**
     * Inizializza la riga dei preferiti per un nuovo utente.
     * <p>
     * Crea una riga vuota nel file dei preferiti associata all'id utente.
     */
    public static void inizializzaPreferitiPerUtente(int idUtente) {

        try (FileWriter fw = new FileWriter(FILE_PREFERITI, true)) {

            // formato: idutente,
            fw.write(idUtente + ",\n");

            System.out.println("Preferiti inizializzati per utente ID: " + idUtente);

        } catch (Exception e) {
            System.out.println("Errore creazione riga preferiti.");
        }
    }

    // AGGIUNGE UN RISTORANTE AI PREFERITI

    /**
     * Aggiunge un ristorante ai preferiti dell'utente.
     * <p>
     * Se il ristorante è già presente, l'operazione viene ignorata.
     */
    public static void aggiungiPreferito(int idUtente, int idRistorante) {

        try {
            List<String> righe = new ArrayList<>();
            boolean aggiornata = false;

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_PREFERITI))) {

                String riga;

                while ((riga = br.readLine()) != null) {

                    if (riga.trim().isEmpty()) {
                        righe.add(riga);
                        continue;
                    }

                    String[] c = riga.split(",", -1);

                    // salta intestazione
                    if (c[0].equalsIgnoreCase("idutente")) {
                        righe.add(riga);
                        continue;
                    }

                    int idUtenteFile = Integer.parseInt(c[0]);

                    if (idUtenteFile == idUtente) {

                        // controllo se esiste già
                        boolean giaPresente = false;

                        for (int i = 1; i < c.length; i++) {
                            if (c[i].isBlank()) continue;
                            if (Integer.parseInt(c[i]) == idRistorante) {
                                giaPresente = true;
                                break;
                            }
                        }

                        if (giaPresente) {
                            System.out.println("Questo ristorante è già tra i preferiti.");
                            righe.add(riga);
                        } else {
                            // aggiungo in fondo
                            riga = riga + idRistorante + ",";
                            righe.add(riga);
                            System.out.println("Ristorante aggiunto ai preferiti!");
                        }

                        aggiornata = true;
                    } else {
                        righe.add(riga);
                    }
                }
            }

            // Nessuna riga trovata → ne creo una nuova
            if (!aggiornata) {
                righe.add(idUtente + "," + idRistorante + ",");
                System.out.println("Creato elenco preferiti e aggiunto il ristorante.");
            }

            // riscrive file
            try (FileWriter fw = new FileWriter(FILE_PREFERITI)) {
                for (String l : righe) fw.write(l + "\n");
            }

        } catch (Exception e) {
            System.out.println("Errore nell'aggiunta ai preferiti: " + e.getMessage());
        }
    }

    // MOSTRA I PREFERITI DI UN UTENTE

    /**
     * Mostra l'elenco dei ristoranti preferiti dell'utente.
     */
    public static void mostraPreferiti(int idUtente) {

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PREFERITI))) {

            String riga;
            boolean trovati = false;

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] c = riga.split(",", -1);

                if (c[0].equalsIgnoreCase("idutente")) continue;

                int idUtenteFile = Integer.parseInt(c[0]);

                if (idUtenteFile == idUtente) {

                    trovati = true;

                    System.out.println("\n--- I TUOI PREFERITI ---");

               for (int i = 1; i < c.length; i++) {

                    if (c[i].isBlank()) continue;

                    int idRisto = Integer.parseInt(c[i]);
                    String nome = getNomeRistorante(idRisto);

                    System.out.println("- " + nome);
                }
            }
            }

            if (!trovati) {
                System.out.println("Non hai ancora ristoranti preferiti.");
            }

        } catch (Exception e) {
            System.out.println("Errore lettura preferiti: " + e.getMessage());
        }
    }

    /**
     * Restituisce il nome del ristorante dato il suo id.
     * <p>
     * La ricerca avviene nel file dei ristoranti.
     */
    private static String getNomeRistorante(int idRistorante) {

        try (BufferedReader br = new BufferedReader(new FileReader("data/Ristoranti.csv"))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] c = riga.split(",", -1);

                if (c[0].equalsIgnoreCase("id")) continue;

                if (Integer.parseInt(c[0]) == idRistorante) {
                    return c[2];   // nome
                }
            }

        } catch (Exception ignored) {}

        return "Ristorante sconosciuto";
    }
}
