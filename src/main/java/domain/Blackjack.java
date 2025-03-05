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
}
