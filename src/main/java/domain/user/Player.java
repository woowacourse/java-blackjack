package domain.user;

import domain.card.Card;

public class Player {

    private final PlayerName playerName;
    private final Hand hand;

    public Player(String playerName, Hand hand) {
        this.playerName = new PlayerName(playerName);
        this.hand = hand;
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean hasSameNameWith(String name) {
        return playerName.isSameWith(name);
    }

    public int sumCardPool() {
        return hand.sumCardNumbers();
    }

    public boolean isOverBlackjack() {
        return hand.isOverBlackjack();
    }

    public PlayerName getPlayerName() {
        return playerName;
    }

    public Hand getCardPool() {
        return hand;
    }
}
