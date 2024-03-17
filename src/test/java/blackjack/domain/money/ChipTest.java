package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("칩")
public class ChipTest {
    @Test
    @DisplayName("1000원 배팅금액을 갖고있는 Chip을 생성한다.")
    void createChip() {
        // given & when
        Chip chip = new Chip(2000);

        // then
        assertThat(chip.betting()).isEqualTo(2000);
    }
}
