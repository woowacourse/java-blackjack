package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SymbolTest {

    @DisplayName("심볼(스페이드, 하트, 클로버, 다이아몬드)을 가진다.")
    @Test
    void should_HaveAllSymbolTypes() {
        Assertions.assertThat(Symbol.values())
                .containsExactlyInAnyOrder(Symbol.SPADE, Symbol.HEART, Symbol.CLUB, Symbol.DIAMOND);
    }
}
