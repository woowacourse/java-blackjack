package domain.name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamesTest {
    @DisplayName("중복된 이름이 존재하는 경우 예외가 발생한다.")
    @Test
    void validateNotDuplicate() {
        List<Name> names = List.of(new Name("tobi"), new Name("tobi"));
        assertThatThrownBy(() -> new Names(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 가진 플레이어는 존재할 수 없습니다.");
    }
}
