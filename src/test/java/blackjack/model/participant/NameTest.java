package blackjack.model.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class NameTest {

    @NullAndEmptySource
    @ParameterizedTest
    @DisplayName("이름에는 공백이 있을 수 없습니다.")
    void createName(String rawName) {
        assertThatThrownBy(() -> new Name(rawName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에는 공백을 사용할 수 없습니다.");
    }
}
