package participant;

import card.Card;
import card.CardNumber;
import card.CardShape;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantTest {

    @Test
    @DisplayName("카드의 합이 21이고, 카드 개수가 2개라면 Blackjack이다.")
    void test1() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));

        // when
        boolean result = player.isBlackjack();

        // then
        Assertions.assertThat(result)
            .isTrue();
    }

    @Test
    @DisplayName("카드의 합이 21이지만, 카드 개수가 2개 아니라면 Blackjack이 아니다.")
    void test2() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TWO));

        // when
        boolean result = player.isBlackjack();

        // then
        Assertions.assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21이 아니라면 Blackjack이 아니다.")
    void test3() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));

        // when
        boolean result = player.isBlackjack();

        // then
        Assertions.assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("카드의 합이 21을 넘으면 Bust이다.")
    void test4() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        // when
        boolean result = player.isBust();

        // then
        Assertions.assertThat(result)
            .isTrue();
    }

    @Test
    @DisplayName("21이 넘지 않으면 Bust가 아니다.")
    void test5() {
        // given
        Player player = new Player("율무", 10000);

        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TWO));

        // when
        boolean result = player.isBust();

        // then
        Assertions.assertThat(result)
            .isFalse();
    }

    @Test
    @DisplayName("참가자가 자신 패의 합을 구할 수 있다.")
    void test6() {
        // given
        Player player = new Player("율무", 10000);

        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.NINE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TWO));

        // when
        int result = player.score();

        // then
        Assertions.assertThat(result)
            .isEqualTo(21);
    }
}
