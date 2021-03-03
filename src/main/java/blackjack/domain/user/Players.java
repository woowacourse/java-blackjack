package blackjack.domain.user;

import blackjack.domain.card.CardDeck;
import java.util.ArrayList;
import java.util.List;

public class Players {

    public static final String DELIMITER = ",";
    private final List<Player> players = new ArrayList<>();

    public Players(CardDeck cardDeck, String input) {
        List<String> playerNames = makePlayerName(input);
        for (String playerName : playerNames) {
            players.add(new Player(playerName, cardDeck.generateUserDeck()));
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
            throw new IllegalArgumentException("[ERROR] 중복되는 이름을 입력할 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
