package blackjack.dto;

import blackjack.domain.Participants;
import java.util.List;

public record PlayerNicknamesResult(
    List<String> nicknames
) {

    public static PlayerNicknamesResult from(Participants participants) {
        return new PlayerNicknamesResult(participants.getAllPlayerNickname());
    }
}
