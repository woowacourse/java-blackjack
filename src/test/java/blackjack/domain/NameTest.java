package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.exception.InvalidNameLengthException;
import blackjack.exception.NonAlphabeticNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {
    @DisplayName("이름 길이가 1 이상 5 이하가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "gambas"})
    void invalidNameLengthTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(InvalidNameLengthException.class);
    }

    @DisplayName("이름에 알파벳이 아닌 문자가 포함된 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"감자s", "마son", "pobi!", "po bi"})
    void nonAlphabeticNameTest(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(NonAlphabeticNameException.class);
    }
}