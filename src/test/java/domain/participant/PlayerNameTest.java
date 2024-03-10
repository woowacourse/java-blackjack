package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerNameTest {

    @DisplayName("플레이어 이름을 입력하면 인스턴스를 생성한다.")
    @Test
    void createPlayerNameTest() {
        // Given
        String inputName = "kelly";

        // When
        PlayerName playerName = new PlayerName(inputName);

        // Then
        assertThat(playerName).isNotNull();
    }

    @DisplayName("유효하지 않은 길이의 이름이 입력되면 예외를 발생시킨다.")
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"kellykelly"})
    void playerNameLengthTest(String invalidName) {
        // When & Then
        assertThatThrownBy(() -> new PlayerName(invalidName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름은 1글자 이상 5글자 이하여야 합니다.");
    }

    @DisplayName("유효하지 않은 이름을 입력하면 예외를 발생시킨다.")
    @Test
    void validateDeniedNameTest() {
        // Given
        String inputName = "딜러";

        // When & Then
        assertThatThrownBy(() -> new PlayerName(inputName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("설정할 수 없는 플레이어 이름 입니다.");
    }
}
