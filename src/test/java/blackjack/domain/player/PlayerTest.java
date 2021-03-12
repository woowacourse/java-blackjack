package blackjack.domain.player;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.blackjackgame.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Type;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @DisplayName("플레이어 카드 점수 합계가 21미만 이라면 드로우할 수 있다.")
    @Test
    void testCanDraw() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.SPADE, Denomination.TEN));

        assertThat(player.canDraw()).isTrue();
    }

    @DisplayName("플레이어 카드 점수 합계가 21이 이라면 드로우할 수 없다.")
    @Test
    void testCanNotDraw() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.SPADE, Denomination.ACE_ELEVEN));

        assertThat(player.canDraw()).isFalse();
    }

    @DisplayName("플레이어가 버스트면, 플레이어는 승리하고 배팅 금액을 잃는다.")
    @Test
    void testPlayerBustLose() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.SPADE, Denomination.TEN));
        player.draw(new Card(Type.DIAMOND, Denomination.TEN));
        player.addMoney(new Money(1000));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.SPADE, Denomination.NINE));
        dealer.draw(new Card(Type.DIAMOND, Denomination.NINE));

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(dealer.resultCount(GameResult.WIN)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(-1000);
    }

    @DisplayName("딜러만 버스트면, 플레이어는 승리하고 배팅 금액의 1배를 수익으로 얻는다.")
    @Test
    void testDealerBustWin() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));
        player.addMoney(new Money(1000));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.SPADE, Denomination.NINE));
        dealer.draw(new Card(Type.DIAMOND, Denomination.NINE));

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(dealer.resultCount(GameResult.LOSE)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(1000);
    }

    @DisplayName("플레이어가 블랙잭이면, 플레이어는 승리하고 배팅 금액의 1.5배를 수익으로 얻는다.")
    @Test
    void testPlayerBlackjackWin() {
        Cards playerCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.ACE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        Deck playerDeck = new Deck(playerCards);

        Player player = new Player("플레이어");
        player.addMoney(new Money(1000));
        player.initialDraw(playerDeck);

        Cards dealerCards = new Cards(
            Arrays.asList(
                new Card(Type.CLUB, Denomination.FIVE),
                new Card(Type.CLUB, Denomination.TEN)
            )
        );
        Deck dealerDeck = new Deck(dealerCards);

        Dealer dealer = new Dealer();
        dealer.initialDraw(dealerDeck);

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(dealer.resultCount(GameResult.LOSE)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(1500);
    }

    @DisplayName("플레이어 카드 점수가 높으면, 플레이어는 승리하고 배팅 금액의 1배를 수익으로 얻는다.")
    @Test
    void testScoreWin() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.HEART, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));
        player.addMoney(new Money(1000));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.HEART, Denomination.NINE));
        dealer.draw(new Card(Type.HEART, Denomination.FOUR));

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.WIN);
        assertThat(dealer.resultCount(GameResult.LOSE)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(1000);
    }

    @DisplayName("플레이어 카드 점수가 낮으면, 플레이어는 패배하고 배팅 금액을 잃는다.")
    @Test
    void testScoreLose() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.DIAMOND, Denomination.FOUR));
        player.draw(new Card(Type.HEART, Denomination.FOUR));
        player.addMoney(new Money(1000));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.HEART, Denomination.TEN));
        dealer.draw(new Card(Type.DIAMOND, Denomination.FOUR));

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.LOSE);
        assertThat(dealer.resultCount(GameResult.WIN)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(-1000);
    }

    @DisplayName("딜러와 플레이어의 카드점수가 같으면, 무승부고 플레이어의 수익은 0원이다.")
    @Test
    void testScoreDraw() {
        Player player = new Player("플레이어");
        player.draw(new Card(Type.DIAMOND, Denomination.TEN));
        player.draw(new Card(Type.HEART, Denomination.FOUR));
        player.addMoney(new Money(1000));

        Dealer dealer = new Dealer();
        dealer.draw(new Card(Type.SPADE, Denomination.TEN));
        dealer.draw(new Card(Type.CLUB, Denomination.FOUR));

        player.matchGameResult(dealer);
        assertThat(player.getGameResult()).isEqualTo(GameResult.DRAW);
        assertThat(dealer.resultCount(GameResult.DRAW)).isEqualTo(1);
        assertThat(player.profit(dealer).getValue()).isEqualTo(0);
    }

}
