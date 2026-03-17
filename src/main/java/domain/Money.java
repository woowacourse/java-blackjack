package domain;

public record Money(int bettingMoney) {
    public Money {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0원보다 커야합니다. 현재 배팅액 : " + bettingMoney);
        }

        if (bettingMoney % 10 != 0) {
            throw new IllegalArgumentException("배팅 금액은 10원 단위여야 합니다. 현재 배팅액 : " + bettingMoney);
        }
    }
}

