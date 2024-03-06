package blackjack.model;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    @Test
    @DisplayName("딜러와 참여자들에게 카드를 2장씩 나누어 준다")
    void dealTest() {
        // given
        Players players = new Players(List.of("mia", "dora"));
        Dealer dealer = new Dealer();
        Game game = new Game(players, dealer);

        // when
        game.deal();

        // then
        assertThat(players.getCards()).hasSize(2);
        assertThat(dealer.getCards()).hasSize(2);
    }
}
