package blackjack;

import blackjack.domain.WinOrLose;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardNumber;
import blackjack.domain.card.Symbol;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {

    Player dealer = new Dealer();
    Player gamer = new Gamer("pobi", 1);

    @BeforeEach
    void init() {
        dealer = new Dealer();
        gamer = new Gamer("pobi", 1);
    }

    @Test
    @DisplayName("게임의 승패를 구한다. 딜러의 점수와 비교하여 구한다.")
    void calculate() {
        //win
        dealer.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.TWO));
        gamer.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.THREE));

        assertThat(WinOrLose.calculate(dealer, gamer)).isEqualTo(WinOrLose.WIN);
        //lose
        dealer.addCardToDeck(new Card(Symbol.CLOVER, CardNumber.FIVE));
        assertThat(WinOrLose.calculate(dealer, gamer)).isEqualTo(WinOrLose.LOSE);
    }

    @Test
    @DisplayName("게임 결과를 뒤집는다.")
    void reverse() {
        assertThat(WinOrLose.WIN.reverse()).isEqualTo(WinOrLose.LOSE);
        assertThat(WinOrLose.LOSE.reverse()).isEqualTo(WinOrLose.WIN);
    }
}