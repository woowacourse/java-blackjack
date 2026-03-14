package dto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.participant.Player;
import org.junit.jupiter.api.Test;

class PlayerStatisticDtoTest {

    @Test
    void of로_생성하면_플레이어_이름과_수익을_반환한다() {
        // given
        Player player = new Player("jacob");
        int profit = 1200;

        // when
        PlayerStatisticDto actual = PlayerStatisticDto.of(player, profit);

        // then
        assertThat(actual.name()).isEqualTo("jacob");
        assertThat(actual.profit()).isEqualTo(profit);
    }
}
