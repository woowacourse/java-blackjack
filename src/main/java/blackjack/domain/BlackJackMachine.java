package blackjack.domain;

public class BlackJackMachine {

    private final CardDeck deck;
    private int index;

    public BlackJackMachine(final CardDeck deck) {
        this.deck = deck;
        this.index = 0;
    }

    public void giveInitialCards(final Dealer dealer, final Participants participants) {
        dealer.addCard(deck.getCard(index++));
        dealer.addCard(deck.getCard(index++));

        for (Participant participant : participants) {
            participant.addCard(deck.getCard(index++));
            participant.addCard(deck.getCard(index++));
        }
    }

    public void giveCardToParticipant(final Participant participant, final Choice choice) {
        if (isYes(choice) && participant.canAddCard()) {
            participant.addCard(deck.getCard(index++));
        }
    }

    private boolean isYes(final Choice choice) {
        return choice == Choice.YES;
    }

    public void giveCardToDealer(final Dealer dealer) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.getCard(index++));
        }
    }
}
