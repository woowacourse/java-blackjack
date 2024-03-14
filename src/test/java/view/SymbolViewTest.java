package view;

import domain.card.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class SymbolViewTest {

    @DisplayName("모든 Symbol에 대응하는 SymbolView가 존재한다.")
    @Test
    void mappingSymbolToSymbolViewTest() {
        assertThatCode(() -> Arrays.stream(Symbol.values()).forEach(SymbolView::findName)).doesNotThrowAnyException();
    }
}
