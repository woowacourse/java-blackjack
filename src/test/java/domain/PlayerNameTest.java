package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void playerName이_Blank_라면_예외처리(String nameInput) {
        Assertions.assertThatThrownBy(() -> new PlayerName(nameInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}