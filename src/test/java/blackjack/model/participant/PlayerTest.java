package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @Test
    void build_exception_blank() {
        assertThatThrownBy(() -> new Player(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }

    @DisplayName("이름이 null값 이면 예외가 발생한다")
    @Test
    void build_exception_null() {
        assertThatThrownBy(() -> new Player(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백이거나 없을 수 없습니다.");
    }
}
