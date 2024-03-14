package blackjack.domain.result;

import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersBattingTest {

    @DisplayName("플레이어의 배팅 결과를 계산한다.")
    @Test
    void calculateBattingReward() {
        //given
        PlayersBatting playersBatting = new PlayersBatting();
        Player player = new Player("test");
        playersBatting.registerPlayerBatting(player, 1000);

        //when
        playersBatting.calculateBattingReward(player, 1.5);
        double battingResult = playersBatting.getPlayerBatting().get(player);

        //then
        assertThat(battingResult).isEqualTo(1000 * 1.5);
    }
}
