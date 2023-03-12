package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class NameTest {
    @Test
    @DisplayName("이름을 받아 플레이어를 생성한다")
    void createNameTest() {
        Name name = new Name("boxster");

        assertThat(name.getValue()).isEqualTo("boxster");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("이름에 공백 혹은 빈값만 들어온다면 예외를 발생시킨다")
    void createException(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }

    @Test
    @DisplayName("이름 앞뒤에 공백이 있는 경우 제거하고 저장한다")
    void createNameWithStripBlank() {
        Name name = new Name(" jamie ");

        assertThat(name.getValue()).isEqualTo("jamie");
    }
}
