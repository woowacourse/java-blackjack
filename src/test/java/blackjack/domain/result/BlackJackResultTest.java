package blackjack.domain.result;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.CardShape;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BlackJackResultTest {

    @Test
    @DisplayName("플레이어와 딜러의 점수를 입력 받아, 둘 다 버스트가 아니면서 플레이어가 승리")
    void of() {
        Player player = new Player("범고래");
        Dealer dealer = new Dealer();
        player.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.JACK));
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf("WIN"));
    }

    @Test
    @DisplayName("Result 결과 값의 반대 결과를 반환한다.")
    void getReverse() {
        Player player = new Player("범고래");
        Dealer dealer = new Dealer();
        player.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.JACK));
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult.getReverse()).isEqualTo(BlackJackResult.valueOf("LOSE"));
    }

    @Test
    @DisplayName("점수가 21점으로 같아도, 블랙잭이면 승리한다.")
    void blackJack() {
        Player player = new Player("범고래");
        Dealer dealer = new Dealer();
        player.addCard(new Card(CardShape.DIAMOND, CardNumber.ACE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.JACK));
        player.addCard(new Card(CardShape.DIAMOND, CardNumber.JACK));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.FIVE));
        dealer.addCard(new Card(CardShape.DIAMOND, CardNumber.SIX));
        BlackJackResult blackJackResult = BlackJackResult.of(player, dealer);
        assertThat(blackJackResult).isEqualTo(BlackJackResult.valueOf("WIN"));
    }
}