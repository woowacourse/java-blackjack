package domain;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void playerName_예외처리_테스트(String nameInput) {
        assertThatThrownBy(() -> new PlayerName(nameInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성_테스트() {
        //given, when, then
        assertDoesNotThrow(() -> new PlayerName("judy"));
    }
}
