package blackjack.domain.user;

public class Player extends User {
    public Player(String name) {
        super(name);
    }

    @Override
    public boolean receivable() {
        super.getPoint().computePoint(super.getCards());
        return !super.getPoint().overBust();
    }
}
