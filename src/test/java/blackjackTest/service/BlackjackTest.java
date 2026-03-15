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
        int bettingAmountValue = 10000;
        Money bettingAmount = new Money(bettingAmountValue);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.ACE, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));

        long profit = pobi.calculateFinalProfit(dealer);
        long expectedAmount = (long) (bettingAmountValue * 1.5);
        assertThat(profit).isEqualTo(expectedAmount);
    }

    @Test
    void 플레이어_카드합_21_넘으면_배팅금액_모두_잃음() {
        long bettingAmountValue = 10000;
        Money bettingAmount = new Money(bettingAmountValue);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        pobi.receiveOneCard(new Card(Rank.TWO, Shape.CLOVER));

        long profit = pobi.calculateFinalProfit(dealer);
        long expectedAmount = -bettingAmountValue;
        assertThat(profit).isEqualTo(expectedAmount);
    }

    @Test
    void 딜러_초기카드_합_21이면_배팅금액_모두_잃음() {
        long bettingAmountValue = 10000;
        Money bettingAmount = new Money(bettingAmountValue);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TEN, Shape.SPADE));
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.CLOVER));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        long profit = pobi.calculateFinalProfit(dealer);
        long expectedAmount = -bettingAmountValue;
        assertThat(profit).isEqualTo(expectedAmount);
    }

    @Test
    void 딜러와_플레이어_모두_초기카드_합_21이면_배팅금액_돌려받음() {
        long bettingAmountValue = 10000;
        Money bettingAmount = new Money(bettingAmountValue);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.ACE, Shape.SPADE));
        dealer.receiveOneCard(new Card(Rank.ACE, Shape.CLOVER));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        long profit = pobi.calculateFinalProfit(dealer);
        assertThat(profit).isEqualTo(bettingAmountValue);
    }

    @Test
    void 플레이어가_딜러보다_점수가_높으면_배팅금액_돌려받음() {
        long bettingAmountValue = 10000;
        Money bettingAmount = new Money(bettingAmountValue);
        Player pobi = new Player("pobi", bettingAmount);
        Dealer dealer = new Dealer();

        pobi.receiveOneCard(new Card(Rank.TEN, Shape.HEART));
        pobi.receiveOneCard(new Card(Rank.TWO, Shape.SPADE));
        pobi.receiveOneCard(new Card(Rank.EIGHT, Shape.CLOVER));

        dealer.receiveOneCard(new Card(Rank.EIGHT, Shape.CLOVER));
        dealer.receiveOneCard(new Card(Rank.QUEEN, Shape.SPADE));

        long profit = pobi.calculateFinalProfit(dealer);

        assertThat(profit).isEqualTo(bettingAmountValue);
    }
}
