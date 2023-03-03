package domain.user;

import domain.Card;
import domain.CardNumber;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Participant {

    protected static final String DEALER_NAME = "딜러";
    protected final String name;
    protected final List<Card> cards = new ArrayList<>();

    public Participant(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getReadyCards() {
        if (cards.size() < 2) {
            throw new IllegalStateException();
        }
        return List.of(cards.get(0), cards.get(1));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int calculateScore() {
        int score = 0;
        for (Card card : cards) {
            score += card.getCardNumber().getScore();
        }
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isPlayer() {
        return !name.equals(DEALER_NAME);
    }

    public boolean hasAce() {
        return cards.stream().anyMatch((card) -> card.getCardNumber() == CardNumber.ACE);
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
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

