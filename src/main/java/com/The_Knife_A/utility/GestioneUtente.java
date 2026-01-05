package com.The_Knife_A.utility;

import java.util.Scanner;
import com.The_Knife_A.models.Utente;

public class GestioneUtente {

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
