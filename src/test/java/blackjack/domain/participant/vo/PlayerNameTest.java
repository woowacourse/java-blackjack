package blackjack.domain.participant.vo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class PlayerNameTest {
    @ParameterizedTest
    @NullSource
    @DisplayName("플레이어의 이름에 null이 들어오면 예외를 발생시킨다.")
    void createExceptionByNull(String nullValue) {
        assertThatThrownBy(() -> new PlayerName(nullValue))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("이름에는 null이 들어올 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어의 이름에 공백이 들어오면 예외를 발생시킨다.")
    void createExceptionByEmpty(String blankValue) {
        assertThatThrownBy(() -> new PlayerName(blankValue))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름에는 공백이 들어올 수 없습니다.");
    }

}