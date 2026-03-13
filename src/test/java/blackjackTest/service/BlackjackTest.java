package blackjackTest.service;

import blackjack.domain.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlackjackTest {

    @Test
    void 플레이어_초기카드_합_21인지_확인() {
        Player pobi = new Player("pobi", new Money(10000));
        pobi.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));

        assertThat(pobi.isBlackjack()).isEqualTo(true);
    }

    @Test
    void 플레이어_초기카드_합_21_아닌경우_1() {
        Player pobi = new Player("pobi", new Money(10000));
        pobi.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));

        assertThat(pobi.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 플레이어_초기카드_합_21_아닌경우_2() {
        Player pobi = new Player("pobi", new Money(10000));
        pobi.receiveOneCard(new Card(Rank.TWO, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        pobi.receiveOneCard(new Card(Rank.NINE, Shape.SPADE));

        assertThat(pobi.isBlackjack()).isEqualTo(false);
    }

    @Test
    void 플레이어_초기카드_합_21이면_배팅금액의_1_5배_지급() {
        Money bettingAmount = new Money(10000);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));

        int profit = pobi.calculateFinalProfit(dealer);

        assertThat(profit).isEqualTo(15000);
    }

    @Test
    void 플레이어_카드합_21_넘으면_배팅금액_모두_읾음() {
        Money bettingAmount = new Money(10000);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        pobi.receiveOneCard(new Card(Rank.TWO, Shape.CLOVER));

        int profit = pobi.calculateFinalProfit(dealer);

        assertThat(profit).isEqualTo(-10000);
    }
}
