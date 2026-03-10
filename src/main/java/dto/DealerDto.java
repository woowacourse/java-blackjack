package dto;

import domain.card.Card;

import java.util.List;

public class DealerHandDto {

    private final Card firstOpenCard;
    private final List<Card> dealerHand;
    private final int score;

    public DealerHandDto(Card firstOpenCard, List<Card> dealerHand, int score) {
        this.firstOpenCard = firstOpenCard;
        this.dealerHand = dealerHand;
        this.score = score;
    }

    public Card getFirstOpenCard() {
        return firstOpenCard;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public int getScore() {
        return score;
    }
}
