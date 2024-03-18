package blackjack.domain.betting;

import blackjack.domain.gamer.Player;
import blackjack.domain.result.GameResult;
import blackjack.domain.result.GameResults;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BettingsTest {

    @DisplayName("배팅 금액이 1000원 이상 또는 100000000원 이하이면 정상적으로 생성된다.")
    @ValueSource(ints = {1000, 100000000})
    @ParameterizedTest
    void betMoneyRangeSuccessTest(int money) {
        //given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();

        //when & then
        Assertions.assertThatCode(() -> bettings.add(player, money))
                .doesNotThrowAnyException();
    }

    @DisplayName("배팅 금액이 0원 미만 또는 100000000원 초과이면 예외를 발생시킨다.")
    @ValueSource(ints = {-1, 100000001})
    @ParameterizedTest
    void betMoneyRangeFailTest(int money) {
        //given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();

        //when & then
        Assertions.assertThatThrownBy(() -> bettings.add(player, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액이 0원 이상 100000000원 이하여야 합니다.");
    }

    @DisplayName("게임 결과가 블랙잭이면 배팅 금액의 1.5배를 수익으로 받는다.")
    @Test
    void blackjackTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.BLACKJACK);

        int benefitMoney = bettings.calculateProfit(player, GameResult.BLACKJACK);

        // then
        Assertions.assertThat(benefitMoney).isEqualTo(15000);
    }

    @DisplayName("게임결과가 무승부이면 수익이 0이다.")
    @Test
    void drawTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.DRAW);
        int benefitMoney = bettings.calculateProfit(player, GameResult.DRAW);

        // then
        Assertions.assertThat(benefitMoney).isEqualTo(0);
    }

    @DisplayName("게임결과가 승리이면 수익이 1배이다.")
    @Test
    void winTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.WIN);
        int benefitMoney = bettings.calculateProfit(player, GameResult.WIN);

        // then
        Assertions.assertThat(benefitMoney).isEqualTo(10000);
    }

    @DisplayName("게임결과가 패배이면 수익이 -1배이다.")
    @Test
    void loseTest() {
        // given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();
        bettings.add(player, 10000);

        // when
        GameResults gameResults = new GameResults();
        gameResults.add(player, GameResult.LOSE);
        int benefitMoney = bettings.calculateProfit(player, GameResult.LOSE);

        // then
        Assertions.assertThat(benefitMoney).isEqualTo(-10000);
    }
}
