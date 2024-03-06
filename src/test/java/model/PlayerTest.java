package model;

import static model.CardNumber.JACK;
import static model.CardNumber.ONE;
import static model.CardNumber.TEN;
import static model.CardNumber.TWO;
import static model.CardShape.HEART;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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
}
