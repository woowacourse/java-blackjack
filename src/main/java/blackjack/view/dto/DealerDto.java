package blackjack.view.dto;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;

public class DealerDto {

    private final String name;
    private final Cards cards;

    private DealerDto(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static DealerDto of(Dealer dealer) {
        return new DealerDto(dealer.getName().getValue(), dealer.getCards());
    }

    public String showOneCard() {
        return cards.getCardHand().get(0).toString();
    }
}
