package participant;

import card.Card;
import card.CardNumber;
import card.CardType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 bust면 카드를 받을 수 없다.")
    void test1() {
        // given
        Player player = new Player("율무");

        player.hit(new Card(CardType.DIAMOND, CardNumber.FOUR));
        player.hit(new Card(CardType.SPADE, CardNumber.TEN));
        player.hit(new Card(CardType.CLOVER, CardNumber.TEN));

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

        player.hit(new Card(CardType.SPADE, CardNumber.TEN));
        player.hit(new Card(CardType.CLOVER, CardNumber.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }
}
