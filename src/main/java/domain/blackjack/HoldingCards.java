package domain.blackjack;

import domain.card.Card;
import domain.card.CardName;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCards {
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

    List<Card> getHoldingCards() {
        return Collections.unmodifiableList(holdingCards);
    }

    boolean hasAce() {
        return holdingCards.stream()
                .anyMatch(card -> card.name() == CardName.ACE);
    }
}
