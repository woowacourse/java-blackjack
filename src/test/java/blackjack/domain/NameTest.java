package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;

class NameTest {

    @ParameterizedTest
    @EmptySource
    @DisplayName("이름이 유효하지 않으면 예외를 던진다.")
    void emptyName(String value) {
        // then
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("공백은 허용되지 않습니다.");
    }

    @Test
    @DisplayName("이름이 제한된 길이를 초과하면 예외를 던진다.")
    void nameLength() {
        // give
        final String value = "1234567890".repeat(10) + "1";

        // then
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("길이는 100자를 초과할 수 없습니다.");

    }
}