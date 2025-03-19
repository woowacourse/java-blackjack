package domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SymbolTest {

    @DisplayName("카드의 문양은 4가지다.")
    @Test
    void cardSymbol() {
        //given

        //when
        Symbol[] values = Symbol.values();

        //then
        assertThat(values).hasSize(4);
    }

}
