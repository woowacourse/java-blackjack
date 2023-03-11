package blackjack.domain.participant;

import blackjack.domain.participant.Players;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PlayersTest {

    @Test
    void validateNull() {
        Assertions.assertThatThrownBy(() -> new Players(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름에 아무것도 들어오지 않았습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"a,b,,", "", " "})
    void validateFormat(String input) {
        Assertions.assertThatThrownBy(() -> new Players(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름이 형식과 맞지 않습니다");
    }
}
