package blackjack.domain.supplies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("칩")
public class ChipTest {
    @Test
    @DisplayName("2000이 있던 칩에 1000을 더하면 3000이 된다.")
    void chipGet() {
        // given
        Chip chip = new Chip(2000);

        // when
        chip.add(1000);

        // then
        assertThat(chip.getChip()).isEqualTo(3000);
    }
}
