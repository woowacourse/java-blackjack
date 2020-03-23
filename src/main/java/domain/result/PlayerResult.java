package domain.result;

import domain.user.Name;

public class PlayerResult {
    private Name name;
    private ResultMoney resultMoney;

    PlayerResult(Name name, double resultMoney) {
        if (name == null) {
            throw new NullPointerException("잘못된 플레이어입니다.");
        }
        this.name = name;
        this.resultMoney = new ResultMoney(resultMoney);
    }

    public Name getName() {
        return name;
    }

    public ResultMoney getResultMoney() {
        return resultMoney;
    }
}
