package domain;

import domain.user.PlayerName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class PlayerNameTest {

    @Test
    @DisplayName("1자 미만의 이름은 만들 수 없다")
    void validateLess() {
        assertThatThrownBy(() -> new PlayerName("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1자의 이름은 만들 수 있다")
    void validateAtLowerBoundary() {
        assertThatCode(() -> new PlayerName("1"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("5자의 이름은 만들 수 있다")
    void validateAtUpperBoundary() {
        assertThatCode(() -> new PlayerName("12345"))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("5자 초과의 이름은 만들 수 없다")
    void validateOver() {
        assertThatThrownBy(() -> new PlayerName("666666")).isInstanceOf(IllegalArgumentException.class);
    }
}
