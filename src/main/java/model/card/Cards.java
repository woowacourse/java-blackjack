package model.card;

import java.util.ArrayList;
import java.util.List;

public class Cards {

    private static final int ACE_SCORE_HIGH = 11;
    private static final int ACE_SCORE_LOW = 1;
    private static final int MIN_SCORE_FOR_ACE_HIGH = 11;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Cards(List<Card> cards) {
        this.cards = List.copyOf(cards);
    }

    public Cards add(Card card) {
        List<Card> addedCards = new ArrayList<>(cards);
        addedCards.add(card);
        return new Cards(addedCards);
    }

    public int score() {
        int score = sumScore(cards);
        while (score <= MIN_SCORE_FOR_ACE_HIGH && hasAce(cards)) {
            score += ACE_SCORE_HIGH - ACE_SCORE_LOW;
        }
        return score;
    }

    private static int sumScore(List<Card> cards) {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum();
    }

    public static boolean hasAce(List<Card> cards) {
        return cards.stream()
            .anyMatch(Card::isAce);
    }

    public boolean isBlackjack() {
        return size() == BLACKJACK_CARD_SIZE && score() == BLACKJACK_SCORE;
    }

    public boolean isBurst() {
        return score() > BLACKJACK_SCORE;
    }

    public int size() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
