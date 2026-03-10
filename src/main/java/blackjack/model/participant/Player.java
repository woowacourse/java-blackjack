package blackjack.model.participant;

import blackjack.model.Hands;
import blackjack.model.cardDeck.CardDeck;

public class Player extends Participant {

    private Player(String name, Hands hands) {
        super(name, hands);
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    @Override
    public void pickInitCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }
}
