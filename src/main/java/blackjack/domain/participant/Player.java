package blackjack.domain.participant;

import blackjack.domain.bet.Money;

public class Player extends Participant {

    private final String name;
    private Money betMoney;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("이름은 빈 문자열이거나 공백일 수 없습니다.");
        }
    }

    public Money getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(Money betMoney) {
        this.betMoney = betMoney;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean canHit() {
        return !hand.isBlackJack() && !hand.isBust();
    }
}
