package blackjack.domain.entry;

import blackjack.domain.entry.vo.BettingMoney;
import blackjack.domain.entry.vo.Name;

public class Player {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney) {
        validateName(name);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public boolean equalsName(Name name) {
        return this.name.equals(name);
    }

    public Name getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    private void validateName(Name name) {
        if (name.getValue().equals(Dealer.NAME)) {
            throw new IllegalArgumentException("플레이어의 이름은 딜러의 이름이 될 수 없습니다.");
        }
    }
}
