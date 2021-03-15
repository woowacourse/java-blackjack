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

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    Result result;
    Dealer dealer;

    @BeforeEach
    void beforeEach() {
        dealer = new Dealer();
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.JACK));
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.TEN));
        result = new Result(dealer);
    }

    @Test
    @DisplayName("딜러, 겜블러 카드 초기화 테스트")
    void testAdd() {
        //given
        Gambler gambler1 = new Gambler("pobi", new BettingMoney(10000));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.JACK));
        WinOrLose winOrLose1 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler1);

        //when
        result.add(gambler1, winOrLose1);

        //then
        assertThat(result.getGamblerResult().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러가 이겼을 때 수익률 테스트")
    void testCalculateProfit() {
        //given
        Gambler gambler = new Gambler("jason", new BettingMoney(20000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.JACK));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.NINE));
        WinOrLose winOrLose2 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);

        result.add(gambler, winOrLose2);

        //when
        result.calculateProfit();

        //then
        assertThat(dealer.money()).isEqualTo(new BettingMoney(20000));
    }

    @Test
    @DisplayName("겜블러가 졌을 때 수익률 테스트")
    void testCalculateProfit2() {
        //given
        Gambler gambler = new Gambler("croffle", new BettingMoney(30000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.NINE));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        WinOrLose winOrLose3 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);
        result.add(gambler, winOrLose3);

        //when
        result.calculateProfit();

        //then
        assertThat(gambler.money()).isEqualTo(new BettingMoney(-30000));
    }

    @Test
    @DisplayName("겜블러가 이겼을 때 수익률 테스트")
    void testCalculateProfit3() {
        //given
        Gambler gambler = new Gambler("croffle", new BettingMoney(30000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.JACK));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        WinOrLose winOrLose3 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);
        result.add(gambler, winOrLose3);

        //when
        result.calculateProfit();

        //then
        assertThat(gambler.money()).isEqualTo(new BettingMoney(30000));
    }

    @Test
    @DisplayName("겜블러가 블랙잭일 때 수익률 테스트")
    void testCalculateProfit4() {
        //given
        Gambler gambler = new Gambler("croffle", new BettingMoney(30000));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        gambler.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        WinOrLose winOrLose3 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler);
        result.add(gambler, winOrLose3);

        //when
        result.calculateProfit();

        //then
        assertThat(gambler.money()).isEqualTo(new BettingMoney(45000));
    }
}
