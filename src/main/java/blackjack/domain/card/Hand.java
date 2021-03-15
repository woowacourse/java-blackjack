package blackjack.domain.card;

import static blackjack.domain.card.Denomination.ACE;
import static blackjack.domain.card.Denomination.ACE_UPPER_VALUE;
import static blackjack.domain.participant.Dealer.BLACKJACK_HAND_SIZE;
import static blackjack.domain.participant.Dealer.BLACKJACK_VALUE;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.ToIntFunction;

public class Hand {

    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(new ArrayList<>());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        int countOfAce = getCountOfAce();
        int result = getUpperScore();

        while (BLACKJACK_VALUE < result && countOfAce > 0) {
            result -= (ACE_UPPER_VALUE - ACE.getValue());
            countOfAce--;
        }
        return result;
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_HAND_SIZE && getUpperScore() == BLACKJACK_VALUE;
    }

    private int getUpperScore() {
        return getScoreByMapper(Card::getUpperValue);
    }

    public boolean isBust() {
        return getLowerScore() > BLACKJACK_VALUE;
    }

    private int getLowerScore() {
        return getScoreByMapper(Card::getRankValue);
    }

    private int getScoreByMapper(ToIntFunction<Card> mapper) {
        return cards.stream()
                .mapToInt(mapper)
                .sum();
    }
}
