package blackjack.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MoneyTest {
    @Test
    @DisplayName("생성자 테스트")
    void Money() {
        Assertions.assertThat(Money.of("500"))
                .isInstanceOf(Money.class);

        Assertions.assertThatThrownBy(() -> Money.of("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");

        Assertions.assertThatThrownBy(() -> Money.of("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양수");

        Assertions.assertThatThrownBy(() -> Money.of("1234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("최소 단위");
    }
}
