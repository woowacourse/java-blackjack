package blackjack.domain.game.dto;

import blackjack.domain.game.Referee;
import blackjack.domain.game.ResultType;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

public class PlayerMatchDto {
    private static final Referee REFEREE = new Referee();

    private final ResultType matchResult;
    private final String name;

    private PlayerMatchDto(ResultType matchResult, String name) {
        this.matchResult = matchResult;
        this.name = name;
    }

    public static PlayerMatchDto of(Player player, Dealer dealer) {
        return new PlayerMatchDto(REFEREE.generatePlayerResult(player, dealer), player.getName());
    }

    public String getName() {
        return name;
    }

    public ResultType getMatchResult() {
        return matchResult;
    }

    @Override
    public String toString() {
        return "PlayerMatchDto{" +
                "matchResult=" + matchResult +
                ", name='" + name + '\'' +
                '}';
    }
}
