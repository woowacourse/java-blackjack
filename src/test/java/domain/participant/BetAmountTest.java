package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participant.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("BetAmount는 ")
public class BetAmountTest {

    @ParameterizedTest(name = "1,000원 단위로 입력가능하다 money = {0}")
    @ValueSource(ints = {6383, 980403, 12345})
    void _1000_원_단위로_입력가능_하다(int money) {
        // given
        // when

        // then
        assertThatThrownBy(() -> BetAmount.from(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 배팅 금액은 1,000원 단위만 가능합니다");
    }

    @ParameterizedTest(name = "1,000 ~ 1,000,000 사이 금액만 입력 가능하다 money = {0}")
    @ValueSource(ints = {0, 2_000_000})
    void _1_000_에서_1_000_000_사이_금액만_입력가능하다(int money) {
        // given
        // when

        // then
        assertThatThrownBy(() -> BetAmount.from(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1,000 ~ 1,000,000 사이의 금액만 배팅 가능합니다");
    }
}
