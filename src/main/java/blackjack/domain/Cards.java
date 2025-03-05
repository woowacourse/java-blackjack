package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(List<Card> cards) {
        this.cards = cards;
    }

    public static Cards initialize() {
        return new Cards(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public void add(List<Card> drawnCards) {
        cards.addAll(drawnCards);
    }

    public int calculateSum() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public int getSize() {
        return cards.size();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
