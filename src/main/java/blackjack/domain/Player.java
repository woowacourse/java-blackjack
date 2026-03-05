package blackjack.domain;

public class Player {
    private static final int NICKNAME_MINIMUM_LENGTH = 4;
    private static final int NICKNAME_MAXIMUM_LENGTH = 10;

    private final String nickname;
    private Hand hand;

    public Player(final String nickname) {
        validateNicknameLength(nickname);
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    private void validateNicknameLength(final String nickname) {
        if (nickname.length() > NICKNAME_MAXIMUM_LENGTH || nickname.length() < NICKNAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 4~10자 이어야 합니다.");
        }
    }


}
