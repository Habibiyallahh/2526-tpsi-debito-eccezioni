public class CreditoInsufficienteException extends Exception {

    private double mancante;

    public CreditoInsufficienteException(String messaggio, double mancante) {
        super(messaggio);
        this.mancante = mancante;
    }

    public double getMancante() {
        return mancante;
    }
}