package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Players;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import blackjack.domain.gambler.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameResultTest {

    private static GameResult gameResult;

    @BeforeAll
    static void resetVariable() {
        Players players = new Players(new Names("jamie,Ravie"));
        CardDeck cardDeck = new CardDeck();
        Dealer dealer = new Dealer();
        dealer.drawCard(new CardDeck(), 2);
        for (Player player : players.getPlayers()) {
            player.drawCard(cardDeck, 2);
        }
        gameResult = new GameResult(dealer, players);
    }

    @DisplayName("생성자 NULL일 경우 예외")
    @Test
    void validNotNull() {
        assertThatThrownBy(() -> new GameResult(new Dealer(), null))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("잘못");
    }

    @DisplayName("플레이어의 승무패를 가져옴")
    @Test
    void checkPlayersResult() {
        Map<Player, Outcome> playerResult = gameResult.getPlayersResult();
        List<Outcome> outcomes = Arrays.asList(Outcome.values());

        for (Player player : playerResult.keySet()) {
            assertThat(outcomes.contains(playerResult.get(player))).isTrue();
        }
    }

    @DisplayName("딜러의 승무패를 가져옴")
    @Test
    void checkDealerResult() {
        Map<Outcome, Integer> dealerResults = gameResult.getDealerResultsNoZero();
        int total = 0;
        for (Outcome outcome : dealerResults.keySet()) {
            total += dealerResults.get(outcome);
            assertThat(dealerResults.get(outcome) != 0).isTrue();
        }
        assertThat(total).isEqualTo(2);
    }
}
