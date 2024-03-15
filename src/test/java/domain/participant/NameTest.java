package domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.participant.Name;

class NameTest {

    @DisplayName("공백이 입력되면 예외를 발생한다.")
    @Test
    void validateBlank() {
        assertThatThrownBy(() -> new Name(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어 이름을 입력해주세요. 예) 포비, 호티, 제우스");
    }
}
