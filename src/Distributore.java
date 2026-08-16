import java.util.ArrayList;

public class Distributore {

    private ArrayList<Prodotto> prodotti;
    private double credito;

    public double getCredito() {
        return credito;
    }

    public Distributore() {
        prodotti = new ArrayList<>();
        credito = 0;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        prodotti.add(prodotto);
    }

    public void inserisciCredito(double importo) {
        credito = credito + importo;
    }

    public void mostraProdotti() {

        System.out.println("\n PRODOTTI ");

        for (Prodotto p : prodotti) {
            p.stampaProdotto();
        }


        System.out.println("Credito: €" + credito);
    }

    public Prodotto acquista(String nome)
            throws ProdottoEsauritoException, CreditoInsufficienteException {

        Prodotto prodottoScelto = null;

        // Cerco il prodotto scelto
        for (Prodotto p : prodotti) {

            if (p.getNome().equalsIgnoreCase(nome)) {
                prodottoScelto = p;
                break;
            }
        }

        // Se il prodotto non esiste
        if (prodottoScelto == null) {

            System.out.println("Prodotto non trovato.");

            return null;
        }

        // Se il prodotto è esaurito
        if (prodottoScelto.getQuantita() == 0) {

            // Cerco il PRIMO prodotto disponibile
            // con prezzo <= credito
            for (Prodotto p : prodotti) {

                if (p.getQuantita() > 0 &&
                        p.getPrezzo() <= credito) {

                    throw new ProdottoEsauritoException(
                            "Il prodotto " + prodottoScelto.getNome()
                                    + " è esaurito. "
                                    + "Ti consiglio: " + p.getNome()
                                    + " (€" + p.getPrezzo() + ")"
                    );
                }
            }

            throw new ProdottoEsauritoException(
                    "Il prodotto " + prodottoScelto.getNome()
                            + " è esaurito e non ci sono alternative."
            );
        }

        // Se il credito è insufficiente
        if (credito < prodottoScelto.getPrezzo()) {

            double mancante =
                    prodottoScelto.getPrezzo() - credito;

            throw new CreditoInsufficienteException(
                    "Credito insufficiente per "
                            + prodottoScelto.getNome(),
                    mancante
            );
        }

        // Acquisto
        prodottoScelto.diminuisciQuantita();

        credito = credito - prodottoScelto.getPrezzo();

        return prodottoScelto;
    }
}