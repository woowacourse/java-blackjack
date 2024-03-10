package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Hand {
    private static final int BLACK_JACK = 21;
    private static final int BLACK_JACK_CARD_COUNT = 2;
    private final List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    private static int accumulateScore(final Card card, final int sum) {
        if (sum + card.getScore() <= BLACK_JACK) {
            return sum + card.getScore();
        }
        return sum + Rank.SMALL_ACE.getScore();
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

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
