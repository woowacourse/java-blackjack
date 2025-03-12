package domain.participant;

import domain.Money;
import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public abstract class Participant {
    private final Cards ownedCards;
    private final Money totalWinnings;

    public Participant() {
        this.ownedCards = Cards.of();
        this.totalWinnings = Money.of(Money.DEFAULT_AMOUNT);
    }

    public void receive(Card card) {
        ownedCards.add(card);
    }

    public int getScore() {
        return ownedCards.calculateScore();
    }

    public int getCardCount() {
        return ownedCards.getSize();
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }

    public int getTotalWinnings() {
        return totalWinnings.getAmount();
    }

    protected void increaseAmount(int amount) {
        totalWinnings.increase(amount);
    }

    protected void decreaseAmount(int amount) {
        totalWinnings.decrease(amount);
    }
}
