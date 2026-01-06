/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
 ***************************************/

package com.The_Knife_A.utility;

import java.util.Scanner;
import com.The_Knife_A.models.Utente;

/**
 * Gestisce le funzionalità riservate ai ristoratori.
 * <p>
 * Attraverso questa classe il ristoratore può inserire nuovi
 * ristoranti, visualizzare le recensioni ricevute e consultare
 * il riepilogo delle valutazioni complessive.
 * <p>
 * Viene richiamata dal menù utente quando il profilo loggato
 * possiede ruolo di ristoratore.
 */
public class GestioneRistoratore {

    /**
     * Mostra il menù principale del ristoratore.
     * <p>
     * Ogni opzione corrisponde a una delle operazioni di gestione
     * dei ristoranti associati all'utente.
     *
     * @param sc scanner per l'interazione a terminale
     * @param utente ristoratore attualmente loggato
     */
    public static void menuRistoratore(Scanner sc, Utente utente) {

        int scelta;

        do {
            System.out.println(
                "\n--- AREA RISTORATORE ---" +
                "\n1. Aggiungi ristorante" +
                "\n2. Visualizza riepilogo recensioni" +
                "\n3. Visualizza recensioni dettagliate" +
                "\n4. Rispondi alle recensioni" +
                "\n0. Torna al menu precedente" +
                "\n-------------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    GestioneRistoranti.aggiungiRistorante(sc, utente);
                    break;

                case 2:
                    GestioneRistoranti.visualizzaRiepilogo(utente);
                    break;

                case 3:
                    GestioneRistoranti.visualizzaRecensioni(utente);
                    break;

                case 4:
                    GestioneRecensioni.rispostaRecensioni(sc, utente);
                    break;

                case 0:
                    System.out.println("Ritorno al menu utente.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);
    }
}
