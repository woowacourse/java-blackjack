package blackjack.domain.user;

import blackjack.domain.card.CardGroup;

public class Player extends User {

    private static final int FIRST_OPEN_CARD_COUNT = 2;

    protected Player(String name, CardGroup cardGroup) {
        super(name, cardGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }
}
