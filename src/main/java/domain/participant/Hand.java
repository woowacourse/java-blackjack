package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.match.MatchResult;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int WIN_SCORE = 21;
    private static final int BLACKJACK_HAND_SIZE = 2;

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
        if (totalPoint + Rank.SOFT_ACE.getPoint() > WIN_SCORE) {
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
            return MatchResult.DEALER_BLACKJACK_LOSE;
        }

        if (this.isBlackjack() && other.isBlackjack()) {
            return MatchResult.DRAW;
        }

        return MatchResult.DEALER_WIN;
    }

    public boolean isBust() {
        return calculateTotalScore() > WIN_SCORE;
    }

    public boolean isBlackjack() {
        return (calculateTotalScore() == WIN_SCORE && cards.size() == BLACKJACK_HAND_SIZE);
    }

    private MatchResult checkBustResult(final Hand other) {
        if (this.isBust() && other.isBust()) {
            return MatchResult.DRAW;
        }
        if (this.isBust()) {
            return MatchResult.DEALER_LOSE;
        }
        return MatchResult.DEALER_WIN;
    }

    private MatchResult checkScoreResult(final Hand other) {
        return MatchResult.judge(this.calculateTotalScore(), other.calculateTotalScore());
    }

    public List<Card> getCards() {
        return cards;
    }
}
