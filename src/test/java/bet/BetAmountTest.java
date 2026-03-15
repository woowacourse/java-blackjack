package bet;

import domain.bet.BetAmount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BetAmountTest {
    private static final String BET_AMOUNT_UNIT_ERROR_MESSAGE = "베팅금액을 100원단위로 맞춰주세요";

    @ParameterizedTest
    @ValueSource(ints = {112, 224, 33545})
    @DisplayName("베팅금이 100의 배수가 아니면 예외가 발생한다.")
    void constructor_throwsException_whenAmountIsNotMultipleOf100(int amount) {
        assertThatThrownBy(() -> new BetAmount(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(BET_AMOUNT_UNIT_ERROR_MESSAGE);
    }

    @ParameterizedTest
    @ValueSource(ints = {1000, 2000, 23000})
    @DisplayName("베팅금이 100의 배수이면 생성에 성공한다.")
    void constructor_successFullyCreate_whenAmountIsMultipleOf100(int amount) {
        BetAmount betAmount = new BetAmount(amount);
        assertThat(betAmount.getBetAmount()).isEqualTo(amount);
    }
}
