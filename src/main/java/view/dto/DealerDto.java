package view.dto;

import java.util.List;

import domain.Cards;
import domain.Dealer;

public class DealerDto extends ParticipantDto {

    public DealerDto(final Dealer dealer) {
        super(dealer.name(), new Cards(List.of(dealer.peek())));
    }
}
