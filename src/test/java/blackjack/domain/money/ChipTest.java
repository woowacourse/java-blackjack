package blackjack.domain.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

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

    @Test
    @DisplayName("음수를 넣으면 예외가 발생한다.")
    void MinusError() {
        // given & when & then
        assertThatCode(() -> new Chip(-2000))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("추가 금액만큼 수익이 늘어난다.")
    void addProfit() {
        // given
        Chip chip = new Chip(2000);

        // when
        chip.addProfit(1000L);

        // then
        assertThat(chip.profit()).isEqualTo(1000L);
    }
}
