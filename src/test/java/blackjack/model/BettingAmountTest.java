package blackjack.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BettingAmountTest {

    @Test
    @DisplayName("플레이어가 패배하면 배팅 금액을 잃는다.")
    void loseAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.LOSE)).isEqualTo(-1000);
    }

    @Test
    @DisplayName("플레이어가 승리하면 배팅 금액을 잃지 않는다.")
    void winAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 무승부면 배팅 금액을 유지된다.")
    void drawAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.DRAW)).isEqualTo(1000);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이면 배팅 금액의 1.5배를 얻는다.")
    void blackjackAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.BLACKJACK)).isEqualTo(1500);
    }
}
