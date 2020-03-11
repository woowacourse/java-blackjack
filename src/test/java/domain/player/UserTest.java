package domain.player;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    @Test
    void 플레이어가_카드를_더뽑을수있는지_확인() {
        User user = new User("pobi");

        user.drawCard(() -> Card.of(Rank.KING, Suit.CLOVER));
        user.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(user.canDrawMore()).isTrue();

        user.drawCard(() -> Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(user.canDrawMore()).isFalse();
    }
}
