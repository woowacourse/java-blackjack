package blackjack.domain.user;

import blackjack.domain.user.status.Status;

public class User extends Participant {
    public User(String name) {
        super(name);
        whenUserNameEqualsDealerName(name);
        whenUserNameEmpty();
    }

    private void whenUserNameEmpty() {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("이름이 공백일 수 없습니다.");
        }
    }

    private void whenUserNameEqualsDealerName(String name) {
        if (name.equals(Dealer.DEALER_NAME)) {
            throw new IllegalArgumentException("딜러는 이름으로 사용할 수 없습니다.");
        }
    }

    public void stopUser() {
        status = Status.STOP;
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }
}
