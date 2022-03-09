package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Gamer {
    private static final Dealer dealer = new Dealer();

    private Dealer() {
        super("딜러", new Cards(new ArrayList<>()));
    }

    public static Dealer init() {
        return dealer;
    }

    public boolean checkHitFlag() {
        return getCards().calculateScore() <= 16;
    }

    @Override
    public List<Card> getViewCard() {
        return List.of(getCards().getCards().get(0));
    }
}
