package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    
    @Test
    @DisplayName("이름의 길이는 최소 1글자에서 최대 5글자이다.")
    void validateNameLength() {
        // given
        String name = "millie";

        // expect
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름 길이는 최소 1글자에서 최대 5글자 입니다.");
     }
}
