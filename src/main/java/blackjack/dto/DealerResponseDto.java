package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;
import java.util.List;

public class DealerResponseDto {

    private final List<Card> cards;
    private final int score;

    public DealerResponseDto(final List<Card> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public static DealerResponseDto firstCardToDto(final Dealer dealer) {
        return new DealerResponseDto(List.of(dealer.findFaceUpCard()), dealer.calculateScore());
    }

    public static DealerResponseDto allCardToDto(final Dealer dealer) {
        return new DealerResponseDto(dealer.getCards(), dealer.calculateScore());
    }
}
