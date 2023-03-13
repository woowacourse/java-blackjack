package blackjack.domain.player;

import blackjack.exception.InvalidPlayerNameException;

public class Challenger extends Player {

    private final String name;

    public Challenger(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (Dealer.NAME.equals(name)) {
            throw new InvalidPlayerNameException();
        }
    }

    @Override
    public Boolean canPick() {
        return holdingCards.getSum() <= BLACKJACK_POINT;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
