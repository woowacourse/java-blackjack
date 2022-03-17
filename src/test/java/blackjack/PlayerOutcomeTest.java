package blackjack;

import blackjack.domain.PlayerOutcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerOutcomeTest {

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 높으면 승리를 반환한다.")
    void playerIsWin() {
        PlayerOutcome match = PlayerOutcome.match(20, 21, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 낮으면 패배를 반환한다.")
    void playerIsLose() {
        PlayerOutcome match = PlayerOutcome.match(21, 20, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 딜러의 합이 같으면 무승부를 반환한다.")
    void playerIsDraw() {
        PlayerOutcome match = PlayerOutcome.match(21, 21, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("플레이어의 합이 21이 넘는 경우 패배를 반환한다.")
    void playerIsLoseByOver21() {
        PlayerOutcome match = PlayerOutcome.match(21, 22, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러의 합이 21이 넘는 경우 승리를 반환한다.")
    void playerIsWinByDealerOver21() {
        PlayerOutcome match = PlayerOutcome.match(22, 21, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 21이 넘는 경우 무승부를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        PlayerOutcome match = PlayerOutcome.match(22, 22, 3, 3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 숫자가 작을 경우 블랙잭 승을 반환한다.")
    void playerBlackJackWinTest() {
        PlayerOutcome match = PlayerOutcome.match(20, 21, 3, 2);
        assertThat(match).isEqualTo(PlayerOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러가 3장 21인 경우 블랙잭 승을 반환한다.")
    void playerBlackJackWinDealer21Test() {
        PlayerOutcome match = PlayerOutcome.match(21, 21, 3, 2);
        assertThat(match).isEqualTo(PlayerOutcome.BLACKJACK_WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고 딜러도 블랙잭인 경우 블랙잭 무승부를 반환한다.")
    void playerBlackJackDealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(21, 21, 2, 2);
        assertThat(match).isEqualTo(PlayerOutcome.BLACKJACK_DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 3장 21인 경우 무승부를 반환한다.")
    void player21DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(21, 21, 2, 3);
        assertThat(match).isEqualTo(PlayerOutcome.DRAW);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 20인 경우 플레이어 패를 반환한다.")
    void player20DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(21, 20, 2, 3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }

    @Test
    @DisplayName("딜러가 블랙잭이고 플레이어가 22인 경우 플레이어 패를 반환한다.")
    void player22DealerBlackjackTest() {
        PlayerOutcome match = PlayerOutcome.match(21, 22, 2, 3);
        assertThat(match).isEqualTo(PlayerOutcome.LOSE);
    }
}
