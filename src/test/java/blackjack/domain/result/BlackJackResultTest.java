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
    void of() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf("WIN"));
    }

    @Test
    @DisplayName("Result 결과 값의 반대 결과를 반환한다.")
    void getReverse() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult.getReverse()).isEqualTo(BlackJackResult.valueOf("LOSE"));
    }

    @Test
    @DisplayName("점수가 21점으로 같아도, 블랙잭이면 승리한다.")
    void blackJack() {
        List<Card> dealerCards = List.of(new Card(CardShape.DIAMOND, CardNumber.THREE), new Card(CardShape.CLOVER, CardNumber.EIGHT));
        List<Card> playerCards = List.of(new Card(CardShape.CLOVER, CardNumber.TWO), new Card(CardShape.CLOVER, CardNumber.TEN));
        Player player = new Player("범고래", playerCards, 1000);
        Dealer dealer = new Dealer(dealerCards);
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf("WIN"));
    }
}