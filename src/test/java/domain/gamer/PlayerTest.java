package domain.gamer;

import exception.NameFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    @DisplayName("잘못된 이름 입력시 예외처리")
    void isValidNameTest() {
        assertThatThrownBy(() -> new Player("po/bi")).isInstanceOf(NameFormatException.class);
    }
}