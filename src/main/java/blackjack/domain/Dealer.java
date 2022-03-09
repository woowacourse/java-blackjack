package blackjack.domain;

import blackjack.domain.card.Cards;
import java.util.ArrayList;

public class Dealer extends Gamer {
    private static final Dealer dealer = new Dealer();

    private Dealer() {
        super("딜러", new Cards(new ArrayList<>()));
    }

    public static Dealer init() {
        dealer.draw();
        dealer.draw();
        return dealer;
    }

    @Override
    public void hit() {
        if (getCards().calculateScore() <= 16) {
            dealer.draw();
        }
    }
}
