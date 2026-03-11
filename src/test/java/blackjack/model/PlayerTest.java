package blackjack.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {
    @Test
    @DisplayName("점수가 21미만일 때 Hit 가능")
    void test_can_hit_before_blackjack() {
        Player player = new Player("pobi", 1000);

        player.addCard(new Card(Suit.HEART, Rank.JACK));
        player.addCard(new Card(Suit.CLOVER, Rank.JACK));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("점수가 21이상일 때 Hit 불가")
    void test_cannot_hit_at_blackjack() {
        Player player = new Player("pobi", 1000);

        player.addCard(new Card(Suit.HEART, Rank.JACK));
        player.addCard(new Card(Suit.CLOVER, Rank.ACE));

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("GameResult default Draw")
    void test_gameresult_default_draw() {
        Player player = new Player("pobi", 1000);

        assertThat(player.getGameResult()).isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("GameResult set Win")
    void test_gameresult_update_win() {
        Player player = new Player("pobi", 1000);
        player.mark(GameResult.WIN);

        assertThat(player.getGameResult()).isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("GameResult set LOSE")
    void test_gameresult_update_lose() {
        Player player = new Player("pobi", 1000);
        player.mark(GameResult.LOSE);

        assertThat(player.getGameResult()).isEqualTo(GameResult.LOSE);
    }
}
