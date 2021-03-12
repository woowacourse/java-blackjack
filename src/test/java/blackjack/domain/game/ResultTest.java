package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    Result result;
    Dealer dealer;

    @BeforeEach
    void beforeEach() {
        //given
        dealer = new Dealer();
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.JACK));
        dealer.cards().add(Card.of(Suit.SPADE, Denomination.TEN));
        result = new Result(dealer);
    }

    @Test
    @DisplayName("딜러, 겜블러 카드 초기화 테스트")
    void testAdd() {
        //given
        Gambler gambler1 = new Gambler("pobi", new Money(10000));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.ACE));
        gambler1.cards().add(Card.of(Suit.HEART, Denomination.JACK));
        WinOrLose winOrLose1 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler1);

        //when
        result.add(gambler1, winOrLose1);

        //then
        assertThat(result.getGamblerResult().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("딜러와 겜블러 수익률 테스트")
    void testCalculateProfit() {
        //given
        Gambler gambler2 = new Gambler("jason", new Money(20000));
        gambler2.cards().add(Card.of(Suit.HEART, Denomination.JACK));
        gambler2.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        WinOrLose winOrLose2 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler2);
        Gambler gambler3 = new Gambler("croffle", new Money(30000));
        gambler3.cards().add(Card.of(Suit.HEART, Denomination.NINE));
        gambler3.cards().add(Card.of(Suit.HEART, Denomination.TEN));
        WinOrLose winOrLose3 = WinOrLose.calculateGamblerWinOrNot(dealer, gambler3);
        result.add(gambler2, winOrLose2);
        result.add(gambler3, winOrLose3);

        //when
        result.calculateProfit();

        //then
        assertThat(dealer.money()).isEqualTo(new Money(30000));
        assertThat(gambler2.money()).isEqualTo(new Money(0));
        assertThat(gambler3.money()).isEqualTo(new Money(-30000));
    }
}
