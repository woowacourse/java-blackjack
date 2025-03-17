package participant;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.CardShape;
import game.GameResult;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {

    @Test
    @DisplayName("딜러는 카드 패의 합이 16이 넘으면 카드를 받을 수 없다.")
    void test1() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.SEVEN));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        assertThat(result)
                .isFalse();
    }

    @Test
    @DisplayName("딜러는 카드 패의 합이 16 넘지않으면 카드를 받을 수 있다.")
    void test2() {
        // given
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        // when
        boolean result = dealer.canReceiveCard();

        // then
        assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("Dealer를 기준으로 승패를 반환한다.")
    void test3() {
        // given
        Dealer dealer = new Dealer();
        Player miso = new Player("미소", 10000);
        Players players = new Players(List.of(miso));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));
        dealer.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));
        miso.receiveCard(new Card(CardShape.SPADE, CardNumber.KING));
        miso.receiveCard(new Card(CardShape.HEART, CardNumber.ACE));

        // when
        Map<Player, GameResult> gameResults = dealer.decideGameResults(players);

        // then
        assertThat(gameResults.get(miso))
                .isEqualTo(GameResult.BLACKJACK);
    }
}
