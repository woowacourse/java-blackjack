package domain.gamer;

import domain.card.Card;
import domain.card.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return sum() > BLACK_JACK;
    }

    public boolean isBlackJack() {
        return sum() == BLACK_JACK && cards.size() == BLACK_JACK_CARD_COUNT;
    }

    public int sum() {
        int totalScore = sumExceptAceCards();

        int aceCardsCount = countAceCards();
        for (int count = 0; count < aceCardsCount; count++) {
            totalScore = totalScore + Rank.selectAceScore(totalScore);
        }

        return totalScore;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(card -> !card.getRank().isAce())
                .mapToInt(Card::getScore).sum();
    }

    private int countAceCards() {
        return (int) cards.stream()
                .filter(card -> card.getRank().isAce())
                .count();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
