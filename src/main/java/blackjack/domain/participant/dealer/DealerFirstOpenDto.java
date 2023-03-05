package blackjack.domain.participant.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Name;

public class DealerFirstOpenDto {
    private final Name name;
    private final Card card;

    private DealerFirstOpenDto(Name name, Card card) {
        this.name = name;
        this.card = card;
    }

    public static DealerFirstOpenDto from(Dealer dealer) {
        return new DealerFirstOpenDto(dealer.getName(), dealer.showOneCard());
    }

    public Name getName() {
        return name;
    }

    public Card getCard() {
        return card;
    }
}
