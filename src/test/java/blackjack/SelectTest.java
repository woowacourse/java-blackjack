package blackjack;

import static org.assertj.core.api.Assertions.*;

import blackjack.domain.Select;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SelectTest {
    @Test
    @DisplayName("y 또는 n 이외의 값을 입력하였는지 확인")
    void validateOption() {
        assertThatThrownBy(() -> new Select("o"))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
