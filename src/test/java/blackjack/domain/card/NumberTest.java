package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NumberTest {

    @DisplayName("카드는 숫자(2~10, Ace, King, Queen, Jack)을 가진다.")
    @Test
    void should_HaveAllNumberTypes() {
        assertThat(Number.values())
                .containsExactlyInAnyOrder(
                        Number.ACE, Number.TWO, Number.THREE, Number.FOUR, Number.FIVE,
                        Number.SIX, Number.SEVEN, Number.EIGHT, Number.NINE,
                        Number.TEN, Number.KING, Number.QUEEN, Number.JACK
                );
    }

    @DisplayName("각 숫자는 값을 가진다.")
    @Test
    void should_HaveAllNumberValues() {
        List<Integer> numberValues = Arrays.stream(Number.values())
                .map(Number::getValue)
                .collect(Collectors.toList());
        assertThat(numberValues)
                .containsExactly(11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10);
    }
}
