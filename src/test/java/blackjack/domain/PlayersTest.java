package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    void 플레이어들이_카드를_받는다() {
        List<String> names = List.of("이산", "바니", "소낙눈");
        Players players = new Players(names);
        Deck deck = new Deck();
        players.receiveCard(deck);

        assertThat(players.getPlayers()).allSatisfy(player -> {
            assertThat(player.getCardCount()).isEqualTo(1);
        });
    }
}
