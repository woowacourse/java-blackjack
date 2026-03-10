package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public class ParticipantHandMapper {

    private ParticipantHandMapper() {
    }

    public static ParticipantHand map(Participant participant) {
        return new ParticipantHand(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }

    public static ParticipantHand map(Participant participant, int cardCount) {
        return new ParticipantHand(participant.toDisplayMyName(), extractAsCount(participant, cardCount));
    }

    private static List<String> extractAsCount(Participant participant, int cardCount) {
        return participant.disPlayMyCardBundle().stream().limit(cardCount).toList();
    }


}
