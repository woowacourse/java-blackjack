package view.dto;

import java.util.List;

import domain.Card;
import domain.Dealer;

public class DealerDto extends ParticipantDto {

    public DealerDto(final Dealer dealer) {
        super(dealer.name(), new CardsDto(dealer.cards(), dealer.cardSum()));
    }

    public DealerDto(final Dealer dealer, final Card card) {
        super(dealer.name(), new CardsDto(List.of(card), dealer.cardSum()) );
    }
}
