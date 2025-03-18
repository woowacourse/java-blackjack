package domain.participant;

import domain.participant.state.State;

public class Player extends Participant {

    private final Name name;
    private final BettingMoney bettingMoney;

    public Player(Name name, BettingMoney bettingMoney, State state) {
        super(state);
        validate(name, bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validate(Name name, BettingMoney bettingMoney) {
        validateNotNull(name, bettingMoney);
    }

    private void validateNotNull(Name name, BettingMoney bettingMoney) {
        if (name == null || bettingMoney == null) {
            throw new IllegalArgumentException("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }
    }

    public String getName() {
        return name.getText();
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }
}
