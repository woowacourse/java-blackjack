package blackjack.domain.betting;

import blackjack.domain.gamer.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("배팅 금액이 1000원 미만 또는 100000000원 초과이면 예외를 발생시킨다.")
    @ValueSource(ints = {999, 100000001})
    @ParameterizedTest
    void betMoneyRangeFailTest(int money) {
        //given
        Player player = new Player("eden");
        Bettings bettings = new Bettings();

        //when & then
        Assertions.assertThatThrownBy(() -> bettings.add(player, money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅 금액이 1000원 이상 100000000원 이하여야 합니다.");
    }
}
