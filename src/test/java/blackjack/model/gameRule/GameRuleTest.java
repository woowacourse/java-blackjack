package blackjack.model.gameRule;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.model.betting.BettingInfo;
import blackjack.model.betting.PlayerBetWallet;
import blackjack.model.card.Card;
import blackjack.model.card.CardNumber;
import blackjack.model.card.CardPattern;
import blackjack.model.gamer.Dealer;
import blackjack.model.gamer.Gamers;
import blackjack.model.gamer.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameRuleTest {

    GameRule gameRule;

    @BeforeEach
    void beforeEach() {
        gameRule = new GameRule();
    }

    @DisplayName("첫 두장의 카드에서 블랙잭인 플레이어는 히트/스테이 대상으로 포함되지 않는다.")
    @Test
    void decideHitOrStayPlayersByBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        //when
        List<Player> decideHitOrStayPlayers = gameRule.decideHitOrStayPlayers(gamers);

        //then
        assertThat(decideHitOrStayPlayers).isEmpty();
    }

    @DisplayName("첫 두장의 카드에서 푸시인 플레이어는 히트/스테이 대상으로 포함되지 않는다.")
    @Test
    void decideHitOrStayPlayersByPush() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        //when
        List<Player> decideHitOrStayPlayers = gameRule.decideHitOrStayPlayers(gamers);

        //then
        assertThat(decideHitOrStayPlayers).isEmpty();
    }

    @DisplayName("첫 두장의 카드에서 딜러가 블랙잭이면 플레이어는 히트/스테이 대상으로 포함되지 않는다.")
    @Test
    void decideHitOrStayPlayersByDealerBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.NINE));

        //when
        List<Player> decideHitOrStayPlayers = gameRule.decideHitOrStayPlayers(gamers);

        //then
        assertThat(decideHitOrStayPlayers).isEmpty();
    }

    @DisplayName("첫 두장의 카드에서 아무것도 아니면 플레이어는 히트/스테이 대상으로 포함된다.")
    @Test
    void decideHitOrStayPlayersByNone() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TWO));

        //when
        List<Player> decideHitOrStayPlayers = gameRule.decideHitOrStayPlayers(gamers);

        //then
        assertThat(decideHitOrStayPlayers).containsExactly(player);
    }

    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아닐 경우 플레이어는 블랙잭에 대한 배당을 받는다.")
    @Test
    void applyGameResultProfitByBlackjack() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(1500);
    }

    @DisplayName("플레이어가 블랙잭이 아니고, 딜러가 블랙잭일 경우 플레이어는 패배이며, 수익이 없다.")
    @Test
    void applyGameResultProfitByLose() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러가 모두 블랙잭일 경우 플레이어는 푸시이며, 배팅 금액 그대로를 받는다.")
    @Test
    void applyGameResultProfitByPush() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.ACE));
        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(1000);
    }

    @DisplayName("플레이어가 버스트일 경우 딜러의 결과에 관계없이 플레이어는 패배하며, 수익이 없다.")
    @Test
    void applyGameResultProfitByBust() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(0);
    }

    @DisplayName("플레이어가 버스트가 아니고, 딜러가 버스트일 경우 플레이어는 승리에 대한 배당을 받는다.")
    @Test
    void applyGameResultProfitByDealerBust() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TEN));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TWO));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.THREE));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(2000);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 높을 경우 플레이어는 승리에 대한 배당을 받는다.")
    @Test
    void applyGameResultProfitByHigherThanDealer() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(2000);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러보다 점수가 작을 경우 플레이어는 패배하며, 수익이 없다.")
    @Test
    void applyGameResultProfitByLowerThanDealer() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.EIGHT));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.TWO));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(0);
    }

    @DisplayName("플레이어와 딜러가 버스트나 블랙잭이 아닐때, 딜러와 점수가 동일할 경우 플레이어는 푸시이며, 배팅 금액 그대로를 받는다.")
    @Test
    void applyGameResultProfitBySameScore() {
        //given
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        Gamers gamers = new Gamers(dealer, List.of(player));
        BettingInfo bettingInfo = new BettingInfo();
        bettingInfo.addPlayerBetAmount(player, 1000);

        dealer.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        dealer.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        player.receiveCard(new Card(CardPattern.DIAMOND, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.HEART, CardNumber.TEN));
        player.receiveCard(new Card(CardPattern.SPADE, CardNumber.ACE));

        //when
        gameRule.decideHitOrStayPlayers(gamers);
        gameRule.applyGameResultProfit(bettingInfo, gamers);
        PlayerBetWallet playerBetWallet = bettingInfo.getBettingInfo().get(player);
        int playerProfitAmount = playerBetWallet.getProfitAmount();

        //then
        assertThat(playerProfitAmount).isEqualTo(1000);
    }
}
