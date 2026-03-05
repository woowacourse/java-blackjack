package blackjack;

public class Card {


    private final CardPoint cardPoint;
    private final CardPattern cardPattern;

    public Card(CardPoint cardPoint, CardPattern cardPattern) {
        this.cardPoint = cardPoint;
        this.cardPattern = cardPattern;
    }

    public int getCardPoint() {
        return cardPoint.getPoint();
    }

    public String getCardPointName() {
        return cardPoint.getName();
    }

    public boolean isAce() {
        return cardPoint.equals(CardPoint.ACE);
    }
}
