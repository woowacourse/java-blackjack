package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @DisplayName("이름으로 참여자를 생성한다.")
    @Test
    void createPlayerWithName() {
        Assertions.assertThatCode(() -> new Player("pobi"))
                .doesNotThrowAnyException();
    }
}
