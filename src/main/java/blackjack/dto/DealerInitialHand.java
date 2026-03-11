package blackjack.dto;

import blackjack.domain.participant.Dealer;

public record DealerInitialHand(
        String nickname,
        String cardDisplayName
) {

    public static DealerInitialHand from(Dealer dealer) {
        return new DealerInitialHand(
                dealer.getNickname(),
                CardDisplayName.from(dealer.getCards().getFirst()).displayName()
        );
    }
}
