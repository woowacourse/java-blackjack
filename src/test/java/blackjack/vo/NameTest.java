package blackjack.vo;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {
    @DisplayName("플레이어 이름은 공백일 수 없다")
    @Test
    void validateName() {
        String emptyName = "";

        assertThatThrownBy(() -> new Name(emptyName)).isInstanceOf(IllegalArgumentException.class);
    }
}
