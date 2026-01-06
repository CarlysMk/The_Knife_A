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
import java.util.Scanner;

/**
 * Gestisce la creazione, visualizzazione e risposta alle recensioni.
 * <p>
 * Le recensioni vengono salvate in un file CSV e possono essere
 * inserite dagli utenti oppure visualizzate/gestite dai ristoratori.
 */
public class GestioneRecensioni {

    private static final String FILE_RECENSIONI = "data/Recensioni.csv";

    /**
     * Calcola il prossimo id disponibile leggendo il file recensioni.
     */
    private static int getNextId() {
        int lastId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] campi = linea.split(",");

                try { lastId = Integer.parseInt(campi[0]); }
                catch (Exception ignored) {}
            }

        } catch (Exception ignored) {}

        return lastId + 1;
    }

    //  RECUPERA USERNAME DA ID

    /**
     * Restituisce l'username di un utente usando il suo id.
     */
    private static String getUsernameById(int idUtente) {

        try (BufferedReader br = new BufferedReader(new FileReader("data/Utenti.csv"))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] c = riga.split(",", -1);

                if (c[0].equalsIgnoreCase("id")) continue;

                if (Integer.parseInt(c[0]) == idUtente)
                    return c[3];
            }

        } catch (Exception ignored) {}

        return "Utente sconosciuto";
    }

    //  UTENTE: LASCIA/AGGIORNA RECENSIONE

    /**
     * Permette all'utente di lasciare o aggiornare una recensione
     * per un determinato ristorante.
     */
    public static void lasciaRecensione(Scanner sc, int idRistorante, int idUtente) {

        try {
            int idRec = getNextId();

            System.out.print("Quante stelle (1-5): ");
            int stelle = Integer.parseInt(sc.nextLine());

            System.out.print("Inserisci commento: ");
            String commento = sc.nextLine();

            java.util.List<String> righe = new java.util.ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {

                String linea;
                boolean aggiornata = false;

                while ((linea = br.readLine()) != null) {

                    if (linea.trim().isEmpty()) {
                        righe.add(linea);
                        continue;
                    }

                    String[] c = linea.split(",", -1);

                    if (c[0].equalsIgnoreCase("id")) {
                        righe.add(linea);
                        continue;
                    }

                    if (Integer.parseInt(c[1]) == idRistorante &&
                        Integer.parseInt(c[2]) == idUtente) {

                        String risposta      = (c.length > 5) ? c[5] : "";
                        String idRistoratore = (c.length > 6) ? c[6] : "";

                        righe.add(
                            c[0] + "," +
                            idRistorante + "," +
                            idUtente + "," +
                            stelle + "," +
                            commento + "," +
                            risposta + "," +
                            idRistoratore
                        );

                        aggiornata = true;
                    } else {
                        righe.add(linea);
                    }
                }

                if (!aggiornata) {
                    righe.add(
                        idRec + "," +
                        idRistorante + "," +
                        idUtente + "," +
                        stelle + "," +
                        commento + "," +
                        "" + "," +
                        ""
                    );
                }
            }

            try (FileWriter fw = new FileWriter(FILE_RECENSIONI)) {
                for (String l : righe) fw.write(l + "\n");
            }

            System.out.println("Recensione salvata.");

        } catch (Exception e) {
            System.out.println("Errore durante inserimento recensione: " + e.getMessage());
        }
    }

    //  RISTORATORE: VISUALIZZA & RISPONDE

    /**
     * Consente al ristoratore di visualizzare le recensioni
     * dei propri ristoranti e inserire una risposta.
     */
    public static void rispondiRecensioni(Scanner sc, int idRistoratore) {

        try {

            // MOSTRA RISTORANTI DEL RISTORATORE
            System.out.println("\nI TUOI RISTORANTI:");

            java.util.List<String[]> mieiRistoranti = new java.util.ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader("data/Ristoranti.csv"))) {

                String riga;

                while ((riga = br.readLine()) != null) {

                    if (riga.trim().isEmpty()) continue;

                    String[] c = riga.split(",", -1);

                    if (c[0].equalsIgnoreCase("id")) continue;

                    if (Integer.parseInt(c[1]) == idRistoratore) {
                        mieiRistoranti.add(c);
                        System.out.println("- " + c[2]);
                    }
                }
            }

            if (mieiRistoranti.isEmpty()) {
                System.out.println("Non hai ristoranti registrati.");
                return;
            }

            System.out.print("\nSeleziona ristorante (scrivi il nome): ");
            String nomeRistorante = sc.nextLine();

            Integer idRistorante = null;

            for (String[] r : mieiRistoranti) {
                if (r[2].equalsIgnoreCase(nomeRistorante)) {
                    idRistorante = Integer.parseInt(r[0]);
                    break;
                }
            }

            if (idRistorante == null) {
                System.out.println("Ristorante non trovato tra i tuoi.");
                return;
            }

            // CARICA RECENSIONI DI QUEL RISTORANTE
            java.util.List<String[]> candidati = new java.util.ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    if (linea.trim().isEmpty()) continue;

                    String[] c = linea.split(",", -1);

                    if (c[0].equalsIgnoreCase("id")) continue;

                    if (Integer.parseInt(c[1]) == idRistorante) {
                        candidati.add(c);
                    }
                }
            }

            if (candidati.isEmpty()) {
                System.out.println("Nessuna recensione per questo ristorante.");
                return;
            }

            System.out.println("\n--- RECENSIONI ---");

            for (String[] c : candidati) {

                int idUtente = Integer.parseInt(c[2]);
                String username = getUsernameById(idUtente);

                System.out.println(
                    "#" + c[0] +
                    " | Utente: " + username +
                    " | Stelle: " + c[3] +
                    "\nCommento: " + c[4] +
                    (c[5].isBlank() ? "" : ("\nRisposta: " + c[5])) +
                    "\n"
                );
            }

            System.out.print("Scrivi il numero della recensione a cui vuoi rispondere: ");
            int idRecTarget = Integer.parseInt(sc.nextLine());

            java.util.List<String> righe = new java.util.ArrayList<>();

            try (BufferedReader br = new BufferedReader(new FileReader(FILE_RECENSIONI))) {

                String linea;

                while ((linea = br.readLine()) != null) {

                    if (linea.trim().isEmpty()) {
                        righe.add(linea);
                        continue;
                    }

                    String[] c = linea.split(",", -1);

                    if (c[0].equalsIgnoreCase("id")) {
                        righe.add(linea);
                        continue;
                    }

                    if (Integer.parseInt(c[0]) == idRecTarget) {

                        System.out.print("Scrivi risposta: ");
                        String risposta = sc.nextLine();

                        righe.add(
                            c[0] + "," +
                            c[1] + "," +
                            c[2] + "," +
                            c[3] + "," +
                            c[4] + "," +
                            risposta + "," +
                            idRistoratore
                        );

                        continue;
                    }

                    righe.add(linea);
                }
            }

            try (FileWriter fw = new FileWriter(FILE_RECENSIONI)) {
                for (String r : righe) fw.write(r + "\n");
            }

            System.out.println("Risposta salvata.");

        } catch (Exception e) {
            System.out.println("Errore gestione recensioni ristoratore: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
