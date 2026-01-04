package com.The_Knife_A.utility;

import java.util.Scanner;
import com.The_Knife_A.models.Utente;

// Classe che gestisce il menu e le azioni per l'utente normale
public class GestioneUtente {

    public static void menu(Scanner sc, Utente u) {

        int scelta;

        do {
            System.out.println(
                "\n--- MENU UTENTE ---" +
                "\n1. Cerca ristorante" +
                "\n2. Inserisci recensione" +
                "\n0. Logout" +
                "\n-------------------" +
                "\nScegli: "
            );

            scelta = Integer.parseInt(sc.nextLine());

            switch (scelta) {

                case 1:
                    System.out.println("Funzione ricerca ristoranti (in sviluppo)");
                    break;

                case 2:
                    System.out.println("Funzione recensioni (in sviluppo)");
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
