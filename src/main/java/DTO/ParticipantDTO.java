package DTO;

public class ParticipantDTO {

    private final String participantName;
    private final String participantCards;

    public ParticipantDTO(final String participantName, final String participantCards) {
        this.participantName = participantName;
        this.participantCards = participantCards;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String getParticipantCards() {
        return participantCards;
    }
}
