import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class GamerTest {

    @DisplayName("카드를 한 장 받는다.")
    @Test
    void hitOneCard() {
        Gamer gamer = new Gamer("name", new GamerCards(new ArrayList<>()));
        Card newCard = new Card(CardNumber.FIVE, CardShape.CLOVER);
        gamer.hit(newCard);

        assertThat(gamer.getCards()).contains(newCard);
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderThreshold() {
        Gamer gamer = new Gamer("name", new GamerCards(new ArrayList<>()));
        gamer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        gamer.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(gamer.isNotBust()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverThreshold() {
        Gamer gamer = new Gamer("name", new GamerCards(new ArrayList<>()));
        gamer.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        gamer.hit(new Card(CardNumber.K, CardShape.CLOVER));
        gamer.hit(new Card(CardNumber.K, CardShape.SPADE));

        assertThat(gamer.isNotBust()).isFalse();
    }
}
