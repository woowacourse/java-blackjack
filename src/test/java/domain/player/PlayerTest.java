package domain.player;

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
        Card newCard = Card.valueOf(CardNumber.FIVE, CardShape.HEART);
        player.hit(newCard);

        assertThat(player.getHand()).contains(newCard);
    }

    @DisplayName("패배(Bust)하지 않는 상황인지 알려준다.")
    @Test
    void checkUnderThreshold() {
        Player player = new Player("name");
        player.hit(Card.valueOf(CardNumber.TWO, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.THREE, CardShape.HEART));
        assertThat(player.canHit()).isTrue();
    }

    @DisplayName("패배(Bust)한 상황인지 알려준다.")
    @Test
    void checkOverThreshold() {
        Player player = new Player("name");
        player.hit(Card.valueOf(CardNumber.TWO, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.TEN, CardShape.HEART));
        player.hit(Card.valueOf(CardNumber.TEN, CardShape.HEART));

        assertThat(player.canHit()).isFalse();
    }
}
