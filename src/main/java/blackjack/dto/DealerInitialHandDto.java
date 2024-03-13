package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;

public record DealerInitialHandDto(CardDto firstCard) {

    public static DealerInitialHandDto fromDealer(Dealer dealer) {
        Card first = dealer.getFirstCard();

        return new DealerInitialHandDto(CardDto.fromCard(first));
    }
}
