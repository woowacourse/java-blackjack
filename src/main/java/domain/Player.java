package domain;

public class Player extends Participant {

    private final String name;
    private final BettingMoney bettingMoney;

    public Player(String name, BettingMoney bettingMoney, Hand hand) {
        super(hand);
        validate(name, bettingMoney);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    private void validate(String name, BettingMoney bettingMoney) {
        validateNotNull(name, bettingMoney);
    }

    private void validateNotNull(String name, BettingMoney bettingMoney) {
        if (name == null || name.isBlank() || bettingMoney == null) {
            throw new IllegalArgumentException("플레이어는 이름과 배팅 금액을 가져야합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public BettingMoney getBettingMoney() {
        return bettingMoney;
    }

    @Override
    public boolean isHitAllowed(Rule rule) {
        return rule.isPlayerHitAllowed(super.hand.getCards());
    }
}
