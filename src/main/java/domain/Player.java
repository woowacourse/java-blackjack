package domain;

public class Player extends Participant {
    public Player(final Name name, final Hand hand) {
        super(name, hand);
        validateName(name);
    }

    public Player(final Name name) {
        this(name, new Hand());
    }

    private void validateName(final Name name) {
        if (name.isDealerName()) {
            throw new IllegalArgumentException("플레이어는 [딜러] 이름을 사용할 수 없습니다.");
        }
    }

    @Override
    public boolean canHit() {
        return hand.isNotBust();
    }
}
