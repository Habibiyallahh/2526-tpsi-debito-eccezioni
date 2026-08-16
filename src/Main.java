import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Distributore distributore = new Distributore();

        distributore.aggiungiProdotto( new Prodotto("Patatine", 1.50, 5));

        distributore.aggiungiProdotto( new Prodotto("Coca Cola", 2.00, 0));

        distributore.aggiungiProdotto( new Prodotto("Cioccolato", 1.00, 5));

        boolean continua = true;

        while (continua) {

            distributore.mostraProdotti();

            System.out.println("\ncredito: " + distributore.getCredito() + " Aggiungi credito:");
            double credito = scanner.nextDouble();

            distributore.inserisciCredito(credito);

            scanner.nextLine();

            System.out.println("Inserisci il prodotto:");
            String nome = scanner.nextLine();

            try {

                Prodotto prodotto = distributore.acquista(nome);

                if (prodotto != null) {

                    System.out.println(
                            "Acquisto effettuato: "
                                    + prodotto.getNome()
                    );
                }

            } catch (ProdottoEsauritoException e) {

                System.out.println(e.getMessage());

            } catch (CreditoInsufficienteException e) {

                System.out.println(e.getMessage());

                System.out.println(
                        "Ti mancano €" + e.getMancante()
                );
            }

            System.out.println("\nVuoi fare un altro acquisto? si o no");

            String risposta = scanner.nextLine();

            if (risposta.equalsIgnoreCase("no")) {
                continua = false;
            }
        }

        System.out.println("Grazie per aver utilizzato il distributore!");

        scanner.close();
    }
}