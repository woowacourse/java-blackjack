public class Game {

    private final CardDistributor cardDistributor;

    public Game(CardDistributor cardDistributor) {
        this.cardDistributor = cardDistributor;
    }

    public void dealerDrawsCardsUntilDone(Dealer dealer) {
        while (!dealer.isDealerDone()) {
            cardDistributor.distributeCardToDealer(dealer);
        }
    }
}
