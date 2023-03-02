package domain.user;

import domain.card.Card;
import java.util.List;

public class Player extends User{
    public Player(List<Card> firstTurnCards) {
        super(firstTurnCards);
    }

}
