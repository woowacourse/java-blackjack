package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class NameTest {

    @Test
    @DisplayName("이름의 길이가 1이상이 아닌 경우 예외가 발생한다")
    void throwsExceptionWhenNameIsLessThanOneCharacter() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(""))
                .withMessage("이름의 길이는 1이상입니다.");
    }
}
