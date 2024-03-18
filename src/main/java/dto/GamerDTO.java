package dto;

import domain.card.Card;
import java.util.List;

public class GamerDTO {
    private final String name;
    private final List<Card> holdingCards;
    private final int summationCardPoint;

    private GamerDTO(String name, List<Card> holdingCards, int summationCardPoint) {
        this.name = name;
        this.holdingCards = holdingCards;
        this.summationCardPoint = summationCardPoint;
    }

    public static GamerDTO playerDTO(String name, List<Card> holdingCards, int summationCardPoint) {
        return new GamerDTO(name, holdingCards, summationCardPoint);
    }

    public static GamerDTO dealerDTO(List<Card> holdingCards, int summationCardPoint) {
        return new GamerDTO("딜러", holdingCards, summationCardPoint);
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
