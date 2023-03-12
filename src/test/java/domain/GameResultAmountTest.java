package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultAmountTest {

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerBlackJackWin() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.BLACK_JACK_WIN);
        GameResultAmount gameResultAmount = new GameResultAmount(result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-15000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerWin() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.WIN);
        GameResultAmount gameResultAmount = new GameResultAmount(result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerLose() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.LOSE);
        GameResultAmount gameResultAmount = new GameResultAmount(result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerDraw() {
        Map<Player, GameResult> result = Map.of(createPlayer(), GameResult.DRAW);
        GameResultAmount gameResultAmount = new GameResultAmount(result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(0);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(0);
    }

    public Player createPlayer() {
        Player player = new Player(new Name("aa"), new Cards(Collections.emptyList()));
        player.betAmount(10000);
        return player;
    }

}
