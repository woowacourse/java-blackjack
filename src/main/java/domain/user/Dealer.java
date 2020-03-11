package domain.user;

import domain.HandCard;
import domain.card.Card;

public class Dealer {
    public static final String DEALER_NAME = "딜러";
    private final HandCard handCard;

    public Dealer() {
        handCard = new HandCard();
    }

    public String getName() {
        return DEALER_NAME;
    }

    public void draw(Card card) {
        handCard.add(card);
    }

    public String getStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(DEALER_NAME)
                .append(": ")
                .append(handCard.getNames());
        return stringBuilder.toString();
    }

    public String getFirstStatus(){
        return getStatus().split(",")[0];
    }

    public int getScore() {
        return handCard.getScore();
    }
}
