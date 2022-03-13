package blackjack.domain;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardDeckIndex;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Participant;
import blackjack.domain.player.Participants;
import blackjack.domain.player.Player;

public class BlackJackMachine {

    private final CardDeck deck;
    private final CardDeckIndex index;

    public BlackJackMachine(final CardDeck deck) {
        this.deck = deck;
        this.index = new CardDeckIndex();
    }

    public void giveInitialCards(final Dealer dealer, final Participants participants) {
        addInitialCards(dealer);

        for (Participant participant : participants) {
            addInitialCards(participant);
        }
    }

    private void addInitialCards(final Player player) {
        player.addCard(deck.getCard(index.getAndIncrease()));
        player.addCard(deck.getCard(index.getAndIncrease()));
    }

    public void giveCardToParticipant(final Participant participant, final Choice choice) {
        if (isYes(choice) && participant.canAddCard()) {
            participant.addCard(deck.getCard(index.getAndIncrease()));
        }
    }

    private boolean isYes(final Choice choice) {
        return choice == Choice.YES;
    }

    public void giveCardToDealer(final Dealer dealer) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.getCard(index.getAndIncrease()));
        }
    }
}
