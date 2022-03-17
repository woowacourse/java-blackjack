package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GamerTest {

    @DisplayName("이름은 공백이 아니어야한다.")
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   "})
    void 이름_공백_예외(String value) {
        assertThatThrownBy(() -> new Player(value, 1000, CardsTestDataGenerator.generateBlackJackCards()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이름은 공백이 아니어야합니다.");
    }

    @DisplayName("이름이 정상적으로 생성된다.")
    @Test
    void 이름_생성_정상() {
        assertDoesNotThrow(() -> new Player("pobi", 1000, CardsTestDataGenerator.generateBlackJackCards()));
    }
}