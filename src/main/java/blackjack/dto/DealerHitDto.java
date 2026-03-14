package blackjack.dto;

import blackjack.domain.participants.Dealer;

public record DealerHitDto(String dealerName, int hitCount) {
    public static DealerHitDto of(Dealer dealer, int dealerHitCount) {
        return new DealerHitDto(dealer.getName(), dealerHitCount);
    }
}
