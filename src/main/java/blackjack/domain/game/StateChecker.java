package blackjack.domain.game;

import blackjack.dto.ResultParticipantDto;

public interface StateChecker {

    boolean isState(ResultParticipantDto player, ResultParticipantDto dealer);
}
