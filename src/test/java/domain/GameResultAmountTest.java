package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultAmountTest {

    private final Player player = new Player(new Name("aa"), new Cards(Collections.emptyList()));
    private final Map<Player, Amount> amount = Map.of(player, new Amount(10000));

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerBlackJackWin() {
        Map<Player, GameResult> result = Map.of(player, GameResult.BLACK_JACK_WIN);
        BettingAmount bettingAmount = new BettingAmount(amount);
        GameResultAmount gameResultAmount = new GameResultAmount(result, bettingAmount.getBettingAmount());

        Map<Name, ResultAmount> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러")).getResultAmount()).isEqualTo(-15000);
        assertThat(resultOfBetting.get(new Name("aa")).getResultAmount()).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerWin() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.WIN);
        BettingAmount bettingAmount = new BettingAmount(amount);
        GameResultAmount gameResultAmount = new GameResultAmount(result, bettingAmount.getBettingAmount());

        Map<Name, ResultAmount> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러")).getResultAmount()).isEqualTo(-10000);
        assertThat(resultOfBetting.get(new Name("aa")).getResultAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerLose() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.LOSE);
        BettingAmount bettingAmount = new BettingAmount(amount);
        GameResultAmount gameResultAmount = new GameResultAmount(result, bettingAmount.getBettingAmount());

        Map<Name, ResultAmount> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러")).getResultAmount()).isEqualTo(10000);
        assertThat(resultOfBetting.get(new Name("aa")).getResultAmount()).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerDraw() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.DRAW);
        BettingAmount bettingAmount = new BettingAmount(amount);
        GameResultAmount gameResultAmount = new GameResultAmount(result, bettingAmount.getBettingAmount());

        Map<Name, ResultAmount> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러")).getResultAmount()).isEqualTo(0);
        assertThat(resultOfBetting.get(new Name("aa")).getResultAmount()).isEqualTo(0);
    }

    public Player createPlayer() {
        Player player = new Player(new Name("aa"), new Cards(Collections.emptyList()));
        player.betAmount(10000);
        return player;
    }

}
