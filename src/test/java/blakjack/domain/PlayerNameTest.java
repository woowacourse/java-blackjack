package blakjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {
    @Test
    @DisplayName("딜러라는 이름으로 생성시 예외 발생")
    void invalid() {
        assertThatThrownBy(() -> new PlayerName("딜러"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
