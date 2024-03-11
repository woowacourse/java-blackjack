package domain.gamer;

import domain.cards.Card;
import domain.cards.cardinfo.CardNumber;
import domain.cards.cardinfo.CardShape;
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

        assertThat(player.getHand()).contains(newCard);
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderThreshold() {
        Player player = new Player("name");
        player.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        player.hit(new Card(CardNumber.THREE, CardShape.HEART));
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverThreshold() {
        Player player = new Player("name");
        player.hit(new Card(CardNumber.TWO, CardShape.CLOVER));
        player.hit(new Card(CardNumber.KING, CardShape.CLOVER));
        player.hit(new Card(CardNumber.KING, CardShape.SPADE));

        assertThat(player.canHit()).isFalse();
    }
}
