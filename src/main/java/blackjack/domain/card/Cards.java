package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cards {

    private static final int BLACK_JACK_SCORE = 21;
    private static final int INITIAL_CARD_SIZE = 2;
    private static final int NUMBER_TO_USE_ACE_CARD_WITH_ONE = 10;

    private final List<Card> cards;

    public Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public Cards(final Card... card) {
        this.cards = Arrays.stream(card)
                .collect(Collectors.toList());
    }

    public void addCard(final Card card) {
        cards.add(card);
    }

    public boolean isInitialCards() {
        return cards.size() == INITIAL_CARD_SIZE;
    }

    public boolean isBlackJack() {
        return isInitialCards() && sum() == BLACK_JACK_SCORE;
    }

    public boolean isBust() {
        return calculateScore(sum(), getCountOfAce()) > BLACK_JACK_SCORE;
    }

    public int calculateScore(final int score, final int countOfAce) {
        if (score <= BLACK_JACK_SCORE || countOfAce == 0) {
            return score;
        }
        return calculateScore(score - NUMBER_TO_USE_ACE_CARD_WITH_ONE, countOfAce - 1);
    }

    public int sum() {
        return cards.stream()
                .mapToInt(Card::getDenomination)
                .sum();
    }

    public int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAceCard)
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
