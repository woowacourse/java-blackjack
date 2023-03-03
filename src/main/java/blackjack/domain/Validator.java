package blackjack.domain;

import blackjack.view.InputView;

import java.util.List;

public class Validator {

    private static final String PLAYER_COUNT_ERROR_MESSAGE = "플레이어 수는 1명 이상 7명 이하여야 합니다.";
    private static final String PLAYER_NAME_DUPLICATE_ERROR_MESSAGE = "플레이어 이름은 중복될 수 없습니다.";
    private static final String PLAYER_INTENTION_ERROR_MESSAGE = "y 혹은 n 만 입력 가능 합니다.";
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;
    private static final Validator INSTANCE = new Validator();

    private Validator() {
    }

    public static Validator getInstance() {
        return INSTANCE;
    }

    public void validatePlayerNames(List<String> playerNames) {
        validatePlayerCount(playerNames);
        validateDuplicate(playerNames);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() < MIN_PLAYER_COUNT || playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(PLAYER_COUNT_ERROR_MESSAGE);
        }
    }

    private void validateDuplicate(List<String> playerNames) {
        if (playerNames.size() != playerNames.stream().distinct().count()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATE_ERROR_MESSAGE);
        }
    }

    public void validatePlayerIntention(String intention) {
        if (!intention.equals("y") && !intention.equals("n")) {
            throw new IllegalArgumentException(PLAYER_INTENTION_ERROR_MESSAGE);
        }
    }
}
