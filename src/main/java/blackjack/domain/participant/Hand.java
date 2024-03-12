package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardRank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

    private static final int BONUS_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;
    private static final int BLACKJACK_CARD_SIZE = 2;

    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    private Hand(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public Hand add(Card card) {
        List<Card> newCards = new ArrayList<>(cards);
        newCards.add(card);

        return new Hand(newCards);
    }

    public int calculateScore() {
        int hardHandScore = calculateHardHandScore();
        int softHandScore = hardHandScore + BONUS_SCORE;

        if (hasAce() && softHandScore <= BLACKJACK_SCORE) {
            return softHandScore;
        }

        return hardHandScore;
    }

    private boolean hasAce() {
        return cards.stream()
                .map(Card::getCardRank)
                .anyMatch(CardRank::isAce);
    }

    private int calculateHardHandScore() {
        return cards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public boolean isBust() {
        return calculateScore() > BLACKJACK_SCORE;
    }

    public boolean isBlackJack() {
        return cards.size() == BLACKJACK_CARD_SIZE && calculateScore() == BLACKJACK_SCORE;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
