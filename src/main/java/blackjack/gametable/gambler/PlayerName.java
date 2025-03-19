package blackjack.gametable.gambler;

public class PlayerName {

    public static final int NAME_LENGTH_LIMIT = 10;

    private final String playerName;

    public PlayerName(String playerName) {
        validateNickNameLength(playerName);
        validateNonBlank(playerName);
        this.playerName = playerName;
    }

    public String getValue() {
        return playerName;
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
