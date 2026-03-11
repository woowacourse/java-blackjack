package blackjack.model.participant;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.Hands;

public class Player extends Participant {

    private final int prize;

    private Player(String name, Hands hands, int prize) {
        super(name, hands);
        this.prize = prize;
    }

    public static Player of(String name, int betAmount) {
        return new Player(
                name,
                Hands.empty(),
                betAmount
        );
    }

    @Override
    public void pickInitialCards(CardDeck cardDeck) {
        hands.addCard(cardDeck.pick());
        hands.addCard(cardDeck.pick());
    }

    public Player bust() {
        return new Player(
                name,
                hands,
                0
        );
    }

    public int getPrize() {
        return prize;
    }
}
