package BlackJack.domain.User;

import BlackJack.domain.Result;

public class Player extends User {

    private static final int PLAYER_ADD_CARD_LIMIT = 21;
    public Player(String name) {
        super(name);
    }

    public void checkBlackJack(){
        if(cards.calculateScore() == 21){
            this.result = Result.BLACKJACK;
        }
    }

    boolean checkPossibleAdd(int currentScore) {
        return currentScore < PLAYER_ADD_CARD_LIMIT;
    }
}
