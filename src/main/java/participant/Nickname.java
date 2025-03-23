package participant;

import java.util.Objects;

public class Nickname {

    public static final int NAME_LENGTH_LIMIT = 10;

    private final String nickname;

    public Nickname(String nickname) {
        validateNicknameLength(nickname);
        validateNonBlank(nickname);
        this.nickname = nickname;
    }

    public String getValue() {
        return nickname;
    }

    private void validateNicknameLength(String nickname) {
        if (nickname.length() > NAME_LENGTH_LIMIT) {
            throw new IllegalArgumentException("[ERROR] 닉네임 길이는 " + NAME_LENGTH_LIMIT + "자를 초과할 수 없습니다.");
        }
    }
    
    private void validateNonBlank(String nickname) {
        if (nickname.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 닉네임은 빈 값을 입력할 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Nickname nickname1 = (Nickname) object;
        return Objects.equals(nickname, nickname1.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }
}
