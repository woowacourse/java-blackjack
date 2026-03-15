package blackjack.dto;

import blackjack.domain.participant.Dealer;

public record DealerHitDto(String dealerName, int hitCount) {
    public static DealerHitDto of(Dealer dealer, int dealerHitCount) {
        return new DealerHitDto(dealer.getName(), dealerHitCount);
    }
}
