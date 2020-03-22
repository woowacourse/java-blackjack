package blackjack.domain.result.outcome;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

// TODO: 2020-03-23 List<PlayerResult> 입력받는 생성자 삭제 고려
public class BettingPlayersResults implements Computable<Double> {
    private final List<BettingPlayerResult> playerResults;

    public BettingPlayersResults(List<BettingPlayerResult> playerResults) {
        Objects.requireNonNull(playerResults, NULL_ERR_MSG);
        this.playerResults = playerResults;
    }

    public BettingPlayersResults(Players<BettingPlayer> players, Dealer dealer) {
        this.playerResults = players.stream()
                .map(player -> new BettingPlayerResult(player, dealer))
                .collect(Collectors.toList());
    }

    @Override
    public Double computeDealerResult() {
        return -playerResults.stream()
                .mapToDouble(BettingPlayerResult::showPlayerResult)
                .sum();
    }

    public Stream<BettingPlayerResult> stream() {
        return this.playerResults.stream();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingPlayersResults that = (BettingPlayersResults) o;
        return Objects.equals(playerResults, that.playerResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResults);
    }

}
