package blackjack.domain.rule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.DealerGameResult;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import fixture.HandFixture;
import fixture.PlayerFixture;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JudgeTest {

    private Judge judge;

    @BeforeEach
    void setUp() {
        judge = new Judge();
    }

    @DisplayName("딜러와 플레이어 중 누가 이겼는지 알 수 있다")
    @Test
    void testSelectWinner() {
        Player player = PlayerFixture.of("리비", 2, 3);
        Dealer dealer = new Dealer(HandFixture.of(3, 4));
        assertThat(judge.isPlayerWin(dealer, player)).isFalse();
    }

    @DisplayName("딜러의 전적을 계산할 수 있다")
    @Test
    void testCalculateDealerResult() {
        Player player = PlayerFixture.of("리비", 2, 3);
        Dealer dealer = new Dealer(HandFixture.of(3, 4));
        Players players = new Players(List.of(player));
        DealerGameResult dealerGameResult = judge.calculateDealerGameResult(dealer, players);
        assertAll(
                () -> assertThat(dealerGameResult.getWinCount()).isEqualTo(1),
                () -> assertThat(dealerGameResult.getLoseCount()).isEqualTo(0)
        );
    }
}
