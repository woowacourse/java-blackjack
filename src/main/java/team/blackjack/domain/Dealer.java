package team.blackjack.domain;

public class Dealer extends Participant {

    public Card draw(Deck deck) {
        return deck.draw();
    }

}
