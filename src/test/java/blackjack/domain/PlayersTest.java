package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    @Test
    @DisplayName("플레이어 이름 중복 검증")
    void validateDuplication() {
        assertThatThrownBy(() -> Players.valueOf("a,b,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 사용할 수 없습니다.");
    }
}
