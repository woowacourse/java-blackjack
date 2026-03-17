package dto.response;

import domain.player.ParticipantGameInfo;
import java.util.List;

public record ParticipantsGameInfoDto(
        List<ParticipantGameInfo> participants) {
}
