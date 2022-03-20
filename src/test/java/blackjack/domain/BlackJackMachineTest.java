package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlackJackMachineTest {

    private final BlackJackMachine blackJackMachine = new BlackJackMachine(new CardDeck());

    @Test
    @DisplayName("참가자가 카드를 더 뽑는다고 하면 카드를 준다.")
    void giveCardToParticipantYes() {
        Participant participant = new Participant("배카라");
        participant.addCard(new Card(Denomination.THREE, Suit.SPADE));
        participant.addCard(new Card(Denomination.TWO, Suit.HEART));

        int totalScore = participant.getTotal();

        blackJackMachine.giveCardToPlayer(participant);

        Assertions.assertThat(participant.getTotal()).isGreaterThan(totalScore);
    }

    @Test
    @DisplayName("딜러의 점수가 16점을 초과할 때 까지 카드를 계속 준다.")
    void giveCardToDealer() {
        Dealer dealer = new Dealer();
        dealer.addCard(new Card(Denomination.TWO, Suit.SPADE));
        dealer.addCard(new Card(Denomination.TWO, Suit.HEART));

        while (dealer.canAddCard()) {
            blackJackMachine.giveCardToPlayer(dealer);
        }

        Assertions.assertThat(dealer.getTotal()).isGreaterThan(16);
    }
}
