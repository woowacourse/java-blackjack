package model.player;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.TEN;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import model.card.Card;
import model.card.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 이름이 빈 값이면 예외를 발생한다")
    @Test
    void shouldPlayerNameNotEmpty() {
        assertThatThrownBy(() -> new Player(""))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("플레이어의 카드 합계가 21점 이하이면 true를 반환한다")
    @Test
    void testCanAddCard() {
        Hand cards = new Hand(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Player player = new Player("lily", cards);
        assertThat(player.isPossibleHit()).isTrue();
    }

    @DisplayName("플레이어의 카드 합계가 22점 이상이면 false를 반환한다")
    @Test
    void testCanNotAddCard() {
        Hand cards = new Hand(
            List.of(new Card(TEN, HEART), new Card(JACK, HEART), new Card(TWO, HEART))
        );
        Player player = new Player("lily", cards);
        assertThat(player.isPossibleHit()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 카드가 1개 증가한 플레이어 객체를 반환한다")
    @Test
    void shouldAddCardWhenAllowed() {
        Hand cards = new Hand(List.of(new Card(ACE, HEART), new Card(JACK, HEART)));
        Player player = new Player("lily", cards);
        Card card = new Card(TWO, DIAMOND);
        Player updatedPlayer = player.hitCard(card);
        assertThat(updatedPlayer.handSize()).isEqualTo(3);
    }
}
