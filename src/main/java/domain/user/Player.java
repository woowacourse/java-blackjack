package domain.user;

import domain.Card.Card;
import java.util.List;

public class Player extends User{
    public Player(List<Card> firstTurnCards) {
        super(firstTurnCards);
    }

}
