package dto;

import domain.Dealer;

public record DealerDto(String dealerName, Integer boundary) {
    public static DealerDto FromEntity(Dealer dealer){
        return new DealerDto(dealer.getName(), dealer.getBoundary());
    }
}
