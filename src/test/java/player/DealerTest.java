package player;

import static org.assertj.core.api.Assertions.assertThatCode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러를 생성한다.")
    void create() {
        assertThatCode(() -> new Dealer())
                .doesNotThrowAnyException();
    }

}
