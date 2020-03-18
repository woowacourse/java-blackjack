package second.domain.card;

import org.junit.jupiter.api.Test;
import second.domain.player.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void 유저가_카드를_더뽑을수있는지_확인() {
        Player player = new Player("pobi");

        player.draw(Card.of(Rank.K, Suit.CLOVER));
        player.draw(Card.of(Rank.Q, Suit.CLOVER));
        assertThat(player.canDrawMore()).isTrue();

        player.draw(Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(player.canDrawMore()).isFalse();
    }
}
