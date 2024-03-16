package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RuleTest {
    @DisplayName("딜러 블랙잭, 플레이어 블랙잭: 수익률 0%")
    @Test
    void blackJackAndBlackjack() {
        // given 
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.ACE, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.ACE, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(0);
    }

    @DisplayName("딜러 블랙잭, 플레이어 블랙잭 이외: 수익률 -100%")
    @Test
    void blackJackAndNotBlackjack() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.ACE, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 블랙잭 이외, 플레이어 블랙잭: 수익률 150%")
    @Test
    void notBlackJackAndBlackjack() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.ACE, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(150);
    }

    @DisplayName("딜러 버스트, 플레이어 버스트X: 수익률 100%")
    @Test
    void bustAndBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.QUEEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트, 플레이어 버스트X: 수익률 100%")
    @Test
    void bustAndNotBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.KING, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.QUEEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트: 수익률 -100%")
    @Test
    void notBustAndBust() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.KING, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.QUEEN, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 딜러승: 수익률 -100%")
    @Test
    void dealerWin() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.THREE, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(-100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 플레이어승: 수익률 100%")
    @Test
    void playerWin() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.THREE, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(100);
    }

    @DisplayName("딜러 버스트X, 플레이어 버스트X, 동점: 수익률 0%")
    @Test
    void tie() {
        // given
        final Player pobi = new Player(new Name("pobi"), new BetAmount(100));
        pobi.drawCard(new Card(Denomination.TEN, Suit.DIAMOND));
        pobi.drawCard(new Card(Denomination.TWO, Suit.DIAMOND));

        final Dealer dealer = new Dealer(new Deck());
        dealer.drawCard(new Card(Denomination.TEN, Suit.CLUBS));
        dealer.drawCard(new Card(Denomination.TWO, Suit.CLUBS));
        // when
        final Rule rule = new Rule();
        final int profit = rule.profit(pobi, dealer);
        //then
        Assertions.assertThat(profit).isEqualTo(0);
    }
}
