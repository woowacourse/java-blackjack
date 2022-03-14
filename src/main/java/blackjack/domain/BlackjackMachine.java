package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.player.Choice;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;

public class BlackjackMachine {

    private final CardDeck deck;

    public BlackjackMachine(final CardDeck deck) {
        this.deck = deck;
    }

    public void giveInitialCards(final Dealer dealer, final Participants participants) {
        addInitialCards(dealer);

        for (Participant participant : participants) {
            addInitialCards(participant);
        }
    }

    private void addInitialCards(final Player player) {
        player.takeCard(deck.pickCard());
        player.takeCard(deck.pickCard());
    }

    public void giveCardToParticipant(final Participant participant, final Choice choice) {
        if (choice.isHit() && participant.canTakeCard()) {
            participant.takeCard(deck.pickCard());
        }
    }

    public void giveCardToDealer(final Dealer dealer) {
        while (dealer.canTakeCard()) {
            dealer.takeCard(deck.pickCard());
        }
    }
}
