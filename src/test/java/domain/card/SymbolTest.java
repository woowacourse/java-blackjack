package domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SymbolTest {

    @Test
    void getName() {
        Symbol symbol = Symbol.SPADE;
        assertThat(symbol.getName()).isEqualTo("스페이드");
    }
}