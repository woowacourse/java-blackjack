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
    @ValueSource(longs = {0, -10000})
    public void 양의_정수가_아닐_때_예외(long price) {
        assertThatThrownBy(() -> new BetPrice(price)).isExactlyInstanceOf(IllegalArgumentException.class).hasMessage(ErrorMessage.NEGATIVE_BET.getMessage());
    }

    @Test
    public void 범위_밖의_양의_정수일_때_예외() {
        assertThatThrownBy(
                () -> new BetPrice(9_223_372_036_854_775_807L)).isExactlyInstanceOf(
                IllegalArgumentException.class).hasMessage(ErrorMessage.OUT_OF_MAXIMUM_RANGE_BET.getMessage());
    }
}
