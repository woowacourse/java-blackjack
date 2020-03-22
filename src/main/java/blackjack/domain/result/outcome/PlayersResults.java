package blackjack.domain.result.outcome;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.ResultType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

// TODO: 2020-03-23 List<PlayerResult> 입력받는 생성자 삭제 고려
public class PlayersResults implements Computable<Map<ResultType, Long>> {
    private static final String EMPTY_ERR_MSG = "Empty 리스트가 인자로 들어올 수 없습니다.";

    private final List<PlayerResult> playerResults;

    public PlayersResults(Players<Player> players, Dealer dealer) {
        this.playerResults = players.stream()
                .map(player -> new PlayerResult(player, dealer))
                .collect(Collectors.toList());
    }

    public PlayersResults(List<PlayerResult> playerResults) {
        Objects.requireNonNull(playerResults, NULL_ERR_MSG);

        if (playerResults.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERR_MSG);
        }

        this.playerResults = playerResults;
    }

    @Override
    public Map<ResultType, Long> computeDealerResult() {
        return playerResults.stream()
                .map(PlayerResult::getResultType)
                .map(ResultType::reverse)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Stream<PlayerResult> stream() {
        return this.playerResults.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayersResults that = (PlayersResults) o;
        return Objects.equals(playerResults, that.playerResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResults);
    }
}
