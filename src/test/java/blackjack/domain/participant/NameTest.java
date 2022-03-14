package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.domain.participant.Name;

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("이름은 빈 문자열일 수 없다.")
    void notNullAndEmpty(String input) {
        // then
        assertThatThrownBy(() -> new Name(input)).isInstanceOf(IllegalArgumentException.class)
            .hasMessage("[ERROR] 이름은 한 글자 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이름은 null일 수 없다.")
    void notNull() {
        // then
        assertThatThrownBy(() -> new Name(null)).isInstanceOf(NullPointerException.class)
            .hasMessage("[ERROR] 이름은 null일 수 없습니다.");
    }
}