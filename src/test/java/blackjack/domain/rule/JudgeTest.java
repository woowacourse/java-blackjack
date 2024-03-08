package blackjack.domain.rule;

import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import fixture.PlayerFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class JudgeTest {

    private Judge judge;

    @BeforeEach
    void setUp() {
        judge = new Judge();
    }

    @DisplayName("딜러와 플레이어 중 누가 이겼는지 알 수 있다")
    @Test
    void testSelectWinner() {
        Player dealer = PlayerFixture.of("딜러", 10, 7);
        Player player = PlayerFixture.of("pobi", 10, 6);

        assertThat(judge.isPlayerWin(dealer, player)).isFalse();
    }

    @DisplayName("딜러의 게임 결과를 계산할 수 있다")
    @Test
    void testDealerResult() {
        Player dealer = PlayerFixture.of("딜러", 3, 9, 8);

        Player player1 = PlayerFixture.of("pobi", 2, 8, 1);
        Player player2 = PlayerFixture.of("jason", 7, 10);
        Players players = new Players(List.of(player1, player2));

        DealerGameResult dealerGameResult = judge.calculateDealerResult(dealer, players);

        assertAll(
                () -> assertThat(dealerGameResult.getWinCount()).isEqualTo(1),
                () -> assertThat(dealerGameResult.getLoseCount()).isEqualTo(1)
        );
    }
}
