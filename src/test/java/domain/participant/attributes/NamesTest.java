package domain.participant.attributes;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NamesTest {
    @DisplayName("중복된 이름이 있으면 예외를 던진다.")
    @Test
    void validateNotDuplicated() {
        List<String> nameInputs = List.of("포비", "제우스", "제우스", "조이썬");
        assertThatThrownBy(() -> new Names(nameInputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복된 이름을 입력할 수 없습니다: 제우스");
    }
}
