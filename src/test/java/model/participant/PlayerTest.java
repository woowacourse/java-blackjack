package model.participant;

import static model.card.CardNumber.ACE;
import static model.card.CardNumber.JACK;
import static model.card.CardNumber.TEN;
import static model.card.CardNumber.TWO;
import static model.card.CardShape.DIAMOND;
import static model.card.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import model.card.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어의 카드 합계가 21점 이하이면 true를 반환한다")
    @Test
    void testCanHit() {
        List<Card> cards = List.of(new Card(ACE, HEART), new Card(JACK, HEART));
        Player player = Player.of("lily", cards);
        assertThat(player.isPossibleHit()).isTrue();
    }

    @DisplayName("플레이어의 카드 합계가 22점 이상이면 false를 반환한다")
    @Test
    void testCanNotHit() {
        List<Card> cards = List.of(new Card(TEN, HEART), new Card(JACK, HEART), new Card(TWO, HEART));
        Player player = Player.of("lily", cards);
        assertThat(player.isPossibleHit()).isFalse();
    }

    @DisplayName("카드 1장을 획득하면 플레이어의 카드가 1개가 증가한다")
    @Test
    void testHitCard() {
        Player player = Player.from("조조");
        Card card = new Card(TWO, DIAMOND);
        player.hit(card);
        assertThat(player.cardSize()).isEqualTo(1);
    }
}
