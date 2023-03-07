package blackjack.domain.participant.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Name;

public class DealerFirstCardDto {
    private final Name name;
    private final Card card;

    private DealerFirstCardDto(Name name, Card card) {
        this.name = name;
        this.card = card;
    }

    public static DealerFirstCardDto from(Dealer dealer) {
        return new DealerFirstCardDto(dealer.getName(), dealer.showOneCard());
    }

    public Name getName() {
        return name;
    }

    public Card getCard() {
        return card;
    }
}
