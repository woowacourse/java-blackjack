package second.domain.gamer;

import org.junit.jupiter.api.Test;
import second.domain.card.Card;
import second.domain.card.Rank;
import second.domain.card.Suit;
import second.domain.gamer.Player;

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

    @Test
    void isBlackJack() {
        Player player = new Player("pobi");

        player.draw(Card.of(Rank.ACE, Suit.CLOVER));
        player.draw(Card.of(Rank.Q, Suit.CLOVER));
        assertThat(player.isBlackJack()).isTrue();

        player.draw(Card.of(Rank.FOUR, Suit.CLOVER));
        assertThat(player.isBlackJack()).isFalse();
    }
}
