package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private String name;
    private HoldingCard holdingCard;

    void receiveCard(Card card){
        holdingCard.add(card);
    };

    List<Card> showCards(){
        return holdingCard.getHoldingCard();
    };

    abstract boolean isFinished();
}
