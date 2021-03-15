package blackjack.domain.card;

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

    public boolean isBust() {
        return getLowerScore() > BLACKJACK_VALUE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_HAND_SIZE && getUpperScore() == BLACKJACK_VALUE;
    }

    private int getLowerScore() {
        return getScoreByMapper(Card::getRankValue);
    }

    private int getUpperScore() {
        return getScoreByMapper(Card::getUpperValue);
    }

    private int getScoreByMapper(ToIntFunction<Card> mapper) {
        return cards.stream()
                .mapToInt(mapper)
                .sum();
    }

    public int getScore() {
        int aceCount = getCountOfAce();
        int result = (aceCount * ACE_UPPER_VALUE) + getScoreWithoutAce();

        while (aceCount >= 0 && BLACKJACK_VALUE < result) {
            result -= 10;
            aceCount--;
        }

        return result;
    }

    private int getCountOfAce() {
        return (int) cards.stream()
                .filter(Card::isAce)
                .count();
    }

    private int getScoreWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getRankValue)
                .sum();
    }
}
