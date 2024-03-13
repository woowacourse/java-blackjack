package domain.blackjack;

import static domain.card.CardName.TEN;

import domain.card.Card;
import domain.card.CardDrawCondition;
import domain.card.CardSelectStrategy;
import domain.card.Deck;
import java.util.List;

class BlackJackGameMachine {
    private final HoldingCards holdingCards;

    BlackJackGameMachine(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    DrawResult draw(Deck deck, CardSelectStrategy cardSelectStrategy, CardDrawCondition cardDrawCondition) {
        if (isBust() || !cardDrawCondition.canDraw() || deck.isEmpty()) {
            return DrawResult.fail("카드를 더이상 뽑을 수 없습니다.", false);
        }
        try {
            Card draw = deck.draw(cardSelectStrategy);
            holdingCards.add(draw);
            return DrawResult.success(!isBust());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return DrawResult.fail(e, !isBust());
        }
    }

    SummationCardPoint calculateSummationCardPoint() {
        SummationCardPoint summationCardPoint = holdingCards.calculateTotalPoint();
        if (hasAceInHoldingCards()) {
            int rawPoint = fixPoint(summationCardPoint.summationCardPoint());
            return new SummationCardPoint(rawPoint);
        }
        return summationCardPoint;
    }

    private int fixPoint(int rawPoint) {
        SummationCardPoint fixPoint = new SummationCardPoint(rawPoint + TEN.getCardNumber());
        if (!fixPoint.isDeadPoint()) {
            return fixPoint.summationCardPoint();
        }
        return rawPoint;
    }

    List<Card> getRawHoldingCards() {
        return List.copyOf(holdingCards.getHoldingCards());
    }

    int calculateSummationCardPointAsInt() {
        return calculateSummationCardPoint().summationCardPoint();
    }

    boolean isBust() {
        return calculateSummationCardPoint().isDeadPoint();
    }

    boolean hasAceInHoldingCards() {
        return holdingCards.hasAce();
    }
}
