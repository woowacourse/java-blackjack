package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {
    private final String name;
    private final List<Card> cards = new ArrayList<>();

    protected Gamer(String name) {
        validateSpace(name);
        this.name = name;
    }

    private static void validateSpace(String name) {
        if(name.contains(" ")) {
            throw new IllegalArgumentException("이름에 공백이 포함됩니다.");
        }
    }

    protected void receiveCard(Card card) {
        cards.add(card);
    }

    protected int calculatePoint() {
        int point = 0;
        for(Card card : cards) {
            point = card.addPoint(point);
        }
        return point;
    }

    protected String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer gamer = (Gamer) o;
        return Objects.equals(name, gamer.name) && Objects.equals(cards, gamer.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }
}
