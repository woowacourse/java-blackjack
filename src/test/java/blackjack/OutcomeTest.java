package blackjack;

import blackjack.domain.Outcome;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OutcomeTest {

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 높으면 승리를 반환한다.")
    void playerIsWin() {
        Outcome match = Outcome.match(20, 21);
        assertThat(match).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("플레이어의 합이 딜러의 합보다 낮으면 패배를 반환한다.")
    void playerIsLose() {
        Outcome match = Outcome.match(21, 20);
        assertThat(match).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("플레이어의 합과 딜러의 합이 같으면 무승부를 반환한다.")
    void playerIsDraw() {
        Outcome match = Outcome.match(21, 21);
        assertThat(match).isEqualTo(Outcome.DRAW);
    }

    @Test
    @DisplayName("플레이어의 합이 21이 넘는 경우 패배를 반환한다.")
    void playerIsLoseByOver21() {
        Outcome match = Outcome.match(21, 22);
        assertThat(match).isEqualTo(Outcome.LOSE);
    }

    @Test
    @DisplayName("딜러의 합이 21이 넘는 경우 승리를 반환한다.")
    void playerIsWinByDealerOver21() {
        Outcome match = Outcome.match(22, 21);
        assertThat(match).isEqualTo(Outcome.WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 21이 넘는 경우 무승부를 반환한다.")
    void playerIsDrawByDealerAndPlayerOver21() {
        Outcome match = Outcome.match(22, 22);
        assertThat(match).isEqualTo(Outcome.DRAW);
    }
}
