package blackjack.model.participant;

import blackjack.model.card.Hands;
import blackjack.dto.CardDto;
import java.util.List;

public class Player extends Participant {

    private Player(String name, Hands hands) {
        super(name, hands);
    }

    public static Player of(String name) {
        return new Player(name, Hands.empty());
    }

    @Override
    public List<CardDto> getInitCards() {
        return hands.getAllCards();
    }
}
