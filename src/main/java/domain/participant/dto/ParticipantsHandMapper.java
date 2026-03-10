package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public class PlayerHandMapper {

    private ParticipantsHandMapper() {
    }

    public static ParticipantsHandDto from(Participant participant) {
        return new ParticipantsHandDto(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }

    public static PlayerHandDto from(Participant participant, int cardCount) {
        return new PlayerHandDto(participant.toDisplayMyName(), extractAsCount(participant, cardCount));
    }

    private static List<String> extractAsCount(Participant participant, int cardCount) {
        return participant.disPlayMyCardBundle().stream().limit(cardCount).toList();
    }


}
