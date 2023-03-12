package domain.profit;

import java.util.List;

import domain.betting.BettingMoney;
import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.participant.Hand;
import domain.participant.Name;
import domain.participant.Player;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalProfitByParticipantTest {

    @Test
    @DisplayName("딜러의 최종 수익을 계산한다.")
    void calculateDealerFinalProfit() {
        // given
        Player player = new Player(
                new Name("seongha"),
                new Hand(List.of(new Card(Suit.SPADE, Denomination.SIX), new Card(Suit.DIAMOND, Denomination.TEN))));
        BettingMoney bettingMoney = new BettingMoney(10000);

        // when
        FinalProfitByParticipant finalProfitByParticipant = new FinalProfitByParticipant();
        finalProfitByParticipant.putParticipantFinalProfit(player, new FinalProfit(bettingMoney.getMoney()));

        // then
        Assertions.assertThat(finalProfitByParticipant.calculateDealerFinalProfit()).isEqualTo(-10000.0);
    }
}
