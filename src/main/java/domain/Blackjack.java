package domain;

public class Blackjack {
    private final Dealer dealer;
    private final Participants participants;
    private final Deck deck;

    public Blackjack(Dealer dealer, Participants participants, Deck deck) {
        this.dealer = dealer;
        this.participants = participants;
        this.deck = deck;
    }

    public void initialCardsDistribution() {
        dealer.receiveCards(deck.drawCards(2));
        int participantsSize = participants.size();
        participants.distributeTwoCards(deck.drawCards(participantsSize * 2));
    }
}
