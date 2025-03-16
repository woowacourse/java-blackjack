package domain.card;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("모양 테스트")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class SymbolTest {

    @Test
    void 모든_모양을_반환한다() {
        List<Symbol> expected = List.of(Symbol.DIAMOND, Symbol.SPADE, Symbol.CLOVER, Symbol.HEART);

        assertThat(Symbol.getAllSymbols()).isEqualTo(expected);
    }
}
