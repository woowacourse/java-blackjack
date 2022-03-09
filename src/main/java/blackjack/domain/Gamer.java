package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Gamer {

    private static final int MAX_SCORE = 21;

    private final Name name;
    private final List<Card> cards;

    public Gamer(Name name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public void hit(Card card) {
        cards.add(card);
    }

    public boolean isValidRange() {
        return getScore() < MAX_SCORE;
    }

    public Name getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return cards.stream()
                .mapToInt(value -> value.getCardNumber().getValue())
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Gamer)) {
            return false;
        }
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "name=" + name +
                ", cards=" + cards +
                '}';
    }
}
