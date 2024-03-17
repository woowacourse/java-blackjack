package domain;

import domain.gamer.Name;
import domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BettingTest {

    @DisplayName("베팅 금액이 5000원 미만, 500000원 초과면 예외를 던진다.")
    @ParameterizedTest
    @ValueSource(strings = {"4999", "500001"})
    void invalidMoneyRangeTest(String money) {
        //given
        Player player = new Player(new Name("pobi"));

        //then
        assertThatThrownBy(() -> new Betting(Map.of(player, money)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 5000원 이상, 500000원 이하만 가능합니다.");
    }

    @DisplayName("게임 결과가 주어지면, 플레이어의 수익을 구한다.")
    @Test
    void calculateProfit() {
        //given
        Player player = new Player(new Name("pobi"));
        String money = "500000";
        Betting betting = new Betting(Map.of(player, money));
        PlayerResult playerResult = PlayerResult.BLACK_JACK_WIN;
        BigDecimal expectedProfit = new BigDecimal(money).multiply(new BigDecimal("1.5"));

        //when
        BigDecimal result = betting.calculateProfit(player, playerResult);

        //then
        assertThat(result).isEqualByComparingTo(expectedProfit);
    }
}
