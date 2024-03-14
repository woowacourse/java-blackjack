package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Pattern;
import blackjack.domain.card.Rank;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.GameBattingManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class GameBattingManagerTest {

    @DisplayName("딜러와 플레이어가 둘 다 버스트면 플레이어가 배팅 금액을 모두 잃는다.")
    @Test
    void calculateBattingReward_allBust() {
        //given
        GameBattingManager gameBattingManager = new GameBattingManager();

        Dealer dealer = new Dealer();
        Player player = new Player("test");
        gameBattingManager.registerPlayerBatting(player, 1000);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TEN));

        //when
        gameBattingManager.decideWinner(dealer, player);

        double dealerResult = gameBattingManager.getDealerResult();
        double playerResult = gameBattingManager.getPlayersResult().get(player);

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(1000),
                () -> assertThat(playerResult).isEqualTo(-1000)
        );
    }

    @DisplayName("플레이어만 블랙잭이면 플레이어가 1.5배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_PlayerBlackjack() {
        //given
        GameBattingManager gameBattingManager = new GameBattingManager();
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        gameBattingManager.registerPlayerBatting(player, 1000);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));
        dealer.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        //when
        gameBattingManager.decideWinner(dealer, player);

        double dealerResult = gameBattingManager.getDealerResult();
        double playerResult = gameBattingManager.getPlayersResult().get(player);

         //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(-1500),
                () -> assertThat(playerResult).isEqualTo(1500)
        );
    }

    @DisplayName("플레이어와 딜러 모두 블랙잭이면 플레이어가 1배의 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_allBlackjack() {
        //given
        GameBattingManager gameBattingManager = new GameBattingManager();
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        gameBattingManager.registerPlayerBatting(player, 1000);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.CLOVER, Rank.ACE));

        //when
        gameBattingManager.decideWinner(dealer, player);

        double dealerResult = gameBattingManager.getDealerResult();
        double playerResult = gameBattingManager.getPlayersResult().get(player);

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(-1000),
                () -> assertThat(playerResult).isEqualTo(1000)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트와 블랙잭이 아닐 경우 더 큰 점수가 배팅 금액을 가져간다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack() {
        //given
        GameBattingManager gameBattingManager = new GameBattingManager();
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        gameBattingManager.registerPlayerBatting(player, 1000);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.TWO));

        //when
        gameBattingManager.decideWinner(dealer, player);

        double dealerResult = gameBattingManager.getDealerResult();
        double playerResult = gameBattingManager.getPlayersResult().get(player);

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(1000),
                () -> assertThat(playerResult).isEqualTo(-1000)
        );
    }

    @DisplayName("딜러와 플레이어가 둘 다 버스트나 블랙잭이 아닐 때 점수가 같으면 플레이어가 1배의 금액을 가져간다.")
    @Test
    void calculateBattingReward_neitherBusterOrBlackjack_sameScore() {
        //given
        GameBattingManager gameBattingManager = new GameBattingManager();
        Dealer dealer = new Dealer();
        Player player = new Player("test");
        gameBattingManager.registerPlayerBatting(player, 1000);

        dealer.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        dealer.receiveCard(new Card(Pattern.CLOVER, Rank.NINE));

        player.receiveCard(new Card(Pattern.SPADE, Rank.TEN));
        player.receiveCard(new Card(Pattern.HEART, Rank.NINE));

        //when
        gameBattingManager.decideWinner(dealer, player);

        double dealerResult = gameBattingManager.getDealerResult();
        double playerResult = gameBattingManager.getPlayersResult().get(player);

        //then
        assertAll(
                () -> assertThat(dealerResult).isEqualTo(-1000),
                () -> assertThat(playerResult).isEqualTo(1000)
        );
    }

}
