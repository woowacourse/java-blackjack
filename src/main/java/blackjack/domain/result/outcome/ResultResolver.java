package blackjack.domain.result.outcome;

import blackjack.domain.participant.Player;
import blackjack.domain.result.ResultType;

import java.util.List;

public interface ResultResolver<T extends Player, E, U> {
    E showPlayerResult(T player, ResultType type);

    U computeDealerResult(List<PlayerResult<T, E, U>> playerResults);
}
