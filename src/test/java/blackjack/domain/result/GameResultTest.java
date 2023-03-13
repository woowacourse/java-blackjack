package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.CardFixture;
import blackjack.domain.game.Money;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultTest {

    private GameResult gameResult;
    private Players players;

    @BeforeEach
    void setPlayers() {
        players = Players.from(List.of("oing", "ditoo"));

        players.getDealer().pickCard(CardFixture.HEART_SEVEN);
        players.getChallengers().get(0).pickCard(CardFixture.HEART_FOUR);
        players.getChallengers().get(1).pickCard(CardFixture.CLOVER_KING);
        gameResult = GameResult.from(players, List.of(Money.bet(10000), Money.bet(20000)));
    }

    @Test
    @DisplayName("도전자들의 수익이 올바르게 계산되는지 확인한다")
    void check_challengers_result() {
        Player oing = players.getChallengers().get(0);
        Player ditoo = players.getChallengers().get(1);

        assertThat(gameResult.getChallengerRevenue(oing).getValue()).isEqualTo(-10000);
        assertThat(gameResult.getChallengerRevenue(ditoo).getValue()).isEqualTo(20000);
    }

    @Test
    @DisplayName("딜러의 수익이 올바르게 계산되는지 확인한다")
    void check_dealer_result() {
        assertThat(gameResult.getDealerRevenue().getValue()).isEqualTo(-10000);
    }
}
