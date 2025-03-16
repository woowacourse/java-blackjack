package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.participants.PlayerNames;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {

    @Test
    @DisplayName("중복된 이름 테스트")
    void duplicateNamesException() {
        // given
        List<String> names = List.of("a", "a", "b");
        // when & then
        assertThatThrownBy(() -> PlayerNames.fromUsernames(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름이 있습니다.");
    }

}
