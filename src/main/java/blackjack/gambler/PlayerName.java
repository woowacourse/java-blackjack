package blackjack.gambler;

public class PlayerName {

    public static final int NAME_LENGTH_LIMIT = 10;

    private final String nickname;

    public PlayerName(String nickname) {
        validateNickNameLength(nickname);
        validateNonBlank(nickname);
        this.nickname = nickname;
    }

    public String getValue() {
        return nickname;
    }

    private void validateNickNameLength(String nickname) {
        if (nickname.length() > NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 닉네임 길이는 " + NAME_LENGTH_LIMIT + "자를 초과할 수 없습니다.");
        }
    }
    
    private void validateNonBlank(String nickname) {
        if (nickname.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 빈 값을 입력할 수 없습니다.");
        }
    }
}
