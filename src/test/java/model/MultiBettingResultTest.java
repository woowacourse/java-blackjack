package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import fixture.MultiBettingResultTestFixture.PlayerScenario;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class MultiBettingResultTest {
    @ParameterizedTest
    @MethodSource("fixture.MultiBettingResultTestFixture#여러_플레이어_혼합_정산_정보제공")
    void 여러_플레이어의_결과를_정산하면_딜러와_플레이어의_수익이_올바르게_반영된다(
            List<PlayerScenario> playerScenarios,
            List<Card> dealerCards,
            long dealerResultMoney
    ) {
        // given
        Dealer dealer = new Dealer();
        dealerCards.forEach(dealer::addCard);

        List<Player> players = new ArrayList<>();
        for (PlayerScenario playerScenario : playerScenarios) {
            Player player = new Player(playerScenario.name());
            playerScenario.cards().forEach(player::addCard);
            player.setMoney(playerScenario.bettingMoney());
            players.add(player);
        }

        // when
        BettingResult bettingResult = new BettingResult();
        bettingResult.calculateBettingMoney(dealer, players);

        // then
        assertAll(
                () -> assertThat(dealer.profit()).isEqualTo(dealerResultMoney),
                () -> {
                    for (int i = 0; i < players.size(); i++) {
                        assertThat(players.get(i).profit()).isEqualTo(playerScenarios.get(i).profit());
                    }
                }
        );
    }
}
