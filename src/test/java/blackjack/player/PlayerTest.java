package blackjack.player;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.card.Card;
import blackjack.card.Number;
import blackjack.card.Shape;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 21점 미만이면 추가 드로우가 가능하다.")
    void ableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.TEN)
        );
        Player player = new Player("aru");
        Iterator<Card> cardIterator = cards.iterator();
        player.drawCards(cardIterator::next, 2);
        // when
        boolean isDrawable = player.isDrawable();
        // then
        assertThat(isDrawable).isTrue();
    }

    @Test
    @DisplayName("플레이어는 21점 이상이면 추가 드로우가 불가능하다.")
    void unableToDrawTest() {
        // given
        List<Card> cards = List.of(
                new Card(Shape.HEART, Number.JACK),
                new Card(Shape.DIAMOND, Number.ACE)
        );
        Iterator<Card> cardIterator = cards.iterator();
        Player player = new Player("atto");
        player.drawCards(cardIterator::next, 2);
        // when
        boolean isDrawable = player.isDrawable();
        // then
        assertThat(isDrawable).isFalse();
    }
}
