package blackjack.domain.betting;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.ParticipantCards;
import blackjack.domain.card.Suit;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProfitCalculatorTest {

    ParticipantCards blackjackCardsSet;
    ParticipantCards hittableCardsLoserSet;

    @BeforeEach
    void setupCards() {
        blackjackCardsSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.JACK)));
        hittableCardsLoserSet = new ParticipantCards(List.of(new Card(Suit.DIAMOND, Denomination.ACE),
            new Card(Suit.DIAMOND, Denomination.FIVE)));
    }


    @Test
    @DisplayName("딜러의 수익을 계산한다.")
    void calculateDealerProfit() {
        Participants participants = Participants.from(List.of("앤지"));

        Player player = participants.getPlayers().get(0);
        Dealer dealer = participants.getDealer();

        player.createBettingMoney(new BettingMoney(1000));
        player.receiveInitCards(blackjackCardsSet.getCards());
        dealer.receiveInitCards(hittableCardsLoserSet.getCards());

        ProfitCalculator profitCalculator = new ProfitCalculator(participants);

        profitCalculator.calculate();

        assertThat(profitCalculator.calculateDealerProfit()).isEqualTo(-1500L);
    }

    @Test
    @DisplayName("플레이어의 수익을 반환한다.")
    void calculatePlayerProfit() {
        Participants participants = Participants.from(List.of("앤지"));

        Player player = participants.getPlayers().get(0);
        Dealer dealer = participants.getDealer();

        player.createBettingMoney(new BettingMoney(1000));
        player.receiveInitCards(blackjackCardsSet.getCards());
        dealer.receiveInitCards(hittableCardsLoserSet.getCards());

        ProfitCalculator profitCalculator = new ProfitCalculator(participants);

        profitCalculator.calculate();
        long playerProfit = profitCalculator.getPlayerProfit().get(player);

        assertThat(playerProfit).isEqualTo(1500L);
    }

}
