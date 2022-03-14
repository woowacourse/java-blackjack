package blackjack.domain;

import java.util.List;

public abstract class Participant {
    private String name;
    private HoldingCards holdingCards;

    public Participant(String name, HoldingCards holdingCards) {
        this.name = name;
        this.holdingCards = holdingCards;
    }

    void receiveCard(Card card){
        holdingCards.add(card);
    };

    List<Card> showCards(){
        return holdingCards.getCards();
    };

    public String getName() {
        return name;
    }

    public HoldingCards getHoldingCard() {
        return holdingCards;
    }

    public abstract boolean isFinished();
}
