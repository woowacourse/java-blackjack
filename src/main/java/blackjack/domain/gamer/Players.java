package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.card.CardDeck;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Players {
    private static final String PLAYER_NUMBER_ERROR_MESSAGE = "인원 수는 2 ~ 8명입니다.";
    private static final String DELIMITER = ",";
    private static final String BLANK = " ";
    private static final String EMPTY = "";
    private static final int MIN_PLAYER_COUNT = 2;
    private static final int MAX_PLAYER_COUNT = 8;

    private final List<Player> players;

    public Players(String input) {
        List<Player> players = splitInput(input);
        validatePlayerCount(players);
        this.players = new ArrayList<>(players);
    }

    private List<Player> splitInput(String players) {
        return Arrays.stream(players.split(DELIMITER))
                .map(s -> s.replaceAll(BLANK, EMPTY))
                .map(Name::new)
                .map(Player::new)
                .collect(toList());
    }

    private void validatePlayerCount(List<Player> player) {
        if (player.size() < MIN_PLAYER_COUNT || player.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(PLAYER_NUMBER_ERROR_MESSAGE);
        }
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
