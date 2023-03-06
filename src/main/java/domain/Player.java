package domain;

import java.util.List;

public class Player extends Participant {

    public Player(PlayerName playerName) {
        super(playerName);
    }

    @Override
    public List<Card> getInitialCards() {
        return getCards();
    }

}
