package blackjack.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player extends AbstractUser {
    private static final Pattern PATTERN = Pattern.compile("^[가-힣a-zA-Z]*$");
    private static final String PLAYER_WRONG_NAME_EXCEPTION_MESSAGE = "이름을 잘못 입력하였습니다. (입력값 : %s)";

    private final String name;

    public Player(final String name) {
        validate(name);
        this.name = name;
    }

    private void validate(final String name) {
        if (!PATTERN.matcher(name).matches()) {
            throw new IllegalArgumentException(String.format(PLAYER_WRONG_NAME_EXCEPTION_MESSAGE, name));
        }
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public boolean isGameOver(final int gameOverScore) {
        int score = getScore();
        return (score > gameOverScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
