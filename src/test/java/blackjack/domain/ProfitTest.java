package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfitTest {

    @DisplayName("Profit 객체 생성 성공")
    @Test
    void create() {
        assertThatCode(() -> new Profit(1000)).doesNotThrowAnyException();
    }

    @DisplayName("Profit 객체 생성 실패 : 음수")
    @Test
    void create_negativeMoney_fail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Profit(-1000))
                .withMessageContaining("음수");
    }

    @DisplayName("Profit 객체 생성 실패 : 0")
    @Test
    void create_zeroMoney_fail() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Profit(0))
                .withMessageContaining("0");
    }
}