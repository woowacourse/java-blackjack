package blackjack.domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

    @Test
    void createInstance() {
        Name name = new Name("아마란스");
        Assertions.assertThat(name).isInstanceOf(Name.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "     "})
    void nameIsNotBlank(String input) {
        Assertions.assertThatThrownBy(() -> new Name(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름은 공백이 될 수 없습니다.");
    }

    @Test
    void nameIsNotNull() {
        Assertions.assertThatThrownBy(() -> new Name(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("이름이 입력되지 않았습니다.");
    }

}
