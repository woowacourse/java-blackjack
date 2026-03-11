package domain.pariticipant;

import domain.card.CardShuffler;
import domain.card.Deck;
import domain.card.Hand;

public class Player extends Participant {

    public Player(Name name, Hand hand) {
        super(name, hand);
    }

    public void hitCard(Deck deck, CardShuffler cardShuffler) {
        this.drawCard(deck, cardShuffler);
    }
}
