package blackjack.dto;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Dealer;

public class DealerInitialHandDto {

    private final String name;
    private final CardDto firstCard;

    private DealerInitialHandDto(String name, CardDto firstCard) {
        this.name = name;
        this.firstCard = firstCard;
    }

    public static DealerInitialHandDto fromDealer(Dealer dealer) {
        String dealerName = dealer.getName();
        Card dealerFirstCard = dealer.getFirstCard();

        return new DealerInitialHandDto(dealerName, CardDto.fromCard(dealerFirstCard));
    }

    public String name() {
        return name;
    }

    public CardDto firstCard() {
        return firstCard;
    }
}
