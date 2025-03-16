package domain.participant;

import domain.card.Card;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Players {
    private static final int MAX_PLAYER_COUNT = 5;

    private final List<Player> players;

    public Players(List<String> playerNames, Dealer dealer) {
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        this.players = registerPlayer(playerNames, dealer);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    private List<Player> registerPlayer(List<String> playerNames, Dealer dealer) {
        return playerNames.stream()
                .map(name -> new Player(name, dealer.pickInitialDeal()))
                .collect(Collectors.toList());
    }

    public void hit(String playerName, Card card) {
        findPlayerByName(playerName).hit(card);
    }

    public boolean canHit(String playerName) {
        return findPlayerByName(playerName).canHit();
    }

    public Player findPlayerByName(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getCardsOf(String playerName) {
        return findPlayerByName(playerName).getCards();
    }
}
