package domain.player;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerNameTest {

    @ParameterizedTest(name = "create()는 파라미터로 입력된 name이 null이거나 비어 있으면 예외가 발생한다")
    @NullAndEmptySource
    void create_givenEmptyName_thenFail(final String name) {
        assertThatThrownBy(() -> PlayerName.create(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백일 수 없습니다.");
    }
}
