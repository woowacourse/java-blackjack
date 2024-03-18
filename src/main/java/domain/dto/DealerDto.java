package domain.dto;

import domain.Card;
import domain.Dealer;
import java.util.List;

public class DealerDto {
    protected final List<Card> cards;
    protected final int totalScore;

    protected DealerDto(List<Card> cards, int totalScore) {
        this.cards = cards;
        this.totalScore = totalScore;
    }

    public static DealerDto from(Dealer dealer) {
        return new DealerDto(dealer.getCards(), dealer.getTotalScore());
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
