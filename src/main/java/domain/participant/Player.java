package domain.participant;

import domain.card.Card;
import java.util.List;

public class Player extends Participant {

    public Player(String name, Hand hand) {
        super(name, hand);
    }

    @Override
    public List<String> getInitialCards() {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }
}
