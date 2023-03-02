package balckjack.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CourtCardTest {

    @ParameterizedTest
    @ValueSource(strings = {"J", "Q", "K"})
    void symbolIsValid(String input) {
        Assertions.assertThatNoException().isThrownBy(() -> new CourtCard(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"k", "a", "A", "E"})
    void symbolIsInvalid(String input) {
        Assertions.assertThatThrownBy(() -> new CourtCard(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"J", "Q", "K"})
    void getValue(String input) {
        Card courtCard = new CourtCard(input);
        Assertions.assertThat(courtCard.getValue()).isEqualTo(10);
    }
}