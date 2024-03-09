package domain.blackjack;

import static domain.card.CardName.TEN;

import domain.card.Card;
import domain.card.CardDrawStrategy;
import domain.card.Deck;
import java.util.List;

public class Gamer {
    private final String name;
    private final HoldingCards holdingCards;

    public Gamer(String name, HoldingCards holdingCards) {
        this.name = name;
        this.holdingCards = holdingCards;
    }

    public DrawResult draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        if (isDead()) {
            return DrawResult.fail("카드를 더이상 뽑을 수 없습니다.", false);
        }
        try {
            Card draw = deck.draw(cardDrawStrategy);
            holdingCards.add(draw);
            return DrawResult.success(!isDead());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return DrawResult.fail(e, !isDead());
        }
    }

    public SummationCardPoint getSummationCardPoint() {
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

    public String getRawName() {
        return name;
    }

    public List<Card> getRawHoldingCards() {
        return List.copyOf(holdingCards.getHoldingCards());
    }

    public int getRawSummationCardPoint() {
        return getSummationCardPoint().summationCardPoint();
    }

    boolean isDead() {
        return holdingCards.calculateTotalPoint().isDeadPoint();
    }

    boolean hasAceInHoldingCards() {
        return holdingCards.hasAce();
    }
}
