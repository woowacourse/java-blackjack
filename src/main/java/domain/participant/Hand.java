package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.match.MatchResult;
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

    private List<Rank> extractAces() {
        return cards.stream()
                .filter(Card::isAce)
                .map(Card::getRank)
                .toList();
    }

    private int calculateAcePoint(int totalPoint, Rank ace) {
        if (totalPoint + Rank.SOFT_ACE.getPoint() > MAX_SCORE) {
            return ace.getPoint();
        }
        return Rank.SOFT_ACE.getPoint();
    }

    public MatchResult determineMatchResult(final Hand other) {
        if (this.isBlackjack() || other.isBlackjack()) {
            return checkBlackjackResult(other);
        }

        if (this.isBust() || other.isBust()) {
            return checkBustResult(other);
        }
        return checkScoreResult(other);
    }

    private MatchResult checkBlackjackResult(final Hand other) {
        if (!this.isBlackjack() && other.isBlackjack()) {
            return MatchResult.BLACKJACK_LOSE;
        }

        if (this.isBlackjack() && other.isBlackjack()) {
            return MatchResult.DRAW;
        }

        return MatchResult.WIN;
    }

    public boolean isBust() {
        return calculateTotalScore() > MAX_SCORE;
    }

    private MatchResult checkBustResult(final Hand other) {
        if (this.isBust() && other.isBust()) {
            return MatchResult.DRAW;
        }
        if (this.isBust()) {
            return MatchResult.LOSE;
        }
        return MatchResult.WIN;
    }

    private MatchResult checkScoreResult(final Hand other) {
        return MatchResult.judge(this.calculateTotalScore(), other.calculateTotalScore());
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean isBlackjack() {
        return (calculateTotalScore() == 21 && cards.size() == 2);
    }
}
