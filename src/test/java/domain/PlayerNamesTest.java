package domain;

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
}
