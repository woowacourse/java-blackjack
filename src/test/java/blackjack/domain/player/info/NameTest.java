package blackjack.domain.player.info;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

    @Test
    @DisplayName("문자열을 통해서 이름을 생성한다.")
    void create_with_String() {
        assertThatCode(() -> {
            final Name name = new Name("초롱");
            assertThat(name.asString()).isEqualTo("초롱");

        }).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("문자열에 공백이 포함되는 이름을 생성될 수 없다.")
    void not_include_blank() {
        assertThrows(IllegalArgumentException.class, () -> new Name("초 롱"));
    }
}
