package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCards {
    private static final int firstHoldingCardCount = 2;
    private static final int DIFFERENCE_AS_ACE_CHANGE_ONE_TO_ELEVEN = 10;

    private final List<Card> holdingCards;

    public HoldingCards(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }

    public static HoldingCards of(Card... cards) {
        return new HoldingCards(new ArrayList<>(List.of(cards)));
    }

    public SummationCardPoint calculateTotalPoint() {
        SummationCardPoint summationCardPoint = calculateSum();
        if (hasAce()) {
            int rawPoint = fixPoint(summationCardPoint.summationCardPoint());
            return new SummationCardPoint(rawPoint);
        }
        return summationCardPoint;
    }

    private int fixPoint(int rawPoint) {
        SummationCardPoint fixPoint = new SummationCardPoint(rawPoint + DIFFERENCE_AS_ACE_CHANGE_ONE_TO_ELEVEN);
        if (!fixPoint.isDeadPoint()) {
            return fixPoint.summationCardPoint();
        }
        return rawPoint;
    }

    private SummationCardPoint calculateSum() {
        List<CardPoint> cardPoints = holdingCards.stream()
                .map(CardPointCalculator::calculate)
                .toList();

        return SummationCardPoint.of(cardPoints);
    }

    public void add(Card card) {
        holdingCards.add(card);
    }

    public List<Card> getHoldingCards() {
        return Collections.unmodifiableList(holdingCards);
    }

    private boolean hasAce() {
        return holdingCards.stream()
                .anyMatch(Card::isCardNameAce);
    }

    public boolean noAdditionalCard() {
        return holdingCards.size() == firstHoldingCardCount;
    }

    public boolean isSummationSameMaximum() {
        return calculateTotalPoint().isMaximum();
    }

    public boolean isSummationExceedMaximum() {
        return calculateTotalPoint().isDeadPoint();
    }
}
