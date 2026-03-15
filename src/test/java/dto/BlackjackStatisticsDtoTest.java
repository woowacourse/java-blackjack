package dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class BlackjackStatisticsDtoTest {

    @Test
    void of로_생성하면_딜러_수익과_플레이어_통계를_포함한다() {
        // given
        int dealerProfit = -500;
        List<PlayerStatisticDto> playerStatistics = List.of(
                new PlayerStatisticDto("jacob", 1000),
                new PlayerStatisticDto("seoye", -500)
        );

        // when
        BlackjackStatisticsDto actual = BlackjackStatisticsDto.of(dealerProfit, playerStatistics);

        // then
        assertThat(actual.dealerProfit()).isEqualTo(dealerProfit);
        assertThat(actual.playerStatisticDtoList()).containsExactlyElementsOf(playerStatistics);
    }
}
