package dto;

import domain.participant.Participant;

import java.util.List;

public class DealerHandsDto {
    private final String displayedCard;

    private DealerHandsDto(final String displayedCard) {
        this.displayedCard = displayedCard;
    }

    public static DealerHandsDto from(final Participant dealer) {
        List<String> cards = dealer.getCardNames();
        return new DealerHandsDto(cards.get(0));
    }

    public String getDisplayedCard() {
        return displayedCard;
    }
}
