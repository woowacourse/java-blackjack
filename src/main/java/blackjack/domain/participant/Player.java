package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.DEALER_NICKNAME;

public class Player extends Participant {

    private boolean stand;

    public Player(String nickname) {
        super(nickname);
        validate(nickname);
        this.stand = false;
    }

    private void validate(String nickname) {
        if (nickname.equals(DEALER_NICKNAME)) {
            throw new IllegalArgumentException("사용 불가능한 플레이어 닉네임입니다.");
        }
    }

    @Override
    public boolean isDrawable() {
        return !stand && super.isDrawable();
    }

    public void stand() {
        stand = true;
    }
}
