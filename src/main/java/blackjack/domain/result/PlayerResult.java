package blackjack.domain.result;

import blackjack.domain.participant.BettingPlayer;
import blackjack.domain.participant.Player;

import java.util.Objects;

import static blackjack.domain.card.Card.NULL_ERR_MSG;

public class PlayerResult {
    private static final String INVALID_METHOD_FOR_NON_BETTING_GAME_ERR_MSG = "배팅 게임에서만 사용할 수 있습니다.";
    private Player player;
    private ResultType resultType;

    public PlayerResult(Player player, ResultType resultType) {
        Objects.requireNonNull(player, NULL_ERR_MSG);
        Objects.requireNonNull(resultType, NULL_ERR_MSG);
        this.player = player;
        this.resultType = resultType;
    }

    public String resultType() {
        return resultType.getWord();
    }

    public boolean hasSameResult(ResultType type) {
        return this.resultType == type;
    }

    public double computeProfit() {
        if (player instanceof BettingPlayer) {
            BettingPlayer bettingPlayer = (BettingPlayer) player;
            return bettingPlayer.computeProfit(resultType);
        }
        throw new IllegalStateException(INVALID_METHOD_FOR_NON_BETTING_GAME_ERR_MSG);
    }

    public String name() {
        return player.name();
    }

    public Player getPlayer() {
        return player;
    }

    public ResultType getResultType() {
        return resultType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerResult that = (PlayerResult) o;
        return Objects.equals(player, that.player) &&
                resultType == that.resultType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, resultType);
    }
}
