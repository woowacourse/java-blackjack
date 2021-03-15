package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.BettingMoney;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static blackjack.domain.game.WinOrLose.WIN_NORMAL;
import static org.assertj.core.api.Assertions.assertThat;

public class WinOrLoseTest {

    Dealer dealer = new Dealer();

    @BeforeEach
    void setUp() {
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.JACK));
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.TEN));
    }

    @Test
    @DisplayName("겜블러 블랙잭 승리 테스트")
    void testCalculateWinBlackJack() {
        //given
        Gambler gambler = new Gambler("pobi", new BettingMoney(10000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.JACK));

        //when
        WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);

        //then
        assertThat(winOrLose).isEqualTo(WinOrLose.WIN_BLACK_JACK);
    }

    @Test
    @DisplayName("겜블러 일반 승리 테스트")
    void testCalculateWinNormal() {
        //given
        Gambler gambler = new Gambler("jason", new BettingMoney(20000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.SIX));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.FIVE));

        //when
        WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);

        //then
        assertThat(winOrLose).isEqualTo(WIN_NORMAL);
    }

    @Test
    @DisplayName("겜블러와 딜러 무승부 테스트")
    void testCalculateDraw() {
        //given
        Gambler gambler = new Gambler("croffle", new BettingMoney(30000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.QUEEN));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));

        //when
        WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);

        //then
        assertThat(winOrLose).isEqualTo(WinOrLose.DRAW);
    }

    @Test
    @DisplayName("겜블러 패배 테스트")
    void testCalculateGamblerWinOrNot() {
        //given
        Gambler gambler = new Gambler("html", new BettingMoney(40000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.NINE));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));

        //when
        WinOrLose winOrLose = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);

        //then
        assertThat(winOrLose).isEqualTo(WinOrLose.LOSE);
    }
}
