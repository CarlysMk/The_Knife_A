package com.The_Knife_A.utility;

import java.util.Scanner;
import com.The_Knife_A.models.Utente;
/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
 ***************************************/
/**
 * Gestisce le funzioni disponibili ai ristoratori.
 * <p>
 * Da questo menù il ristoratore può creare nuovi ristoranti
 * e gestire le recensioni ricevute sulle proprie attività.
 */
public class GestioneRistoratore {

    // menu principale ristoratore

    /**
     * Mostra il menù dedicato al ristoratore e gestisce le operazioni selezionate.
     * <p>
     * Il ciclo rimane attivo finché l’utente non effettua il logout.
     *
     * @param sc scanner utilizzato per l’interazione con l’utente
     * @param u  ristoratore attualmente loggato
     */
    public static void menu(Scanner sc, Utente u) {

        int scelta;

        do {
            System.out.println(
                    "\n--- MENU RISTORATORE ---" +
                    "\n1. Crea ristorante" +
                    "\n2. Visualizza Recensioni" +
                    "\n0. Logout" +
                    "\n------------------------" +
                    "\nScegli: ");

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    GestioneRistoranti.aggiungiRistorante(u);
                    break;

                case 2:
                    GestioneRecensioni.rispondiRecensioni(sc, u.getId());
                    break;

                case 0:
                    System.out.println("Logout effettuato.");
                    break;
            }

        } while (scelta != 0);
    }
}
