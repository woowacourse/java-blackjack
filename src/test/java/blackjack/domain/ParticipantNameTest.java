package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParticipantNameTest {
    @DisplayName("이름이 공백일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    @NullSource
    void validateBlank(String input) {
        assertThatThrownBy(() -> new ParticipantName(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("참여자 이름에 공백을 입력할 수 없습니다.");
    }
}