package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardGroup;

import java.util.List;
import java.util.stream.Collectors;

public class Player extends User {

    static final String NAME_EQUAL_DEALER_CODE_EXCEPTION_MESSAGE = "이름은 딜러코드(" + Dealer.DEALER_NAME_CODE + ") 와 같을 수 없습니다.";

    public Player(String name, CardGroup cardGroup) {
        super(name, cardGroup);
        validateIsNameDealerCode(name);
    }

    private void validateIsNameDealerCode(final String name) {
        if (name.equals(Dealer.DEALER_NAME_CODE)) {
            throw new IllegalArgumentException(NAME_EQUAL_DEALER_CODE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    protected List<Card> getInitialHoldingCards() {
        return getHandholdingCards().stream()
                .limit(2)
                .collect(Collectors.toUnmodifiableList());
    }
}
