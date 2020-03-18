package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardBundle {

    private final List<Card> cards = new ArrayList<>();

    private CardBundle() {
    }

    public static CardBundle emptyBundle() {
        return new CardBundle();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        return cards.stream()
                .mapToInt(Card::getNumber)
                .sum();
    }

    public boolean isNotEmpty() {
        return !cards.isEmpty();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int size() {
        return cards.size();
    }
}
