package blackjack.gamer;

import blackjack.card.Hand;

public class Player extends Gamer {

    private final String nickName;

    public Player(final Hand hand, final String nickName) {
        super(hand);
        this.nickName = nickName;
    }
}
