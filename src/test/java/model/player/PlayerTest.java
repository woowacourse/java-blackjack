package model.player;

import static model.card.CardNumber.JACK;
import static model.card.CardNumber.ONE;
import static model.card.CardNumber.TEN;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어의 카드 합계가 21점 이하이면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Cards cards = new Cards(List.of(new Card(ONE, HEART), new Card(JACK, HEART)));
        Player player = new Player("lily", cards);
        assertThat(player.isPossibleAddCard()).isTrue();
    }

    @DisplayName("플레이어의 카드 합계가 22점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Cards cards = new Cards(
            List.of(new Card(TEN, HEART), new Card(JACK, HEART), new Card(TWO, HEART))
        );
        Player player = new Player("lily", cards);
        assertThat(player.isPossibleAddCard()).isFalse();
    }

    @DisplayName("카드를 추가로 받을 수 있으면 카드 1장 획득")
    @Test
    void shouldAddCardWhenAllowed() {
        Cards cards = new Cards(List.of(new Card(ONE, HEART), new Card(JACK, HEART)));
        Player player = new Player("lily", cards);
        Card card = new Card(TWO, DIAMOND);
        Player updatedPlayer = player.addCard(card);
        assertThat(updatedPlayer.cardsSize()).isEqualTo(3);
    }

    @DisplayName("카드를 추가로 받을 수 없으면 카드 획득 불가")
    @Test
    void shouldNotAddCardWhenNotAllowed() {
        Cards cards = new Cards(
            List.of(new Card(TEN, HEART), new Card(JACK, HEART), new Card(TWO, HEART))
        );
        Player player = new Player("lily", cards);
        Card card = new Card(TWO, DIAMOND);
        Player updatedPlayer = player.addCard(card);
        assertThat(updatedPlayer.cardsSize()).isEqualTo(3);
    }
}
