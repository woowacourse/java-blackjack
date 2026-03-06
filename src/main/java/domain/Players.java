package domain;

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

    public List<String> getAllPlayersName() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    private void validatePlayers(List<String> names) {
        names.forEach(this::validateNameLength);
        validateDuplicatedName(names);
        validatePlayerNumber(names);
    }

    private void validateNameLength(String name) {
        if (name.isBlank() || name.length() > 5) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 1-5글자 사이어야 합니다.");
        }
    }

    private void validateDuplicatedName(List<String> names) {
        Set<String> distinctNames = Set.copyOf(names);
        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerNumber(List<String> names) {
        if (names.isEmpty() || names.size() > 7) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 1명 이상 7명 이하여야 합니다.");
        }
    }

    public void initializeCard(Card firstCard, Card secondCard) {
        players.forEach(player -> {
            player.addCards(List.of(firstCard, secondCard));
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
                .orElseThrow(() -> new IllegalArgumentException("플레이어를 찾을 수 없습니다."));
    }

    public List<Result> decideAllResults(int dealerScore, boolean dealerBurst) {
        List<Result> results = new ArrayList<>();
        for (Player player : players) {
            Result playerResult = player.calculateResult(dealerScore, dealerBurst);
            results.add(playerResult);
        }
        return results;
    }

    public boolean checkScoreUnderCriterion(String name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.checkScoreUnderCriterion();
    }

    public List<Card> getPlayerCards(String name) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.cardBoard.getCards();
    }

    public Result getPlayerResult(String name, int dealerScore, boolean dealerBurst) {
        Player foundPlayer = findPlayerByName(name);
        return foundPlayer.calculateResult(dealerScore, dealerBurst);
    }
}
