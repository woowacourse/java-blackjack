package domain.participant;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersTest {

    @Test
    @DisplayName("플레이어는 최대 5명만 참가 가능하다.")
    void playerMaxNumberTest() {
        Assertions.assertThatThrownBy(
                () -> Players.of(List.of("one", "two", "three", "four", "five", "six")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("플레이어 최대 인원수는 5명입니다.");
    }
}
