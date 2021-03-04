package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.ArrayList;

public class Player extends Participant {

    private Player(String name, CardHand cardHand) {
        super(name, cardHand);
    }

    public static Player from(String name) {
        return new Player(name, new CardHand(new ArrayList<>()));
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public String getName() {
        return name;
    }

    @Override
    public int getCardSum() {
        return cardHand.playerSum();
    }
}
