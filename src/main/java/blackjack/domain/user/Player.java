package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Player extends User {

    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        this.name = name;
    }

    @Override
    public boolean isAvailableDraw() {
        return !super.isBurstCondition() && super.getPoint() < UserDeck.BLACK_JACK_NUMBER;
    }

    public String betResult(Dealer dealer) {
        if (super.isBurstCondition() && dealer.isBurstCondition()) {
            return "패";
        }
        if (super.getPoint() < dealer.getPoint()) {
            return "패";
        }
        if (super.getPoint() > dealer.getPoint()) {
            return "승";
        }
        return "무";
    }

    public String getName() {
        return name;
    }
}
