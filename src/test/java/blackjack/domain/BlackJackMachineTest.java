package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackMachineTest {

    private final BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());

    @Test
    @DisplayName("참가자가 카드를 더 뽑는다고 하면 카드를 준다.")
    void giveCardToParticipantYes() {
        Participant participant = new Participant("배카라");
        participant.takeCard(new Card(Denomination.THREE, Suit.SPADE));
        participant.takeCard(new Card(Denomination.TWO, Suit.HEART));

        int totalScore = participant.getTotalScore();

        blackJackMachine.giveCardToParticipant(participant, Choice.HIT);

        assertThat(participant.getTotalScore()).isGreaterThan(totalScore);
    }

    @Test
    @DisplayName("참가자가 카드를 더 뽑지 않는다고 하면 카드를 주지 않는다.")
    void giveCardToParticipantNo() {
        Participant participant = new Participant("배카라");
        participant.takeCard(new Card(Denomination.THREE, Suit.SPADE));
        participant.takeCard(new Card(Denomination.ACE, Suit.HEART));

        int totalScore = participant.getTotalScore();

        blackJackMachine.giveCardToParticipant(participant, Choice.STAY);

        assertThat(participant.getTotalScore()).isEqualTo(totalScore);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과할 때 까지 카드를 계속 준다.")
    void giveCardToDealer() {
        Dealer dealer = new Dealer();
        dealer.takeCard(new Card(Denomination.TWO, Suit.SPADE));
        dealer.takeCard(new Card(Denomination.TWO, Suit.HEART));

        blackJackMachine.giveCardToDealer(dealer);

        assertThat(dealer.getTotalScore()).isGreaterThan(16);
    }
}
