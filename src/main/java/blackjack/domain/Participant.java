package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private String name;
    private HoldingCard holdingCard;

    public Participant(String name, HoldingCard holdingCard) {
        this.name = name;
        this.holdingCard = holdingCard;
    }

    void receiveCard(Card card){
        holdingCard.add(card);
    };

    List<Card> showCards(){
        return holdingCard.getHoldingCard();
    };

    public String getName() {
        return name;
    }

    public HoldingCard getHoldingCard() {
        return holdingCard;
    }

    public abstract boolean isFinished();
}
