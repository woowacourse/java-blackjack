package player;

import card.Card;
import card.Number;
import card.Shape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 21점 미만이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.TEN)
        );
        Hand hand = new Hand(cards);
        Player player = new Player("aru", hand);
        // when
        boolean isDrawable = player.hasDrawableScore();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("플렝이어는 21점 이상이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.ACE)
        );
        Hand hand = new Hand(cards);
        Player player = new Player("atto", hand);
        // when
        boolean isDrawable = player.hasDrawableScore();
        // then
        assertThat(isDrawable).isFalse();
    }
}
