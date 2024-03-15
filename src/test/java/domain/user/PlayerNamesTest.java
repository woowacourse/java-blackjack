package domain.user;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerNamesTest {
    @Test
    @DisplayName("이름 수가 2명 미만이면 오류를 던진다.")
    void validateNamesAmountMinTest() {
        assertThatThrownBy(() -> new PlayerNames(List.of(new Name("a"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 2명에서 8명이여야 합니다: 1");
    }

    @Test
    @DisplayName("이름 수가 8명 초과이면 오류를 던진다.")
    void validateNamesAmountMaxTest() {
        assertThatThrownBy(() -> new PlayerNames(
                List.of(new Name("a"), new Name("b"), new Name("c"), new Name("d"), new Name("e"), new Name("f"),
                        new Name("g"), new Name("h"), new Name("i"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 수는 2명에서 8명이여야 합니다: 9");
    }

    @Test
    @DisplayName("중복된 이름을 입력하면 오류를 던진다.")
    void validateDuplicatedNamesTest() {
        assertThatThrownBy(() -> new PlayerNames(List.of(new Name("a"), new Name("a"))))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름은 입력할 수 없습니다: a");
    }
}
