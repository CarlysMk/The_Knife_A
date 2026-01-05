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

    // Legge l'ultimo ID e restituisce il prossimo
    private static int getNextId() {
        int lastId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_RISTORANTI))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] campi = line.split(",");

                try {
                    lastId = Integer.parseInt(campi[0]);
                } catch (NumberFormatException ignored) {}
            }
        } catch (IOException e) {
            // se il file non esiste, si parte da 1
        }

        return lastId + 1;
    }

    // Aggiunge un nuovo ristorante
    public static void aggiungiRistorante(Utente proprietario) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nazione: ");
        String nazione = scanner.nextLine();

        System.out.print("Citta: ");
        String citta = scanner.nextLine();

        System.out.print("Indirizzo: ");
        String indirizzo = scanner.nextLine();

        System.out.print("Latitudine: ");
        double latitudine = Double.parseDouble(scanner.nextLine());

        System.out.print("Longitudine: ");
        double longitudine = Double.parseDouble(scanner.nextLine());

        System.out.print("Prezzo medio: ");
        double prezzo = Double.parseDouble(scanner.nextLine());

        System.out.print("Delivery (true/false): ");
        boolean delivery = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Prenotazione online (true/false): ");
        boolean prenotazione = Boolean.parseBoolean(scanner.nextLine());

        System.out.print("Tipo cucina: ");
        String tipoCucina = scanner.nextLine();

        int id = getNextId();

        Ristorante r = new Ristorante(
                nome, nazione, citta, indirizzo,
                latitudine, longitudine,
                prezzo,
                delivery, prenotazione,
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


    private static double distanza(double lat1, double lon1, double lat2, double lon2) {
        double RADIUS = 6371;
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(deltaLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS * c;
    }

    public static ArrayList<Ristorante> getCoordinateRistoranti() {

        ArrayList<Ristorante> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/Ristoranti.csv"))) {

            String line;

            while ((line = br.readLine()) != null) {

                if (line.trim().isEmpty()) continue;

                String[] campi = line.split(",");

                int id = Integer.parseInt(campi[0]);
                double lat = Double.parseDouble(campi[5]);
                double lon = Double.parseDouble(campi[6]);

                lista.add(new Ristorante(id, lat, lon));
            }

        } catch (Exception e) {
            System.out.println("Errore lettura ristoranti: " + e.getMessage());
        }

        return lista;
    }



}
