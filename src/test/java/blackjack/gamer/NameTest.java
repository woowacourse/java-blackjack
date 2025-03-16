package blackjack.gamer;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    @DisplayName("이름은 공백일 수 없다.")
    @Test
    void notEmptyName() {
        // given
        String nameInput = "";

        // when // then
        assertThatCode(() -> new Nickname(nameInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageStartingWith("[ERROR]");
    }
}
