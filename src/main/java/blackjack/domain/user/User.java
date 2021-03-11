package blackjack.domain.user;

public class User extends Participant {
    private static final String SAME_NAME_WITH_DEALER_ERROR_MSG = "유저의 이름으로 딜러는 사용할 수 없습니다.";

    public User(Name name) {
        super(name);
        validateNotDealerName(name);
    }

    private void validateNotDealerName(Name name) {
        if (Dealer.DEALER_NAME.equals(name)) {
            throw new IllegalArgumentException(SAME_NAME_WITH_DEALER_ERROR_MSG);
        }
    }

    public void stopUser() {
        status = Status.STOP;
    }

    public boolean canContinueGame() {
        return status.canContinueGame();
    }
}
