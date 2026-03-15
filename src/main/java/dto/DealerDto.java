package dto;

import domain.card.Card;
import domain.participant.Dealer;

import java.util.List;

public class DealerDto {

    private final Card firstOpenCard;
    private final List<Card> dealerHand;
    private final int score;

    public DealerDto(Dealer dealer) {
        this.firstOpenCard = dealer.getFirstCard();
        this.dealerHand = List.copyOf(dealer.getCards());
        this.score = dealer.getScore();
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
