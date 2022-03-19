package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BettingTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThat(new Betting(1))
                .isEqualTo(new Betting(1));
    }

    @Test
    @DisplayName("생성자 오류 테스트")
    void createError() {
        assertAll(
                () -> assertThatThrownBy(() -> new Betting(-1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요."),
                () -> assertThatThrownBy(() -> new Betting(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요.")
        );
    }

    @Test
    @DisplayName("배율 테스트")
    void multipleTest() {
        assertThat(new Betting(10000).getMultipliedMoney(1.5))
                .isEqualTo(15000);
    }
}
