public class BlackJack {
    private Deck deck;
    private Dealer dealer;
    private Participants participants;

    public BlackJack(Deck deck, Dealer dealer, Participants participants) {
        this.deck = deck;
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing() {
        dealer.receiveCard(deck.draw());
        dealer.receiveCard(deck.draw());
        for (int i = 0; i < participants.getValue().size(); i++) {
            participants.getValue().get(i).receiveCard(deck.draw());
            participants.getValue().get(i).receiveCard(deck.draw());
        }
    }
}
