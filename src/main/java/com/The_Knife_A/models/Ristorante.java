// Classe che rappresenta un ristorante
public class Ristorante {

    private String nome;
    private String nazione;
    private String citta;
    private String indirizzo;

    private double latitudine;
    private double longitudine;

    private double prezzoMedio;

    private boolean delivery;
    private boolean prenotazioneOnline;

    private String tipoCucina;

    // Costruttore vuoto
    public Ristorante() {
    }

    // Costruttore completo
    public Ristorante(String nome, String nazione, String citta, String indirizzo,
                      double latitudine, double longitudine,
                      double prezzoMedio,
                      boolean delivery, boolean prenotazioneOnline,
                      String tipoCucina) {

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

    // Getter e setter (semplici)

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNazione() { return nazione; }
    public void setNazione(String nazione) { this.nazione = nazione; }

    public String getCitta() { return citta; }
    public void setCitta(String citta) { this.citta = citta; }

    public String getIndirizzo() { return indirizzo; }
    public void setIndirizzo(String indirizzo) { this.indirizzo = indirizzo; }

    public double getLatitudine() { return latitudine; }
    public void setLatitudine(double latitudine) { this.latitudine = latitudine; }

    public double getLongitudine() { return longitudine; }
    public void setLongitudine(double longitudine) { this.longitudine = longitudine; }

    public double getPrezzoMedio() { return prezzoMedio; }
    public void setPrezzoMedio(double prezzoMedio) { this.prezzoMedio = prezzoMedio; }

    public boolean isDelivery() { return delivery; }
    public void setDelivery(boolean delivery) { this.delivery = delivery; }

    public boolean isPrenotazioneOnline() { return prenotazioneOnline; }
    public void setPrenotazioneOnline(boolean prenotazioneOnline) { this.prenotazioneOnline = prenotazioneOnline; }

    public String getTipoCucina() { return tipoCucina; }
    public void setTipoCucina(String tipoCucina) { this.tipoCucina = tipoCucina; }
}
