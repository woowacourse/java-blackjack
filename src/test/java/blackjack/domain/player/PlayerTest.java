package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.GameResult;
import blackjack.domain.card.Type;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어가 버스트면 플레이어는 패배한다.")
    @Test
    void testPlayerBustLose() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.SPADE, Denomination.TEN));
        player.draw(new Card(Type.DIAMOND, Denomination.TEN));

        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.SPADE, Denomination.NINE));
        dealer.draw(new Card(Type.DIAMOND, Denomination.NINE));

        player.calculateGameResult(dealer);
        assertThat(player.getResult()).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러만 버스트면 플레이어는 승리한다.")
    @Test
    void testDealerBustWin() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.SPADE, Denomination.NINE));
        dealer.draw(new Card(Type.DIAMOND, Denomination.NINE));

        player.calculateGameResult(dealer);
        assertThat(player.getResult()).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 카드 점수가 높으면, 승리한다.")
    @Test
    void testScoreWin() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.HEART, Denomination.FOUR));

        player.calculateGameResult(dealer);
        assertThat(player.getResult()).isEqualTo(GameResult.WIN);
    }

    @DisplayName("플레이어 카드 점수가 낮으면, 패배한다.")
    @Test
    void testScoreLose() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.DIAMOND, Denomination.FOUR));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.HEART, Denomination.TEN));
        dealer.draw(new Card(Type.DIAMOND, Denomination.FOUR));

        player.calculateGameResult(dealer);
        assertThat(player.getResult()).isEqualTo(GameResult.LOSE);
    }

    @DisplayName("딜러와 플레이어의 카드점수가 같으면, 무승부다.")
    @Test
    void testScoreDraw() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.DIAMOND, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));

        Dealer dealer = new Dealer("딜러");
        dealer.draw(new Card(Type.SPADE, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.FOUR));

        player.calculateGameResult(dealer);
        assertThat(player.getResult()).isEqualTo(GameResult.DRAW);
    }

}
