package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoldingCards {
    private final List<Card> holdingCards;

    private HoldingCards(List<Card> holdingCards) {
        this.holdingCards = holdingCards;
    }

    // TODO: 팩토리 메서드 변수명 변경??
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

    // TODO: countOfAce 메서드가 쓰이는가? 아래 hasAce로 충분한 듯 보임.
    int countOfAce() {
        return (int) holdingCards.stream()
                .filter(card -> card.name() == CardName.ACE)
                .count();
    }

    boolean hasAce() {
        return holdingCards.stream()
                .anyMatch(card -> card.name() == CardName.ACE);
    }
}
