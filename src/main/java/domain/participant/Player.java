package domain.participant;

import domain.card.Cards;

public final class Player extends Participant {
    private final Name name;

    public Player(Name name) {
        super();
        this.name = name;
    }

    public Player(String name) {
        this(new Name(name));
    }

    public void hit(final Cards cards) {
        drawCard(cards);
    }

    public boolean canHit() {
        return getHandState().isHit();
    }

    public String getName() {
        return name.getName();
    }
}
