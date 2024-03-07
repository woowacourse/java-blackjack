package view.dto;

import java.util.List;

import domain.Card;
import domain.Cards;
import domain.Dealer;

public class DealerDto extends ParticipantDto {

    public DealerDto(final Dealer dealer) {
        super(dealer.name(), dealer.cards());
    }

    public DealerDto(final Dealer dealer, final Card card) {
        super(dealer.name(), new Cards(List.of(card)));
    }
}
