package blackjack.domain.participant;

public class Player extends Participant {

    private boolean stand;

    public Player(String nickname, int bettingAmount) {
        super(nickname);
        validateBettingAmount(bettingAmount);
        this.stand = false;
    }

    private void validateBettingAmount(int bettingAmount) {
        if (bettingAmount < 0) {
            throw new IllegalArgumentException("배팅 금액은 1 이상의 양수여야 합니다.");
        }
        if (bettingAmount == 0) {
            throw new IllegalArgumentException("배팅 금액은 0일 수 없습니다.");
        }
    }

    @Override
    public boolean isDrawable() {
        return !stand && super.isDrawable();
    }

    public void stand() {
        stand = true;
    }

    public boolean isStand() {
        return stand;
    }
}
