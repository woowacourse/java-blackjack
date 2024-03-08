package view.dto.participant;

import java.util.List;

import domain.card.Card;
import domain.participant.Dealer;
import view.dto.card.CardsDto;

public class DealerDto extends ParticipantDto {

    public DealerDto(final Dealer dealer) {
        super(dealer.name(), new CardsDto(dealer.cards(), dealer.cardSum()));
    }

    public DealerDto(final Dealer dealer, final Card card) {
        super(dealer.name(), new CardsDto(List.of(card), dealer.cardSum()));
    }
}
