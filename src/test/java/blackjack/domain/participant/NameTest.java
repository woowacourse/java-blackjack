package blackjack.domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {

    @ParameterizedTest
    @DisplayName("이름을 null로 생성하려고하면 예외가 발생한다.")
    @NullSource
    void nullThrowException(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(NullPointerException.class)
                .hasMessage("이름은 null일 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("빈 이름으로 생성하려고하면 예외가 발생한다.")
    @EmptySource
    void emptyThrowException(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("공백의 이름으로 생성하려고하면 예외가 발생한다.")
    void emptySpacesThrowException() {
        assertThatThrownBy(() -> new Name("   ")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 비어있을 수 없습니다.");
    }
}