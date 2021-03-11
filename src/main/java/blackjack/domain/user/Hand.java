package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int FIRST_INDEX = 0;
    private static final int SURPLUS = 10;
    private final List<Card> cards;

    public Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Hand createEmptyHand() {
        return new Hand(Collections.emptyList());
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    public int calculateScore() {
        int score = sumScore();
        long numberOfAce = countNumberOfAce();

        for (int count = 0; count < numberOfAce; count++) {
            score = decideAceScore(score);
        }
        return score;
    }

    private int decideAceScore(int score) {
        if (score > Status.BLACKJACK_SCORE) {
            score -= SURPLUS;
        }
        return score;
    }

    private long countNumberOfAce() {
        return cards.stream()
                .filter(this::isAce)
                .count();
    }

    private boolean isAce(Card card) {
        return card.getValue() == Value.ACE;
    }

    private int sumScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public Card getFirstCard() {
        return cards.get(FIRST_INDEX);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
