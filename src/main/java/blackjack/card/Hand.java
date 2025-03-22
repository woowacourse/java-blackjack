package blackjack.card;

import blackjack.result.BlackjackMatchResult;
import java.util.ArrayList;
import java.util.List;

public class Hand {

    public static final int SOFT_ACE_POINT = 11;
    private static final int HIGHEST_SCORE = 21;
    private static final int INITIAL_DEALING_CARDS_SIZE = 2;

    private final List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public void initialDeal(CardDeck cardDeck) {
        for (int i = 0; i < INITIAL_DEALING_CARDS_SIZE; i++) {
            add(cardDeck.drawCard());
        }
    }

    public BlackjackMatchResult determineMatchResultFor(Hand other) {
        if (this.isBlackjack() || other.isBlackjack()) {
            return checkBlackjackResult(other);
        }

        if (this.isBust() || other.isBust()) {
            return checkBurstResult(other);
        }

        return compareScores(other);
    }

    private BlackjackMatchResult checkBlackjackResult(Hand other) {
        if (this.isBlackjack() && other.isBlackjack()) {
            return BlackjackMatchResult.DRAW;
        }
        if (this.isBlackjack()) {
            return BlackjackMatchResult.LOSE;
        }

        return BlackjackMatchResult.WIN;

    }

    private BlackjackMatchResult checkBurstResult(Hand other) {
        if (this.isBust() && other.isBust()) {
            return BlackjackMatchResult.DRAW;
        }
        if (this.isBust()) {
            return BlackjackMatchResult.WIN;
        }
        return BlackjackMatchResult.LOSE;
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
        if (totalPoint + SOFT_ACE_POINT > HIGHEST_SCORE) {
            return ace.getPoint();
        }
        return SOFT_ACE_POINT;
    }

    public boolean isBust() {
        return calculateTotalPoint() > HIGHEST_SCORE;
    }

    public boolean isBlackjack() {
        return cards.size() == INITIAL_DEALING_CARDS_SIZE && calculateTotalPoint() == HIGHEST_SCORE;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }
}
