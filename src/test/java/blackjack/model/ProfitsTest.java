package blackjack.model;

import blackjack.dto.ProfitsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ProfitsTest {

    @Test
    @DisplayName("딜러의 총 수익은 플레이어들의 수익의 반대 부호 합산이다.")
    void calculateDealerProfitTest() {
        // given
        Player player1 = new Player("luke", 1000);
        Player player2 = new Player("sm", 2000);

        Map<Player, GameResult> results = new LinkedHashMap<>();
        results.put(player1, GameResult.WIN);
        results.put(player2, GameResult.LOSE);
        Profits profits = new Profits(results);
        // when
        double dealerProfit = profits.calculateDealerProfit();
        // then
        assertThat(dealerProfit).isEqualTo(1000.0);
    }

    @Test
    @DisplayName("특정 플레이어의 게임 결과에 따른 수익을 정확히 계산한다.")
    void calculatePlayerProfitTest() {
        // given
        Player player = new Player("luke", 10000);
        Map<Player, GameResult> results = Map.of(player, GameResult.WIN);
        Profits profits = new Profits(results);
        // when
        double playerProfit = profits.calculatePlayerProfit(player);
        // then
        assertThat(playerProfit).isEqualTo(10000.0);
    }

    @Test
    @DisplayName("도메인 데이터를 뷰를 위한 ProfitsDto로 정확하게 변환한다.")
    void toDtoTest() {
        // given
        Player player1 = new Player("luke", 1000);
        Player player2 = new Player("sm", 2000);

        Map<Player, GameResult> results = new LinkedHashMap<>();
        results.put(player1, GameResult.WIN);
        results.put(player2, GameResult.LOSE);
        Profits profits = new Profits(results);
        // when
        ProfitsDto dto = profits.toDto();
        // then
        assertThat(dto.getDealerProfit()).isEqualTo(1000.0);
        assertThat(dto.getPlayerProfits()).hasSize(2)
                .containsEntry("luke", 1000.0)
                .containsEntry("sm", -2000.0);
    }
}
