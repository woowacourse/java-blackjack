package domain.user;

public class Betting {

    private final Long bettingMoney;

    public Betting(Long bettingMoney) {
        validateBetting(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBetting(Long bettingMoney) {
        if (bettingMoney == 0) {
            throw new IllegalArgumentException("배팅금액을 입력해 주세요.");
        }
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("배팅금액은 음수가 불가능합니다.");
        }
    }

    public Long getBettingMoney() {
        return bettingMoney;
    }
}
