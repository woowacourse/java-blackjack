package blackjack.domain;

public class Game {
    private final Deck deck;
    private final GamePlayer gamePlayer;

    public Game(Deck deck, GamePlayer gamePlayer) {
        this.deck = deck;
        this.gamePlayer = gamePlayer;
    }

    public boolean isHitPlayerByIndex(int i) {
        return gamePlayer.isHitPlayerByIndex(i);
    }

    public boolean isHitDealer() {
        return gamePlayer.isHitDealer();
    }

    public void giveCardToPlayerByIndex(int i, Card card) {
        gamePlayer.giveCardToPlayerByIndex(i, card);
    }

    public void giveCardToDealer(Card card) {
        gamePlayer.giveCardToDealer(card);
    }

    public Players getPlayers() {
        return gamePlayer.getPlayers();
    }

    public Dealer getDealer() {
        return gamePlayer.getDealer();
    }
}
