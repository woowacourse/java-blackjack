package domain.participant;

import domain.card.BlackJackCards;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private final String name;
    private final BlackJackCards blackJackCards;
    private final Betting betting;

    protected Participant(String name, Betting betting) {
        this.name = name;
        this.blackJackCards = new BlackJackCards();
        this.betting = betting;
    }

    protected Participant(String name) {
        this.name = name;
        this.blackJackCards = new BlackJackCards();
        this.betting = new Betting(0);
    }

    public abstract boolean canPick();

    public abstract boolean isPlayer();

    public int getBettingAmount() {
        return betting.amount();
    }

    public String getName() {
        return name;
    }

    public boolean isBlackJack() {
        return blackJackCards.isBlackJack();
    }

    public boolean isBust(int value) {
        return blackJackCards.isBustBy(value);
    }

    public void addCard(Card card) {
        blackJackCards.add(card);
    }

    public abstract List<Card> getShownCard();

    public List<Card> getCards() {
        return blackJackCards.getCards();
    }

    public Card getFirstCard() {
        return blackJackCards.getFirstCard();
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
