package domain;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Participant {
    public Dealer() {
        super(new ArrayList<>());
    }

    public List<String> getDealerCard() {
        return hand.getCards().subList(0, 1);
    }
}
