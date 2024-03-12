package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    @DisplayName("중복된 이름을 입력하면 오류를 던진다.")
    void validateDuplicatedNamesTest() {
        assertThatThrownBy(
                () -> new Players(List.of(new Player(new Name("a")), new Player(new Name("a"))))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 입력할 수 없습니다: a");
    }
}
