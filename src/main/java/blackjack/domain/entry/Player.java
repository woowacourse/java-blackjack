package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.HoldCards;

public class Player extends Participant {
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney, HoldCards holdCards) {
        super(holdCards);
        validateName(name);
        this.bettingMoney = bettingMoney;
        this.name = name;
    }

    public PlayerOutcome match(Dealer dealer) {
        return PlayerOutcome.match(countCards(), dealer.countCards());
    }

    public int countBettingMoney(Dealer dealer) {
        if (hasBlackjack()) {
            return bettingMoney.countBlackjackPay();
        }
        return bettingMoney.bet(match(dealer));
    }

    @Override
    public boolean canHit() {
        return countCards() <= BLACKJACK_NUMBER;
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
