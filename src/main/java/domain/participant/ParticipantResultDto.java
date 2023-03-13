package domain.participant;

import java.util.List;

public class ParticipantResultDto {

    private final String participantName;
    private final List<String> cardNames;

    public ParticipantResultDto(String participantName, List<String> cardNames) {
        this.participantName = participantName;
        this.cardNames = cardNames;
    }

    public String getParticipantName() {
        return participantName;
    }

    public List<String> getCardNames() {
        return List.copyOf(cardNames);
    }
}
