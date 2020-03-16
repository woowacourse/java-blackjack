package domain.gamer;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void 플레이어가_카드를_더뽑을수있는지_확인() {
        Player player = new Player("pobi");

        player.drawCard(() -> Card.of(Rank.KING, Suit.CLOVER));
        player.drawCard(() -> Card.of(Rank.QUEEN, Suit.CLOVER));
        assertThat(player.canDrawMore()).isTrue();

        player.drawCard(() -> Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(player.canDrawMore()).isFalse();
    }
}
