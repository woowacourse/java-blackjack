package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WinOrLoseTest {

    @Test
    @DisplayName("딜러와 비교하여 각 경우에 대해 결과 테스트")
    void testCalculateGamblerWinOrNot() {
        //given
        Dealer dealer = new Dealer();
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.JACK));
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.TEN));

        Gambler gambler1 = new Gambler("pobi", new Money(10000));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.JACK));

        Gambler gambler2 = new Gambler("jason", new Money(20000));
        gambler2.cards().add(Card.of(Suit.HEART, Denomination.SIX));
        gambler2.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        gambler2.cards().add(Card.of(Suit.HEART, Denomination.FIVE));

        Gambler gambler3 = new Gambler("croffle", new Money(30000));
        gambler3.cards().add(Card.of(Suit.HEART, Denomination.QUEEN));
        gambler3.cards().add(Card.of(Suit.HEART, Denomination.TEN));

        Gambler gambler4 = new Gambler("html", new Money(40000));
        gambler4.cards().add(Card.of(Suit.HEART, Denomination.NINE));
        gambler4.cards().add(Card.of(Suit.HEART, Denomination.TEN));

        //when
        WinOrLose winOrLose1 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler1);
        WinOrLose winOrLose2 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler2);
        WinOrLose winOrLose3 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler3);
        WinOrLose winOrLose4 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler4);

        //then
        assertThat(winOrLose1).isEqualTo(WinOrLose.WIN_BLACK_JACK);
        assertThat(winOrLose2).isEqualTo(WinOrLose.WIN_NORMAL);
        assertThat(winOrLose3).isEqualTo(WinOrLose.DRAW);
        assertThat(winOrLose4).isEqualTo(WinOrLose.LOSE);
    }
}
