package blackjack.domain.user;

import blackjack.domain.card.CardGroup;

public class Player extends User {

    private static final int FIRST_OPEN_CARD_COUNT = 2;

    private final PlayerName name;

    protected Player(final String name, final CardGroup cardGroup) {
        super(cardGroup);
        this.name = new PlayerName(name);
    }

    @Override
    public CardGroup getFirstOpenCardGroup() {
        return cardGroup.getSubCardGroup(FIRST_OPEN_CARD_COUNT);
    }

    @Override
    public PlayerName getName() {
        return name;
    }

    public boolean isSameName(final Name name) {
        return this.name.isSame(name);
    }

    public boolean isDrawable() {
        return getScore().isDrawAble();
    }
}
