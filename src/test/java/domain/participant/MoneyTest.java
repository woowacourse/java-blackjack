package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("금액을 입력하지 않으면 예외처리")
    void shouldFailMoneyIsBlank() {
        assertThatThrownBy(() -> new Money(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");
    }

    @Test
    @DisplayName("금액에 공백이 포함되면 예외처리")
    void shouldFailMoneyContainSpace() {
        assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> new Money(" "))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");

            softly.assertThatThrownBy(() -> new Money("10 0"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");

        });
    }

    @Test
    @DisplayName("금액에 숫자 이외의 문자가 포함되면 예외처리")
    void shouldFailMoneyContainNotNumber() {
        assertSoftly(softly -> {
            softly.assertThatThrownBy(() -> new Money("dino"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");

            softly.assertThatThrownBy(() -> new Money("wow"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");

            softly.assertThatThrownBy(() -> new Money("12@"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");

            softly.assertThatThrownBy(() -> new Money("10,000"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 베팅 금액은 숫자로 입력해 주세요");
        });
    }

    @Test
    @DisplayName("금액이 0이면 예외처리")
    void shouldFailMoneyIsZero() {
        assertThatThrownBy(() -> new Money("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 베팅 금액은 0이 될 수 없습니다.");
    }
}
