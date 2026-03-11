package domain.participant;

import static domain.constant.GameRule.MAX_PLAYER_NUMBER;
import static domain.constant.GameRule.MIN_PLAYER_NUMBER;
import static message.ErrorMessage.PLAYER_NAME_DUPLICATED;
import static message.ErrorMessage.PLAYER_NOT_FOUND;
import static message.ErrorMessage.PLAYER_NUMBER_OUT_OF_RANGE;

import domain.card.Card;
import domain.card.Deck;
import domain.enums.Result;
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

    public void distributeCardForAllPlayers(Deck deck) {
        players.forEach(player -> {
            player.addCard(deck.drawCard());
        });
    }

    public void distributeCard(String name, Card card) {
        Player foundPlayer = findPlayerByName(name);
        foundPlayer.addCard(card);
    }

    private Player findPlayerByName(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PLAYER_NOT_FOUND.getMessage()));
    }

    public List<Result> decideAllResults(int dealerScore, boolean dealerBust) {
        List<Result> results = new ArrayList<>();
        for (Player player : players) {
            Result playerResult = player.calculateResult(dealerScore, dealerBust);
            results.add(playerResult);
        }
        return results;
    }

    public boolean checkScoreUnderCriterion(String name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.checkScoreUnderCriterion();
    }

    public List<String> getAllPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Card> getPlayerCards(String name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getCards();
    }

    public Result getPlayerResult(String name, int dealerScore, boolean dealerBust) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.calculateResult(dealerScore, dealerBust);
    }

    public int getPlayerScore(String name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.getScore();
    }
}
