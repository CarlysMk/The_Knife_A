/**************************************
 * Matricola    Cognome     Nome
 * 754320       Baracca     Filippo
 * 753747       Masolo      Carlos
 *
 * Sede: Como
 ***************************************/
package com.The_Knife_A.models;


/**
 * Rappresenta un ristorante presente nella piattaforma TheKnife.
 * <p>
 * Ogni ristorante contiene informazioni anagrafiche (nome e indirizzo),
 * geografiche (latitudine e longitudine), economiche (prezzo medio)
 * e funzionali (disponibilità di delivery e prenotazione online),
 * oltre al tipo di cucina offerto.
 */
public class Ristorante {

    private int id;
    private String nome, nazione, citta, indirizzo, tipoCucina;
    private double latitudine, longitudine, prezzoMedio;
    private boolean delivery, prenotazioneOnline;

    /**
     * Costruttore vuoto, utile per lettura/scrittura su file
     * o per utilizzo con eventuali framework.
     */
    public Ristorante() { }

    /**
     * Costruttore completo.
     *
     * @param nome nome del ristorante
     * @param nazione nazione del ristorante
     * @param citta città del ristorante
     * @param indirizzo indirizzo completo
     * @param latitudine coordinata geografica
     * @param longitudine coordinata geografica
     * @param prezzoMedio prezzo medio a persona
     * @param delivery true se è disponibile il servizio di consegna
     * @param prenotazioneOnline true se è possibile prenotare online
     * @param tipoCucina tipologia di cucina offerta
     */
    public Ristorante(String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine, double prezzoMedio, boolean delivery,
                      boolean prenotazioneOnline, String tipoCucina) {

        this.nome = nome;
        this.nazione = nazione;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.prezzoMedio = prezzoMedio;
        this.delivery = delivery;
        this.prenotazioneOnline = prenotazioneOnline;
        this.tipoCucina = tipoCucina;
    }

    /**
     * Restituisce l'identificativo del ristorante.
     *
     * @return id del ristorante
     */
    public int getId() { return id; }

    /**
     * Imposta l'identificativo del ristorante.
     *
     * @param id nuovo identificativo
     */
    public void setId(int id) { this.id = id; }

    /**
     * Restituisce il nome del ristorante.
     *
     * @return nome del ristorante
     */
    public String getNome() { return nome; }

    /**
     * Imposta il nome del ristorante.
     *
     * @param nome nuovo nome
     */
    public void setNome(String nome) { this.nome = nome; }

    /**
     * Restituisce la nazione del ristorante.
     *
     * @return nazione
     */
    public String getNazione() { return nazione; }

    /**
     * Imposta la nazione del ristorante.
     *
     * @param nazione nuova nazione
     */
    public void setNazione(String nazione) { this.nazione = nazione; }

    /**
     * Restituisce la città del ristorante.
     *
     * @return città
     */
    public String getCitta() { return citta; }

    /**
     * Imposta la città del ristorante.
     *
     * @param citta nuova città
     */
    public void setCitta(String citta) { this.citta = citta; }

    /**
     * Restituisce l'indirizzo del ristorante.
     *
     * @return indirizzo completo
     */
    public String getIndirizzo() { return indirizzo; }

    /**
     * Imposta l'indirizzo del ristorante.
     *
     * @param indirizzo nuovo indirizzo
     */
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    /**
     * Restituisce la latitudine del ristorante.
     *
     * @return latitudine
     */
    public double getLatitudine() { return latitudine; }

    /**
     * Imposta la latitudine del ristorante.
     *
     * @param latitudine nuova latitudine
     */
    public void setLatitudine(double latitudine) { this.latitudine = latitudine; }

    /**
     * Restituisce la longitudine del ristorante.
     *
     * @return longitudine
     */
    public double getLongitudine() { return longitudine; }

    /**
     * Imposta la longitudine del ristorante.
     *
     * @param longitudine nuova longitudine
     */
    public void setLongitudine(double longitudine) { this.longitudine = longitudine; }

    /**
     * Restituisce il prezzo medio per persona.
     *
     * @return prezzo medio in euro
     */
    public double getPrezzoMedio() { return prezzoMedio; }

    /**
     * Imposta il prezzo medio del ristorante.
     *
     * @param prezzoMedio nuovo prezzo medio
     */
    public void setPrezzoMedio(double prezzoMedio) { this.prezzoMedio = prezzoMedio; }

    /**
     * Indica se è disponibile il servizio di delivery.
     *
     * @return true se disponibile, false altrimenti
     */
    public boolean isDelivery() { return delivery; }

    /**
     * Imposta la disponibilità del servizio delivery.
     *
     * @param delivery true se disponibile
     */
    public void setDelivery(boolean delivery) { this.delivery = delivery; }

    /**
     * Indica se è disponibile la prenotazione online.
     *
     * @return true se disponibile, false altrimenti
     */
    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }

    /**
     * Imposta la disponibilità della prenotazione online.
     *
     * @param prenotazioneOnline true se disponibile
     */
    public void setPrenotazioneOnline(boolean prenotazioneOnline) {
        this.prenotazioneOnline = prenotazioneOnline;
    }

    /**
     * Restituisce il tipo di cucina del ristorante.
     *
     * @return tipo di cucina
     */
    public String getTipoCucina() { return tipoCucina; }

    /**
     * Imposta il tipo di cucina del ristorante.
     *
     * @param tipoCucina nuova tipologia di cucina
     */
    public void setTipoCucina(String tipoCucina) { this.tipoCucina = tipoCucina; }

    /**
     * Costruttore alternativo che crea un ristorante conoscendo solo id e coordinate.
     *
     * @param id identificativo univoco
     * @param latitudine coordinata geografica
     * @param longitudine coordinata geografica
     */
    public Ristorante(int id, double latitudine, double longitudine) {
        this.id = id;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }
}
