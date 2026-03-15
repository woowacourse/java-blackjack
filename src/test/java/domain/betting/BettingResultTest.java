package domain.betting;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BettingResultTest {
    @Test
    @DisplayName("플레이어와 딜러 모두 버스트가 아닐 때, 딜러보다 21에 가까우면, 베팅한 금액먄큼 받는다.")
    void playerStayWinTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.stay();

        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.EIGHT, CardShape.CLUB));
        dealer.stay();

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(1.0);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트가 아닐 때, 딜러가 21에 가까우면, 베팅한 금액먄큼 잃는다.")
    void playerStayLoseTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.stay();

        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.ACE, CardShape.CLUB));
        dealer.stay();

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 버스트가 아닐 때, 딜러와 플레이어의 점수가 같다면, 돈을 돌려받는다.")
    void playerDrawTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.stay();

        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.JACK, CardShape.CLUB));
        dealer.stay();

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(0.0);
    }


    @Test
    @DisplayName("플레이어가 버스트이면 베팅한 돈을 잃는다.")
    void playerBustTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.TEN, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.draw(new Card(CardNumber.FIVE, CardShape.CLUB));

        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.EIGHT, CardShape.CLUB));

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(-1.0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아니라면, 베팅한 금액의 1.5배의 돈을 받는다.")
    void playerBlackJackWinTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.ACE, CardShape.CLUB), new Card(CardNumber.TEN, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.EIGHT, CardShape.CLUB));

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(1.5);
    }

    @Test
    @DisplayName("플레이어와 딜러 둘다 블랙잭이라면, 돈을 돌려받는다.")
    void playerBlackJackDrawTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.ACE, CardShape.CLUB), new Card(CardNumber.JACK, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.QUEEN, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        dealer.drawInitialCards(dealerInitHands);

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(0.0);
    }

    @Test
    @DisplayName("플레이어가 버스트가 아니고 딜러가 버스트라면 베팅한 금액먄큼 돈을 받는다.")
    void dealerBustTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.FIVE, CardShape.CLUB), new Card(CardNumber.FOUR, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.stay();

        dealer.drawInitialCards(dealerInitHands);

        dealer.draw(new Card(CardNumber.SIX, CardShape.CLUB));
        dealer.draw(new Card(CardNumber.TEN, CardShape.CLUB));


        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(1.0);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이 아니고 딜러 둘다 블랙잭이라면 베팅한 금액먄큼 돈을 잃는다.")
    void dealerBlackJackTest() {
        // Given
        Player player = new Player("플레이어1");
        Dealer dealer = new Dealer();

        List<Card> playerInitHands = List.of(new Card(CardNumber.EIGHT, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));
        List<Card> dealerInitHands = List.of(new Card(CardNumber.QUEEN, CardShape.CLUB), new Card(CardNumber.ACE, CardShape.DIAMOND));

        player.drawInitialCards(playerInitHands);
        player.stay();

        dealer.drawInitialCards(dealerInitHands);

        // When
        double earningsRate = GameResult.judge(player, dealer).getEarningRate();

        // Then
        assertThat(earningsRate).isEqualTo(-1.0);
    }
}
