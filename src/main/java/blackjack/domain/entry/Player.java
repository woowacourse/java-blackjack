package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

public class Player extends Participant {
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;

    public Player(String name, HoldCards holdCards) {
        super(holdCards);
        validateName(name);
        this.name = name;
    }

    public PlayerOutcome match(Dealer dealer) {
        return PlayerOutcome.match(countCards(), dealer.countCards());
    }

    @Override
    public boolean canHit() {
        return countCards() <= BLACKJACK_NUMBER;
    }

    @Override
    public List<Card> openCard() {
        HoldCards holdCards = getHoldCards();
        return List.copyOf(holdCards.getCards());
    }

    @Override
    public String getName() {
        return name;
    }

    private void validateName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 공백이 될 수 없습니다.");
        }
        if (name.equals(Dealer.NAME)) {
            throw new IllegalArgumentException("플레이어의 이름에는 딜러가 포함될 수 없습니다.");
        }
    }
}
