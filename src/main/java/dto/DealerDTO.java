package dto;

import domain.card.Card;
import java.util.List;

public class DealerDTO {
    private final List<Card> holdingCards;
    private final int summationCardPoint;

    public DealerDTO(List<Card> holdingCards, int summationCardPoint) {
        this.holdingCards = holdingCards;
        this.summationCardPoint = summationCardPoint;
    }

    public List<Card> getHoldingCards() {
        return holdingCards;
    }

    public int getSummationCardPoint() {
        return summationCardPoint;
    }
}
