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
 * Gestisce le operazioni disponibili agli utenti registrati.
 * <p>
 * Questa classe si occupa della fase di login e delle funzioni
 * accessibili dopo l'autenticazione. In base al ruolo assegnato
 * all'utente (cliente o ristoratore), vengono abilitate le
 * relative funzionalità del sistema.
 * <p>
 * È richiamata dal menù principale quando l'utente decide
 * di effettuare l'accesso alla piattaforma.
 */
public class GestioneUtente {

    /**
     * Gestisce la procedura di login.
     * <p>
     * Richiede le credenziali, verifica la correttezza dei dati
     * e, in caso positivo, restituisce l'oggetto {@link Utente}
     * associato all'account.
     *
     * @param sc scanner utilizzato per leggere i dati inseriti
     * @return utente autenticato oppure {@code null} se il login fallisce
     */
    public static Utente login(Scanner sc) {

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        Utente utente = GestioneFile.cercaUtente(username, password);

        if (utente == null) {
            System.out.println("Credenziali errate.");
        }

        return utente;
    }

    /**
     * Mostra il menù principale dell'utente autenticato.
     * <p>
     * Le opzioni disponibili dipendono dal ruolo:
     * <ul>
     *     <li>cliente: preferiti e recensioni</li>
     *     <li>ristoratore: gestione ristoranti e recensioni ricevute</li>
     * </ul>
     *
     * @param sc scanner per l'interazione
     * @param utente utente attualmente loggato
     */
    public static void menuUtente(Scanner sc, Utente utente) {

        int scelta;

        do {
            System.out.println(
                "\n--- AREA UTENTE ---" +
                "\n1. Funzioni cliente" +
                "\n2. Funzioni ristoratore" +
                "\n0. Logout" +
                "\n-------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    GestionePreferiti.menuCliente(sc, utente);
                    break;

                case 2:
                    GestioneRistoratore.menuRistoratore(sc, utente);
                    break;

                case 0:
                    System.out.println("Logout eseguito.");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);
    }
}
