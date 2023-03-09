package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultCalculatorTest {

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerBlackJackWin() {
        Map<Player, GameResult> result = Map.of(new Player(new Name("aa"), new Cards(Collections.emptyList())), GameResult.BLACK_JACK_WIN);
        Map<Name, Money> bettingDto = Map.of(new Name("aa"), Money.of(10000));
        Betting betting = new Betting(bettingDto);
        ResultCalculator resultCalculator = new ResultCalculator(betting, result);

        Map<Name, Integer> resultOfBetting = resultCalculator.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-15000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(15000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerWin() {
        Map<Player, GameResult> result = Map.of(new Player(new Name("aa"), new Cards(Collections.emptyList())), GameResult.WIN);
        Map<Name,  Money> bettingDto = Map.of(new Name("aa"), Money.of(10000));
        Betting betting = new Betting(bettingDto);
        ResultCalculator resultCalculator = new ResultCalculator(betting, result);

        Map<Name, Integer> resultOfBetting = resultCalculator.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(-10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerLose() {
        Map<Player, GameResult> result = Map.of(new Player(new Name("aa"), new Cards(Collections.emptyList())), GameResult.LOSE);
        Map<Name,  Money> bettingDto = Map.of(new Name("aa"), Money.of(10000));
        Betting betting = new Betting(bettingDto);
        ResultCalculator resultCalculator = new ResultCalculator(betting, result);

        Map<Name, Integer> resultOfBetting = resultCalculator.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(10000);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(-10000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭으로 이긴 경우를 계산한다.")
    void playerDraw() {
        Map<Player, GameResult> result = Map.of(new Player(new Name("aa"), new Cards(Collections.emptyList())), GameResult.DRAW);
        Map<Name, Money> bettingDto = Map.of(new Name("aa"), Money.of(10000));
        Betting betting = new Betting(bettingDto);
        ResultCalculator resultCalculator = new ResultCalculator(betting, result);

        Map<Name, Integer> resultOfBetting = resultCalculator.getResultOfBetting();

        assertThat(resultOfBetting.get(new Name("딜러"))).isEqualTo(0);
        assertThat(resultOfBetting.get(new Name("aa"))).isEqualTo(0);
    }

}
