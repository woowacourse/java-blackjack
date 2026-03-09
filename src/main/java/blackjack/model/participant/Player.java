package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Player extends Participant {

    private Player(String name, Hands hands) {
        super(name, hands);
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }
}
