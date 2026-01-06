/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
 ***************************************/

package com.The_Knife_A.utility;

import java.util.Scanner;

/**
 * Gestisce l'interazione con gli utenti ospiti (non registrati).
 * <p>
 * Questa classe offre le funzionalità di base per chi accede
 * senza effettuare login. Gli utenti guest possono consultare
 * i ristoranti disponibili, ma non possono inserire recensioni
 * o accedere alle funzionalità riservate agli utenti registrati.
 * <p>
 * Viene richiamata dal menù principale quando l'utente decide
 * di proseguire come ospite.
 */
public class GestioneGuest {

    /**
     * Visualizza il menu dedicato all'utente guest.
     * <p>
     * Attraverso questo menu l'utente può accedere alle funzioni
     * di sola consultazione. Il ciclo rimane attivo finché non viene
     * scelta l'opzione di ritorno al menù principale.
     *
     * @param sc oggetto {@link Scanner} utilizzato per leggere l'input da tastiera
     */
    public static void menu(Scanner sc) {

        int scelta;

        do {
            System.out.println(
                "\n--- AREA GUEST ---" +
                "\n1. Cerca ristorante vicino" +
                "\n0. Torna al menu principale" +
                "\n---------------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    cercaTrePiuViciniGuest(sc);
                    break;

                case 0:
                    System.out.println("Ritorno al menu principale.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);
    }

    /**
     * Mostra i tre ristoranti più vicini al luogo indicato dall'utente.
     * <p>
     * Il metodo richiama la funzione di ricerca della classe
     * {@link GestioneRistoranti} in modalità sola lettura.
     * <p>
     * È utilizzato esclusivamente nell'area guest e non consente
     * ulteriori operazioni sui risultati ottenuti.
     *
     * @param sc scanner utilizzato per acquisire i dati inseriti dall'utente
     */
    private static void cercaTrePiuViciniGuest(Scanner sc) {

        try {
            System.out.println("\nModalità ospite: puoi solo visualizzare i ristoranti.");
            GestioneRistoranti.cercaTrePiuVicini(sc, null);

        } catch (Exception e) {
            System.out.println("Errore durante la ricerca in modalità guest.");
        }
    }
}
