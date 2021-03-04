package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.card.CardDeck;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {
    private static final String EXCEED_MAXIMUM_PLAYER_ERROR_MESSAGE = "최대 플레이어 인원 수는 8명입니다.";
    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final int MAX_COUNT = 8;

    private final List<Player> players;

    public Players(String input) {
        List<Player> players = splitInput(input);
        validatePlayerCount(players);
        this.players = new ArrayList<>(players);
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() > MAX_COUNT) {
            throw new IllegalArgumentException(EXCEED_MAXIMUM_PLAYER_ERROR_MESSAGE);
        }
    }

    private List<Player> splitInput(String players) {
        return Arrays.stream(players.split(DELIMITER))
                .map(s -> s.replaceAll(BLANK, EMPTY))
                .map(Player::new)
                .collect(toList());
    }

    public Map<Player, MatchResult> verifyResultByCompareScore(Dealer dealer) {
        Map<Player, MatchResult> result = new LinkedHashMap<>();
        for (Player player : players) {
            result.put(player, dealer.matchGame(player));
        }
        return result;
    }

    public void eachPlayerDrawCard(CardDeck cardDeck) {
        for (Player player : players) {
            player.receiveCard(cardDeck.drawCard());
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
