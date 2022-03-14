package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    public static final int BLACKJACK_SYMBOL_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;
    private static final int ACE_UPPER_SCORE = 11;
    private static final int ACE_SCORE_DIFFERENCE = 10;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card... cards) {
        this.cards.addAll(List.of(cards));
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int getScore() {
        int countOfAce = findCountOfAce();
        int sumOfNoAce = getSumExcludingAce() + (countOfAce * ACE_UPPER_SCORE);

        while (countOfAce > 0 && BLACKJACK_SYMBOL_SCORE < sumOfNoAce) {
            sumOfNoAce -= ACE_SCORE_DIFFERENCE;
            countOfAce--;
        }

        return sumOfNoAce;
    }

    private int getSumExcludingAce() {
        return cards.stream()
            .filter(card -> !card.isAce())
            .mapToInt(Card::getScore)
            .sum();
    }

    private int findCountOfAce() {
        return Math.toIntExact(cards.stream()
            .filter(Card::isAce)
            .count());
    }

    public boolean compareScore(Hand other) {
        return this.getScore() > other.getScore();
    }

    public boolean isBust() {
        return getScore() > BLACKJACK_SYMBOL_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == BLACKJACK_CARD_SIZE && getScore() == BLACKJACK_SYMBOL_SCORE;
    }

    public Card openCard() {
        return cards.get(0);
    }
}
