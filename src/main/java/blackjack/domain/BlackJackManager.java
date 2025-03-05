package blackjack.domain;

public class BlackJackManager {

    private final CardDeck cardDeck;
    private final Participants participants;

    public BlackJackManager(CardDeck cardDeck, Participants participants) {
        this.cardDeck = cardDeck;
        this.participants = participants;
    }

    public void initCardsToParticipants() {
        for (Participant participant : participants.getParticipants()) {
            Card card1 = cardDeck.pickRandomCard();
            Card card2 = cardDeck.pickRandomCard();
            participant.addCards(card1, card2);
        }
    }
}
