package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackResultTest {
    @Test
    @DisplayName("플레이어와 딜러의 점수를 입력 받아, 둘 다 버스트가 아니면서 플레이어가 승리")
    void playerWinNotBust() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.findResult(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.WIN);
    }

    @Test
    @DisplayName("플레이어가 블랙잭이고, 딜러가 블랙잭이 아닐 경우 플레이어는 블랙잭 승리를 한다.")
    void playerBlackJackWin() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.ACE), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.findResult(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.BLACK_JACK_WIN);
    }

    @Test
    @DisplayName("플레이어와 딜러 모두 블랙잭일 경우, 무승부다.")
    void draw() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.ACE), new Card(CardShape.CLOVER, CardNumber.TEN));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.ACE), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.findResult(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.DRAW);
    }

    @Test
    @DisplayName("Result 결과 값의 반대 결과를 반환한다.")
    void getReverse() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.findResult(player, dealer);
        assertThat(blackJackResult.getReverse((int) blackJackResult.getProfitRate() * player.getBettingMoney())).isEqualTo(-1000);
    }

    @Test
    @DisplayName("점수가 21점으로 같아도, 블랙잭이면 승리한다.")
    void blackJack() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.ACE));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TEN), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        player.addCard(new Card(CardShape.HEART, CardNumber.ACE));
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.findResult(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.LOSE);
    }

    @Test
    @DisplayName("금액을 입력 받아 수익을 반환한다.")
    void getProfit() {
        BlackJackResult blackJackResult = BlackJackResult.WIN;
        assertThat(blackJackResult.getProfit(1000)).isEqualTo(1000);
    }
}