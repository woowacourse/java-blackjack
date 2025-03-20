package participant;

public class Nickname {

    public static final int MAXIMUM_LENGTH = 10;

    private final String nickname;

    public Nickname(String nickname) {
        validateNickname(nickname);
        this.nickname = nickname;
    }

    private void validateNickname(String nickname) {
        validateIsBlank(nickname);
        validateNicknameMaximumLength(nickname);
    }

    private void validateIsBlank(String nickname) {
        if (nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 비어있을 수 없습니다.");
        }
    }

    private void validateNicknameMaximumLength(String nickname) {
        if (nickname.length() > MAXIMUM_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 %d자를 초과할 수 없습니다.");
        }
    }

    public String getNickname() {
        return nickname;
    }
}
