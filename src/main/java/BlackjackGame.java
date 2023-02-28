public class BlackjackGame {
    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackjackGame(Players players){
        this.deck = new Deck();
        this.dealer = new Dealer();

        this.players = players;
    }

    public void giveInitCards() {
        giveCardsToDealer();
        giveCardsToPlayers();
    }

    private void giveCardsToPlayers() {
        for (Player player : players.getPlayers()) {
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
        }
    }

    private void giveCardsToDealer() {
        dealer.receiveCard(deck.drawCard());
        dealer.receiveCard(deck.drawCard());
    }

}
