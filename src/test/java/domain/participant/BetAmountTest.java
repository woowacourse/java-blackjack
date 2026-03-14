package domain.participant;

import static message.ErrorMessage.BET_AMOUNT_INVALID_UNIT;
import static message.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetAmountTest {
    @ParameterizedTest
    @DisplayName("배팅 금액이 배팅 한도를 벗어날 경우 예외가 발생한다.")
    @ValueSource(ints = {900, 310000})
    void 배팅_금액이_배팅_한도를_벗어날_경우_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BET_AMOUNT_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @DisplayName("배팅 금액이 1000원 단위로 나누어 떨어지지 않을 경우 예외가 발생한다.")
    @ValueSource(ints = {1293, 45399})
    void 배팅_금액이_1000원_단위로_나누어_떨어지지_않을_경우_예외가_발생한다(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BET_AMOUNT_INVALID_UNIT.getMessage());
    }

    @ParameterizedTest
    @DisplayName("배팅 금액이 배팅 한도 이내일 경우 금액이 저장된다.")
    @ValueSource(ints = {1000, 10000, 300000})
    void 배팅_금액이_배팅_한도_이내일_경우_금액이_저장된다(int amount) {
        BetAmount betAmount = new BetAmount(amount);
        assertThat(betAmount.amount()).isEqualTo(amount);
    }
}
