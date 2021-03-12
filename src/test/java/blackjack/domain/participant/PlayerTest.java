package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        this.player = new Player(new Name("pobi"));
        this.player.betting(3_000);
    }

    @Test
    @DisplayName("플레이어 이름 반환 테스트")
    void testPlayerName() {
        assertThat(this.player.getName()).isEqualTo("pobi");
    }

    @Test
    @DisplayName("플레이어가 블랙잭 승리시 베팅한 금액 반환 테스트")
    void testPlayerBlackjackWinProfit() {
        assertThat(this.player.profit(Result.BLACKJACK_WIN)).isEqualTo(4_500);
    }

    @Test
    @DisplayName("플레이어가 승리시 베팅한 금액 반환 테스트")
    void testPlayerWinProfit() {
        assertThat(this.player.profit(Result.WIN)).isEqualTo(3_000);
    }

    @Test
    @DisplayName("플레이어가 무승부시 베팅한 금액 반환 테스트")
    void testPlayerDrawProfit() {
        assertThat(this.player.profit(Result.DRAW)).isEqualTo(3_000);
    }

    @Test
    @DisplayName("플레이어가 패배시 베팅한 금액 반환 테스트")
    void testPlayerLoseProfit() {
        assertThat(this.player.profit(Result.LOSE)).isEqualTo(-3_000);
    }
}
