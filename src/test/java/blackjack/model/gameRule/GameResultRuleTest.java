package blackjack.model.gameRule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Name;
import blackjack.model.gamer.Player;
import blackjack.model.result.PlayersResult;
import blackjack.model.result.Result;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameResultRuleTest {

    GameResultRule gameResultRule = new GameResultRule();

    @DisplayName("플레이어의 초기 결과값을 설정한다.")
    @Test
    void initializePlayerResults() {
        //given
        Player player = Player.of(Name.from("ted"), 100);

        //when
        gameResultRule.initializePlayerResults(List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.NONE);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭일 경우 플레이어는 푸시이다.")
    @Test
    void InitialResultRuleByDealerPlayerBlackjack() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameResultRule.InitialResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.PUSH);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아닐 경우 플레이어는 블랙잭이다.")
    @Test
    void InitialResultRuleByPlayerBlackjack() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TWO));

        //when
        gameResultRule.InitialResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.BLACKJACK);
    }

    @DisplayName("플레이어가 블랙잭이 아니고, 딜러가 블랙잭일 경우 플레이어는 패배한다.")
    @Test
    void InitialResultRuleByDealerBlackjack() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.EIGHT));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameResultRule.InitialResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트일 경우 딜러의 결과에 관계없이 플레이어는 패배한다.")
    @Test
    void finalResultRuleByPlayerBust() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TEN));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TEN));

        //when
        gameResultRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어가 버스트가 아니고, 딜러가 버스트일 경우 플레이어는 승리한다.")
    @Test
    void finalResultRuleByDealerBust() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.TWO));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.THREE));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TEN));

        //when
        gameResultRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 작을 경우 플레이어는 패배한다.")
    @Test
    void finalResultRuleByLowerThanDealer() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.EIGHT));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TWO));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameResultRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.LOSE);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 높을 경우 플레이어는 승리한다.")
    @Test
    void finalResultRuleByHigherThanDealer() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.HEART, CardNumber.EIGHT));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.TWO));

        //when
        gameResultRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.WIN);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러와 점수가 동일할 경우 플레이어는 푸시이다.")
    @Test
    void finalResultRuleBySameScore() {
        //given
        Player player = Player.of(Name.from("ted"), 100);
        Dealer dealer = Dealer.from(100);

        player.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(Card.of(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameResultRule.finalResultRule(dealer, List.of(player));
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result playerResult = playersResult.findPlayerResult(player);

        //then
        assertThat(playerResult).isEqualTo(Result.PUSH);
    }

    @DisplayName("결과가 정해지지 않은 플레이어만 히트/스테이 대상이다.")
    @Test
    void hitStayTargetPlayerDecisionRule() {
        //given
        Player player1 = Player.of(Name.from("ted"), 100);
        player1.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));
        player1.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.ACE));

        Player player2 = Player.of(Name.from("ted"), 100);
        player2.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.FIVE));
        player2.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));

        Dealer dealer = Dealer.from(200);
        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.FIVE));
        dealer.receiveCard(Card.of(CardPattern.DIAMOND, CardNumber.TEN));

        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);

        //when
        gameResultRule.initializePlayerResults(players);
        gameResultRule.InitialResultRule(dealer, players);
        List<Player> hitStayTargetPlayer = gameResultRule.hitStayTargetPlayerDecisionRule(players);
        PlayersResult playersResult = gameResultRule.getPlayersResult();
        Result player1Result = playersResult.findPlayerResult(player1);
        Result player2Result = playersResult.findPlayerResult(player2);

        //then
        assertAll(
                () -> assertThat(player1Result).isEqualTo(Result.BLACKJACK),
                () -> assertThat(player2Result).isEqualTo(Result.NONE),
                () -> assertThat(hitStayTargetPlayer).containsExactly(player2)
        );
    }
}
