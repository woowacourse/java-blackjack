package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public class PlayerHandMapper {

    public static PlayerHandDto from(Participant participant) {
        return new PlayerHandDto(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }

    public static PlayerHandDto from(Participant participant, int cardCount) {
        return new PlayerHandDto(participant.toDisplayMyName(), extractAsCount(participant, cardCount));
    }

    private static List<String> extractAsCount(Participant participant, int cardCount) {
        return participant.disPlayMyCardBundle().stream().limit(cardCount).toList();
    }


}
