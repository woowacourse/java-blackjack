package domain.blackjack;

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

    public void draw(Deck deck, CardDrawStrategy cardDrawStrategy) {
        Card draw = deck.draw(cardDrawStrategy);
        holdingCards.add(draw);
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
        SummationCardPoint fixPoint = new SummationCardPoint(rawPoint + 10);
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
