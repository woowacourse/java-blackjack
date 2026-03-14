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
    @DisplayName("플레이어가 승리 시 배팅 금액만큼의 추가 수익을 얻는다.")
    void winAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.WIN)).isEqualTo(1000);
    }

    @Test
    @DisplayName("무승부 시 추가 수익은 발생하지 않는다.")
    void drawAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.DRAW)).isEqualTo(0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭 승리 시 배팅 금액의 1.5배를 수익으로 얻는다.")
    void blackjackAmountTest() {
        // given
        Player player = new Player("luke", 1000);
        // when & then
        assertThat(player.getBettingAmount(GameResult.BLACKJACK)).isEqualTo(1500);
    }
}
