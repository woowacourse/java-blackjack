package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private static final int MAX_SUM = 21;
    private static final int BLACKJACK_CARD_COUNT_COND = 2;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isBust() {
        return sum() > MAX_SUM;
    }

    public boolean isBlackJack() {
        return sum() == MAX_SUM && cards.size() == BLACKJACK_CARD_COUNT_COND;
    }

    public int sum() {
        int totalScore = sumExceptAceCards();
        List<Card> aceCards = filterAceCards();

        for (Card aceCard : aceCards) {
            totalScore = accumulateScore(aceCard, totalScore);
        }
        return totalScore;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getScore).sum();
    }

    private List<Card> filterAceCards() {
        return cards.stream()
                .filter(Card::isAce)
                .toList();
    }

    private int accumulateScore(final Card card, final int sum) {
        if (sum + card.getScore() <= MAX_SUM) {
            return sum + card.getScore();
        }
        return sum + Rank.SMALL_ACE.getScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
