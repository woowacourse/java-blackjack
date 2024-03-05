package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SymbolTest {

    @DisplayName("인덱스로 문양을 찾아 반환한다.")
    @Test
    void findSymbolByIndex() {
        // given
        int cloverIndex = 1;
        Symbol expectedSymbol = Symbol.CLOVER;

        // when
        Symbol symbol = Symbol.findBy(cloverIndex);

        // then
        assertThat(symbol).isEqualTo(expectedSymbol);
    }
}
