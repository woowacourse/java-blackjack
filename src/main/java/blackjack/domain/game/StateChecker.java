package blackjack.domain.game;

import blackjack.dto.ResultStateDto;

@FunctionalInterface
public interface StateChecker {

    boolean isState(ResultStateDto player, ResultStateDto dealer);
}
