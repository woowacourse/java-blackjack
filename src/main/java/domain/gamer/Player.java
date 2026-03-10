package domain.gamer;

import domain.card.CardBundle;

public class Player extends Gamer{

    private Player(PlayerName name) {
        super(name);
    }

    public static Player from(PlayerName name) {
        return new Player(name);
    }

    public CardBundle addCardBundle(CardBundle newCardBundle) {
        cardBundle = cardBundle.add(newCardBundle);
        return cardBundle;
    }

}
