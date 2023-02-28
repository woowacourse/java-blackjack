package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NumberTest {

    @DisplayName("카드는 숫자(2~10, Ace, King, Queen, Jack)을 가진다.")
    @Test
    void should_HaveAllNumberValues() {
        Assertions.assertThat(Number.values())
                .containsExactlyInAnyOrder(
                        Number.ACE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE,
                        Number.SIX, Number.SEVEN, Number.EIGHT, Number.NINE,
                        Number.TEN, Number.KING, Number.QUEEN, Number.JACK
                );
    }
}
