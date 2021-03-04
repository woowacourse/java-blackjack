package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Players;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackjackManagerTest {

    /*@Test
    @DisplayName("각 플레이어가 초기 2장씩 소지한다.")
    void testAllPlayersGetTwoCards() {
        List<String> names = Arrays.asList("pobi", "jason");
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        BlackjackManager blackjackManager = new BlackjackManager(dealer, players);
        blackjackManager.initGame();
        assertThat(players.toList()
            .stream()
            .filter(player -> player.getHand().size() == 2)
            .count())
            .isEqualTo(2);
    }*/
}
