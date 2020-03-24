package second.domain.gamer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import second.domain.card.Card;
import second.domain.card.Rank;
import second.domain.card.Suit;
import second.domain.gamer.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("유저가 카드를 더 뽑을 수 있는지 확인")
    void canDrawMore() {
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
