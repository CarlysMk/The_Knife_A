package com.The_Knife_A;

import java.util.Scanner;

import com.The_Knife_A.models.Utente;
import com.The_Knife_A.utility.GestioneFile;
import com.The_Knife_A.utility.GestioneRistoranti;   

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestioneFile fileUtenti = new GestioneFile("data/Utenti.csv");

        int scelta;
        String nome, cognome, username, password, dataNascita, domicilio, ruolo;
        Utente utente;

        while (true) {
            System.out.println("----------------------------------" +
                    "\nBenvenuto in The Knife" +
                    "\n1. Login" +
                    "\n2. Registrazione utente" +
                    "\n3. Guest" +
                    "\n0. Esci" +
                    "\n----------------------------------" +
                    "\nScegli un'opzione: ");

            if (sc.hasNextInt()) {
                scelta = sc.nextInt();
                sc.nextLine();

                switch (scelta) {
                    case 1:
                     System.out.println("Login\n----------------------------------");

                     System.out.println("inserisci username");
                         username = sc.nextLine();

                     System.out.println("inserisci password");
                        password = sc.nextLine();

                    // creo l'oggetto Utente per il login
                     Utente temp = new Utente(username, password);

                     // se il ruolo è stato caricato, il login è andato bene
                   if (temp.getRuolo() != null) {

                    // controllo il ruolo
                     if (temp.getRuolo().equals("ristoratore")) {
                        System.out.println("\nSei un ristoratore — entri nella tua area.");
                        com.The_Knife_A.utility.GestioneRistoratore.menu(sc, temp);
                      } else {
                        System.out.println("\nSei un utente — entri nella tua area.");
                        com.The_Knife_A.utility.GestioneUtente.menu(sc, temp);

                       }

                     } else {
                        System.out.println("Login non riuscito.");
                    }
                            break;

                    case 2:
                        System.out.println("Registrazione nuovo utente");

                        System.out.println("Inserisci nome");
                        nome = sc.nextLine();
                        System.out.println("Inserisci cognome");
                        cognome = sc.nextLine();
                        System.out.println("Inserisci username");
                        username = sc.nextLine();

                        if (fileUtenti.cercaMatch(username, 3)) {
                            System.out.println("Username già esistente, riprova");
                            break;
                        } else {
                            System.out.println("Username disponibile");

                        }

                        System.out.println("Inserisci password");
                        password = sc.nextLine();
                        System.out.println("Inserisci data di nascita");
                        dataNascita = sc.nextLine();

                        System.out.println("Inserisci domicilio (città o indirizzo)");
                        domicilio = sc.nextLine();


                        System.out.println("Sei un ristoratore? (1. Si / 2. No)");

                        switch (Integer.parseInt(sc.nextLine())) {
                            case 1:
                                ruolo = "ristoratore";
                                break;
                            case 2:
                                ruolo = "utente";
                                break;
                            default:
                                System.out.println("Opzione non valida");
                                continue;
                        }

                        utente = new Utente(nome, cognome, username, password, dataNascita, domicilio, ruolo);
                        System.out.println("Registrazione completata!");
                        System.out.println("Benvenuto " + utente.getUsername());
                        System.out.println("Effettua nuovamente il login dalla tab di inizio");
                        break;

                    case 3:
                        System.out.println("Accesso come Guest!");
                        com.The_Knife_A.utility.GestioneGuest.menu(sc);
                        break;


                    case 0:
                        System.out.println("Uscita dal programma...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Opzione non valida");
                }
            } else {
                System.out.println("Errore: Inserisci un numero valido!");
                sc.nextLine();
            }
        }
    }
}
