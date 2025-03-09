package domain;

import java.util.List;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }

    @Override
    public List<Card> openInitialCards() {
        return getCards(2);
    }
    
    public boolean isDealer() {
        return false;
    }
}
