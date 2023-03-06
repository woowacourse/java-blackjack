package blackjack.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Player extends User {

    private static final int FIRST_OPEN_CARD_COUNT = 2;

    protected Player(String name, CardGroup cardGroup) {
        super(name, cardGroup);
    }

    @Override
    public List<Card> getFirstOpenCardGroup() {
        return getCardGroups().subCardGroup(FIRST_OPEN_CARD_COUNT);
    }
}
