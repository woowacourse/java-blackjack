package blackjack.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.card.CardProperties;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleTest {

    @DisplayName("카드를 추가할 때 버스트가 되는 경우 보유한 Ace 카드를 1로 설정한다.")
    @Test
    void adjustAceValue() {
        //given
        Card tenCard = new Card(new CardProperties(CardPattern.HEART, CardNumber.TEN));
        Card aceCard = new Card(new CardProperties(CardPattern.SPADE, CardNumber.ACE));
        Card twoCard = new Card(new CardProperties(CardPattern.SPADE, CardNumber.TWO));

        Player player = new Player("test");

        //when
        player.receiveCard(tenCard);
        player.receiveCard(aceCard);

        int scoreEleven = aceCard.getScore();
        player.receiveCard(twoCard);
        int scoreOne = aceCard.getScore();

        //then
        assertAll(
                () -> assertThat(scoreEleven).isEqualTo(11),
                () -> assertThat(scoreOne).isEqualTo(1),
                () -> assertThat(player.calculateTotalScore()).isEqualTo(13)
        );
    }

    @DisplayName("플레이어의 점수가 21이하면 히트할 수 있다.")
    @Test
    void playerHitRule() {
        //given
        int playerScore = 20;

        //when, then
        assertThat(GameRule.playerHitRule(playerScore)).isTrue();
    }

    @DisplayName("딜러의 점수가 16이하면 히트할 수 있다.")
    @Test
    void dealerHitRule() {
        //given
        int dealerScore = 16;

        //when, then
        assertThat(GameRule.dealerHitRule(dealerScore)).isTrue();
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트면 딜러가 승리한다.")
    @Test
    void selectWinner_allBust() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");

        dealer.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.TEN)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.TEN)));

        player.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        player.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.TEN)));
        player.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.TEN)));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Result playerResult = gameResult.findPlayerResult(player);

        //then
        assertAll(
                () -> assertThat(gameResult.countDealerWins()).isEqualTo(1),
                () -> assertThat(playerResult).isEqualTo(Result.LOSE)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 블랙잭이면 플레이어가 승리한다.")
    @Test
    void selectWinner_allBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");

        dealer.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.NINE)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.TWO)));

        player.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        player.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.NINE)));
        player.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.TWO)));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Result playerResult = gameResult.findPlayerResult(player);

        //then
        assertAll(
                () -> assertThat(gameResult.countDealerLoses()).isEqualTo(1),
                () -> assertThat(playerResult).isEqualTo(Result.WIN)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트와 블랙잭이 아닐 경우 더 큰 점수가 승리한다.")
    @Test
    void selectWinner_neitherBusterOrBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");

        dealer.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.NINE)));

        player.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        player.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.TWO)));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Result playerResult = gameResult.findPlayerResult(player);

        //then
        assertAll(
                () -> assertThat(gameResult.countDealerWins()).isEqualTo(1),
                () -> assertThat(playerResult).isEqualTo(Result.LOSE)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트와 블랙잭이 아닐 때 점수가 같으면 무승부다.")
    @Test
    void selectWinner_neitherBusterOrBlackjack_sameScore() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");

        dealer.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        dealer.receiveCard(new Card(new CardProperties(CardPattern.CLOVER, CardNumber.NINE)));

        player.receiveCard(new Card(new CardProperties(CardPattern.SPADE, CardNumber.TEN)));
        player.receiveCard(new Card(new CardProperties(CardPattern.HEART, CardNumber.NINE)));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Result playerResult = gameResult.findPlayerResult(player);

        //then
        assertAll(
                () -> assertThat(gameResult.countDealerTies()).isEqualTo(1),
                () -> assertThat(playerResult).isEqualTo(Result.TIE)
        );
    }
}
