package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.view.CardMapper;

public record DealerInitialHand(
        String nickname,
        String cardDisplayName
) {
    
    public static DealerInitialHand from(Dealer dealer) {
        return new DealerInitialHand(
                dealer.getNickname(),
                CardMapper.toDisplayName(dealer.getCards().getFirst()).displayName()
        );
    }
}
