package blackjack.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;

public class CardsTest {
    @Test
    @DisplayName("패 생성 테스트")
    void constructCards() {
        assertThatNoException().isThrownBy(() -> new Cards());
    }
}
