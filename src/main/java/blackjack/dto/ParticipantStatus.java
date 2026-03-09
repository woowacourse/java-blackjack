package blackjack.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;

public record ParticipantStatus(
        String nickname,
        String cardStatus,
        int totalScore
) {
    
    public ParticipantStatus(Participant participant) {
        this(
                participant.getNickname(),
                participant.getInfoSnapshot(),
                participant.getTotalScore()
        );
    }
    
    public ParticipantStatus(Dealer dealer) {
        this(
                dealer.getNickname(),
                dealer.getFirstCardInfoSnapshot(),
                dealer.getTotalScore()
        );
    }
}
