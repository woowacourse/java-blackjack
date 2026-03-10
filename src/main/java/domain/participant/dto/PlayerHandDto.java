package domain.participant.dto;

import domain.participant.Participant;
import java.util.List;

public record PlayerHandDto(String playerName, List<String> handOnCards) {
    public static PlayerHandDto of(Participant participant) {
        return new PlayerHandDto(participant.toDisplayMyName(), participant.disPlayMyCardBundle());
    }
}
