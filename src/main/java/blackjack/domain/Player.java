package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Player extends Gamer{
    private static final String NAME_LENGTH_ERROR_MESSAGE = "이름은 최소 1글자, 최대 6글자로 입력해야 합니다.";
    private static final String NAME_EMPTY_ERROR_MESSAGE = "빈 문자는 이름으로 입력할 수 없습니다.";
    private static final int MINIMUM_NAME_LENGTH = 1;
    private static final int MAXIMUM_NAME_LENGTH = 6;

    private final String name;

    public Player(String name) {
        validateName(name);
        this.name = name;
    }

    public static List<Player> of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
        return players;
    }
    
    private void validateName(String name) {
        validateEmpty(name);
        validateLength(name);
    }

    private void validateEmpty(String name) {
        if (isEmpty(name)) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR_MESSAGE);
        }
    }

    private boolean isEmpty(String name) {
        return name == null || name.isBlank();
    }

    private void validateLength(String name) {
        if (isProperLength(name)) {
            throw new IllegalArgumentException(NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    private boolean isProperLength(String name) {
        return name.length() < MINIMUM_NAME_LENGTH || name.length() > MAXIMUM_NAME_LENGTH;
    }

    public int compareCardsSumTo(int sum) {
        if (isBust()) {
            return -1;
        }

        return Integer.compare(this.getCardGroupSum(), sum);
    }

    public String getName() {
        return this.name;
    }
}
