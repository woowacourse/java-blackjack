package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private static final int HIGHEST_SCORE = 21;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public BlackjackMatchResult compareWith(Hand other) {
        BlackjackMatchResult burstResult = handleBurstCases(other);
        if (burstResult != null) {
            return burstResult;
        }

        return compareScores(other);
    }

    private BlackjackMatchResult handleBurstCases(Hand other) {
        if (this.isBurst() && other.isBurst()) {
            return BlackjackMatchResult.DRAW;
        }
        if (this.isBurst()) {
            return BlackjackMatchResult.LOSE;
        }
        if (other.isBurst()) {
            return BlackjackMatchResult.WIN;
        }
        return null;
    }

    private BlackjackMatchResult compareScores(Hand other) {
        int thisScore = this.calculateTotalPoint();
        int otherScore = other.calculateTotalPoint();
        return BlackjackMatchResult.judge(thisScore, otherScore);
    }

    public int calculateTotalPoint() {
        int totalPoint = sumPointWithoutAce();
        totalPoint = sumAcePoint(totalPoint);

        return totalPoint;
    }

    private int sumPointWithoutAce() {
        return cards.stream()
                .filter(card -> !card.isAce())
                .mapToInt(Card::getPoint)
                .sum();
    }

    private int sumAcePoint(int totalPoint) {
        List<CardRank> aces = extractAces();

        for (CardRank ace : aces) {
            totalPoint += calculateAcePoint(totalPoint, ace);
        }

        return totalPoint;
    }

    private List<CardRank> extractAces() {
        return cards.stream()
                .filter(Card::isAce)
                .map(Card::getCardRank)
                .toList();
    }

    private int calculateAcePoint(int totalPoint, CardRank ace) {
        if (totalPoint + CardRank.SOFT_ACE.getPoint() > HIGHEST_SCORE) {
            return ace.getPoint();
        }
        return CardRank.SOFT_ACE.getPoint();
    }

    public boolean isBurst() {
        return calculateTotalPoint() > HIGHEST_SCORE;
    }

    public List<Card> getCards() {
        return cards.stream()
                .map(card -> new Card(card.getCardSymbol(), card.getCardRank()))
                .toList();
    }
}
