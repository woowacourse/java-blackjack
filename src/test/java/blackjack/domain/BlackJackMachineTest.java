package blackjack.domain;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackMachineTest {

    private final BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());

    @Test
    @DisplayName("참가자가 카드를 더 뽑는다고 하면 카드를 준다.")
    void giveCardToParticipantYes() {
        Participant participant = new Participant("배카라",
                List.of(new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.TWO, Suit.HEART)));
        int totalScore = participant.getTotalScore();
        blackJackMachine.giveCardToParticipant(participant, Choice.YES);
        Assertions.assertThat(participant.getTotalScore()).isGreaterThan(totalScore);
    }

    @Test
    @DisplayName("참가자가 카드를 더 뽑지 않는다고 하면 카드를 주지 않는다.")
    void giveCardToParticipantNo() {
        Participant participant = new Participant("배카라",
                List.of(new Card(Denomination.THREE, Suit.SPADE), new Card(Denomination.ACE, Suit.HEART)));
        int totalScore = participant.getTotalScore();
        blackJackMachine.giveCardToParticipant(participant, Choice.NO);
        Assertions.assertThat(participant.getTotalScore()).isEqualTo(totalScore);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과할 때 까지 카드를 계속 준다.")
    void giveCardToDealer() {
        Dealer dealer = new Dealer(
                List.of(new Card(Denomination.TWO, Suit.SPADE), new Card(Denomination.TWO, Suit.HEART)));
        blackJackMachine.giveCardToDealer(dealer);
        Assertions.assertThat(dealer.getTotalScore()).isGreaterThan(16);
    }
}
