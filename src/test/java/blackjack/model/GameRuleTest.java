package blackjack.model;

import blackjack.model.card.Card;
import blackjack.model.card.Pattern;
import blackjack.model.card.Rank;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Player;
import blackjack.model.result.GameResult;
import blackjack.model.result.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameRuleTest {

    @DisplayName("딜러와 플레이어가 둘 다 버스트면 딜러가 승리한다.")
    @Test
    void selectWinner_allBust() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Map<Player, Result> playersResult = gameResult.getPlayersResult();
        Result playerResult = playersResult.get(player);

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

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));
        player.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Map<Player, Result> playersResult = gameResult.getPlayersResult();
        Result playerResult = playersResult.get(player);

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

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Map<Player, Result> playersResult = gameResult.getPlayersResult();
        Result playerResult = playersResult.get(player);

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

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.NINE));

        //when
        GameResult gameResult = new GameResult();
        GameRule.decideWinner(dealer, player, gameResult);

        Map<Player, Result> playersResult = gameResult.getPlayersResult();
        Result playerResult = playersResult.get(player);

        //then
        assertAll(
                () -> assertThat(gameResult.countDealerTies()).isEqualTo(1),
                () -> assertThat(playerResult).isEqualTo(Result.TIE)
        );
    }

}
