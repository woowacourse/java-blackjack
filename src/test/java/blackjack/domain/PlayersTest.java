package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class PlayersTest {
    @Test
    void 플레이어들이_카드를_받는다() {
        Players players = new Players(List.of("이산", "바니"));
        Deck deck = new Deck();
        players.recieveCard(deck);
        assertThat(deck.getCount()).isEqualTo(50);
    }
}
