package blackjack.domain.user;

import blackjack.domain.card.CardGroup;

public class Player extends User {

    private static final int FIRST_OPEN_CARD_COUNT = 2;

    protected Player(final String name, final CardGroup cardGroup) {
        super(new PlayerName(name), cardGroup);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return getCardGroups().getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    public boolean isSameName(final Name name) {
        return getName().isSame(name);
    }

    public boolean isDrawable() {
        return getScore().isDrawAble();
    }
}
