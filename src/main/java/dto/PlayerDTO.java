package dto;

import domain.card.Card;
import java.util.List;

public class PlayerDTO {
    private final String name;
    private final List<Card> holdingCards;
    private final int summationCardPoint;

    public PlayerDTO(String name, List<Card> holdingCards, int summationCardPoint) {
        this.name = name;
        this.holdingCards = holdingCards;
        this.summationCardPoint = summationCardPoint;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHoldingCards() {
        return holdingCards;
    }

    public int getSummationCardPoint() {
        return summationCardPoint;
    }
}
