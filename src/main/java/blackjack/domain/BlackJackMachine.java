package blackjack.domain;

public class BlackJackMachine {

    private final CardDeck deck;

    public BlackJackMachine(final CardDeck deck) {
        this.deck = deck;
    }

    public void giveInitialCards(final Dealer dealer, final Participants participants) {
        addInitialCards(dealer);

        for (Participant participant : participants) {
            addInitialCards(participant);
        }
    }

    private void addInitialCards(final Player player) {
        player.addCard(deck.pickCard());
        player.addCard(deck.pickCard());
    }

    public void giveCardToParticipant(final Participant participant, final Choice choice) {
        if (choice.isHit() && participant.canAddCard()) {
            participant.addCard(deck.pickCard());
        }
    }

    public void giveCardToDealer(final Dealer dealer) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.pickCard());
        }
    }
}
