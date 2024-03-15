package blackjack.domain.participant;

import blackjack.domain.dealer.Dealer;

public class Player extends Participant {
    public Player(final String name) {
        super(name);
        validateNotDealerName(name);
    }

    private void validateNotDealerName(final String name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(Dealer.DEALER_NAME + " 라는 이름을 사용할 수 없습니다.");
        }
    }

    public boolean isName(final String otherName) {
        return name.isSame(otherName);
    }
}
