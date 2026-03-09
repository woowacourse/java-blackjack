package dto;

import domain.player.Dealer;

public record DealerDto(String dealerName, Integer boundary) {
    public static DealerDto fromEntity(Dealer dealer) {
        return new DealerDto(dealer.getName(), dealer.getBoundary());
    }
}
