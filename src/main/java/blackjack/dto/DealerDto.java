package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import java.util.List;

public class DealerDto {

    private final List<Card> cards;
    private final int score;

    public DealerDto(final List<Card> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public static DealerDto firstCardToDto(final Dealer dealer) {
        return new DealerDto(List.of(dealer.findFaceUpCard()), dealer.calculateScore());
    }

    public static DealerDto allCardToDto(final Dealer dealer) {
        return new DealerDto(dealer.getCards(), dealer.calculateScore());
    }
}
