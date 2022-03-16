package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerMatchResult {

    private final ResultType matchResult;
    private final String name;

    private PlayerMatchResult(ResultType matchResult, String name) {
        this.matchResult = matchResult;
        this.name = name;
    }

    public static PlayerMatchResult of(Player player, Dealer dealer) {
        return new PlayerMatchResult(player.compareWith(dealer), player.getName());
    }

    public String getName() {
        return name;
    }

    public ResultType getMatchResult() {
        return matchResult;
    }

    @Override
    public String toString() {
        return "PlayerMatchResult{" +
                "matchResult=" + matchResult +
                ", name='" + name + '\'' +
                '}';
    }
}
