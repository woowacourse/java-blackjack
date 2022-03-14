package blackJack.domain;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participants;

public class BlackJackGame {

    private final Participants participants;

    public BlackJackGame(Participants participants) {
        this.participants = participants;
    }

    public Dealer doDealerGame() {
        Dealer dealer = participants.getDealer();
        while (dealer.hasNextTurn()) {
            dealer.receiveCard(Deck.getCard());
        }
        return dealer;
    }
}
