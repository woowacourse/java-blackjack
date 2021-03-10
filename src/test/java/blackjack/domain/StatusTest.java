package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusTest {

    @DisplayName("플레이어가 버스트되면 딜러 점수와 무관하게 패한다.")
    @Test
    void loseStatus() {
        Score dealerScore = new Score(11);
        Score dealerBustScore = new Score(22);
        Score playerScore = new Score(23);

        assertThat(Status.compare(dealerScore, playerScore)).isEqualTo(Status.LOSE);
        assertThat(Status.compare(dealerBustScore, playerScore)).isEqualTo(Status.LOSE);
    }

    @DisplayName("플레이어와 딜러의 점수가 같으면 무승부다.")
    @Test
    void drawStatus() {
        Score dealerScore = new Score(13);
        Score playerScore = new Score(13);

        assertThat(Status.compare(dealerScore, playerScore)).isEqualTo(Status.DRAW);
    }

    @DisplayName("딜러가 버스트되고 플레이어는 버스트하지 않으면 플레이어가 이긴다.")
    @Test
    void dealerBustWinStatus() {
        Score dealerBustScore = new Score(23);
        Score playerScore = new Score(20);

        assertThat(Status.compare(dealerBustScore, playerScore)).isEqualTo(Status.WIN);
    }

    @DisplayName("둘 다 버스트 되지 않고 플레이어 점수가 높으 플레이어가 이긴다.")
    @Test
    void winStatus() {
        Score dealerScore = new Score(18);
        Score playerScore = new Score(20);

        assertThat(Status.compare(dealerScore, playerScore)).isEqualTo(Status.WIN);
    }
}