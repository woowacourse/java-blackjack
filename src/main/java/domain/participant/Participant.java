package domain.participant;

import domain.card.BlackJackCards;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final String name;
    private final BlackJackCards blackJackCards;
    private Betting betting;

    protected Participant(String name, BlackJackCards blackJackCards) {
        this.name = name;
        this.blackJackCards = blackJackCards;
    }

    public abstract boolean canPick();

    public abstract boolean isPlayer();

    public int getBettingAmount() {
        return betting.amount();
    }

    public void startBetting(int bettingAmount) {
        this.betting = new Betting(bettingAmount);
    }

    public String getName() {
        return name;
    }

    public boolean isBlackJack() {
        return blackJackCards.decideBlackJackByTotalValue();
    }

    public boolean isBust(int value) {
        return blackJackCards.decideBustByValue(value);
    }

    public void addCard(Card card) {
        blackJackCards.add(card);
    }

    public abstract List<Card> getShownCard();

    public List<Card> getCards() {
        return blackJackCards.getCards();
    }

    public Card getFirstCard() {
        return blackJackCards.findFirstCard();
    }

    public int getTotalValue() {
        return blackJackCards.getTotalValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
            "name='" + name + '\'' +
            ", cards=" + blackJackCards +
            '}';
    }
}
