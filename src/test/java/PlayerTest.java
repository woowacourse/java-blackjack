import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.domain.Rank;
import blackjack.domain.Shape;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void calculate_player_total_score() {
        Player player1 = new Player("player1");
        player1.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player1.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        assertThat(player1.calculateTotalScore()).isEqualTo(21);

        Player player2 = new Player("player2");
        player2.receiveOneCard(new Card(Rank.THREE, Shape.HEART));
        player2.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        assertThat(player2.calculateTotalScore()).isEqualTo(13);
    }

    @Test
    void player_is_blackjack_when_score_is_21() {
        Player player = new Player("player");
        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.JACK, Shape.SPADE));
        assertThat(player.isBlackjack()).isTrue();
    }

    @Test
    void player_is_not_blackjack_when_score_is_20() {
        Player player = new Player("player");
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.HEART));
        player.receiveOneCard(new Card(Rank.JACK, Shape.SPADE));
        assertThat(player.isBlackjack()).isFalse();
    }
}
