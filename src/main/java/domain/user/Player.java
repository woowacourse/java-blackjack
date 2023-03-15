package domain.user;

import domain.name.Name;

public class Player extends User {
    private final Name name;

    public Player(final Name name) {
        this.name = name;
    }

    public boolean isRightName(final String name) {
        return new Name(name).equals(this.name);
    }

    @Override
    public boolean isHittable() {
        return hand.isAddable();
    }

    @Override
    public boolean isDealer() {
        return false;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    public String getName() {
        return name.getName();
    }
}
