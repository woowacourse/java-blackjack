package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {

    @Test
    @DisplayName("생성자 테스트")
    void Names() {
        assertThat(Names.of("jamie, ravie")).isInstanceOf(Names.class);
        assertThatThrownBy(() -> Names.of(null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Null");
    }
}
