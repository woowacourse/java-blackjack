package participant;

import card.Card;
import card.CardNumber;
import card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 bust면 카드를 받을 수 없다.")
    void test1() {
        // given
        Player player = new Player("율무");

        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.FOUR));
        player.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("플레이어는 bust가 아니면 카드를 받을 수 있다.")
    void test2() {
        // given
        Player player = new Player("율무");

        player.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}
