package model.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import model.card.Card;
import model.card.CardRank;
import model.card.CardSuit;
import model.participant.Dealer;
import model.participant.Player;
import model.turn.PlayerTurn;
import model.winning.WinningResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayersBettingTest {

    @Test
    @DisplayName("게임에서 졌을 경우 수익 확인 테스트")
    void computeResultByWinningStatus() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        List<PlayerTurn> playerTurns = List.of(playerTurn);
        PlayersBetting playersBetting = PlayersBetting.from(playerTurns);

        int expect = -10000;
        int result = playersBetting.computeResultByWinningStatus(player, WinningResult.LOSE);

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("게임에서 이겼을 경우 수익 확인 테스트")
    void 게임에서_이겼을_경우_수익_확인_테스트() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        List<PlayerTurn> playerTurns = List.of(playerTurn);
        PlayersBetting playersBetting = PlayersBetting.from(playerTurns);

        int expect = 10000;
        int result = playersBetting.computeResultByWinningStatus(player, WinningResult.WIN);

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("게임에서 비겼을 경우 수익 확인 테스트")
    void 게임에서_비겼을_경우_수익_확인_테스트() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        List<PlayerTurn> playerTurns = List.of(playerTurn);
        PlayersBetting playersBetting = PlayersBetting.from(playerTurns);

        int expect = 0;
        int result = playersBetting.computeResultByWinningStatus(player, WinningResult.DRAW);

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("게임에서 블랙잭으로 이겼을 경우 수익 확인 테스트")
    void 게임에서_블랙잭으로_이겼을_경우_수익_확인_테스트() {
        Player player = new Player("a");
        player.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        player.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        Betting betting = new Betting(10000);
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        List<PlayerTurn> playerTurns = List.of(playerTurn);
        PlayersBetting playersBetting = PlayersBetting.from(playerTurns);

        int expect = 15000;
        int result = playersBetting.computeResultByWinningStatus(player, WinningResult.WIN);

        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("보험금을 걸었고 딜러가 블랙잭이었을 경우 보험금 계산 확인 테스트")
    void 보험금을_걸었고_딜러가_블랙잭이었을_경우_보험금_확인_테스트() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        int insurance = 4000;
        betting.updateInsuranceBet(insurance);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.KING, CardSuit.CLOVER));
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        PlayersBetting playersBetting = PlayersBetting.from(List.of(playerTurn));

        int expect = 8000;
        int result = playersBetting.computeInsuranceResult(player, dealer);
        assertThat(expect).isEqualTo(result);
    }


    @Test
    @DisplayName("보험금을 걸었고 딜러가 블랙잭이 아니었을 경우 보험금 계산 확인 테스트")
    void computeInsuranceResult() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        int insurance = 4000;
        betting.updateInsuranceBet(insurance);
        Dealer dealer = new Dealer();
        dealer.receiveCard(new Card(CardRank.ACE, CardSuit.CLOVER));
        dealer.receiveCard(new Card(CardRank.NINE, CardSuit.CLOVER));
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        PlayersBetting playersBetting = PlayersBetting.from(List.of(playerTurn));

        int expect = 0;
        int result = playersBetting.computeInsuranceResult(player, dealer);
        assertThat(expect).isEqualTo(result);
    }

    @Test
    @DisplayName("플레이어가 서렌했을 경우 수익 확인 테스트")
    void computeSurrenderResult() {
        Player player = new Player("a");
        Betting betting = new Betting(10000);
        Dealer dealer = new Dealer();
        PlayerTurn playerTurn = new PlayerTurn(player, betting);
        PlayersBetting playersBetting = PlayersBetting.from(List.of(playerTurn));
        playersBetting.computeSurrenderResult(player);

        int expect = -5000;
        int result = playersBetting.computeSurrenderResult(player);
        assertThat(expect).isEqualTo(result);
    }
}