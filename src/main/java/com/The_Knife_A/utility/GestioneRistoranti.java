package com.The_Knife_A.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.The_Knife_A.models.Ristorante;
import com.The_Knife_A.models.Utente;

public class GestioneRistoranti {

    private static final String FILE_RISTORANTI = "data/Ristoranti.csv";

    // Ritorna il prossimo ID disponibile leggendo l'ultimo nel file
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

        } catch (IOException e) {
            // se il file non esiste parto da 0
        }

        return ultimoId + 1;
    }


    // Inserimento di un nuovo ristorante da parte del ristoratore
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

            // Scrive una nuova riga CSV
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


    // ---------------------------------
    // CERCA i tre ristoranti più vicini
    // (mostra solo i nomi)
    // ---------------------------------
    public static void cercaTrePiuVicini(Scanner sc) {

        try {
            System.out.print("Inserisci la tua latitudine: ");
            double latUtente = Double.parseDouble(sc.nextLine());

            System.out.print("Inserisci la tua longitudine: ");
            double lonUtente = Double.parseDouble(sc.nextLine());

            BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI));

            String riga;

            // migliori tre risultati
            String[] miglioriNomi = { "-", "-", "-" };
            double[] miglioriDistanze = {
                    Double.MAX_VALUE,
                    Double.MAX_VALUE,
                    Double.MAX_VALUE
            };

            while ((riga = br.readLine()) != null) {

                if (riga.trim().isEmpty()) continue;

                String[] campi = riga.split(",", -1);

                // salta intestazione
                if (campi[0].equalsIgnoreCase("id")) continue;

                String nome = campi[2];
                double lat = Double.parseDouble(campi[6]);
                double lon = Double.parseDouble(campi[7]);

                // distanza semplificata (basta per il ranking)
                double distanza =
                        Math.pow(latUtente - lat, 2) +
                        Math.pow(lonUtente - lon, 2);

                // inserisce ordinando i 3 migliori
                for (int i = 0; i < 3; i++) {
                    if (distanza < miglioriDistanze[i]) {

                        for (int j = 2; j > i; j--) {
                            miglioriDistanze[j] = miglioriDistanze[j - 1];
                            miglioriNomi[j]      = miglioriNomi[j - 1];
                        }

                        miglioriDistanze[i] = distanza;
                        miglioriNomi[i]     = nome;
                        break;
                    }
                }
            }

            br.close();

            System.out.println("\nI 3 ristoranti più vicini:");
            System.out.println("1) " + miglioriNomi[0]);
            System.out.println("2) " + miglioriNomi[1]);
            System.out.println("3) " + miglioriNomi[2]);

        } catch (Exception e) {
            System.out.println("Errore nella ricerca: " + e.getMessage());
        }
    }
}
