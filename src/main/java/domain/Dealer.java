package domain;

import java.util.List;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    @Override
    public List<Card> getShownCard() {
        return List.of(getCards().getFirst());
    }
}
