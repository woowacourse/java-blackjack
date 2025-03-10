package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int MAX_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();

    public void hit(Card card) {
        cards.add(card);
    }

    public int calculateTotalScore() {
        int totalPoint = sumPointWithoutAce();
        totalPoint = sumAcePoint(totalPoint);

        return totalPoint;
    }

    private int sumPointWithoutAce() {
        return cards.stream().filter(card -> !card.isAce())
                .mapToInt(card -> card.getRank().getPoint())
                .sum();
    }

    private int sumAcePoint(int totalPoint) {
        List<Rank> aces = extractAces();

        for (Rank ace : aces) {
            int acePoint = calculateAcePoint(totalPoint, ace);
            totalPoint += acePoint;
        }

        return totalPoint;
    }

    private int calculateAcePoint(int totalPoint, Rank ace) {
        if (totalPoint + Rank.SOFT_ACE.getPoint() > MAX_SCORE) {
            return ace.getPoint();
        }
        return Rank.SOFT_ACE.getPoint();
    }

    private List<Rank> extractAces() {
        return cards.stream()
                .filter(Card::isAce)
                .map(Card::getRank)
                .toList();
    }

    public boolean isBust() {
        return calculateTotalScore() > MAX_SCORE;
    }

    public List<Card> getCards() {
        return cards;
    }
}
