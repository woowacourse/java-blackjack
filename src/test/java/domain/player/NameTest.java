package domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NameTest {

    private final int MAX_NAME_LENGTH = 10;

    @Test
    @DisplayName("이름이 비어 있으면 예외가 발생한다")
    void blank() {
        assertThatThrownBy(() -> new Name("")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("이름이 최대 글자 수를 초과하면 예외가 발생한다")
    void nameLength() {
        assertThatThrownBy(() -> new Name("12345678901")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이름은 %d자 이하로 입력해주세요", MAX_NAME_LENGTH));
    }

}
