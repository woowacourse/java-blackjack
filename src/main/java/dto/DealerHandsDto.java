package dto;

import domain.Dealer;

import java.util.List;

public class DealerHandsDto {
    private final String displayedCard;

    private DealerHandsDto(final String displayedCard) {
        this.displayedCard = displayedCard;
    }

    public static DealerHandsDto from(final Dealer dealer) {
        List<String> cards = dealer.getCards();
        return new DealerHandsDto(cards.get(0));
    }

    public String getDisplayedCard() {
        return displayedCard;
    }
}
