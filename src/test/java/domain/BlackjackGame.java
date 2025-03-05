package domain;

import except.BlackJackException;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final int MIN_PEOPLE_WITHOUT_DEALER = 1;
    private final int MAX_PEOPLE_WITHOUT_DEALER = 7;

    List<BlackjackPlayer> players;
    private final String INVALID_BLACKJACK_PLAYER_SIZE = "블랙잭은 2-8명만 이용하실 수 있습니다";

    public BlackjackGame(List<String> names) {
        int playerSize = names.size();
        if (playerSize < MIN_PEOPLE_WITHOUT_DEALER || playerSize > MAX_PEOPLE_WITHOUT_DEALER) {
            throw new BlackJackException(INVALID_BLACKJACK_PLAYER_SIZE);
        }

        players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public void gameStart() {

    }
}
