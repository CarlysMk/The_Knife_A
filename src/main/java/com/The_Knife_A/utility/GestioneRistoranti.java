package com.The_Knife_A.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import com.The_Knife_A.models.Ristorante;
import com.The_Knife_A.models.Utente;

public class GestioneRistoranti {

    private static final String FILE_RISTORANTI = "data/Ristoranti.csv";

    // legge l'ultimo ID e restituisce il prossimo
    private static int getNextId() {
        int ultimoId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] campi = riga.split(",");

                try {
                    ultimoId = Integer.parseInt(campi[0]);
                } catch (NumberFormatException ignored) {}
            }

        } catch (IOException e) {}

        return ultimoId + 1;
    }

    // aggiunge un nuovo ristorante
    public static void aggiungiRistorante(Utente proprietario) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nazione: ");
        String nazione = scanner.nextLine();

        System.out.print("Città: ");
        String citta = scanner.nextLine();

        System.out.print("Indirizzo: ");
        String indirizzo = scanner.nextLine();

        System.out.print("Latitudine: ");
        double latitudine = Double.parseDouble(scanner.nextLine());

        System.out.print("Longitudine: ");
        double longitudine = Double.parseDouble(scanner.nextLine());

        System.out.print("Prezzo medio: ");
        double prezzoMedio = Double.parseDouble(scanner.nextLine());

        System.out.print("Delivery (true/false): ");
        boolean delivery = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Prenotazione online (true/false): ");
        boolean prenotazioneOnline = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Tipo cucina: ");
        String tipoCucina = scanner.nextLine();

        int id = getNextId();

        Ristorante r = new Ristorante(
                nome, nazione, citta, indirizzo,
                latitudine, longitudine,
                prezzoMedio,
                delivery, prenotazioneOnline,
                tipoCucina
        );

        try (FileWriter fw = new FileWriter(FILE_RISTORANTI, true)) {

            fw.write(
                id + "," +
                proprietario.getId() + "," +
                r.getNome() + "," +
                r.getNazione() + "," +
                r.getCitta() + "," +
                r.getIndirizzo() + "," +
                r.getLatitudine() + "," +
                r.getLongitudine() + "," +
                r.getPrezzoMedio() + "," +
                r.isDelivery() + "," +
                r.isPrenotazioneOnline() + "," +
                r.getTipoCucina() + "\n"
            );

            System.out.println("Ristorante aggiunto con ID: " + id);

        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del ristorante.");
        }
    }

    // recupera username partendo da id utente (per mostrare nelle recensioni)
    public static String getUsernameById(int idUtente) {

        try (BufferedReader br = new BufferedReader(new FileReader("data/Utenti.csv"))) {

            String riga;

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] c = riga.split(",", -1);

                if (c[0].equalsIgnoreCase("id")) continue;

                if (Integer.parseInt(c[0]) == idUtente) {
                    return c[1];   // username
                }
            }

        } catch (Exception ignored) {}

        return "Utente sconosciuto";
    }

    // cerca i tre ristoranti più vicini e permette di vedere i dettagli
    public static void cercaTrePiuVicini(Scanner sc, Utente utenteLoggato) {

        try {
            System.out.print("Inserisci la tua latitudine: ");
            double latUtente = Double.parseDouble(sc.nextLine());

            System.out.print("Inserisci la tua longitudine: ");
            double lonUtente = Double.parseDouble(sc.nextLine());

            BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI));

            String riga;

            String[] miglioriNomi = { "-", "-", "-" };
            int[] viciniId = { -1, -1, -1 };
            double[] miglioriDistanze = {
                Double.MAX_VALUE,
                Double.MAX_VALUE,
                Double.MAX_VALUE
            };

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] campi = riga.split(",", -1);

                if (campi[0].equalsIgnoreCase("id")) continue;

                String nome = campi[2];
                int id = Integer.parseInt(campi[0]);
                double lat = Double.parseDouble(campi[6]);
                double lon = Double.parseDouble(campi[7]);

                double distanza =
                    Math.pow(latUtente - lat, 2) +
                    Math.pow(lonUtente - lon, 2);

                for (int i = 0; i < 3; i++) {

                    if (distanza < miglioriDistanze[i]) {

                        for (int j = 2; j > i; j--) {
                            miglioriDistanze[j] = miglioriDistanze[j - 1];
                            miglioriNomi[j]     = miglioriNomi[j - 1];
                            viciniId[j]         = viciniId[j - 1];
                        }

                        miglioriDistanze[i] = distanza;
                        miglioriNomi[i]     = nome;
                        viciniId[i]         = id;
                        break;
                    }
                }
            }

            br.close();

            System.out.println("\nI 3 ristoranti più vicini:");
            System.out.println("1) " + miglioriNomi[0]);
            System.out.println("2) " + miglioriNomi[1]);
            System.out.println("3) " + miglioriNomi[2]);

            System.out.print("\nSeleziona un ristorante (1-3) oppure 0 per tornare indietro: ");
            int scelta = Integer.parseInt(sc.nextLine());

            if (scelta < 1 || scelta > 3) return;

            int idSelezionato = viciniId[scelta - 1];

            // carica scheda ristorante
            String[] campiSel = null;

            try (BufferedReader br2 = new BufferedReader(new FileReader(FILE_RISTORANTI))) {

                String riga2;

                while ((riga2 = br2.readLine()) != null) {

                    if (riga2.trim().isEmpty()) continue;

                    String[] c = riga2.split(",", -1);

                    if (!c[0].equalsIgnoreCase("id") &&
                        Integer.parseInt(c[0]) == idSelezionato) {

                        campiSel = c;
                        break;
                    }
                }

            } catch (Exception e) {
                System.out.println("Errore lettura dettagli ristorante.");
                return;
            }

            if (campiSel == null) {
                System.out.println("Ristorante non trovato.");
                return;
            }

            System.out.println("\n------ SCHEDA RISTORANTE ------");
            System.out.println("Nome: " + campiSel[2]);
            System.out.println("Nazione: " + campiSel[3]);
            System.out.println("Città: " + campiSel[4]);
            System.out.println("Indirizzo: " + campiSel[5]);
            System.out.println("Latitudine: " + campiSel[6]);
            System.out.println("Longitudine: " + campiSel[7]);
            System.out.println("Prezzo medio: " + campiSel[8]);
            System.out.println("Delivery: " + campiSel[9]);
            System.out.println("Prenotazione online: " + campiSel[10]);
            System.out.println("Tipo cucina: " + campiSel[11]);

            System.out.println("\n--- RECENSIONI ---");

            try (BufferedReader brRec = new BufferedReader(new FileReader("data/Recensioni.csv"))) {

                String lineaRec;
                boolean trovate = false;

                while ((lineaRec = brRec.readLine()) != null) {

                    if (lineaRec.trim().isEmpty()) continue;

                    String[] r = lineaRec.split(",", -1);

                    if (r[0].equalsIgnoreCase("id")) continue;
                    if (r.length < 5) continue;

                    int idRistoFile = Integer.parseInt(r[1]);

                    if (idRistoFile == idSelezionato) {

                        trovate = true;

                        int idUtente = Integer.parseInt(r[2]);
                        String username = getUsernameById(idUtente);

                        System.out.println("\nRecensione #" + r[0]);
                        System.out.println("Utente: " + username);
                        System.out.println("Stelle: " + r[3]);
                        System.out.println("Commento: " + r[4]);

                        if (r.length > 5 && !r[5].isBlank()) {
                            System.out.println("Risposta del ristoratore: " + r[5]);
                        }
                    }
                }

                if (!trovate)
                    System.out.println("Nessuna recensione disponibile.");

            } catch (Exception e) {
                System.out.println("Errore lettura recensioni: " + e.getMessage());
            }

            if (utenteLoggato == null) {
                System.out.println("\n0. Torna indietro");
                
            } else {
        System.out.println(
            "\n1. Aggiungi ai preferiti" +
            "\n2. Lascia una recensione" +
            "\n0. Torna indietro" +
            "\nScegli: "
        );
}


            int azione = Integer.parseInt(sc.nextLine());
            if (utenteLoggato == null) {
                return;
            } 
            else {
                switch (azione) {

                    case 1:
                        GestionePreferiti.aggiungiPreferito(
                            utenteLoggato.getId(),
                            idSelezionato
                    );
                        break;


                    case 2:
                        GestioneRecensioni.lasciaRecensione(
                            sc,
                            idSelezionato,
                            utenteLoggato.getId()
                        );
                        break;

                    case 0:
                        return;
                }
            }
        } catch (Exception e) {
            System.out.println("Errore nella ricerca: " + e.getMessage());
        }
    }
}
