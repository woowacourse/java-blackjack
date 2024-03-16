package domain.blackjack;

import static domain.card.CardName.TEN;

import domain.card.Card;
import domain.card.CardDrawCondition;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import java.util.List;

class BlackJackGameMachine {
    private static final int INITIAL_CARD_COUNT = 2;
    private final HoldingCards holdingCards;

    BlackJackGameMachine(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy, CardDrawCondition cardDrawCondition) {
        if (canDraw(deck, cardDrawCondition)) {
            return DrawResult.fail();
        }
        try {
            Card draw = deck.draw(cardSelectStrategy);
            holdingCards.add(draw);
            return DrawResult.success(!isBust());
        } catch (IllegalArgumentException e) {
            return DrawResult.fail(e, !isBust());
        }
    }

    private boolean canDraw(Deck deck, CardDrawCondition cardDrawCondition) {
        return isBust() || !cardDrawCondition.canDraw() || deck.isEmpty();
    }

    int calculateSummationCardPointAsInt() {
        return calculateSummationCardPoint().summationCardPoint();
    }

    SummationCardPoint calculateSummationCardPoint() {
        SummationCardPoint summationCardPoint = holdingCards.calculateTotalPoint();
        if (hasAceInHoldingCards()) {
            int rawPoint = fixPoint(summationCardPoint.summationCardPoint());
            return new SummationCardPoint(rawPoint);
        }
        return summationCardPoint;
    }

    private boolean hasAceInHoldingCards() {
        return holdingCards.hasAce();
    }

    private int fixPoint(int rawPoint) {
        SummationCardPoint fixPoint = new SummationCardPoint(rawPoint + TEN.getCardNumber());
        if (fixPoint.isDeadPoint()) {
            return rawPoint;
        }
        return fixPoint.summationCardPoint();
    }

    boolean isBust() {
        return calculateSummationCardPoint().isDeadPoint();
    }

    boolean isBlackJack() {
        List<Card> rawHoldingCards = getRawHoldingCards();
        int holdingCardCount = rawHoldingCards.size();
        SummationCardPoint summationCardPoint = calculateSummationCardPoint();
        return holdingCardCount == INITIAL_CARD_COUNT && summationCardPoint.isBlackJackPoint();
    }

    List<Card> getRawHoldingCards() {
        return List.copyOf(holdingCards.getHoldingCards());
    }

    List<Card> getRawHoldingCards(CardShowStrategy cardShowStrategy) {
        List<Card> allCards = holdingCards.getHoldingCards();
        return List.copyOf(cardShowStrategy.showSub(allCards));
    }

}
