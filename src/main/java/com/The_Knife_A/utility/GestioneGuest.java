package com.The_Knife_A.utility;

import java.util.Scanner;

/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
 ***************************************/

/**
 * Gestisce l’area dedicata agli utenti guest (non registrati).
 * <p>
 * Permette di accedere alle funzioni di sola consultazione,
 * senza possibilità di modificare dati o inserire contenuti.
 * Viene richiamata dal menù principale quando l’utente sceglie
 * di continuare senza effettuare il login.
 */
// Menu per utenti Guest (non registrati)
public class GestioneGuest {

    /**
     * Mostra il menu principale dell'area guest e gestisce le scelte inserite.
     * <p>
     * Il ciclo rimane attivo finché l’utente non seleziona l’uscita.
     *
     * @param sc oggetto Scanner utilizzato per leggere l’input da tastiera
     */
    public static void menu(Scanner sc) {

        int scelta;

        do {
            System.out.println(
                "\n--- AREA GUEST ---" +
                "\n1. Cerca ristorante" +
                "\n0. Torna al menu principale" +
                "\n---------------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    System.out.println("Funzione ricerca ristoranti (in sviluppo)");
                    break;

                case 0:
                    System.out.println("Ritorno al menu principale.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);
    }
}
