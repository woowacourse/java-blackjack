package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
}
