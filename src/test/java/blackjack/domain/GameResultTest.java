package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameResultTest {

    @Test
    @DisplayName("딜러가 이기면 참가자의 배팅 금액을 얻는다")
    void 딜러가_이기면_참가자의_배팅_금액을_얻는다() {
        Dealer dealer = new Dealer();
        Gambler gambler = new Gambler("비타", 1_000);
        List<Gambler> gamblers = List.of(gambler);

        GameResults gameResults = new GameResults(dealer, gamblers);

        Map<Player, Integer> result = gameResults.getGameResults();

        assertAll(
                () -> assertThat(result.get(dealer)).isEqualTo(1_000),
                () -> assertThat(result.get(gambler)).isEqualTo(-1_000)
        );
    }
}
