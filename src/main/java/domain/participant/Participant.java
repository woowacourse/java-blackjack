package domain.participant;

import domain.card.Card;
import domain.card.CardBundle;
import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final PlayerName playerName;
    private final CardBundle cardBundle;

    protected Participant(String name) {
        this.playerName = new PlayerName(name);
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

    public boolean isBlackjack() {
        return cardBundle.isBlackjack();
    }

    public boolean isBust() {
        return cardBundle.isBust();
    }

    public CardBundle getCardBundle() {
        return cardBundle;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(playerName, that.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(playerName);
    }
}
