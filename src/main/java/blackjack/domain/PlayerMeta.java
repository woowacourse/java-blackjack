package blackjack.domain;

public record PlayerMeta(String name, int betAmount) {
    private static final int MINIMUM_BET_AMOUNT = 0;

    public PlayerMeta {
        if (name.isBlank()) {
            throw new IllegalArgumentException("이름이 비어있습니다.");
        }
        if (betAmount < MINIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 " + MINIMUM_BET_AMOUNT + "원 미만일 수 없습니다.");
        }
    }
}
