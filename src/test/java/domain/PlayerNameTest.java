package domain;

import domain.player.PlayerName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void playerName이_Blank_라면_예외처리(String nameInput) {
        assertThatThrownBy(() -> new PlayerName(nameInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("빈 칸");
    }
}
