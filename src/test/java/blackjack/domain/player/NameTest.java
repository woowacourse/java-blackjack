package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("이름 테스트")
class NameTest {

    @ParameterizedTest
    @CsvSource({
            "''",
            "' '",
    })
    @DisplayName("이름의 길이가 1이상이 아닌 경우 예외가 발생한다")
    void throwsExceptionWhenNameIsLessThanOneCharacter(String name) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(name))
                .withMessage("최소 이름의 길이는 1자 이상입니다.");
    }

    @Test
    @DisplayName("이름이 NULL인 경우 예외가 발생한다")
    void throwsExceptionWhenNameIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Name(null))
                .withMessage("최소 이름의 길이는 1자 이상입니다.");
    }
}
