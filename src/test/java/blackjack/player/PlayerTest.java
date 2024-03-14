package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Rank;
import blackjack.card.Shape;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 21점 미만이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.TEN)
        );
        Hand hand = new Hand(cards);
        Player player = new Player("aru", hand);
        // when
        boolean isDrawable = player.hasDrawableScore();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("플렝이어는 21점 초과이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Rank.JACK),
                new Card(Shape.DIAMOND, Rank.TEN),
                new Card(Shape.DIAMOND, Rank.TWO)
        );
        Hand hand = new Hand(cards);
        Player player = new Player("atto", hand);
        // when
        boolean isDrawable = player.hasDrawableScore();
        // then
        assertThat(isDrawable).isFalse();
    }
}
