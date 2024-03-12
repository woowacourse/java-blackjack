package blackjackgame.domain.blackjack;

import blackjackgame.domain.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCards {
    private static final int firstHoldingCardCount = 2;

    private final List<Card> holdingCards;

    private HoldingCards(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }

    public static HoldingCards of(Card... cards) {
        return new HoldingCards(new ArrayList<>(List.of(cards)));
    }

    public SummationCardPoint calculateTotalPoint() {
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

    public boolean hasAce() {
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
