package second.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HandCards {
    private static final String DELIMITER = ", ";
    private static final int FIRST_INDEX = 0;

    private final List<Card> cards;

    public HandCards(final List<Card> cards) {
        this.cards = cards;
    }

    public HandCards() {
        this(new ArrayList<>());
    }

    public void drawCard(final Card drawCard) {
        cards.add(drawCard);
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public int calculateDefaultSum() {
        return cards.stream()
                .mapToInt(Card::extractScore)
                .sum();
    }

    public boolean isRightSize(int size) {
        return cards.size() == size;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getOneCard() {
        return cards.get(FIRST_INDEX);
    }

    @Override
    public String toString() {
        return cards.stream()
                .map(Card::toString)
                .collect(Collectors.joining(DELIMITER));
    }
}
