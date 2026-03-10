package blackjack.dto;

import blackjack.domain.participant.Participant;

public record DealerInitialHand(
        String nickname,
        String cardName
) {
    public static DealerInitialHand from(Participant participant) {
        return new DealerInitialHand(
                participant.getNickname(),
                participant.getCardNames().getFirst()
        );
    }
}
