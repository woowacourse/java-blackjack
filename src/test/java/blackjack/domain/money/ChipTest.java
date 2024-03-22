package blackjack.domain.money;

import blackjack.domain.game.PlayerResult;
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
        Chip chip = new Chip(2000L);

        // then
        assertThat(chip.value()).isEqualTo(2000L);
    }

    @Test
    @DisplayName("음수를 넣으면 예외가 발생한다.")
    void MinusError() {
        // given & when & then
        assertThatCode(() -> new Chip(-2000L))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("게임 결과에 따라 수익이 늘어난다.")
    void addProfit() {
        // given
        Chip chip = new Chip(2000L);

        // when
        long actual = chip.totalProfit(PlayerResult.BLACKJACK_WIN);

        // then
        assertThat(actual).isEqualTo((long) (2000L * 1.5));
    }
}
