package blackjack.domain.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BetAmountTest {

    @ParameterizedTest
    @DisplayName("베팅 금액이 숫자가 아닌 경우 에러")
    @ValueSource(strings = {"\n", " ", "", "a", "ㅁ", "1a"})
    void givenBetAmountIsNotNumber_thenFail(String amount) {
        //then
        Assertions.assertThatThrownBy(() -> BetAmount.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 금액은 숫자만 입력할 수 있습니다.");
    }

    @ParameterizedTest
    @DisplayName("베팅 금액이 1000원 미만 경우 에러")
    @ValueSource(strings = {"999", "0", "-1", "-1000", "-999"})
    void givenBetAmountUnder1000_thenFail(String amount) {
        //then
        Assertions.assertThatThrownBy(() -> BetAmount.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("베팅 최소 금액은 1000원 입니다.");
    }
}
