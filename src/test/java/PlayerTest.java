import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @DisplayName("카드를 한 장 받는다.")
    @Test
    void hitOneCard() {
        Player player = new Player("name");
        Card newCard = new Card(CardNumber.FIVE, CardShape.CLOVER);
        player.hit(newCard);

        assertThat(player.getCards().getCards()).contains(newCard);
    }

    @DisplayName("점수가 21 이하인지 알려준다.")
    @Test
    void checkUnderThreshold() {
        Player player = new Player("name");
        player.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        player.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(player.hasScoreUnderThreshold()).isTrue();
    }

    @DisplayName("점수가 21 초과인지 알려준다.")
    @Test
    void checkOverThreshold() {
        Player player = new Player("name");
        player.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        player.hit(new Card(CardNumber.K, CardShape.CLOVER));
        player.hit(new Card(CardNumber.K, CardShape.SPADE));

        assertThat(player.hasScoreUnderThreshold()).isFalse();
    }
}
