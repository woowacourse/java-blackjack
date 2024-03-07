package domain;

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
        int fixPoint = rawPoint + 10;
        if (fixPoint <= 21) {
            rawPoint = fixPoint;
        }
        return rawPoint;
    }

    public String getRawName() {
        return name;
    }

    public List<Card> getRawHoldingCards() {
        return holdingCards.getHoldingCards();
    }

    // TODO: getSummationCardPoint -> 프로그램의 동작을 위한 메서드
    // TODO: getRawSummationCardPoint -> DTO를 위한 메서드
    // TODO: holdingCards.calculateTotalPoint()를 내부 메서드로 만들기 (. 줄이고 가시성 향상)
    public int getRawSummationCardPoint() {
        return getSummationCardPoint().summationCardPoint();
    }

    public boolean isDead() {
        return holdingCards.calculateTotalPoint().isBiggerThan(new SummationCardPoint(21));
    }

    boolean hasAceInHoldingCards() {
        return holdingCards.hasAce();
    }
}
