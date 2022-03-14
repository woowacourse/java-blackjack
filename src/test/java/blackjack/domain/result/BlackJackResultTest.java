package blackjack.domain.result;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Bet;
import blackjack.domain.player.Gamer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackResultTest {

    @Test
    @DisplayName("블랙잭 게임 룰 상 결과가 블랙잭이다.")
    void blackJackResultBlackJack() {
        Gamer gamer = new Gamer("huni", new Bet(10000));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.ACE));

        BlackJackResult blackJackResult = BlackJackResult.findBlackJackResult(gamer);
        assertAll(
                () -> assertThat(blackJackResult).isEqualTo(BlackJackResult.BLACK_JACK),
                () -> assertThat(blackJackResult.getResult()).isInstanceOf(BlackJack.class)
        );
    }

    @Test
    @DisplayName("블랙잭 게임 룰 상 결과가 버스트이다.")
    void blackJackResultBust() {
        Gamer gamer = new Gamer("huni", new Bet(10000));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.SPADE, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.HEART, Denomination.TWO));

        BlackJackResult blackJackResult = BlackJackResult.findBlackJackResult(gamer);
        assertAll(
                () -> assertThat(blackJackResult).isEqualTo(BlackJackResult.BUST),
                () -> assertThat(blackJackResult.getResult()).isInstanceOf(Lose.class)
        );
    }

    @Test
    @DisplayName("블랙잭 게임 룰 상 결과가 히트 이다.")
    void blackJackResultHit() {
        Gamer gamer = new Gamer("huni", new Bet(10000));
        gamer.receiveCard(new Card(Suit.DIAMOND, Denomination.JACK));
        gamer.receiveCard(new Card(Suit.SPADE, Denomination.JACK));

        BlackJackResult blackJackResult = BlackJackResult.findBlackJackResult(gamer);
        assertAll(
                () -> assertThat(blackJackResult).isEqualTo(BlackJackResult.HIT),
                () -> assertThat(blackJackResult.getResult()).isInstanceOf(Keep.class)
        );
    }

}
