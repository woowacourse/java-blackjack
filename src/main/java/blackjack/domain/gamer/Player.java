package blackjack.domain.gamer;

import blackjack.domain.result.BlackJackResult;

public class Player extends Gamer{

    public Player(String name) {
        super(name);
    }

    public BlackJackResult match(Dealer dealer) {
        int playerPoint = getCardsNumberSum();
        int dealerPoint = dealer.getCardsNumberSum();
        return BlackJackResult.of(playerPoint, dealerPoint);
    }
}
