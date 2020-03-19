package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.Money;
import blackjack.domain.Name;
import blackjack.domain.Names;
import blackjack.domain.card.CardDeck;
import blackjack.domain.gambler.Dealer;
import blackjack.domain.gambler.Players;

import java.util.Arrays;
import java.util.LinkedHashMap;
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
        Map<Name, Money> playerInfo = new LinkedHashMap<>();
        for (Name name : new Names("jamie1,jamie2").getNames()) {
            playerInfo.put(name, Money.of("500"));
        }
        Players players = new Players(playerInfo);
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
        Map<Player, PlayerOutcome> playerResult = gameResult.getPlayersResult();
        List<PlayerOutcome> playerOutcomes = Arrays.asList(PlayerOutcome.values());

        for (Player player : playerResult.keySet()) {
            assertThat(playerOutcomes.contains(playerResult.get(player))).isTrue();
        }
    }

    @DisplayName("딜러의 승무패를 가져옴")
    @Test
    void checkDealerResult() {
        Map<PlayerOutcome, Integer> dealerResults = gameResult.getDealerResultsNoZero();
        int total = 0;
        for (PlayerOutcome playerOutcome : dealerResults.keySet()) {
            total += dealerResults.get(playerOutcome);
            assertThat(dealerResults.get(playerOutcome) != 0).isTrue();
        }
        assertThat(total).isEqualTo(2);
    }
}
