package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolTest {
    @Test
    @DisplayName("Symbol 객체가 잘 생성되는 지 확인")
    void valueOf() {
        assertThat(Symbol.valueOf("ACE")).isEqualTo(Symbol.ACE);
    }
}
