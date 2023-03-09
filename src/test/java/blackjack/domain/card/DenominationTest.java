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
class DenominationTest {

    @DisplayName("카드는 끗수(2~10, Ace, King, Queen, Jack)을 가진다.")
    @Test
    void should_HaveAllNumberTypes() {
        assertThat(Denomination.values())
                .containsExactlyInAnyOrder(
                        Denomination.ACE, Denomination.TWO, Denomination.THREE, Denomination.FOUR, Denomination.FIVE,
                        Denomination.SIX, Denomination.SEVEN, Denomination.EIGHT, Denomination.NINE,
                        Denomination.TEN, Denomination.KING, Denomination.QUEEN, Denomination.JACK
                );
    }

    @DisplayName("각 끗수는 값을 가진다.")
    @Test
    void should_HaveAllNumberValues() {
        final List<Integer> numberValues = Arrays.stream(Denomination.values())
                .map(Denomination::getValue)
                .collect(Collectors.toList());
        assertThat(numberValues)
                .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10);
    }
}
