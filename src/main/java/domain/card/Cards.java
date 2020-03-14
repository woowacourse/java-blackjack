package domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    public static final String COMMA = ", ";
    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_SIZE = 2;
    private static final int FIRST_INDEX = 0;
    private static final int TEN = 10;

    private List<Card> cards;

    public Cards() {
        cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculatePointAccordingToHasAce() {
        if (areBust() && hasAce()) {
            return calculatePoint() - TEN;
        }
        return calculatePoint();
    }

    private int calculatePoint() {
        return cards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    public boolean areBust() {
        return calculatePoint() > BLACK_JACK;
    }

    public boolean areBlackJack() {
        return cards.size() == BLACK_JACK_SIZE && areBlackJackPoint();
    }

    public boolean areBlackJackPoint() {
        return calculatePoint() == BLACK_JACK;
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }

    public String getCardsDrawResult() {
        return cards.stream()
                .map(Card::getName)
                .collect(Collectors.joining(COMMA));
    }

    public List<Card> getCards() {
        return cards;
    }
}
