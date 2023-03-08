package batting;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AmountTest {
    @Test
    @DisplayName("숫자를 입력받아 인스턴스를 생성한다.")
    void create() {
        assertThatCode(() -> new Amount(120))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력받은 숫자가 음수인 경우 예외를 던진다.")
    void createFailNegative() {
        assertThatThrownBy(() -> new Amount(-1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("100이상의 정수만 입력 가능합니다.");
    }
    
}
