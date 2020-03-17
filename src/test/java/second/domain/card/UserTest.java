package second.domain.card;

import org.junit.jupiter.api.Test;
import second.domain.player.User;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void 유저가_카드를_더뽑을수있는지_확인() {
        User player = new User("pobi");

        player.drawCard(() -> Card.of(Rank.K, Suit.CLOVER));
        player.drawCard(() -> Card.of(Rank.Q, Suit.CLOVER));
        assertThat(player.canDrawMore()).isTrue();

        player.drawCard(() -> Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(player.canDrawMore()).isFalse();
    }
}
