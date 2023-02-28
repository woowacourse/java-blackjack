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
        giveCardToDealer();
        for (Player player : players.getPlayers()) {
            player.receiveCard(deck.drawCard());
            player.receiveCard(deck.drawCard());
        }
    }

    private void giveCardToDealer() {
        Card card = deck.drawCard();
        dealer.receiveCard(card);
        dealer.receiveCard(card);
    }

}
