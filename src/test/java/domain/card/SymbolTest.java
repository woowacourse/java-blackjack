package domain.card;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SymbolTest {

    @Test
    void getName() {
        Symbol symbol = Symbol.SPADE;
        assertThat(symbol.getName()).isEqualTo("스페이드");
    }
}