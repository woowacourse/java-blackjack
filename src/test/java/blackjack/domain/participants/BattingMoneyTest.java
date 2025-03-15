package blackjack.domain.participants;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BattingMoneyTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 999, 1500})
    void 배팅금액이_1000원_단위가_아니라면_예외를_발생시킨다(int amount) {
        //given & when & then
        assertThatThrownBy(() -> new BettingMoney(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팅금액은 1000원 단위여야합니다.");
    }
}
