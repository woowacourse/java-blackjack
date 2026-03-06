package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private static final int NICKNAME_MINIMUM_LENGTH = 4;
    private static final int NICKNAME_MAXIMUM_LENGTH = 10;

    private final String nickname;


    public Player(final Hand hand, final Status status, final String nickname) {
        super(hand, status);
        validateNicknameLength(nickname);
        this.nickname = nickname;
    }

    private void validateNicknameLength(final String nickname) {
        if (nickname.length() > NICKNAME_MAXIMUM_LENGTH || nickname.length() < NICKNAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 4~10자 이어야 합니다.");
        }
    }

    public GameResult calculateGameResult(Dealer dealer) {
        if (isBurst()) {
            return GameResult.LOSE;
        }
        if (dealer.isBurst() || dealer.getScore() < getScore()) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == getScore()) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

}
