package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public class ParticipantHandDtoMapper {

    private ParticipantHandDtoMapper() {
    }

    public static ParticipantHandDto map(Participant participant) {
        return new ParticipantHandDto(participant.getName().name(), participant.disPlayMyCardBundle());
    }

    public static ParticipantHandDto map(Participant participant, int cardCount) {
        return new ParticipantHandDto(participant.getName().name(), extractAsCount(participant, cardCount));
    }

    private static List<String> extractAsCount(Participant participant, int cardCount) {
        return participant.disPlayMyCardBundle().stream().limit(cardCount).toList();
    }


}
