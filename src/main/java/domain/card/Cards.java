package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int FIRST_INDEX = 0;

    private List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculatePointAccordingToHasAce() {
        if (isBust() && hasAce()) {
            return calculatePoint() - 10;
        }
        return calculatePoint();
    }

    private int calculatePoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public boolean isBust() {
        return calculatePoint() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && isBlackJackPoint();
    }

    public boolean isBlackJackPoint() {
        return calculatePoint() == BLACK_JACK;
    }

    public boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }

    public String getCardsDrawResult() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(", "));
    }

    public List<Card> getCards() {
        return cards;
    }
}
