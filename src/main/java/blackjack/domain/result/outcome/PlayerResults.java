package blackjack.domain.result.outcome;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class PlayerResults<T extends Player, E, U> {

    private final List<PlayerResult<T, E, U>> playerResults;
    private final ResultResolver<T, E, U> resultResolver;

    public PlayerResults(Players<T> players, Dealer dealer, ResultResolver<T, E, U> resultResolver) {
        Objects.requireNonNull(players, NULL_ERR_MSG);
        Objects.requireNonNull(dealer, NULL_ERR_MSG);
        Objects.requireNonNull(resultResolver, NULL_ERR_MSG);

        this.playerResults = players.stream()
                .map(player -> new PlayerResult<>(player, dealer, resultResolver))
                .collect(Collectors.toList());
        this.resultResolver = resultResolver;
    }

    public U computeDealerResult() {
        return resultResolver.computeDealerResult(playerResults);
    }

    public Stream<PlayerResult<T, E, U>> stream() {
        return this.playerResults.stream();
    }
}
