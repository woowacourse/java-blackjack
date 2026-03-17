package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BetPriceTest {
    @Test
    public void 정상_작동() {
        BetPrice betPrice = new BetPrice(10000);
        assertThat(betPrice.value()).isEqualTo(10000);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10000})
    public void 범위_밖_입력_예외(int price) {
        assertThatThrownBy(() -> new BetPrice(price)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.OUT_OF_RANGE_BET.getMessage());
    }
}
