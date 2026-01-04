package com.The_Knife_A.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.The_Knife_A.models.Ristorante;   // import della classe Ristorante

// Classe per la gestione dei ristoranti
public class GestioneRistoranti {

    // Metodo per aggiungere un nuovo ristorante
    public static void aggiungiRistorante() {

        Scanner scanner = new Scanner(System.in);

        // Chiedo i dati del ristorante
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

        // Creo l'oggetto ristorante
        Ristorante r = new Ristorante(
                nome, nazione, citta, indirizzo,
                latitudine, longitudine,
                prezzo,
                delivery, prenotazione,
                tipoCucina
        );

        // Scrivo il ristorante nel file (in append)
        try (FileWriter fw = new FileWriter("data/Ristoranti.txt", true)) {

            // Riga in formato CSV semplice
            fw.write(
                    r.getNome() + ";" +
                    r.getNazione() + ";" +
                    r.getCitta() + ";" +
                    r.getIndirizzo() + ";" +
                    r.getLatitudine() + ";" +
                    r.getLongitudine() + ";" +
                    r.getPrezzoMedio() + ";" +
                    r.isDelivery() + ";" +
                    r.isPrenotazioneOnline() + ";" +
                    r.getTipoCucina() + "\n"
            );

            System.out.println("Ristorante aggiunto correttamente.");

        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio del ristorante.");
        }
    }
}
