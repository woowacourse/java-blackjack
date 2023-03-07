package blackjack.domain.card;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SuitTest {

    @DisplayName("슈트(스페이드, 하트, 클로버, 다이아몬드)을 가진다.")
    @Test
    void should_HaveAllSuitTypes() {
        Assertions.assertThat(Suit.values())
                .containsExactlyInAnyOrder(Suit.SPADE, Suit.HEART, Suit.CLUB, Suit.DIAMOND);
    }
}
