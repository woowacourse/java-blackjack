package blackjack.domain.participant;


import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.result.GameOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setup() {
        player = new Player("pobi", 1000);
    }

    @Test
    @DisplayName("점수가 21미만일 때 Hit 가능")
    void test_can_hit_before_blackjack() {

        player.addCard(new Card(Suit.HEART, Rank.JACK));
        player.addCard(new Card(Suit.CLOVER, Rank.JACK));

        assertThat(player.canHit()).isTrue();
    }

    @Test
    @DisplayName("점수가 21이상일 때 Hit 불가")
    void test_cannot_hit_at_blackjack() {

        player.addCard(new Card(Suit.HEART, Rank.JACK));
        player.addCard(new Card(Suit.CLOVER, Rank.ACE));

        assertThat(player.canHit()).isFalse();
    }

    @Test
    @DisplayName("GameResult default Draw")
    void test_gameresult_default_draw() {

        assertThat(player.getGameOutcome()).isEqualTo(GameOutcome.DRAW);
    }

    @Test
    @DisplayName("GameResult set Win")
    void test_gameresult_update_win() {
        player.mark(GameOutcome.WIN);

        assertThat(player.getGameOutcome()).isEqualTo(GameOutcome.WIN);
    }

    @Test
    @DisplayName("GameResult set LOSE")
    void test_gameresult_update_lose() {
        player.mark(GameOutcome.LOSE);

        assertThat(player.getGameOutcome()).isEqualTo(GameOutcome.LOSE);
    }
}
