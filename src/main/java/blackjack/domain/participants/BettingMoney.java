package blackjack.domain.participants;


public record BettingMoney(
        int amount
) {
    public BettingMoney {
        if (amount < 1000 || amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금액은 1000원 단위여야합니다.");
        }
    }
}
