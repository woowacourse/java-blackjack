package blackjack.domain.result;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BetAmountTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThat(new BetAmount(1))
                .isEqualTo(new BetAmount(1));
    }

    @Test
    @DisplayName("생성자 오류 테스트")
    void createError() {
        assertAll(
                () -> assertThatThrownBy(() -> new BetAmount(-1))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요."),
                () -> assertThatThrownBy(() -> new BetAmount(0))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("1 이상의 정수를 입력해주세요.")
        );
    }
}
