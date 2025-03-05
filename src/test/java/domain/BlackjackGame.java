package domain;

import except.BlackJackException;
import java.util.List;

public class BlackjackGame {

    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 2-8명만 이용하실 수 있습니다";

    public BlackjackGame(List<String> names) {
        if (names.size() < 2 || names.size() > 8) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }
    }
}
