package blackjack.domain;

public class Player extends Participant {
    private final Batting batting;

    public Player(Name name, Batting batting) {
        super(name);
        this.batting = batting;
    }

    @Override
    public boolean canAddCard() {
        return (isNotBust());
    }

    public boolean isFirstTurnBackJack() {
        return isBlackJack() && hands.getHands().size() == 2;
    }

    public Double getBat() {
        return batting.getBat();
    }
}
