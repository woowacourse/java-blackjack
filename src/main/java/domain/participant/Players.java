package domain.participant;

import static constant.GameRule.MAX_PLAYER_NUMBER;
import static constant.GameRule.MIN_PLAYER_NUMBER;
import static message.ErrorMessage.PLAYER_NAME_DUPLICATED;
import static message.ErrorMessage.PLAYER_NOT_FOUND;
import static message.ErrorMessage.PLAYER_NUMBER_OUT_OF_RANGE;

import domain.BlackjackRule;
import domain.card.Card;
import domain.enums.GameResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Players {
    private final Map<Name, Player> players = new LinkedHashMap<>();

    public Players(List<String> names) {
        validatePlayers(names);
        names.forEach(name -> {
            Name playerName = new Name(name);
            players.put(playerName, new Player(playerName));
        });
    }

    public List<Name> getAllPlayerNames() {
        return List.copyOf(players.keySet());
    }

    private void validatePlayers(List<String> names) {
        validateDuplicatedName(names);
        validatePlayerNumber(names);
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException(PLAYER_NAME_DUPLICATED.getMessage());
        }
    }

    private void validatePlayerNumber(List<String> names) {
        if (names.size() < MIN_PLAYER_NUMBER || names.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(PLAYER_NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public void distributeCard(Name name, Card card) {
        Player foundPlayer = findPlayerByName(name);
        foundPlayer.addCard(card);
    }

    public void initializeCards(Name name, List<Card> cards) {
        Player foundPlayer = findPlayerByName(name);
        foundPlayer.receiveInitialCards(cards);
    }

    private Player findPlayerByName(Name name) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException(PLAYER_NOT_FOUND.getMessage());
        }

        return players.get(name);
    }

    public Map<Name, GameResult> decidePlayerResults(Dealer dealer) {
        Map<Name, GameResult> playerResults = new LinkedHashMap<>();
        for (Entry<Name, Player> playerEntry : players.entrySet()) {
            playerResults.put(playerEntry.getKey(), BlackjackRule.judgePlayerResult(playerEntry.getValue(), dealer));
        }
        return playerResults;
    }

    public List<Card> getPlayerCards(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getHand();
    }

    public int getPlayerScore(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getScore();
    }

    public boolean canDraw(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.canDraw();
    }
}
