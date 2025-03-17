package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.card.CardNumber;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.fixture.DeckFixture;
import blackjack.fixture.GameManagerFixture;

class BettingTableTest {

    private Player player = new Player("pobi");
    private Dealer dealer = new Dealer();
    private BettingTable bettingTable = new BettingTable(dealer);

    @Test
    @DisplayName("플레이어는 게임을 시작할 때 배팅 금액을 정한다.")
    void bettingTest() {
        // when
        Throwable throwable = catchThrowable(() -> {
            bettingTable.betting(player, 1000);
        });

        // then
        assertThat(throwable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("카드를 추가로 뽑아 21을 초과할 경우 베팅 금액을 모두 잃는다.")
    void loseIfBustTest() {
        // given
        double initialMoney = 1000;
        bettingTable.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING)
        );

        // when
        gameManager.drawStartingCards(player);
        gameManager.drawCard(player);

        // then
        assertThat(bettingTable.getEndBettingMoney(player)).isZero();
    }

    @Test
    @DisplayName("처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅금액의 1.5배를 받는다.")
    void endGameIfBlackjackTest() {
        // given
        double initialMoney = 10000;
        bettingTable.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE)
        );

        // when
        gameManager.drawStartingCards(player);
        bettingTable.endGameIfBlackjack(player);

        // then
        assertThat(bettingTable.getEndBettingMoney(player)).isEqualTo(initialMoney * 1.5);
    }

    @Test
    @DisplayName("딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.")
    void bothBlackjackTest() {
        // given
        double initialMoney = 10000;
        bettingTable.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(CardNumber.JACK, CardNumber.ACE, CardNumber.JACK, CardNumber.ACE)
        );

        // when
        gameManager.drawStartingCards(player);
        gameManager.drawStartingCards(dealer);

        bettingTable.endGameIfBlackjack(dealer);

        // then
        assertThat(bettingTable.getEndBettingMoney(player)).isEqualTo(initialMoney);
    }

    @Test
    @DisplayName("딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 승리해 베팅 금액을 받는다.")
    void endGameIfDealerBustTest() {
        // given
        double initialMoney = 10000;
        bettingTable.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(
                CardNumber.KING, CardNumber.SEVEN, CardNumber.QUEEN, // dealer
                CardNumber.JACK, CardNumber.ACE)); // player

        // when
        gameManager.drawStartingCards(player);
        gameManager.drawStartingCards(dealer);
        gameManager.drawCard(dealer);
        bettingTable.endGameIfDealerBust();

        // then
        assertThat(bettingTable.getEndBettingMoney(player)).isEqualTo(initialMoney * 2);
    }

    @Test
    @DisplayName("게임 종료 시 최종 수익을 출력한다.")
    void computeResultTest1() {
        // given
        int player1Money = 10000;
        int player2Money = 5000;
        Player player1 = new Player("pobi");
        Player player2 = new Player("moko");
        bettingTable.betting(player1, player1Money);
        bettingTable.betting(player2, player2Money);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(
                CardNumber.KING, CardNumber.SEVEN, // dealer
                CardNumber.TWO, CardNumber.THREE, // player 2
                CardNumber.JACK, CardNumber.ACE)); // player 1
        gameManager.drawStartingCards(player1);
        gameManager.drawStartingCards(player2);
        gameManager.drawStartingCards(dealer);

        // when
        bettingTable.computeResult();

        // then
        Map<Gamer, Double> profit = bettingTable.getAllProfit();
        assertThat(profit.get(player1)).isEqualTo(player1Money);
        assertThat(profit.get(player2)).isEqualTo(-player2Money);
        assertThat(profit.get(dealer)).isEqualTo(-player1Money + player2Money);
    }


    @Test
    @DisplayName("무승부일 경우 배팅 금액을 그대로 돌려받는다.")
    void computeResultTest2() {
        // given
        int initialMoney = 10000;
        Player player = new Player("pobi");
        bettingTable.betting(player, initialMoney);
        GameManager gameManager = GameManagerFixture.GameManagerWith(
            DeckFixture.deckOf(
                CardNumber.JACK, CardNumber.ACE, // dealer
                CardNumber.JACK, CardNumber.ACE)); // player 1
        gameManager.drawStartingCards(player);
        gameManager.drawStartingCards(dealer);

        // when
        bettingTable.computeResult();

        // then
        Map<Gamer, Double> profit = bettingTable.getAllProfit();
        assertThat(profit.get(player)).isEqualTo(0);
    }
}
