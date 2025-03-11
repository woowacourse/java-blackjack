package domain;

public class Player extends Participant {

    private final String name;

    public Player(String name, Hand hand) {
        super(hand);
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        validateNotNull(name);
    }

    private void validateNotNull(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어는 이름을 가져야합니다.");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isHitAllowed(Rule rule) {
        return rule.isPlayerHitAllowed(super.hand.getCards());
    }
}
