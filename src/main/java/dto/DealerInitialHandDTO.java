package dto;

import domain.participant.Dealer;

public record DealerInitialHandDTO(String firstHandCard) {
    public static DealerInitialHandDTO from(Dealer dealer) {
        return new DealerInitialHandDTO(dealer.getFirstCard().toString());
    }
}
