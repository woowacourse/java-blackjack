package model;

import static org.assertj.core.api.Assertions.assertThat;

import model.card.Card;
import model.card.CardShape;
import model.card.CardValue;
import model.paticipant.Player;
import org.junit.jupiter.api.Test;

public class PlayerBustTest {

    @Test
    void 플레이어의_카드_점수_합이_20_이하_일_때_카드를_더_받을_수_있다() {
        // given
        Player pobi = new Player("pobi");
        pobi.addCard(new Card(CardShape.HEART, CardValue.TEN));
        pobi.addCard(new Card(CardShape.DIAMOND, CardValue.TEN));

        // then
        assertThat(pobi.canHit()).isTrue();
    }

    @Test
    void 플레이어의_카드_점수_합이_21_이상_일_때_카드를_더_받을_수_없다() {
        // given
        Player pobi = new Player("pobi");
        pobi.addCard(new Card(CardShape.HEART, CardValue.TEN));
        pobi.addCard(new Card(CardShape.DIAMOND, CardValue.TEN));
        pobi.addCard(new Card(CardShape.DIAMOND, CardValue.ACE));

        // then
        assertThat(pobi.canHit()).isFalse();
    }
}
