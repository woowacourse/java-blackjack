package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.CardNumber;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import blackjack.fixture.GameManagerFixture;

class GameRoundTest {

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정한다.")
    void playerStartBettingTest() {
        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        assertThatCode(() -> round.betting(player, 1000))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃는다.")
    void additionalCardMoreThan21Test() {
        int initialMoney = 1000;

        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
        );
        gameManager.drawStartingCards(player);
        gameManager.drawCard(player);

        assertThat(round.loseIfBust(player)).isTrue();
        assertThat(round.getEndBettingMoney(player)).isZero();
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅금액의 1.5배를 받는다.")
    void blackjackBettingTest() {
        int initialMoney = 10000;

        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE)
        );
        gameManager.drawStartingCards(player);
        assertThat(round.endGameIfBlackjack(player)).isTrue();
        assertThat(round.getEndBettingMoney(player)).isEqualTo((int)(initialMoney * 1.5));
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.")
    void bothBalckjackRollbackTest() {
        int initialMoney = 10000;

        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        round.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE, CardNumber.JACK, CardNumber.ACE)
        );
        gameManager.drawStartingCards(player);
        gameManager.drawStartingCards(dealer);
        assertThat(round.endGameIfBlackjack(dealer)).isTrue();
        assertThat(round.getEndBettingMoney(player)).isEqualTo(initialMoney);
    }

    @Test
    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 승리해 베팅 금액을 받는다.")
    void dealerBustPlayersWinTest() {
        int initialMoney = 10000;

        Player player = new Player("pobi");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        round.betting(player, initialMoney);
        round.dealerBust();
        assertThat(round.getEndBettingMoney(player)).isEqualTo(initialMoney * 2);
    }

    @Test
    @DisplayName("게임 종료 시 최종 수익을 출력한다.")
    void finalBettingMoneyTest() {
        int player1Money = 10000;
        int player2Money = 5000;

        Player player1 = new Player("pobi");
        Player player2 = new Player("moko");
        Dealer dealer = new Dealer();
        GameRound round = new GameRound(dealer);
        round.betting(player1, player1Money);
        round.betting(player2, player2Money);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(
                CardNumber.KING, CardNumber.SEVEN, // dealer
                CardNumber.TWO, CardNumber.THREE, // player 2
                CardNumber.JACK, CardNumber.ACE)); // player 1
        gameManager.drawStartingCards(player1);
        gameManager.drawStartingCards(player2);
        gameManager.drawStartingCards(dealer);

        round.computeResult();
        Map<Gamer, Double> profit = round.getAllProfit();
        assertThat(profit.get(player1)).isEqualTo(player1Money);
        assertThat(profit.get(player2)).isEqualTo(-player2Money);
        assertThat(profit.get(dealer)).isEqualTo(-player1Money + player2Money);
    }
}
