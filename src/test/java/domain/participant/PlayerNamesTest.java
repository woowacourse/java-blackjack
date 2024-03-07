package domain.participant;

import domain.participant.PlayerNames;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlayerNamesTest {

    @DisplayName("플레이어 이름들을 입력하면 인스턴스를 생성한다.")
    @Test
    void createPlayerNamesTest() {
        // Given
        List<String> inputPlayerNames = List.of("kelly", "movin");

        // When
        PlayerNames playerNames = PlayerNames.of(inputPlayerNames);

        // Then
        Assertions.assertThat(playerNames).isNotNull();
    }

    @DisplayName("플레이어 이름에 중복이 있다면 예외를 발생한다.")
    @Test
    void validateDuplicatedTest() {
        // Given
        List<String> inputPlayerNames = List.of("kelly", "kelly");

        // When & Then
        Assertions.assertThatThrownBy(() -> PlayerNames.of(inputPlayerNames))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어 이름에 중복이 존재합니다.");
    }
}
