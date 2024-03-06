package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final int BLACK_JACK = 21;
    private static final int ACE_SCORE_CRITERIA = 10;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }

    public boolean isOverBlackJack() {
        return sum() > BLACK_JACK;
    }

    public int sum() {
        int totalScore = sumExceptAceCards();
        List<Card> aceCards = filterAceCards();

        for (Card aceCard : aceCards) {
            totalScore = accumulateScore(aceCard, totalScore);
        }
        return totalScore;
    }

    private List<Card> filterAceCards() {
        List<Card> aceCards = cards.stream()
                .filter(card -> card.getRank().isAce())
                .collect(Collectors.toList());
        return aceCards;
    }

    private int sumExceptAceCards() {
        return cards.stream()
                .filter(card -> !card.getRank().isAce())
                .mapToInt(Card::getScore).sum();
    }

    private static int accumulateScore(final Card card, final int sum) {
        if (sum + card.getScore() <= ACE_SCORE_CRITERIA) {
            return sum + card.getScore();
        }
        return sum + Rank.SMALL_ACE.getScore();
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
