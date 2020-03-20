package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    @DisplayName("생성자 테스트")
    void Name() {
        assertThat(new Name("jamie")).isInstanceOf(Name.class);
        assertThatThrownBy(() -> new Name(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
        assertThatThrownBy(() -> new Name(""))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Empty");
        assertThatThrownBy(() -> new Name("12345678910"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("long");
    }
}
