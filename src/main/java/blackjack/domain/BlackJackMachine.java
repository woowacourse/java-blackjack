package blackjack.domain;

public class BlackJackMachine {

    private final CardDeck deck;
    private int index;

    public BlackJackMachine(CardDeck deck) {
        this.deck = deck;
        this.index = 0;
    }


    public void giveCardToParticipant(Participant participant, Choice choice) {
        if (isYes(choice) && participant.canAddCard()) {
            participant.addCard(deck.getCard(index++));
        }
    }

    private boolean isYes(Choice choice) {
        return choice == Choice.YES;
    }

    public void giveCardToDealer(Dealer dealer) {
        while (dealer.canAddCard()) {
            dealer.addCard(deck.getCard(index++));
        }
    }
}
