package blackjack.domain.result;

import blackjack.domain.betting.Money;
import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.card.Type;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.ParticipantAcceptStrategy;
import blackjack.domain.player.Players;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ProfitCalculatorTest {

    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateDealerProfit() {
        Participant zero = new Participant("zero", new ParticipantAcceptStrategy(), new Money(10000));
        zero.addCard(new Card(Type.SPADE, Score.KING));
        zero.addCard(new Card(Type.HEART, Score.SIX));
        zero.addCard(new Card(Type.HEART, Score.KING));
        Participant corinne = new Participant("corinne", new ParticipantAcceptStrategy(), new Money(20000));
        corinne.addCard(new Card(Type.CLOVER, Score.SIX));
        corinne.addCard(new Card(Type.HEART, Score.TEN));
        corinne.addCard(new Card(Type.DIAMOND, Score.SIX));

        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Type.HEART, Score.ACE));
        dealer.addCard(new Card(Type.DIAMOND, Score.KING));

        Players players = new Players(List.of(zero, corinne), dealer);
        ProfitCalculator.calculateParticipantProfit(players);

        assertThat(ProfitCalculator.calculateDealerProfit(players.getParticipants())).isEqualTo(30000);
    }
}
