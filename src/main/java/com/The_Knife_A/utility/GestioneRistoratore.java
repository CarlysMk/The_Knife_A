package com.The_Knife_A.utility;

import java.util.Scanner;
import com.The_Knife_A.models.Utente;

public class GestioneRistoratore {

    // menu principale ristoratore
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
