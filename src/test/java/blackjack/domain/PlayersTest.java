package blackjack.domain;

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

    @Test
    void validateDuplicateName() {
        Assertions.assertThatThrownBy(() -> new Players("아마란스,무민,아마란스,프리지아"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("중복되는 이름이 존재합니다. : 아마란스");
    }

    @Test
    void validateBlankName() {
        Assertions.assertThatThrownBy(() -> new Players("아마란스, ,무민,프리지아"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }
}
