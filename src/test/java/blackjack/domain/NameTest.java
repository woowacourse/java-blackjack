package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class NameTest {

    @DisplayName("이름은 양쪽 끝의 공백을 포함하지 않고 1~5자이다.")
    @ParameterizedTest
    @ValueSource(strings = {" 12345 ", "푸우", " 가 나 다 "})
    void validTest(String value) {
        assertDoesNotThrow(() -> new Name(value));
    }

    @DisplayName("이름이 1자 미만 5자 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"123456", " ", ""})
    void inValidTest(String value) {
        assertThatThrownBy(() -> new Name(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 1~5 글자만 허용합니다.");
    }
}
