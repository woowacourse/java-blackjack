package blackjack.model;

public class BlackJackGame {
    private final Deck deck;
    private final Participants participants;
    private final Dealer dealer;

    public BlackJackGame(DeckInitializer deckInitializer, Participants participants, Dealer dealer) {
        this.deck = deckInitializer.generateDeck();
        this.participants = participants;
        this.dealer = dealer;
    }

    public void initializeGame() {
        participants.stream()
                .forEach(participant -> participant.putCard(deck.drawCard()));
        dealer.putCard(deck.drawCard());
        dealer.putCard(deck.drawCard());
    }


}
