package blackjack.domain;

public class GamePlayer {
    private final Players players;
    private final Dealer dealer;

    public GamePlayer(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
    }

    public void giveCardToDealer(Card card) {
        dealer.addCard(card);
    }

    public void giveCardToPlayerByIndex(int i, Card card) {
        players.addCardToPlayer(i, card);
    }
}
