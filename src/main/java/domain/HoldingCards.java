package domain;

import java.util.List;

public class HoldingCards {
    private final List<Card> holdingCards;

    public HoldingCards(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }

    public SummationCardPoint calculateTotalPoint() {
        List<CardPoint> cardPoints = holdingCards.stream()
                .map(CardPointCalculator::calculate)
                .toList();

        return SummationCardPoint.of(cardPoints);
    }
}
