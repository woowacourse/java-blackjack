package model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {
    @Test
    void 플레이어들의_이름에_중복이_존재하면_예외를_발생한다() {
        // given
        List<String> duplicated = List.of("pobi", "jason", "pobi");

        // when and then
        assertThatThrownBy(() -> PlayerNames.from(duplicated))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
