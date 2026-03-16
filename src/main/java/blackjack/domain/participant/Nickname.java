package blackjack.domain.participant;

import java.util.Objects;

public class Nickname {
    private static final String DEALER_NAME = "딜러";
    private static final int NICKNAME_MINIMUM_LENGTH = 4;
    private static final int NICKNAME_MAXIMUM_LENGTH = 10;

    private final String nickname;

    private Nickname(String nickname) {
        this.nickname = nickname;
    }

    public static Nickname from(String nickname) {
        validateNullOrEmpty(nickname);
        String trimmedNickname = nickname.trim();
        validateNicknameLength(trimmedNickname);
        return new Nickname(trimmedNickname);
    }

    public static Nickname dealer() {
        return new Nickname(DEALER_NAME);
    }

    private static void validateNullOrEmpty(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private static void validateNicknameLength(final String nickname) {
        if (nickname.length() > NICKNAME_MAXIMUM_LENGTH || nickname.length() < NICKNAME_MINIMUM_LENGTH) {
            throw new IllegalArgumentException("닉네임은 4~10자 이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Nickname other = (Nickname) obj;
        return Objects.equals(nickname, other.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nickname);
    }

    @Override
    public String toString() {
        return nickname;
    }
}
