package blackjack.domain.game;

import blackjack.dto.ResultDto;

public interface StateChecker {

    boolean isState(ResultDto player, ResultDto dealer);
}
