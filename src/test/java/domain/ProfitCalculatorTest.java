package domain;

import domain.card.Card;
import domain.card.CardNumber;
import domain.card.CardShape;
import domain.hand.Hand;
import domain.participant.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfitCalculatorTest {

    @Test
    void 플레이어가_버스트면_배팅금액만큼_잃는다() {
        Player player = player("pobi",1000,
                card(CardNumber.K, CardShape.SPADE),
                card(CardNumber.Q, CardShape.HEART),
                card(CardNumber.TWO, CardShape.CLOVER));

        Dealer dealer = dealer(
                card(CardNumber.TEN, CardShape.DIAMOND),
                card(CardNumber.SEVEN, CardShape.CLOVER));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);

        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    void 플레이어가_블랙잭이면_배팅금액의_1점5배를_번다(){
        Player player = player("pobi",1000,
                card(CardNumber.ACE, CardShape.DIAMOND),
                card(CardNumber.K, CardShape.CLOVER));

        Dealer dealer = dealer(
                card(CardNumber.TEN, CardShape.CLOVER),
                card(CardNumber.EIGHT, CardShape.DIAMOND));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);
        assertThat(profit).isEqualTo(1500);
    }

    @Test
    void 플레이어와_딜러가_모두_블랙잭이면_무승부다(){
        Player player = player("pobi",1000,
                card(CardNumber.ACE, CardShape.SPADE),
                card(CardNumber.K, CardShape.HEART));

        Dealer dealer = dealer(
                card(CardNumber.ACE, CardShape.DIAMOND),
                card(CardNumber.Q, CardShape.CLOVER));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);

        assertThat(profit).isEqualTo(0);
    }

    @Test
    void 딜러가_버스트면_플레이어는_배팅금액만큼_수익(){
        Player player = player("pobi",1000,
                card(CardNumber.TEN, CardShape.SPADE),
                card(CardNumber.NINE, CardShape.HEART));

        Dealer dealer = dealer(
                card(CardNumber.K, CardShape.DIAMOND),
                card(CardNumber.TWO, CardShape.CLOVER),
                card(CardNumber.Q, CardShape.HEART));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);

        assertThat(profit).isEqualTo(1000);
    }

    @Test
    void 플레이어_점수가_더_크면_배팅금액만큼_수익(){
        Player player = player("pobi",1000,
                card(CardNumber.EIGHT, CardShape.DIAMOND),
                card(CardNumber.K, CardShape.SPADE));

        Dealer dealer = dealer(
                card(CardNumber.TEN, CardShape.CLOVER),
                card(CardNumber.SEVEN, CardShape.HEART));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);
        assertThat(profit).isEqualTo(1000);
    }
    @Test
    void 플레이어_점수가_더_작으면_배팅금액만큼_잃는다() {
        Player player = player("pobi",1000,
                card(CardNumber.TEN, CardShape.SPADE),
                card(CardNumber.SEVEN, CardShape.HEART));

        Dealer dealer = dealer(
                card(CardNumber.TEN, CardShape.DIAMOND),
                card(CardNumber.NINE, CardShape.CLOVER));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);

        assertThat(profit).isEqualTo(-1000);
    }

    @Test
    void 점수가_같으면_무승부다() {
        Player player = player("pobi",1000,
                card(CardNumber.TEN, CardShape.SPADE),
                card(CardNumber.EIGHT, CardShape.HEART));

        Dealer dealer = dealer(
                card(CardNumber.NINE, CardShape.DIAMOND),
                card(CardNumber.NINE, CardShape.CLOVER));

        double profit = ProfitCalculator.calculatePlayerProfit(player, dealer);

        assertThat(profit).isEqualTo(0);
    }

    @Test
    void 딜러_수익은_모든_플레이어_수익의_반대값이다() {
        Player winPlayer = player("pobi", 1000,
                card(CardNumber.TEN, CardShape.SPADE),
                card(CardNumber.NINE, CardShape.HEART));

        Player losePlayer = player("jason", 2000,
                card(CardNumber.TEN, CardShape.CLOVER),
                card(CardNumber.SEVEN, CardShape.DIAMOND));

        Dealer dealer = dealer(
                card(CardNumber.TEN, CardShape.DIAMOND),
                card(CardNumber.EIGHT, CardShape.CLOVER));

        Players players = Players.from(List.of(winPlayer, losePlayer));

        double dealerProfit = ProfitCalculator.calculateDealerProfit(players, dealer);

        assertThat(dealerProfit).isEqualTo(1000);
    }


    private Player player(String name, int bettingMoney, Card... cards) {
        return Player.of(
                Name.from(name),
                new Hand(List.of(cards)),
                BettingMoney.of(bettingMoney)
        );
    }

    private Dealer dealer(Card... cards) {
        return Dealer.from(new Hand(List.of(cards)));
    }

    private Card card(CardNumber cardNumber, CardShape cardShape){
        return Card.of(cardNumber, cardShape);
    }

}
