package game;

import static org.assertj.core.api.Assertions.assertThat;

import card.Card;
import card.CardNumber;
import card.CardShape;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import participant.Dealer;
import participant.Player;

class GameResultTest {

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어만 Blackjack)")
    void test1() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.CLOVER, CardNumber.THREE));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.BLACKJACK);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어, 딜러 모두 Blackjack)")
    void test2() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.ACE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (딜러만 Blackjack)")
    void test3() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.ACE));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어 > 딜러 && 둘 다 Bust X)")
    void test4() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.THREE));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어 < 딜러 && 둘 다 Bust X)")
    void test5() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.THREE));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어 = 딜러 && 둘 다 Bust X)")
    void test6() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (둘 다 Bust)")
    void test7() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.DRAW);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (딜러만 Bust)")
    void test8() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.WIN);
    }

    @Test
    @DisplayName("플레이어의 승패 여부를 결정한다. (플레이어만 Bust)")
    void test9() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));
        player.receiveCard(new Card(CardShape.DIAMOND, CardNumber.EIGHT));

        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        dealer.receiveCard(new Card(CardShape.SPADE, CardNumber.EIGHT));

        // when
        GameResult result = GameResult.judgePlayerResult(dealer, player);

        // then
        assertThat(result)
                .isEqualTo(GameResult.LOSE);
    }

    @Test
    @DisplayName("얻은 돈을 계산하여 반환한다.")
    void test10() {
        // given
        int bettingMoney = 10000;
        GameResult gameResult = GameResult.WIN;

        // when
        int earnings = gameResult.calculateEarnings(bettingMoney);

        // then
        assertThat(earnings)
                .isEqualTo((int) (bettingMoney * gameResult.getProfitRate()));
    }
}
