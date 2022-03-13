package BlackJack.domain.User;

import BlackJack.domain.Result;

public class Player extends User {

    private static final int PLAYER_ADD_CARD_LIMIT = 21;
    public Player(String name) {
        super(name);
    }

    }

    boolean checkPossibleAdd(int currentScore) {
        return currentScore < PLAYER_ADD_CARD_LIMIT;
    }
}
