package blackjack.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolTest {
    @Test
    @DisplayName("스코어를 잘 불러오는지 확인")
    void checkScore() {
        assertThat(Symbol.ACE.getScore()).isEqualTo(1);
    }
}
