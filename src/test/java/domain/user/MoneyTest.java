package domain.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {

    @Test
    @DisplayName("생성 확인")
    void create() {
        assertThatCode(() -> new Money(1000))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("0 이하일 경우")
    @ValueSource(ints = {0, -1})
    void createWithInvalidInput(int money) {
        assertThatThrownBy(() -> new Money(money))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("배팀금액은 0원 이하일 수 없습니다.");
    }
}