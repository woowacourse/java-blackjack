package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerResultTest {

    @DisplayName("플레이어는 게임 결과에 따라 금액을 수령한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK_JACK_WIN,15000", "WIN,10000", "LOSE,-10000", "TIE,0"})
    void winByBlackJack(PlayerResult playerResult, String profit) {
        //given
        Money money = new Money(10000);
        Money expectedProfit = new Money(profit);

        //when
        Money result = playerResult.earn(money);

        //then
        assertThat(result).isEqualTo(expectedProfit);
    }
}
