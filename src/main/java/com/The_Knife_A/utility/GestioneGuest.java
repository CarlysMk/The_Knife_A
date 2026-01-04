package com.The_Knife_A.utility;

import java.util.Scanner;

// Menu per utenti Guest (non registrati)
public class GestioneGuest {

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
