package blackjack.domain.participant;

public record ParticipantResult(
        String infoSnapshot,
        int totalScore
) {
    
    public ParticipantResult(Participant participant) {
        this(
                participant.getInfoSnapshot(),
                participant.getTotalScore()
        );
    }
    
    public ParticipantResult(Participant participant, String infoSnapshot) {
        this(
                infoSnapshot,
                participant.getTotalScore()
        );
    }
}
