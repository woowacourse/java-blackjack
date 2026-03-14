package blackjack.domain.profit;

import blackjack.domain.card.Card;
import blackjack.domain.card.Rank;
import blackjack.domain.card.Suit;
import blackjack.domain.money.Money;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.GameResults;
import blackjack.domain.wager.Wager;
import blackjack.domain.wager.Wagers;
import blackjack.fixture.GameResultFixture;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitCalculatorTest {

    @Test
    void 플레이어가_지면_베팅금액만큼_잃고_딜러_수익에_그만큼_합산된다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = GameResultFixture.플레이어가_딜러에게_지는_게임_결과(player, dealer);

        Money wager = new Money(10000);

        Wagers wagers = new Wagers();
        wagers.add(new Wager(player, wager));

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);

        assertThat(profitResults.profitOf(player)).isEqualTo(wager.negate());
        assertThat(profitResults.dealerProfit()).isEqualTo(wager);
    }

    @Test
    void 플레이어가_이기면_베팅금액만큼_얻고_딜러_수익에서_그만큼_차감된다() {
        Dealer dealer = new Dealer();
        Player player = new Player("pobi");
        GameResults gameResults = GameResultFixture.플레이어가_딜러에게_이기는_게임_결과(player, dealer);

        Money wager = new Money(10000);

        Wagers wagers = new Wagers();
        wagers.add(new Wager(player, wager));

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);

        assertThat(profitResults.profitOf(player)).isEqualTo(wager);
        assertThat(profitResults.dealerProfit()).isEqualTo(wager.negate());
    }

    @Test
    void 플레이어_중에_한_명이_비기면_딜러_수익에_합산도_차감도_되지_않는다() {
        Dealer dealer = new Dealer();
        Player loser = new Player("loser");
        Player drawer = new Player("drawer");

        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SEVEN));

        loser.receiveCard(new Card(Suit.HEART, Rank.TEN));
        loser.receiveCard(new Card(Suit.SPADE, Rank.SIX));

        drawer.receiveCard(new Card(Suit.CLUB, Rank.TEN));
        drawer.receiveCard(new Card(Suit.CLUB, Rank.SEVEN));

        GameResults gameResults = GameResults.create(new Players(List.of(loser, drawer)), dealer);

        Money loserWager = new Money(10000);
        Money drawerWager = new Money(20000);

        Wagers wagers = new Wagers();
        wagers.add(new Wager(loser, loserWager));
        wagers.add(new Wager(drawer, drawerWager));
        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);

        assertThat(profitResults.profitOf(loser)).isEqualTo(loserWager.negate());
        assertThat(profitResults.profitOf(drawer)).isEqualTo(new Money(0));
        assertThat(profitResults.dealerProfit()).isEqualTo(loserWager);
    }

    @Test
    void 플레이어만_블랙잭이면_플레이어는_베팅_금액의_150퍼센트의_수익을_얻고_딜러는_그만큼_잃는다() {
        Dealer dealer = new Dealer();
        Player blackjack = new Player("blackjack");

        blackjack.receiveCard(new Card(Suit.HEART, Rank.ACE));
        blackjack.receiveCard(new Card(Suit.HEART, Rank.KING));

        dealer.receiveCard(new Card(Suit.HEART, Rank.TEN));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.SIX));

        GameResults gameResults = GameResults.create(new Players(List.of(blackjack)), dealer);

        Money blackjackWager = new Money(10000);
        Wagers wagers = new Wagers();
        wagers.add(new Wager(blackjack, blackjackWager));

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);

        Money expectedProfit = blackjackWager.multiply(1.5);
        assertThat(profitResults.profitOf(blackjack)).isEqualTo(expectedProfit);
        assertThat(profitResults.dealerProfit()).isEqualTo(expectedProfit.negate());
    }

    @Test
    void 플레이어와_딜러_모두_블랙잭이면_플레이어와_딜러_모두_수익이_없다() {
        Dealer dealer = new Dealer();
        Player blackjack = new Player("blackjack");

        blackjack.receiveCard(new Card(Suit.HEART, Rank.ACE));
        blackjack.receiveCard(new Card(Suit.HEART, Rank.KING));

        dealer.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        dealer.receiveCard(new Card(Suit.SPADE, Rank.KING));

        GameResults gameResults = GameResults.create(new Players(List.of(blackjack)), dealer);

        Money blackjackWager = new Money(10000);
        Wagers wagers = new Wagers();
        wagers.add(new Wager(blackjack, blackjackWager));

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);

        Money expectedProfit = new Money(0);
        assertThat(profitResults.profitOf(blackjack)).isEqualTo(expectedProfit);
        assertThat(profitResults.dealerProfit()).isEqualTo(expectedProfit);
    }

    @Test
    void 딜러만_블랙잭이면_플레이어의_베팅_금액만큼_잃고_딜러는_그만큼_얻는다() {
        Dealer blackjackDealer = new Dealer();
        Player loser = new Player("loser");
        Money money = new Money(10000);
        loser.receiveCard(new Card(Suit.HEART, Rank.QUEEN));
        loser.receiveCard(new Card(Suit.HEART, Rank.KING));
        blackjackDealer.receiveCard(new Card(Suit.SPADE, Rank.ACE));
        blackjackDealer.receiveCard(new Card(Suit.SPADE, Rank.KING));

        GameResults gameResults = GameResults.create(new Players(List.of(loser)), blackjackDealer);

        Wagers wagers = new Wagers();
        wagers.add(new Wager(loser, money));

        ProfitCalculator profitCalculator = new ProfitCalculator();
        ProfitResults profitResults = profitCalculator.calculate(gameResults, wagers);


        Money expectedPlayerProfit = new Money(-10000);
        assertThat(profitResults.profitOf(loser)).isEqualTo(expectedPlayerProfit);
        assertThat(profitResults.dealerProfit()).isEqualTo(expectedPlayerProfit.negate());
    }
}
