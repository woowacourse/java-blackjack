package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class Hit extends Running {

    @Override
    public State draw(UserDeck userDeck) {
        if (userDeck.isBlackjack()) {
            System.out.println("외않되");
            return new Stay();
        }
        if (userDeck.isBust()) {
            return new Bust();
        }
        return new Hit();
    }
}
