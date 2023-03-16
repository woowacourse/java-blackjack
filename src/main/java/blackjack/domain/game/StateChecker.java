package blackjack.domain.game;

import blackjack.dto.ResultStateDto;

public interface StateChecker {

    boolean isState(ResultStateDto player, ResultStateDto dealer);
}
