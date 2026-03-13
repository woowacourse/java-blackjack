package blackjackTest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.*;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void player_total_score_blackJack() {
        Player player = new Player("player1", new Money(10000));
        player.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));
        assertThat(player.calculateTotalScore()).isEqualTo(21);
    }

    @Test
    void player_total_score_not_blackjack() {
        Player player = new Player("player2", new Money(10000));
        player.receiveOneCard(new Card(Rank.THREE, Shape.HEART));
        player.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        assertThat(player.calculateTotalScore()).isEqualTo(13);
    }
}
