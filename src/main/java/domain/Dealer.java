package domain;

public class Dealer extends Player{

    public static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public String openOneCard() {
        StringBuilder sb = new StringBuilder();
        Card firstCard =  hand.getCards().getFirst();
        sb.append(firstCard.getDenomination().getValue())
                .append(firstCard.getSuit().getShape())
                .append("\n");
        return sb.toString();
    }


    public boolean isBelowThreshold() {
        return getHandTotal() <= 16;
    }

    public int getCardCount() {
        return hand.getSize();
    }
}
