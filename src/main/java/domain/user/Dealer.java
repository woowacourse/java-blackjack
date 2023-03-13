package domain.user;

import domain.card.Card;
import domain.state.DealerReady;

import java.util.List;

public class Dealer extends User {
    public Dealer() {
        super(new UserData(new Name("딜러"), new DealerReady()));
    }

    public int drawCount() {
        return getCards().size() - 2;
    }

    public List<Card> getOnlyFirstCard() {
        return getCards().subList(0,1);
    }
}
