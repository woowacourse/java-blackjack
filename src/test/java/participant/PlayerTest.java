package participant;

import card.Card;
import card.CardNumber;
import card.CardShape;
import game.GameResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    @DisplayName("플레이어는 Bust라면 카드를 받을 수 없다.")
    void test1() {
        // given
        Player player = new Player("율무", 10000);
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
    @DisplayName("플레이어는 Bust가 아니라면 카드를 받을 수 있다.")
    void test2() {
        // given
        Player player = new Player("율무", 10000);
        player.receiveCard(new Card(CardShape.SPADE, CardNumber.TEN));
        player.receiveCard(new Card(CardShape.CLOVER, CardNumber.TEN));

        // when
        boolean result = player.canReceiveCard();

        // then
        Assertions.assertThat(result)
                .isTrue();
    }

    @Test
    @DisplayName("플레이어의 돈을 업데이트한다.")
    void test3() {
        // given
        int bettingMoney = 10000;
        Player player = new Player("미소", bettingMoney);
        GameResult gameResult = GameResult.WIN;

        // when
        player.updateMoney(gameResult.calculateEarnings(bettingMoney), gameResult.isProfitable());

        // then
        Assertions.assertThat(player.getEarnedMoney())
                .isEqualTo((int) (bettingMoney + bettingMoney * gameResult.getProfitRate()));
    }

    @Test
    @DisplayName("플레이어의 배팅 여부에 따라서 수익을 반환한다.")
    void test4() {
        // given
        int bettingMoney = 10000;
        Player player = new Player("미소", bettingMoney);
        GameResult gameResult = GameResult.WIN;
        player.updateMoney(gameResult.calculateEarnings(bettingMoney), gameResult.isProfitable());

        // when
        Profit profit = player.getProfit();

        // then
        Assertions.assertThat(profit.getAmount())
                .isEqualTo((int) (bettingMoney * gameResult.getProfitRate()));
    }
}
