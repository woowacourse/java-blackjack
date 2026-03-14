package blackjack.domain.participant;

import static blackjack.domain.participant.Dealer.DEALER_NICKNAME;

public class Player extends Participant {

    public Player(String nickname) {
        super(nickname);
        validate(nickname);
    }

    private void validate(String nickname) {
        if (nickname.equals(DEALER_NICKNAME)) {
            throw new IllegalArgumentException("사용 불가능한 플레이어 닉네임입니다.");
        }
    }
}
