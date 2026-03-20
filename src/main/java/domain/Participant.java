package domain;

import java.util.List;

public abstract class Participant {
    private final PlayerName playerName;
    private final CardBundle cardBundle;

    protected Participant(PlayerName playerName) {
        this.playerName = playerName;
        this.cardBundle = new CardBundle();
    }

    public abstract boolean canHit();

    public void addCard(Card card) {
        cardBundle.addCard(card);
    }

    public List<Card> getCards() {
        return cardBundle.getCards();
    }

    public String getName() {
        return playerName.getName();
    }

    public int getScore() {
        return cardBundle.calculateScore();
    }

    public boolean getBlackJack() {
        return cardBundle.isBlackjack();
    }
}
