package domain.card;

import java.util.ArrayList;
import java.util.List;

import static domain.card.BlackJackScore.BLACK_JACK_SCORE;

public class CardArea {

    private static final int FIRST_CARD_INDEX = 0;
    private static final int BLACK_JACK_CARD_COUNT = 2;

    private final List<Card> cards = new ArrayList<>();

    private CardArea(final Card firstCard, final Card secondCard) {
        cards.addAll(List.of(firstCard, secondCard));
    }

    public static CardArea initialWithTwoCard(final Card firstCard, final Card secondCard) {
        return new CardArea(firstCard, secondCard);
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public BlackJackScore calculate() {
        BlackJackScore total = minScore();
        if (hasAce()) {
            return total.plusTenIfNotBust();
        }
        return total;
    }

    private BlackJackScore minScore() {
        return BlackJackScore.of(cards.stream()
                .mapToInt(Card::defaultScore).sum());
    }

    private boolean hasAce() {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    public boolean canMoreCard() {
        return BLACK_JACK_SCORE.isLargerThan(calculate());
    }

    public boolean isBust() {
        return calculate().isBust();
    }

    public boolean isBlackJack() {
        return cards.size() == BLACK_JACK_CARD_COUNT && calculate().isBlackJackScore();
    }

    public Card firstCard() {
        return cards.get(FIRST_CARD_INDEX);
    }

    public List<Card> cards() {
        return new ArrayList<>(cards);
    }
}
