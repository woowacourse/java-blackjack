package blackjack.domain.player;

import blackjack.exception.InvalidArgumentException;

public class Challenger extends Player {

    private final String name;

    public Challenger(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (Dealer.NAME.equals(name)) {
            throw new InvalidArgumentException("플레이어의 이름은 " + Dealer.NAME + "이면 안됩니다.");
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
