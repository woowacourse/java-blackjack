package domain.participant;

import static constant.GameRule.MAX_PLAYER_NUMBER;
import static constant.GameRule.MIN_PLAYER_NUMBER;
import static message.ErrorMessage.PLAYER_NAME_DUPLICATED;
import static message.ErrorMessage.PLAYER_NOT_FOUND;
import static message.ErrorMessage.PLAYER_NUMBER_OUT_OF_RANGE;

import domain.card.Card;
import domain.enums.GameResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(List<String> names) {
        validatePlayers(names);
        players.addAll(names.stream()
                .map(Player::new)
                .toList());
    }

    public List<Name> getAllPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
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

    public void distributeCards(Name name, List<Card> cards) {
        Player foundPlayer = findPlayerByName(name);
        foundPlayer.addCards(cards);
    }

    private Player findPlayerByName(Name name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUND.getMessage()));
    }

    public List<GameResult> decideAllResults(int dealerScore, boolean dealerBurst) {
        List<GameResult> gameResults = new ArrayList<>();
        for (Player player : players) {
            GameResult playerGameResult = GameResult.calculatePlayerResult(player, dealerScore, dealerBurst);
            gameResults.add(playerGameResult);
        }
        return gameResults;
    }

    public boolean checkScoreUnderCriterion(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.checkScoreUnderCriterion();
    }

    public List<Card> getPlayerCards(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getHand();
    }

    public GameResult getPlayerResult(Name name, int dealerScore, boolean dealerBust) {
        Player foundPlayer = findPlayerByName(name);
        return GameResult.calculatePlayerResult(foundPlayer, dealerScore, dealerBust);
    }

    public int getPlayerScore(Name name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getScore();
    }
}
