package model.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import constant.ErrorMessage;
import org.junit.jupiter.api.Test;

public class BetPriceTest {
    @Test
    public void 정상_작동() {
        BetPrice betPrice = new BetPrice(10000);
        assertThat(betPrice.value()).isEqualTo(10000);
    }

    @Test
    public void 음수_및_제로_입력_예외() {
        assertAll(
                () -> assertThatThrownBy(() -> new BetPrice(0))
                        .isExactlyInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.OUT_OF_RANGE_BET.getMessage()),
                () -> assertThatThrownBy(() -> new BetPrice(-10000))
                        .isExactlyInstanceOf(IllegalArgumentException.class)
                        .hasMessage(ErrorMessage.OUT_OF_RANGE_BET.getMessage())
        );
    }
}
