package domain.game;

import controller.dto.response.PlayerOutcome;
import domain.participant.Participants;
import java.util.List;

public class Referee {
    private final Participants participants;

    public Referee(final Participants participants) {
        this.participants = participants;
    }

    public List<PlayerOutcome> judge() {
        if (participants.getDealer().isBlackJack()) {
            return participants.getPlayersOutcomeIf(
                    GameRule::judgeWhenDealerIsBlackJack
            );
        }

        if (participants.getDealer().isBusted()) {
            return participants.getPlayersOutcomeIf(
                    GameRule::judgeWhenDealerIsBusted
            );
        }

        return participants.getPlayersOutcomeIf(
                GameRule::judgeWhenDealerIsNormal
        );
    }
}
