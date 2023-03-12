package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class GameResultAmountTest {

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerBlackJackWin() {
        Map<Name, GameResult> result = Map.of(new Name("aa"), GameResult.BLACK_JACK_WIN);
        Map<Name, Amount> bettingDto = Map.of(new Name("aa"), new Amount(10000));
        Betting betting = new Betting(bettingDto);
        GameResultAmount gameResultAmount = new GameResultAmount(betting, result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-15000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerWin() {
        Map<Name, GameResult> result = Map.of(new Name("aa"), GameResult.WIN);
        Map<Name, Amount> bettingDto = Map.of(new Name("aa"), new Amount(10000));
        Betting betting = new Betting(bettingDto);
        GameResultAmount gameResultAmount = new GameResultAmount(betting, result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerLose() {
        Map<Name, GameResult> result = Map.of(new Name("aa"), GameResult.LOSE);
        Map<Name, Amount> bettingDto = Map.of(new Name("aa"), new Amount(10000));
        Betting betting = new Betting(bettingDto);
        GameResultAmount gameResultAmount = new GameResultAmount(betting, result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerDraw() {
        Map<Name, GameResult> result = Map.of(new Name("aa"), GameResult.DRAW);
        Map<Name, Amount> bettingDto = Map.of(new Name("aa"), new Amount(10000));
        Betting betting = new Betting(bettingDto);
        GameResultAmount gameResultAmount = new GameResultAmount(betting, result);

        Map<Name, Integer> resultOfBetting = gameResultAmount.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(0);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(0);
    }

}
