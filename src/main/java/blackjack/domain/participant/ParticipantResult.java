package blackjack.domain.participant;

public record ParticipantResult(
        String nickname,
        String cardStatus,
        int totalScore
) {
    
    public ParticipantResult(Participant participant) {
        this(
                participant.getNickname(),
                participant.getCardStatus(),
                participant.getTotalScore()
        );
    }
    
    public ParticipantResult(Participant participant, String cardStatus) {
        this(
                participant.getNickname(),
                cardStatus,
                participant.getTotalScore()
        );
    }
}
