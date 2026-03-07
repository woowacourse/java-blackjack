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
        if (nickname.length() < NICKNAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 4자 이상이어야 합니다.");
        }
        if (nickname.length() > NICKNAME_MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 10자 이하여야 합니다.");
        }
    }

    @Override
    public String getNickname() {
        return nickname;
    }

}
