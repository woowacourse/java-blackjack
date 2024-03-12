package dto;

import domain.participant.Dealer;
import java.util.List;

public record DealerHandsDto(String card) {

    public static DealerHandsDto from(final Dealer dealer) {
        List<String> cards = dealer.getCardNames();
        return new DealerHandsDto(cards.get(0));
    }

    public String getCard() {
        return card;
    }
}
