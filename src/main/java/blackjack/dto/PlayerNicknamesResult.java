package blackjack.dto;

import blackjack.domain.Participants;
import java.util.List;

public record PlayerNicknamesResult(
    List<String> nicknames
) {

    public PlayerNicknamesResult(Participants participants) {
        this(
            participants.getAllPlayerNickname()
        );
    }
}
