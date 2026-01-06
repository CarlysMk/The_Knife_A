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
 * Gestisce le funzionalità disponibili agli utenti registrati.
 * <p>
 * Dopo il login, permette all’utente di accedere alle operazioni
 * principali, come la ricerca dei ristoranti vicini e la gestione
 * dei preferiti.
 */
public class GestioneUtente {

    /**
     * Mostra il menù dell’utente autenticato e gestisce le scelte effettuate.
     * <p>
     * Le opzioni disponibili dipendono dalle funzionalità attive
     * per l’utente registrato.
     *
     * @param sc scanner utilizzato per l’interazione a terminale
     * @param u  utente attualmente loggato
     */
    public static void menu(Scanner sc, Utente u) {

        int scelta;

        do {
            System.out.println(
                "\n--- MENU UTENTE ---" +
                "\n1. Cerca ristorante vicino" +
                "\n2. Visualizza i ristoranti preferiti" +
                "\n0. Logout" +
                "\n-------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    GestioneRistoranti.cercaTrePiuVicini(sc, u);
                    break;

                case 2:
                    GestionePreferiti.mostraPreferiti(u.getId());
                    break;

                case 0:
                    System.out.println("Logout effettuato.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);
    }
}
