package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardGroup {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int ACE_OFFSET = -10;
    private static final int NUMBER_OF_INITIAL_CARD_NUMBER = 2;

    private final List<Card> cards = new ArrayList<>();

    public CardGroup(final Card firstCard, final Card secondCard) {
        cards.add(firstCard);
        cards.add(secondCard);
    }

    public void add(final Card newCard) {
        cards.add(newCard);
    }

    public List<Card> getCards() {
        return List.copyOf(cards);
    }

    public int getScore() {
        int score = getTotalValue();
        final int aceCount = getAceCount();

        for (int tryCount = 0; tryCount < aceCount && score > BLACK_JACK_NUMBER; tryCount++) {
            score += ACE_OFFSET;
        }

        return score;
    }

    private int getTotalValue() {
        return cards.stream()
                .map(Card::getNumber)
                .mapToInt(CardNumber::getValue)
                .sum();
    }

    private int getAceCount() {
        return (int) cards.stream()
                .filter(card -> card.getNumber() == CardNumber.ACE)
                .count();
    }

    public boolean isBust() {
        return getScore() > BLACK_JACK_NUMBER;
    }

    public boolean isBlackJack() {
        return getScore() == BLACK_JACK_NUMBER && cards.size() == NUMBER_OF_INITIAL_CARD_NUMBER;
    }
}
