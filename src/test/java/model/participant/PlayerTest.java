package model.participant;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.IllegalBlackjackInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerTest {

    @DisplayName("플레이어를 생성한다.")
    @ParameterizedTest
    @ValueSource(strings = {"호떡", "훌라", "윌슨", "네오"})
    void createTest(final String name) {
        assertThatCode(() -> new Player(name)).doesNotThrowAnyException();
    }

    @DisplayName("플레이어의 이름이 null이거나 blank라면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void validateNullOrEmptyTest(final String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }

    @DisplayName("플레이어의 이름이 10자 초과라면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"12345678901", "11글자짜리이름입니다"})
    void validateLengthTest(final String name) {
        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(IllegalBlackjackInputException.class);
    }
}
