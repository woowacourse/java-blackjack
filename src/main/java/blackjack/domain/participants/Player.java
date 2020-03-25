package blackjack.domain.participants;

import blackjack.domain.card.Deck;

public class Player extends Participant {

    public Player(String input) {
        super(input);
    }

    public void drawMoreCard(final Deck deck) {
        draw(deck.pop());
    }

}
