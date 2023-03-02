package domain;

abstract class Participant {

    private final Hand hand;

    Participant() {
        this.hand = new Hand();
    }

    void receiveCard(Card card) {
        hand.add(card);
    }
}
