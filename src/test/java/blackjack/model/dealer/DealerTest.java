package blackjack.model.dealer;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.dto.PlayerBettingProfitOutcome;
import blackjack.model.card.Card;
import blackjack.model.card.Denomination;
import blackjack.model.card.Suit;
import blackjack.model.cardgenerator.SequentialCardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("카드 딜링을 하면 딜러가 카드를 2장 받는다")
    void dealTest() {
        // when
        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // then
        assertThat(dealer.getCards()).hasSize(2);
    }

    @Test
    @DisplayName("딜러는 카드의 합이 17점 이상이 될 때까지 카드를 받는다")
    void drawTest() {
        // given
        Dealer dealer = new Dealer();
        dealer.dealCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TWO),
                new Card(Suit.HEART, Denomination.TWO)
        )));

        // when
        dealer.drawCards(new SequentialCardGenerator(List.of(
                new Card(Suit.HEART, Denomination.TEN),
                new Card(Suit.HEART, Denomination.FOUR)
        )));

        // then
        int cardsTotal = dealer.calculateCardsTotalScore();
        assertThat(cardsTotal).isGreaterThan(17);
    }

    @Test
    @DisplayName("플레이어들의 수익의 합을 이용해 딜러의 수익을 계산한다")
    void calculateDealerBettingProfitTest() {
        // given
        Dealer dealer = new Dealer();
        List<PlayerBettingProfitOutcome> playerBettingProfitOutcomes = List.of(
                new PlayerBettingProfitOutcome("dora", 1000),
                new PlayerBettingProfitOutcome("bora", -2000),
                new PlayerBettingProfitOutcome("sora", -1000)
        );

        // when
        int dealerProfit = dealer.calculateBettingProfit(playerBettingProfitOutcomes);

        // then
        assertThat(dealerProfit).isEqualTo(2000);
    }
}
