import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Player;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void calculate_player_total_score() {
        Player player1 = new Player("player1");
        player1.receiveOneCard(new Card("A", "하트"));
        player1.receiveOneCard(new Card("Q", "스페이드"));
        assertThat(player1.calculateTotalScore()).isEqualTo(21);

        Player player2 = new Player("player2");
        player2.receiveOneCard(new Card("3", "하트"));
        player2.receiveOneCard(new Card("10", "스페이드"));
        assertThat(player2.calculateTotalScore()).isEqualTo(13);
    }

    @Test
    void player_is_blackjack_when_score_is_21() {
        Player player = new Player("player");
        player.receiveOneCard(new Card("A", "하트"));
        player.receiveOneCard(new Card("J", "스페이드"));
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    void player_is_not_blackjack_when_score_is_20() {
        Player player = new Player("player");
        player.receiveOneCard(new Card("Q", "하트"));
        player.receiveOneCard(new Card("J", "스페이드"));
        assertThat(player.isBlackjack()).isFalse();
    }
}
