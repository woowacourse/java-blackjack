package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import blackjack.domain.card.UserDeck;
import blackjack.exception.BlackJackException;
import java.util.ArrayList;
import java.util.List;

public class Players {

    public static final String DELIMITER = ",";
    public static final String OVERLAPPED_PLAYER_NAME_MESSAGE = "[ERROR] 중복되는 이름을 입력할 수 없습니다.";
    private final List<Player> players = new ArrayList<>();

    public Players(CardDeck cardDeck, String input) {
        List<String> playerNames = makePlayerName(input);
        for (String playerName : playerNames) {
            UserDeck initialRandomUserDeck = cardDeck.generateInitialUserDeck();
            players.add(new Player(playerName, initialRandomUserDeck));
        }
    }

    private List<String> makePlayerName(String input) {
        List<String> playerNames = new ArrayList<>();
        for (String value : input.split(DELIMITER, -1)) {
            value = value.trim();
            validateDuplicate(playerNames, value);
            playerNames.add(value);
        }
        return playerNames;
    }

    private void validateDuplicate(List<String> names, String value) {
        if (names.contains(value)) {
            throw new BlackJackException(OVERLAPPED_PLAYER_NAME_MESSAGE);
        }
    }

    public List<Player> getRawPlayers() {
        return players;
    }
}
