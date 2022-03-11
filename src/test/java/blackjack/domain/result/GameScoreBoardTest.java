package blackjack.domain.result;

import static blackjack.domain.TestBlackjackUtils.createCardHand;
import static blackjack.domain.TestCardFixture.*;
import static blackjack.domain.result.Result.LOSE;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameScoreBoardTest {

    private Dealer dealer;
    private List<Player> players;

    @BeforeEach
    void setUp() {
        dealer = new Dealer(createCardHand(aceCard, sevenCard));
        players = createPlayers();
    }

    @Test
    @DisplayName("딜러의 게임 승패를 잘 반환하는지 확인")
    void equalsDealerGameResult() {
        GameScoreBoard result = GameScoreBoard.recordGameScore(dealer, players);
        Map<Result, Integer> dealerGameResult = result.getDealerGameResult();

        for (Entry<Result, Integer> dealerResult : dealerGameResult.entrySet()) {
            assertThat(dealerResult.getKey()).isEqualTo(LOSE);
        }
    }

    @Test
    @DisplayName("게임 승패를 잘 반환하는지 확인")
    void equalsPlayerGameResult() {
        GameScoreBoard result = GameScoreBoard.recordGameScore(dealer, players);
        Map<String, String> playerGameResultMap = result.getPlayerGameResultMap();

        for (Entry<String, String> playerResult : playerGameResultMap.entrySet()) {
            assertThat(playerResult.getValue()).isEqualTo("승");
        }
    }

    private List<Player> createPlayers() {
        return Collections.singletonList(
            new Player(new Name("승팡"), createCardHand(aceCard, tenCard)));
    }
}
