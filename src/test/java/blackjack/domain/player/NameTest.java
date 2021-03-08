package blackjack.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameTest {
    @ParameterizedTest
    @DisplayName("이름을 null로 생성하려고하면 예외가 발생한다.")
    @NullSource
    void nullThrowException(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest
    @DisplayName("사이에 공백이 있는 이름으로 생성하려고하면 예외가 발생한다.")
    @EmptySource
    void emptyThrowException(String name) {
        assertThatThrownBy(() -> new Name(name)).isInstanceOf(IllegalArgumentException.class);
    }
}