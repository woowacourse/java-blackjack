package blackjack.domain.gamer;

import blackjack.domain.card.Hands;

public class Dealer extends Gamer {

    public Dealer(Hands hands) {
        this("딜러", hands);
    }

    private Dealer(String name, Hands hands) {
        super(name, hands);
    }
}
