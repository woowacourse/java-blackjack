package blackjack.model.gamer;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @DisplayName("이름에 공백이 포함되면 예외를 발생시킨다.")
    @Test
    void createName() {
        //given
        String name = "test ";

        //when, then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("이름의 길이가 0~10이 아니면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345678901"})
    void createName(String name) {
        //given, when, then
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
