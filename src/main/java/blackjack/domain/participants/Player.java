package blackjack.domain.participants;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    // TODO 이름 관련 검증 (글자수제한)

    @Override
    public boolean isAvailable() {
        return isSafe();
    }
}
